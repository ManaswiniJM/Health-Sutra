package com.example.login1;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
        import android.app.AlarmManager;
        import android.app.PendingIntent;
        import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TimePicker;
        import android.widget.Toast;
        import android.widget.ToggleButton;

        import java.util.Calendar;

public class Reminder_Activity  extends AppCompatActivity implements View.OnClickListener{

    private int notificationId=1;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(ContextCompat.getColor(Reminder_Activity.this, R.color.black));
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#39a9e87c"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("Reminder");

        setContentView(R.layout.activity_reminder);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText editText=findViewById(R.id.editText);
        TimePicker timePicker=findViewById(R.id.timePicker);

        Intent intent=new Intent(Reminder_Activity.this,AlarmReceiver.class);
        intent.putExtra("notificationId",notificationId);
        intent.putExtra("message",editText.getText().toString());

        alarmIntent=PendingIntent.getBroadcast(Reminder_Activity.this,0,
                intent,PendingIntent.FLAG_CANCEL_CURRENT);

        alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);

        switch(view.getId()) {

            case R.id.setBtn:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // Set a one-time alarm
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
                Toast.makeText(this, "Reminder set", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelBtn:
                alarmManager.cancel(alarmIntent);
                Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show();
                break;

        }
        finish();
    }
}
