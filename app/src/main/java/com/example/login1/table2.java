package com.example.login1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class table2 extends AppCompatActivity {
    //P_sans,P_ver,P_bot,P_fam,P_part

    TextView p_name,p_des,p_sans,p_ver,p_bot,p_fam,p_part;
    ImageView my_img;
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(table2.this, R.color.black));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("Herb HandBook");
        setContentView(R.layout.table_details);

        my_img = findViewById(R.id.img1);
        p_name = findViewById(R.id.plname);
        p_des = findViewById(R.id.tabdes);
        p_sans=findViewById(R.id.sansname);
        p_ver=findViewById(R.id.vername);
        p_bot=findViewById(R.id.botname);
        p_fam=findViewById(R.id.famname);
        p_part=findViewById(R.id.part);




        p_name.setText(getIntent().getExtras().getString("Plant_Name"));
        p_des.setText(getIntent().getExtras().getString("P_des"));
        p_sans.setText(getIntent().getExtras().getString("P_sans"));
        p_ver.setText(getIntent().getExtras().getString("P_ver"));
        p_bot.setText(getIntent().getExtras().getString("P_bot"));
        p_fam.setText(getIntent().getExtras().getString("P_fam"));
        p_part.setText(getIntent().getExtras().getString("P_part"));
        int my_int_img = getIntent().getIntExtra("Image", 0);
        my_img.setImageResource(my_int_img);


    }
}
