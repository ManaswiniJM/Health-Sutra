package com.example.login1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class user_or_admin extends AppCompatActivity {
   //check if user logged in
    FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

            String userEmail = currentUser.getEmail();
            if (userEmail.equals("healthsutra20@gmail.com")) {
                Intent i = new Intent(user_or_admin.this, admin_home_page.class);
                startActivity(i);
                finish();
            }else{
                Intent i = new Intent(user_or_admin.this, home_page.class);
                startActivity(i);
                finish();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_or_admin);
        //status bar color
        getWindow().setStatusBarColor(ContextCompat.getColor(user_or_admin.this, R.color.status));


        //firebase auth instance object
        mAuth=FirebaseAuth.getInstance();

        //user
        Button user=findViewById(R.id.user_btn);
        user.setOnClickListener(view -> {

            Toast.makeText(user_or_admin.this,"Welcome User",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(user_or_admin.this,alertdi.class);
            startActivity(i);
            finish();
        });

        //admin
        Button admin=findViewById(R.id.admin_btn);
        admin.setOnClickListener(view -> {
            Toast.makeText(user_or_admin.this,"Welcome Admin",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(user_or_admin.this,admin_login.class);
            startActivity(i);
            finish();
        });
    }
}
