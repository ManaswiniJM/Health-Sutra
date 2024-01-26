package com.example.login1;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.locks.ReadWriteLock;


public class Registration_form extends AppCompatActivity {

    private EditText regemail,regpass,reguname,regadd,regno,regcpass;
    private Button register,bktlog;
    private FirebaseAuth mAuth;
  //check if user already logged in
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent i = new Intent(Registration_form.this, home_page.class);
            startActivity(i);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_form);
        getWindow().setStatusBarColor(ContextCompat.getColor(Registration_form.this, R.color.status));


        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]{3}+";
        String phonepattern="[6-9]{1}+[0-9]{9}";
        register=findViewById(R.id.btnregis);
        bktlog=findViewById(R.id.bktolog);
        reguname =findViewById(R.id.edtuser);
        regadd =findViewById(R.id.edtaddress);
        regcpass =findViewById(R.id.edtconpass);
        regemail =findViewById(R.id.regisemail);
        regpass=findViewById(R.id.regispass);
        regno=findViewById(R.id.edtphone);

        //firebase auth instance object
        mAuth=FirebaseAuth.getInstance();

//opens login page
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username,address,email,password,conpass,phone;
                email=String.valueOf(regemail.getText());
                password=String.valueOf(regpass.getText());
                conpass=String.valueOf(regcpass.getText());
                phone=String.valueOf(regno.getText());
                username=String.valueOf(reguname.getText());
                address=String.valueOf(regadd.getText());

                //check if email password empty
                if(TextUtils.isEmpty(email)){
                    regemail.setError("Enter correct Email");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Registration_form.this,"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!email.matches(emailPattern)) {
                    regemail.setError("Enter correct Email");
                }
                else if (phone.length()<10||phone.length()>10) {
                    regno.setError("Phone number must contain 10 digits");
                }
                else if (!phone.matches(phonepattern)) {
                    regno.setError("Please enter proper Phone number");
                }
                else if (password.length()<8) {
                    regpass.setError("Password must contain at least 8 characters");
                }
                else if (!password.equals(conpass)) {
                    regcpass.setError("Password not matched");
                } else {
                    //create user
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override

                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //progressbar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                //showDialog();

                                FirebaseUser firebaseUser= mAuth.getCurrentUser();

                                ReadWriteUserDeatils writeUserDeatils=new ReadWriteUserDeatils(username,email,address,phone);

                                DatabaseReference referenceProfile= FirebaseDatabase.getInstance().getReference("User");
                                referenceProfile.child(firebaseUser.getUid()).setValue(writeUserDeatils).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {

                                            firebaseUser.sendEmailVerification();
                                            Toast.makeText(Registration_form.this, "Account Created.",
                                                    Toast.LENGTH_SHORT).show();

                                            Intent i = new Intent(Registration_form.this, home_page.class);
                                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(i);
                                            finish();

                                        }else{
                                            Toast.makeText(Registration_form.this,"User Registered Failed",Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });

                            }
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {

                                regemail.setError("Provided Email Is Already Registered!");
                            }else {
                                Intent i = new Intent(Registration_form.this, MainActivity
                                        .class);
                                startActivity(i);
                            }
                        }
                    });
                }
            }
        });


//button to return back to login page
        bktlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Registration_form.this,MainActivity.class);
                startActivity(i);
            }
        });
    }

}
