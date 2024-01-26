package com.example.login1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

public class admin_diagnosis extends AppCompatActivity implements View.OnClickListener{

    public CardView c1,c2,c3,c4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnosis_admin);

        //status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(admin_diagnosis.this, R.color.black));

        //find cardview and assign objects
        c1=(CardView) findViewById(R.id.c1_adm);
        c2=(CardView) findViewById(R.id.c2_adm);
        c3=(CardView) findViewById(R.id.c3_adm);
        c4=(CardView) findViewById(R.id.c4_adm);

        //set onclcik for all card view
        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
        Intent i,j,k,l;
        switch (v.getId()){

            case R.id.c1_adm:
                i=new Intent(this, Activity_general_adm.class);
                startActivity(i);
                break;
            case R.id.c2_adm:
                j=new Intent(this, Activity_skin_adm.class);
                startActivity(j);
                break;
            case R.id.c3_adm:
                k=new Intent(this, Activity_hair_adm.class);
                startActivity(k);
                break;
            case R.id.c4_adm:
                l=new Intent(this, Activity_digestive_adm.class);
                startActivity(l);
                break;
        }

    }
}