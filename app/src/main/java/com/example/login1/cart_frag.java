package com.example.login1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login1.products.MyCartAdapter;
import com.example.login1.products.MyCartModel;
import com.example.login1.products.placed_order;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class cart_frag extends AppCompatActivity {

    RecyclerView recyclerView;
    MyCartAdapter cartAdapter;
    List<MyCartModel> cartModelList;

    // Button confirm;
    // TextView overTotalAmount;

    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(ContextCompat.getColor(cart_frag.this, R.color.black));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("User Orders");

        setContentView(R.layout.cart_frag);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.admin_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // confirm = findViewById(R.id.confirm_now);
        //overTotalAmount = findViewById(R.id.textView16);

        cartModelList = new ArrayList<>();
        cartAdapter = new MyCartAdapter(this, cartModelList);
        recyclerView.setAdapter(cartAdapter);

        /*db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult().getDocuments()) {
                               // String documentId = documentSnapshot.getId();
                                MyCartModel cartModel = document.toObject(MyCartModel.class);
                                cartModel.setDocumentId(document.getId());
                                cartModelList.add(cartModel);

                            }
                            cartAdapter.notifyDataSetChanged();
                            //  calculateTotalAmount(cartModelList);
                        }else {
                            Toast.makeText(cart_frag.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });*/
        db.collection("CurrentUser")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot userDocument : task.getResult()) {
                                String userId = userDocument.getId();
                                db.collection("CurrentUser")
                                        .document(userId)
                                        .collection("AddToCart")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot orderDocument : task.getResult()) {
                                                        MyCartModel cartModel = orderDocument.toObject(MyCartModel.class);
                                                        cartModelList.add(cartModel);
                                                    }
                                                    cartAdapter.notifyDataSetChanged();
                                                } else {
                                                    Toast.makeText(cart_frag.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        } else {
                            Toast.makeText(cart_frag.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}

        /*db.collection("CurrentUser")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot userDocument : task.getResult()) {
                                String userId = userDocument.getId();
                                db.collection("CurrentUser")
                                        .document(userId)
                                        .collection("MyOrder")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot orderDocument : task.getResult()) {
                                                        MyCartModel cartModel = orderDocument.toObject(MyCartModel.class);
                                                        cartModelList.add(cartModel);
                                                    }
                                                    cartAdapter.notifyDataSetChanged();
                                                } else {
                                                    Toast.makeText(cart_frag.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        } else {
                            Toast.makeText(cart_frag.this, "Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}*/

       /* confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cartModelList.isEmpty()) {
                    Toast.makeText(cart_frag.this, "Please add items to the cart", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(cart_frag.this, placed_order.class);
                    intent.putExtra("itemList", (Serializable) cartModelList);
                    startActivity(intent);
                }
            }
        });*/


    /*private void calculateTotalAmount(List<MyCartModel> cartModelList) {
        int totalAmount = 0;
        for (MyCartModel myCartModel : cartModelList) {
            totalAmount += myCartModel.getTotalPrice();
            overTotalAmount.setText("Total Bill  " + totalAmount + " Rs.");
        }
    }*/

