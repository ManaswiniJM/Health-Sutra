package com.example.login1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class diagnosis extends AppCompatActivity implements View.OnClickListener {

    public CardView cv1,cv2,cv3,cv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diagnosis);
        //status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(diagnosis.this, R.color.black));

        //find cardview and assign objects
        cv1=(CardView) findViewById(R.id.c1);
        cv2=(CardView) findViewById(R.id.c2);
        cv3=(CardView) findViewById(R.id.c3);
        cv4=(CardView) findViewById(R.id.c4);

        //set onclcik for all card view
        cv1.setOnClickListener(this);
        cv2.setOnClickListener(this);
        cv3.setOnClickListener(this);
        cv4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i,j,k,l;
        switch (v.getId()){
            case R.id.c1:
                i=new Intent(this, Activity_general.class);
                startActivity(i);
                break;
            case R.id.c2:
                j=new Intent(this, Activity_skin.class);
                startActivity(j);
                break;
            case R.id.c3:
                k=new Intent(this, Activity_hair.class);
                startActivity(k);
                break;
            case R.id.c4:
                l=new Intent(this, Activity_digestive.class);
                startActivity(l);
                break;
        }

    }
}









  /*  //add data to database
    HashMap<String,Object> map=new HashMap<>();
        map.put("Name","pai");
                map.put("email","dew1306@gmail.com");
                FirebaseDatabase.getInstance().getReference().child("General").child("Cold").updateChildren(map);*/