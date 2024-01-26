package com.example.login1.Clinic_Compass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LauncherActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.login1.R;
import com.example.login1.home_page;

import java.util.ArrayList;
import java.util.Iterator;

public class MainClinic extends AppCompatActivity implements ItemClickListener, SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private SearchView searchView;
    private ArrayList<ListItem> allItems;
    private ArrayList<ListItem> displayedItems;

    public MainClinic() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main_clinic);

        //status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(MainClinic.this, R.color.black));
        //action bar color d4f0b6   0d6941   #2A712D
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#176639"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'><b><i>Clinic Compass</i></b> </font>"));

        recyclerView = findViewById(R.id.recyclerclinic);
        searchView = findViewById(R.id.searchclinic);
        ArrayList<ListItem> items = new ArrayList();
        items.add(new ListItem(R.drawable.loc, "Hitauyu Ayurveda Center\nKunjibettu,Udupi\nOpens 9:30am"));
        items.add(new ListItem(R.drawable.loc, "AYURDHARA,Ayurveda\nPachakarma Chikitsalaya\nVarahi Complex,Kadiyali,\nUdupi\nOpens 7:00am"));
        items.add(new ListItem(R.drawable.loc, "AKSHARA AYURVEDA \nCLINIC\nAjjarkadu, Udupi\nOpens 9:30am"));
        items.add(new ListItem(R.drawable.loc, "Himalaya Ayurvedic\nTreatment Centre\nKadiyali, Udupi\nOpens 9:00am"));
        items.add(new ListItem(R.drawable.loc, "Shrikrishna Ayurveda\nClinic and Panchakarma\nCentre\nChitpady, Udupi\nOpens 10:00am"));
        items.add(new ListItem(R.drawable.loc, "Sri Raksha Ayurveda\nClinic\nChitpady, Udupi\nOpens 10:00am"));
        items.add(new ListItem(R.drawable.loc, "Muniyal Ayurveda \nHospital\nManipal ,Udupi\nOpen 24 hours"));
        items.add(new ListItem(R.drawable.loc, "Dhanvantari Ayurveda\nHospital\nCenter for Remedy and \nResearch\nDoddangudde, Udupi\nOpen 24 hours"));
        items.add(new ListItem(R.drawable.loc, "Padma Ayurveda Clinic\n||Dr Anjaly RK\nAmbalpadi ,Udupi \nOpens 12:00pm"));
        items.add(new ListItem(R.drawable.loc, "Dhatri Ayurveda\nAmbalpadi, Udupi\nOpens 8:30am"));
        items.add(new ListItem(R.drawable.loc, "Durga Ayurveda Clinic\nVadiraja road, Udupi \nOpens 5:00pm"));
        items.add(new ListItem(R.drawable.loc, "Atharva Ayurveda Clinic\nKadekoppala,Chitpady,\nUdupi \nOpens 10:00am"));
        items.add(new ListItem(R.drawable.loc, "Neolife Wellness Centre\nThenkpete,Brahmagiri,\nAmbalpadi,Udupi \nOpens 8:30am"));
        items.add(new ListItem(R.drawable.loc, "SDM Ayurveda Hospital\nUdyavara, Udupi\nOpen 24 hours"));
        items.add(new ListItem(R.drawable.loc, "AyurLife Clinics\nKampanabettu,Udyavara,\nUdupi\nOpens 8:00am"));
        items.add(new ListItem(R.drawable.loc, "Ayurvedeeyam\nKadiyali,Kalsanka,\nUdupi \nOpens 10:00am"));
        items.add(new ListItem(R.drawable.loc, "Agastya Clinic\nDoddanagudde, Udupi \n10:00am"));
        items.add(new ListItem(R.drawable.loc, "Dhanvantari Clinic and\nHospital(Dr A R Acharya)\nAdi-Udupi\nOpens 7:30am"));
        items.add(new ListItem(R.drawable.loc, "Health Care Clinic\nMalpe, Udupi \n9:00am"));
        items.add(new ListItem(R.drawable.loc, "Nati Vaidya Srinivasa\nPoojary\nKodankoor ,Udupi \nOpens 8:00am"));
        items.add(new ListItem(R.drawable.loc, "Panchashri Clinic\nKukkikatte,Udupi\nOpens 5:30pm"));
        items.add(new ListItem(R.drawable.loc, "Dr Anu Dental\nand Ayurveda\nAnanda rao RD,Ambalpadi,\nUdupi\nOpens 10:00am"));
        items.add(new ListItem(R.drawable.loc, "Deerghayu Ayurveda\nChikitsalayam\nManipal, Udupi \nOpens 10:00am"));
        items.add(new ListItem(R.drawable.loc, "Rathnashree\nArogyam\nUdyavara, Udupi\nOpen 24 hours"));
        adapter = new CustomAdapter(items, this);
        recyclerView.setAdapter(this.adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("Search here");
        displayedItems = new ArrayList(items);
        allItems = new ArrayList(items);
    }

    public void onItemClick(ListItem item) {
        Toast.makeText(this, "Clicked: " + item.getText(), Toast.LENGTH_SHORT).show();
        openMap(item.getText());
    }

    private void openMap(String location) {
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        Intent mapIntent = new Intent("android.intent.action.VIEW", geoUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        this.startActivity(mapIntent);
    }

    public boolean onQueryTextChange(String newText) {
        displayedItems.clear();
        if (newText.isEmpty()) {
            displayedItems.addAll(this.allItems);
        } else {
            Iterator var2 = allItems.iterator();

            while(var2.hasNext()) {
                ListItem item = (ListItem)var2.next();
                if (item.getText().toLowerCase().contains(newText.toLowerCase())) {
                    displayedItems.add(item);
                }
            }
        }

        adapter.setItems(displayedItems);
        return true;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}
