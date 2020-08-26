package com.example.fitnessapp;
import com.bumptech.glide.Glide;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity{
    Button btnExcercises , btnSetting , btnpado;
    ImageView imageView;
//    inputDialog inputDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnExcercises = (Button)findViewById(R.id.btnExcercises);
        btnSetting = (Button)findViewById(R.id. btnSettings);
        btnpado = (Button)findViewById(R.id. btnPadometer);
        imageView = findViewById(R.id.btnTraining);
        Glide.with(this).load(R.drawable.ob4).into(imageView);
        btnExcercises.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ListExcercises.class);
                        startActivity(intent);

            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingsPage.class);
                startActivity(intent);

            }
        });

        btnpado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    private void openDialog() {
        inputDialog exampleDialog = new inputDialog();
        exampleDialog.show(getSupportFragmentManager(), "example dialog");
    }


}