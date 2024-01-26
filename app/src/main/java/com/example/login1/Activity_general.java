package com.example.login1;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login1.products.ProductAdapter;
import com.example.login1.products.ProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Activity_general extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private SearchView searchView;
    RecyclerView diogrec;
    FirebaseFirestore db;

    List<Disease> diseaseList;
    DiseaseAdapter diseaseAdapter;
    List<Disease> filteredDiseaseList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_general);

        getWindow().setStatusBarColor(ContextCompat.getColor(Activity_general.this, R.color.black));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("General Disease");

        db = FirebaseFirestore.getInstance();
        diogrec = findViewById(R.id.grecyclerView);
        searchView = findViewById(R.id.gsearchView);


        diogrec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search here");


        diseaseList = new ArrayList<>();
        filteredDiseaseList = new ArrayList<>();
        diseaseAdapter = new DiseaseAdapter(this, filteredDiseaseList);
        diogrec.setAdapter(diseaseAdapter);

        db.collection("General")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Disease disease = document.toObject(Disease.class);
                                diseaseList.add(disease);
                                filteredDiseaseList.add(disease);
                                diseaseAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(Activity_general.this, "Error" + task.getException(), Toast.LENGTH_SHORT).show();
                        }
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
            for (Disease disease : diseaseList) {
                if (disease.getName().toLowerCase().contains(newText.toLowerCase())) {
                    filteredDiseaseList.add(disease); // Add diseases that match the search query
                }
            }
        }
        diseaseAdapter.notifyDataSetChanged(); // Notify the adapter about the changes in the filtered list
        return true;
    }
}

