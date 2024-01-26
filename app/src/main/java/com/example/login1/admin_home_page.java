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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;

public class admin_home_page extends AppCompatActivity {
    Button admKart;
    Button admDiag;
    FirebaseAuth mAuth;

    //for profile menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.admin_options, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //PROFILE options menu
        switch (item.getItemId()) {
            case R.id.admloggoff:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(admin_home_page.this, user_or_admin.class);
                startActivity(i);
                finish();
                break;

            case R.id.admdetails:
                Intent v = new Intent(admin_home_page.this, user_details.class);
                startActivity(v);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getSupportActionBar().setIcon(R.drawable.logoo);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setContentView(R.layout.admin_home);
        //status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(admin_home_page.this, R.color.black));

//action bar color d4f0b6   0d6941   #2A712D
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#176639"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'><b><i>HealthSutra</i></b> </font>"));

        mAuth=FirebaseAuth.getInstance();
        //kart
        admKart =findViewById(R.id.admkart);
        admKart.setOnClickListener(view -> {
            Intent i=new Intent(admin_home_page.this,change_frag.class);
            startActivity(i);
        });

        //Diagnosis
        admDiag =findViewById(R.id.admdiag);
        admDiag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(admin_home_page.this,admin_diagnosis.class);
                startActivity(i);
            }
        });
    }

}