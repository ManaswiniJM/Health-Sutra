package com.example.login1.products;

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
import com.example.login1.displayProductAdmin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import javax.sql.StatementEvent;

public class displayProduct extends AppCompatActivity {

    TextView quantity;
    int totalQuality=1;
    int totalPrice=0;

    ImageView detailedImg;
    TextView prname,price,description;
    Button addTocart;
    ImageView addItem,removeItem;
    ProductModel productModel=null;

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(ContextCompat.getColor(displayProduct.this, R.color.black));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("HealthSutra");

        setContentView(R.layout.display_product);


        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        final Object object=getIntent().getSerializableExtra("detail");
        if(object instanceof ProductModel){
            productModel=(ProductModel) object;
        }

        quantity=findViewById(R.id.quantity);


        detailedImg=findViewById(R.id.display_product);
        addItem=findViewById(R.id.add_item);
        removeItem=findViewById(R.id.remove_item);

        price=findViewById(R.id.dis_price);
        prname=findViewById(R.id.dis_pro_name);
        description=findViewById(R.id.dis_pro_des);

        if(productModel!=null){
            Glide.with(getApplicationContext()).load(productModel.getImg_url()).into(detailedImg);
            prname.setText(productModel.getName());
            description.setText(productModel.getDescription());
            price.setText(Integer.toString(productModel.getPrice()));

            totalPrice=productModel.getPrice()*totalQuality;

        }

        addTocart=findViewById(R.id.add_to_cart);
        addTocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addedTocart();
                totalPrice=productModel.getPrice()*totalQuality;

            }


        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (totalQuality < 10) {
                    totalQuality++;
                    quantity.setText(String.valueOf(totalQuality));
                    totalPrice=productModel.getPrice()*totalQuality;

                }

            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(totalQuality>1){
                    totalQuality--;
                    quantity.setText(String.valueOf(totalQuality));
                }
            }
        });

    }
    private void addedTocart() {

        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate=Calendar.getInstance();

        SimpleDateFormat currentDate=new SimpleDateFormat("MM dd,yyyy");
        saveCurrentDate=currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime=currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap=new HashMap<>();

        cartMap.put("productName",prname.getText().toString());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
        .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(displayProduct.this,"Added to Cart",Toast.LENGTH_SHORT).show();
                        finish();

                    }
                });

    }
}