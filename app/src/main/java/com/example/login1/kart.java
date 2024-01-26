package com.example.login1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;

public class kart extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(kart.this, R.color.black));

        //action bar color d4f0b6   0d6941
      /*  ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#176639"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff' style='@drawable/merriweather'><b><i>MediKart</i></b> </font>"));*/


        setContentView(R.layout.kart);

        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);

        tabLayout.setupWithViewPager(viewPager);
        VPAdapter vpAdapter=new VPAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new frag_home(),"Home");
        vpAdapter.addFragment(new frag_cart(),"Cart");
      //  vpAdapter.addFragment(new frag_order(),"Orders");
        viewPager.setAdapter(vpAdapter);
    }
}