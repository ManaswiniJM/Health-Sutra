package com.example.login1;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_profile extends AppCompatActivity {
    private TextView txtname, txtemail,txtaddress, txtpnumber;
    private String name,email,address,pnumber;
    private FirebaseAuth authProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(ContextCompat.getColor(user_profile.this, R.color.black));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("User Profile");

        setContentView(R.layout.user_profile);

        txtname=findViewById(R.id.uname);
        txtemail=findViewById(R.id.uemail);
        txtaddress=findViewById(R.id.uadd);
        txtpnumber=findViewById(R.id.upnumber);

        authProfile=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser=authProfile.getCurrentUser();

        if(firebaseUser==null){
            Toast.makeText(user_profile.this,"Something went wrong",Toast.LENGTH_SHORT).show();
        }else{
            showUserProfile(firebaseUser);
        }
    }
    private void showUserProfile(FirebaseUser firebaseUser){
        String userID=firebaseUser.getUid();

        DatabaseReference referenceProfile= FirebaseDatabase.getInstance().getReference("User");
        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ReadWriteUserDeatils readUserDeatils=snapshot.getValue(ReadWriteUserDeatils.class);
                if(readUserDeatils!=null){
                    name=readUserDeatils.username;
                    email=readUserDeatils.uemail;
                    address=readUserDeatils.uaddress;
                    pnumber=readUserDeatils.uphone;

                    txtname.setText(name);
                    txtemail.setText(email);
                    txtaddress.setText(address);
                    txtpnumber.setText(pnumber);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(user_profile.this,"Something is wrong",Toast.LENGTH_SHORT).show();

            }
        });

    }

    }
