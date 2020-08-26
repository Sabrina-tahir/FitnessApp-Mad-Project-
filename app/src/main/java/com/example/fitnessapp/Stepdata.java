package com.example.fitnessapp;

public class Stepdata {
    private String goal;
    private String achieved;
    private String date;

    public Stepdata(String goal, String achieved,String date) {
        this.goal = goal;
        this.achieved = achieved;
        this.date=date;
    }

    public String getGoal() {
        return goal;
    }

    public String getAchieved() {
        return achieved;
    }

    public String getDate() {
        return date;
    }
}
