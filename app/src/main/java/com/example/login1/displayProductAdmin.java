package com.example.login1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.login1.R;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.sql.StatementEvent;

public class displayProductAdmin extends AppCompatActivity {


    ImageView detailedImg;
    TextView prname, price, description;
    ProductModelAdmin productModel = null;
    List<ProductModelAdmin> productModelList;
    ProductAdapterAdmin productAdapter;


    FloatingActionButton deleteButton;

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(displayProductAdmin.this, R.color.black));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("HealthSutra");
        setContentView(R.layout.display_product_admin);


        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");
        if (object instanceof ProductModelAdmin) {
            productModel = (ProductModelAdmin) object;
        }

        detailedImg = findViewById(R.id.display_product_admin);
        deleteButton = findViewById(R.id.deleteButton);
        price = findViewById(R.id.dis_price_admin);
        prname = findViewById(R.id.dis_pro_name_admin);
        description = findViewById(R.id.dis_pro_des_admin);


        if (productModel != null) {
            Glide.with(getApplicationContext()).load(productModel.getImg_url()).into(detailedImg);
            prname.setText(productModel.getName());
            description.setText(productModel.getDescription());
            // String productId = (String) getText(productModel.getProductId());
        }

        productModelList = new ArrayList<>();

        deleteButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                                            if (productModel != null) {
                                                String documentId = productModel.getDocumentId(); // Get the document ID
                                                if (documentId != null) {
                                                    productModelList.remove(productModel); // Remove from the list

                                                    db.collection("Products")
                                                            .document(documentId)
                                                            .delete()
                                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                @Override
                                                                public void onSuccess(Void aVoid) {
                                                                    // Delete successful
                                                                    Toast.makeText(displayProductAdmin.this, "Product Deleted", Toast.LENGTH_SHORT).show();
                                                                    productAdapter.updateProductList(productModelList); // Update the adapter's product list
                                                                    finish();
                                                                }
                                                            })
                                                            .addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    // Error occurred while deleting the item from Firestore
                                                                    Toast.makeText(displayProductAdmin.this, "Error deleting item: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                } else {
                                                    // Handle the case when documentId is null
                                                    Toast.makeText(displayProductAdmin.this, "Invalid document ID", Toast.LENGTH_SHORT).show();

                                                }
                                            }
                                        }
        });
    }
}