package com.example.fitnessapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Date;

public class SettingsPage extends AppCompatActivity {
    Button btnSave;
    RadioButton rdiEasy,rdiMedium,rdiHard;
    RadioGroup radioGroup;
    FitnessDb fdb;
    ToggleButton switchAlarm;
    TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        btnSave = (Button) findViewById(R.id.btnSave);
        radioGroup = (RadioGroup) findViewById(R.id.rdiGroup);
        rdiEasy = (RadioButton) findViewById(R.id.rdiEasy);
        rdiMedium = (RadioButton) findViewById(R.id.rdiMed);
        rdiHard = (RadioButton)  findViewById(R.id.rdiHard);
        fdb = new FitnessDb(this);
        switchAlarm = (ToggleButton) findViewById(R.id.switchAlarm);
        timePicker = (TimePicker) findViewById(R.id.timepicker);

        int mode = fdb.getModes();
        setRadioButton(mode);

       btnSave.setOnClickListener(new View.OnClickListener() {
           @RequiresApi(api = Build.VERSION_CODES.O)
           @Override
           public void onClick(View v) {
               saveMode();
               SaveAlarm(switchAlarm.isChecked());
               Toast.makeText(SettingsPage.this,"SAVED",Toast.LENGTH_SHORT).show();
               finish();
           }
       });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void SaveAlarm(boolean checked) {
        if(checked){
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent;
            PendingIntent pendingIntent;
            intent = new Intent(SettingsPage.this,AlarmNotification.class);
            pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
            Calendar calender = Calendar.getInstance();
            Date today = Calendar.getInstance().getTime();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                NotificationChannel nc=new NotificationChannel("n","n", NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager nm = getSystemService(NotificationManager.class);
                nm.createNotificationChannel(nc);
                calender.set(today.getYear(),today.getMonth(),today.getDay(),timePicker.getHour(),timePicker.getMinute());
                manager.setRepeating(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
                Log.d("DEBUG","aLARM WILL WAKE AT : "+timePicker.getHour()+":"+timePicker.getMinute());
            }

        }
        else{
            Intent intent = new Intent (SettingsPage.this,AlarmNotification.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);
        }

    }

    private void saveMode() {
        int id  = radioGroup.getCheckedRadioButtonId();
        if(id == rdiEasy.getId())
            fdb.SaveMode(0);
        else if(id == rdiMedium.getId())
            fdb.SaveMode(1);
       else if(id == rdiHard.getId())
            fdb.SaveMode(2);
    }

    private void setRadioButton(int mode) {
        if(mode==0)
            radioGroup.check(R.id.rdiEasy);
        if(mode==1)
            radioGroup.check(R.id.rdiMed);
        if(mode==2)
            radioGroup.check(R.id.rdiHard);

    }
}