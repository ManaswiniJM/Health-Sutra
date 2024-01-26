package com.example.login1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.firestore.FirebaseFirestore;

public class display_digestive_admin extends AppCompatActivity {

    TextView name,symptoms,remedies,dosandonts;
    FirebaseFirestore firestore;
    Button deleteDisease;

    DiseaseAdmin disease=null;
    DiseaseAdapterDigestive diseaseAdapter;

    private static final int RESULT_CODE_DELETED = 2;
    private int deletedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       /* requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();*/

        setContentView(R.layout.activity_diagnosis_result_admin);

        getWindow().setStatusBarColor(ContextCompat.getColor(display_digestive_admin.this, R.color.black));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("Diagnosis");


        firestore=FirebaseFirestore.getInstance();


        final Object object=getIntent().getSerializableExtra("detail");
        if(object instanceof DiseaseAdmin){
            disease=(DiseaseAdmin) object;
        }


        name=findViewById(R.id.textViewDiseaseNameAdm);
        symptoms=findViewById(R.id.txtsymAdm);
        remedies=findViewById(R.id.txtrmdAdm);
        dosandonts=findViewById(R.id.txtddAdm);
        deleteDisease=findViewById(R.id.deleteDisease);

        if(disease!=null){

            name.setText(disease.getName());
            symptoms.setText(disease.getSymptoms());
            remedies.setText(disease.getRemedies());
            dosandonts.setText(disease.getDosAndDonts());

        }
        deleteDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDiseaseFromFirestore();
            }
        });
    }

    private void deleteDiseaseFromFirestore() {
        if (disease != null) {
            firestore.collection("Digestive")
                    .document(disease.getId())
                    .delete()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(display_digestive_admin.this, "Disease deleted successfully!", Toast.LENGTH_SHORT).show();
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("position", getIntent().getIntExtra("position", -1));
                        setResult(RESULT_CODE_DELETED, resultIntent);
                        diseaseAdapter.notifyDataSetChanged();
                        finish();  // Close the current activity and return to the previous one
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(display_digestive_admin.this, "Error deleting disease: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
    }
    @Override
    public void onBackPressed() {
        // If the back button is pressed without deleting, return the deleted position as -1
        Intent resultIntent = new Intent();
        resultIntent.putExtra("position", -1);
        setResult(RESULT_CODE_DELETED, resultIntent);
        super.onBackPressed();
    }
}