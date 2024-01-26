package com.example.login1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class add_product extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageView addImage;
    private Uri imageUri;
    private EditText productNameEditText;
    private EditText productPriceEditText;
    private EditText productDescriptionEditText;
    private Button saveButton;

    private FirebaseFirestore firestore;

    private List<ProductModelAdmin> productModelList;
    private ProductAdapterAdmin adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(ContextCompat.getColor(add_product.this, R.color.black));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("Add Product");

        setContentView(R.layout.product_add);

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();

        productNameEditText = findViewById(R.id.add_name);
        productPriceEditText = findViewById(R.id.add_price);
        productDescriptionEditText = findViewById(R.id.add_des);
        saveButton = findViewById(R.id.save_pro);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });
        addImage = findViewById(R.id.add_img);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                addImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveProduct() {
        String name = productNameEditText.getText().toString().trim();
        int price = Integer.parseInt(productPriceEditText.getText().toString().trim());
        String description = productDescriptionEditText.getText().toString().trim();
        if (imageUri != null) {
            uploadImage(name, description, price);
        } else {
            Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
        }
    }
    private void uploadImage(String name, String description, int price) {

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference imageRef = storageRef.child("product_images/" + UUID.randomUUID().toString());

        imageRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String imageUrl = uri.toString();
                                saveProductToFirestore(name, description, price, imageUrl);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(add_product.this, "Failed to upload image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void saveProductToFirestore(String name, String description, int price, String imageUrl) {
        ProductModelAdmin product = new ProductModelAdmin(name, description, price, imageUrl, "document_id_placeholder");

        firestore.collection("Products")
                .add(product)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String documentId = documentReference.getId();
                        product.setDocumentId(documentId);
                        updateProductList(product);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(add_product.this, "Failed to save product: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        }
    private void updateProductList(ProductModelAdmin product) {
        Toast.makeText(add_product.this, "Product saved successfully!", Toast.LENGTH_SHORT).show();
        productModelList.add(product);
        adapter.notifyDataSetChanged();
    }
}
