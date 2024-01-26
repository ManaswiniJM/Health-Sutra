package com.example.login1.prakriti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.login1.R;
import com.example.login1.home_page;

public class ResultActivity extends AppCompatActivity {
    private TextView resultTextView;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

    //status bar
    getWindow().setStatusBarColor(ContextCompat.getColor(ResultActivity.this, R.color.black));
    ColorDrawable colorDrawable
            = new ColorDrawable(Color.parseColor("#EDF3E6"));
    getSupportActionBar().setBackgroundDrawable(colorDrawable);
    getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'><b><i>Prakruthi</i></b> </font>"));

        resultTextView = findViewById(R.id.prak_tv);
        String nature = getIntent().getStringExtra("nature");
        resultTextView.setText("\n\t " + nature);
    }
}