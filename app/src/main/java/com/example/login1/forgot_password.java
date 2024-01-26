package com.example.login1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity {
    private Button frgtbtn;

    private EditText txtmail;
    private String email;
    private FirebaseAuth auth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

    //        to remove title bar
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
            setContentView(R.layout.forgotpass);

            auth=FirebaseAuth.getInstance();
            txtmail=findViewById(R.id.txtfmail);
            frgtbtn=findViewById(R.id.btnsubmit);
            frgtbtn.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validateData();
                }
            }));
        }
        private  void validateData(){
            email=txtmail.getText().toString();
            if(email.isEmpty()){
                txtmail.setError("Required");
                }else
            {
                forgetPass();

            }
        }
        private  void forgetPass(){
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(forgot_password.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(forgot_password.this, MainActivity.class));
                                finish();
                            }
                            else
                            {
                                Toast.makeText(forgot_password.this, "Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }