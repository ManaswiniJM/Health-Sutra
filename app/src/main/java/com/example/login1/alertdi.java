package com.example.login1;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class alertdi extends AppCompatActivity {

   // private CheckBox checkBox;
   FirebaseAuth mAuth;
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(alertdi.this, home_page.class);
            startActivity(i);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//removes title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

       // checkBox = findViewById(R.id.checkBox);
        setContentView(R.layout.user_or_admin);

        //firebase auth instance object
        mAuth=FirebaseAuth.getInstance();

        showDialog();

    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirmation");
        builder.setMessage(R.string.thisapp);

        builder.setPositiveButton("Accept", (dialog, which) -> {
            Toast.makeText(getApplicationContext(), "Please Login.",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(alertdi.this,MainActivity.class);
            startActivity(intent);

            finish();
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> {
            dialog.dismiss();
            //logs the user out if cancle is clicked
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(alertdi.this, user_or_admin.class);
            startActivity(i);
            finish();

        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

