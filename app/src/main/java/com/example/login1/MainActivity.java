package com.example.login1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;


public class  MainActivity extends AppCompatActivity {

    EditText logemail,lopass;
    FirebaseAuth mAuth;
    Button reg,login,forgot;


    //check if user already logged in
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(MainActivity.this, home_page.class);
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

        setContentView(R.layout.activity_main);

//firebase auth instance object
        mAuth=FirebaseAuth.getInstance();

        logemail =findViewById(R.id.lgemail);
        lopass=findViewById(R.id.logpass);
        forgot=findViewById(R.id.frgtpass);
        reg=findViewById(R.id.btnRegister);
        login=findViewById(R.id.btnlogin);
        //emailpattern
        String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        //opens registration page
        reg.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, Registration_form.class);
            startActivity(i);
        });

        //forgot password
        forgot.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, forgot_password.class);
            startActivity(i);
        });

        //login button
        Button login=findViewById(R.id.btnlogin);

        login.setOnClickListener(view -> {
            String email,password;
            email=String.valueOf(logemail.getText());
            password=String.valueOf(lopass.getText());

            //check if email password empty
            if(TextUtils.isEmpty(email)){
                logemail.setError("Please enter Email");
                return;
            }
             if(TextUtils.isEmpty(password)){
                lopass.setError("Please enter Password");
                //Toast.makeText(MainActivity.this,"Enter Password",Toast.LENGTH_SHORT).show();
                return;
            }

            //signin with email and password
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if(!email.matches(emailPattern))
                        {
                            logemail.setError("Enter correct Email");
                        }
                        else if(password.length()<8) {
                           // lopass.setError("Please enter Password");
                            lopass.setError("Password is incorrect");
                        }
                        else {
                            if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Welcome to HealthSutra.",
                                    Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this,home_page.class);
                            startActivity(i);
                            finish();
                            }
                            else {
                            if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                                String errorMessage = task.getException().getMessage();
                                if (errorMessage.contains("no user record")) {
                                    // User is not registered
                                    showUserNotRegisteredDialog();
                                    //  Toast.makeText(MainActivity.this, "User is not registered.", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Other error occurred during login
                                    Toast.makeText(MainActivity.this, "Login failed: " + errorMessage, Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Password entered is incorrect",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        }
                        });

        });
    }
    private void showUserNotRegisteredDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("User Not Registered");
        builder.setMessage("The provided email is not registered.");

        // Set OK button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}

