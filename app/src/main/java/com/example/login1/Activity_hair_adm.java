package com.example.login1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Activity_hair_adm extends AppCompatActivity implements OnQueryTextListener {

    private SearchView searchView;
    RecyclerView diogrec;
    FirebaseFirestore db;
    Button add;

    List<DiseaseAdmin> diseaseList;
    DiseaseAdapterHair diseaseAdapter;
    List<DiseaseAdmin> filteredDiseaseList;

    private static final int REQUEST_CODE_ADD_DISEASE = 1;
    private static final int RESULT_CODE_DELETED = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_hair_adm);

        getWindow().setStatusBarColor(ContextCompat.getColor(Activity_hair_adm.this, R.color.black));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("Hair Disease");

        db = FirebaseFirestore.getInstance();
        diogrec = findViewById(R.id.hrecyclerViewAdm);
        searchView = findViewById(R.id.hsearchViewAdm);
        add = findViewById(R.id.hdiseaseAdd);

        diogrec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search here");

        diseaseList = new ArrayList<>();
        filteredDiseaseList = new ArrayList<>();
        diseaseAdapter = new DiseaseAdapterHair(this, filteredDiseaseList);
        diogrec.setAdapter(diseaseAdapter);

        fetchDiseaseData();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity_hair_adm.this, DiseaseAddHair.class);
                startActivityForResult(i, 1);
            }
        });
    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filteredDiseaseList.clear();
        if (newText.isEmpty()) {
            filteredDiseaseList.addAll(diseaseList); // If the search query is empty, show all diseases
        } else {
            for (DiseaseAdmin disease : diseaseList) {
                if (disease.getName().toLowerCase().contains(newText.toLowerCase())) {
                    filteredDiseaseList.add(disease); // Add diseases that match the search query
                }
            }
        }
        diseaseAdapter.notifyDataSetChanged(); // Notify the adapter about the changes in the filtered list
        return true;
    }


    private void fetchDiseaseData(){
        db.collection("Hair")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            diseaseList.clear();
                            filteredDiseaseList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DiseaseAdmin disease = document.toObject(DiseaseAdmin.class);
                                disease.setId(document.getId());
                                Log.d("DiseaseAdmin", "Disease ID: " + disease.getId());
                                diseaseList.add(disease);
                                filteredDiseaseList.add(disease);
                            }
                            diseaseAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(Activity_hair_adm.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_DISEASE && resultCode == RESULT_OK) {
            fetchDiseaseData();
        } else if (resultCode == RESULT_CODE_DELETED) {
            int deletedPosition = data.getIntExtra("position", -1);
            if (deletedPosition != -1) {
                diseaseList.remove(deletedPosition); // Remove the item from the diseaseList
                filteredDiseaseList.clear(); // Clear the filtered list
                filteredDiseaseList.addAll(diseaseList); // Update the filtered list
            }
        }
    }

}

