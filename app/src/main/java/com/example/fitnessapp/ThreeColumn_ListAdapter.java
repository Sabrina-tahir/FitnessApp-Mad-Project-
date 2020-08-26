package com.example.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ThreeColumn_ListAdapter extends ArrayAdapter<Stepdata> {

    private LayoutInflater mInflater;
    private ArrayList<Stepdata> data;
    private int mViewResourceId;

    public ThreeColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<Stepdata> data) {
        super(context, textViewResourceId, data);
        this.data = data;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Stepdata user = data.get(position);

        if (user != null) {
            TextView DATE = (TextView) convertView.findViewById(R.id.date);
            TextView goal = (TextView) convertView.findViewById(R.id.goal);
            TextView actualsteps = (TextView) convertView.findViewById(R.id.step);

            if (goal!= null) {
                goal.setText(user.getGoal());
            }
            if (DATE != null) {
                DATE.setText((user.getDate()));
            }
            if (actualsteps != null) {
                actualsteps.setText((user.getAchieved()));
            }

        }

        return convertView;
    }}