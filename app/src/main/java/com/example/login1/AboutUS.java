package com.example.login1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;

public class AboutUS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        //status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(AboutUS.this, R.color.black));

//action bar color d4f0b6   0d6941
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#176639"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'><b><i>HealthSutra</i></b> </font>"));
    }
}