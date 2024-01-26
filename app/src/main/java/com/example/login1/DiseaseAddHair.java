package com.example.login1;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.firestore.FirebaseFirestore;

public class DiseaseAddHair extends AppCompatActivity {

    EditText etDiseaseName, etSymptoms, etRemedies, etDosAndDonts;
    Button btnSave;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setStatusBarColor(ContextCompat.getColor(DiseaseAddHair.this, R.color.black));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("Hair Disease");

        setContentView(R.layout.add_disease);

        etDiseaseName = findViewById(R.id.Dname);
        etSymptoms = findViewById(R.id.Dsym);
        etRemedies = findViewById(R.id.Dremedies);
        etDosAndDonts = findViewById(R.id.Ddd);
        btnSave = findViewById(R.id.savebtn);
        db = FirebaseFirestore.getInstance();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String diseaseName = etDiseaseName.getText().toString().trim();
                String symptoms = etSymptoms.getText().toString().trim();
                String remedies = etRemedies.getText().toString().trim();
                String dosAndDonts = etDosAndDonts.getText().toString().trim();

                DiseaseAdmin newDisease = new DiseaseAdmin(diseaseName, symptoms, remedies, dosAndDonts);

                db.collection("Hair")
                        .add(newDisease)
                        .addOnSuccessListener(documentReference -> {
                            Toast.makeText(DiseaseAddHair.this, "Disease added successfully!", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK); // Set the result to indicate success
                            finish(); // Close the current activity and return to the previous one
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(DiseaseAddHair.this, "Error adding disease: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });
    }
}
