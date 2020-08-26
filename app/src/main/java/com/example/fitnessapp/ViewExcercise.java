package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewExcercise extends AppCompatActivity {
    int image_id;
    String name;
    TextView timer,title;
    ImageView detail_img;
    Button btnStart;
    Boolean isRunning=true;
    FitnessDb fdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_excercise);
        timer = (TextView) findViewById(R.id.timer);
        title=(TextView) findViewById(R.id.title);
        detail_img=(ImageView)findViewById(R.id.detail_image);
        fdb = new FitnessDb(this);
        btnStart = (Button)findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRunning) {
                    btnStart.setText("DONE");
                    int timelimit = 0;
                    if(fdb.getModes()==0)
                        timelimit=TimeForModes.EasyTime;
                    else if(fdb.getModes()==1)
                        timelimit=TimeForModes.MedTime;
                   else if(fdb.getModes()==2)
                        timelimit=TimeForModes.HardTime;

                    new CountDownTimer(timelimit, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timer.setText("" + millisUntilFinished/1000);
                        }

                        @Override
                        public void onFinish() {
                            Toast.makeText(ViewExcercise.this, "FINISH!!!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }.start();
                }
                else{
                    Toast.makeText(ViewExcercise.this, "Not FINISH!!!!", Toast.LENGTH_SHORT).show();
                      finish();
                }
                isRunning = !isRunning;
            }

        });
        timer.setText("");
        if(getIntent()!=null){
            image_id=getIntent().getIntExtra("image_id",-1);
            name=getIntent().getStringExtra("name");
            detail_img.setImageResource(image_id);
            title.setText(name);
        }
    }
}