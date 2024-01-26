package com.example.login1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class bmi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);
        EditText edttxtweight=findViewById(R.id.weight);
        EditText edttxtheight=findViewById(R.id.height);
        Button btn=findViewById(R.id.bmical);
        TextView txtview=findViewById(R.id.txtbmi);
        TextView bmitext=findViewById(R.id.txtbmi2);
        //status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(bmi.this, R.color.black));

//action bar
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("BMI Calculator");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(TextUtils.isDigitsOnly(edttxtweight.getText()))) {
                    edttxtweight.setError("Please enter number only");
                }
                 else   if(!(TextUtils.isDigitsOnly(edttxtheight.getText()))){
                        edttxtheight.setError("Please enter number only");
                    }
                 else if(TextUtils.isEmpty(edttxtweight.getText())){
                    edttxtweight.setError("Please don't leave blank");
                }
                else if(TextUtils.isEmpty(edttxtheight.getText())){
                    edttxtheight.setError("Please don't leave blank");
                }
                 else{
                    float height = Float.parseFloat(String.valueOf(edttxtheight.getText())) / 100;
                    float weight = Float.parseFloat(String.valueOf(edttxtweight.getText()));
                    float bmi = weight / (height * height);
                    txtview.setText(String.valueOf(bmi));
                    if(bmi<=18.5){
                        bmitext.setText("\tYou are UnderWeight\nA BMI of less than 18.5 indicates that you are underweight," +
                                "\n So you may need to put on some weight.\nYou are recommended to ask your doctor or a dietitian for advice.");
                    }
                    else if(bmi>18.5&&bmi<=24.9){
                        bmitext.setText("\tYou are Healthy" +
                                "\nA BMI of 18.5 to 24.9 indicates that you are Healthy weight for your height," +
                                "\n By maintaining a healthy weight, you lower your risk of developing serious problems.");
                    }
                    else if(bmi>24.9&&bmi<=29.9){
                        bmitext.setText("\tYou are OverWeight" +
                                "\nA BMI of 25 to 29.9 indicates that you are slightly Overweight," +
                                "\nYou may be advised to lose some weight for health reasons.\nYou are recommended to talk to your doctor or dietitian for advice.");
                    }
                    else if(bmi>29.9){
                        bmitext.setText("\tYou are Obese" +
                                "\nA BMI of over 30 indicates that you are Heavily Overweight," +
                                "\nYYour health may be at risk if you do not lose weight.\nYou are recommended to talk to your doctor or dietitian for advice.");

                    }
                }

            }

        });


    }
}