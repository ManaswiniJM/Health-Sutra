package com.example.login1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class display_diag extends AppCompatActivity {

    TextView name,symptoms,remedies,dosandonts;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    Disease disease=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();*/

        setContentView(R.layout.activity_diagnosis_result);

        getWindow().setStatusBarColor(ContextCompat.getColor(display_diag.this, R.color.black));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("Diagnosis");


        firestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        final Object object=getIntent().getSerializableExtra("detail");
        if(object instanceof Disease){
            disease=(Disease) object;
        }


        name=findViewById(R.id.textViewDiseaseName);
        symptoms=findViewById(R.id.txtsym);
        remedies=findViewById(R.id.txtrmd);
        dosandonts=findViewById(R.id.txtdd);

        if(disease!=null){

            name.setText(disease.getName());
            symptoms.setText(disease.getSymptoms());
            remedies.setText(disease.getRemedies());
            dosandonts.setText(disease.getDosAndDonts());

        }

    }
}