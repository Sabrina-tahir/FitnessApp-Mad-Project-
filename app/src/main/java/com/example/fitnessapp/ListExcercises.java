package com.example.fitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ListExcercises extends AppCompatActivity {
    List<Excercise> excerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_excercises);
        poses();
        recyclerView =(RecyclerView)findViewById(R.id.list_ex);
        adapter=new RecyclerViewAdapter(excerciseList,getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void poses() {
        excerciseList.add(new Excercise(R.drawable.ex1,"Sukhasna Pose"));
        excerciseList.add(new Excercise(R.drawable.ex2,"Downward Pose"));
        excerciseList.add(new Excercise(R.drawable.ex3,"Tree Pose"));
        excerciseList.add(new Excercise(R.drawable.ex4,"Trikonasana Pose"));
        excerciseList.add(new Excercise(R.drawable.ex5,"Some Pose"));
        excerciseList.add(new Excercise(R.drawable.ex6,"Some Pose"));
        excerciseList.add(new Excercise(R.drawable.ex7,"Some Pose"));
        excerciseList.add(new Excercise(R.drawable.ex8,"Cobra Pose"));
        excerciseList.add(new Excercise(R.drawable.ex9,"Warrior Pose"));
    }
}