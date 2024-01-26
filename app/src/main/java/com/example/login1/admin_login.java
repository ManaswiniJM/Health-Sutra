package com.example.login1;

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

public class admin_login extends AppCompatActivity {

    EditText admemail,admpass;
    FirebaseAuth mAuth;
    Button admlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //removes title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.admin_login);

        //firebase auth instance object
        mAuth=FirebaseAuth.getInstance();

        admemail =findViewById(R.id.admemail);
        admpass=findViewById(R.id.admpass);
        admlogin=findViewById(R.id.admlogin);

        admlogin.setOnClickListener(view -> {
            String email, password;
            email = String.valueOf(admemail.getText());
            password = String.valueOf(admpass.getText());

            if (TextUtils.isEmpty(password)) {
                admpass.setError("Please enter Password");
                //Toast.makeText(MainActivity.this,"Enter Password",Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (password.length() < 8) {
                            // lopass.setError("Please enter Password");
                            admpass.setError("Password is incorrect");
                        } else {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Admin, Welcome to HealthSutra.",
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(admin_login.this, admin_home_page.class);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(admin_login.this, "Password entered is incorrect",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
}