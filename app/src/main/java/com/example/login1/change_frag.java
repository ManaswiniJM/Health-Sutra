package com.example.login1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;


public class change_frag extends AppCompatActivity {

    Button addbtn;
    //recycler view
    RecyclerView productRec,productrec1;
    FirebaseFirestore db;

    //product list
    List<ProductModelAdmin> productModelList;
    ProductAdapterAdmin productAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_admin);

        getWindow().setStatusBarColor(ContextCompat.getColor(change_frag.this, R.color.black));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
       // getSupportActionBar().setTitle("MediKart");

        db=FirebaseFirestore.getInstance();
        productRec=findViewById(R.id.pro_rec_adm);

        //set recycler view 1 move horizontally
       productRec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        productModelList=new ArrayList<>();
        productAdapter = new ProductAdapterAdmin(this, productModelList);

      //  productRec.setAdapter(productAdapter,productModelList);
        productRec.setAdapter(productAdapter);

        db.collection("Products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(QueryDocumentSnapshot document: task.getResult()){

                                ProductModelAdmin productModel=document.toObject(ProductModelAdmin.class);
                                productModel.setDocumentId(document.getId()); // Set the document ID
                                productModelList.add(productModel);
                                productAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(change_frag.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        addbtn=findViewById(R.id.addButton);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(change_frag.this,add_product.class);
                startActivity(i);
            }
        });
    }
}