package com.example.login1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.login1.Clinic_Compass.MainClinic;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class home_page extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Button kart,herb,diag,arfit,clinic ;

    FirebaseAuth mAuth;
    FirebaseUser user;

    //for profile menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //PROFILE options menu
        switch (item.getItemId()) {
            case R.id.loggoff:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(home_page.this, user_or_admin.class);
                startActivity(i);
                finish();
                break;
            case R.id.changepass:
                Intent j = new Intent(home_page.this, change_pass.class);
                startActivity(j);
                break;
            case R.id.abtus:
                Intent a=new Intent(home_page.this,AboutUS.class);
                startActivity(a);
                break;
            case R.id.userList:
                Intent v=new Intent(home_page.this,user_profile.class);
                startActivity(v);
                break;
        }

        //drawer menu
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getSupportActionBar().setIcon(R.drawable.logoo);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(R.layout.homepg);
        //status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(home_page.this, R.color.black));

//action bar color d4f0b6   0d6941   #2A712D
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#176639"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'><b><i>HealthSutra</i></b> </font>"));



        //drawer fields header and list
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_open, R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //drawer menu
                switch (item.getItemId()) {
                    case R.id.history:
                        Toast.makeText(home_page.this, "History of Ayurveda", Toast.LENGTH_SHORT).show();
                        Intent k = new Intent(home_page.this, history.class);
                        startActivity(k);
                        break;
                    case R.id.reminder:
                        Toast.makeText(home_page.this, "Reminders", Toast.LENGTH_SHORT).show();
                        Intent j = new Intent(home_page.this, Reminder_Activity.class);
                        startActivity(j);
                        break;
                    case R.id.bmi:
                        Toast.makeText(home_page.this, "Check your Body Mass Index", Toast.LENGTH_SHORT).show();
                        Intent l = new Intent(home_page.this, bmi.class);
                        startActivity(l);
                        break;
                }
                return true;
            }
        });

        //kart
        kart =findViewById(R.id.kart);
        kart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(home_page.this,kart.class);
                startActivity(i);
            }
        });

        //herb handbook
        herb =findViewById(R.id.plant);
        herb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(home_page.this,table.class);
                startActivity(i);
            }
        });

        //Diagnosis
        diag =findViewById(R.id.diagnosis);
        diag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(home_page.this,diagnosis.class);
                startActivity(i);
            }
        });

        //ayurfit
         arfit =findViewById(R.id.daily);
        arfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(home_page.this,ayurfit.class);
                startActivity(i);
            }
        });
//Clinic compass
       clinic =findViewById(R.id.maps);
        clinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(home_page.this, MainClinic.class);
                startActivity(i);
            }
        });



        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user == null) {
            Intent i = new Intent(home_page.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}



