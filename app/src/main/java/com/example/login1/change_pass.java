package com.example.login1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class change_pass extends AppCompatActivity {

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
        setContentView(R.layout.change_pass);

        auth=FirebaseAuth.getInstance();
        txtmail=findViewById(R.id.chngemail);
        frgtbtn=findViewById(R.id.changsub);
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
                            Toast.makeText(change_pass.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(change_pass.this, MainActivity.class));
                            finish();
                        }
                        else
                        {
                            Toast.makeText(change_pass.this, "Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}