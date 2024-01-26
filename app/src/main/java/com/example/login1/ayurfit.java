package com.example.login1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.login1.prakriti.DailyActivity;
import com.example.login1.prakriti.ResultActivity;
import com.example.login1.prakriti.StartActivity;

public class ayurfit extends AppCompatActivity implements View.OnClickListener {

    public CardView fitcrd1,fitcrd2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
//no action bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
*/
        getWindow().setStatusBarColor(ContextCompat.getColor(ayurfit.this, R.color.black));
        //action bar color d4f0b6   0d6941   #2A712D
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#EDF3E6"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'><b><i>AyurFit</i></b> </font>"));

        setContentView(R.layout.fit1);

        fitcrd1=(CardView) findViewById(R.id.fitcrd1);
        fitcrd2=(CardView) findViewById(R.id.fitcrd2);

        fitcrd1.setOnClickListener(this);
        fitcrd2.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        Intent i,j;
        switch (v.getId()){
            case R.id.fitcrd1:
                i=new Intent(this, StartActivity.class);
                startActivity(i);
                break;
            case R.id.fitcrd2:
                j=new Intent(this, DailyActivity.class);
                startActivity(j);
                break;
        }

    }
}