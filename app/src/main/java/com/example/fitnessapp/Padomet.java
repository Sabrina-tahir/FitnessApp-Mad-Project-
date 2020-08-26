package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import static android.hardware.Sensor.TYPE_STEP_COUNTER;
import static android.hardware.Sensor.TYPE_STEP_DETECTOR;

public class Padomet extends AppCompatActivity implements SensorEventListener
{
    TextView counter;
    Button done,list;
    SensorManager sensor;
    CircularProgressBar circularProgressBar;
    boolean running = false;
    Sensor steps;
    float totalsteps= (float) 0.0;
    float previoussteps= (float) 0.0;
    public static float goals;
    public static int currentsteps;
    static int result;
    static float target;
    private static final String TAG = "Padomet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padomet);
        counter = (TextView)findViewById(R.id.count);
        done = (Button) findViewById(R.id.done);
        list = (Button) findViewById(R.id.list);
        circularProgressBar = findViewById(R.id.circularProgressBar);
        String goal = getIntent().getStringExtra("stepcount");
        goals = Float.parseFloat(goal);
        send(goals);
        circularProgressBar.setProgressMax(goals);
        loadData();
        resetSteps();
        sensor = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        steps = sensor.getDefaultSensor(TYPE_STEP_COUNTER);
        done.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                stepsDone exampleDialog = new stepsDone();
                exampleDialog.show(getSupportFragmentManager(), "example dialog");

            }
        });
        list.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Padomet.this, ListDataActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        running=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        running=true;

        if(steps!=null){
            sensor.registerListener(this,steps,SensorManager.SENSOR_DELAY_UI);
        }
        else{
            Toast.makeText(this, "SENSOR NOT FOUND IN YOUR DEVICE", Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public void onSensorChanged(SensorEvent event) {
        if (running) {
            totalsteps = event.values[0];
            currentsteps = (int) (totalsteps - previoussteps);
            counter.setText(String.valueOf(currentsteps));
            circularProgressBar.setProgressWithAnimation((float) currentsteps);
            sendcurrent(currentsteps);



        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    private void resetSteps(){
        counter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                previoussteps=totalsteps;
                counter.setText((String.valueOf(0)));
                circularProgressBar.setProgress(0);
                savedata();
                return true;
            }
        });
    }

    private void savedata() {
        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putFloat("key", previoussteps);
        editor.apply();
    }
    private void loadData(){
        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        float savedNumber= sharedpreferences.getFloat("key",0);
        previoussteps = savedNumber;
    }

   public static void send(float f){
        target=f;
   }
    public static void sendcurrent(int s){
        result=s;
    }
    public static float get(){
        return target;
    }
    public static int getc(){
        return result;
    }

}