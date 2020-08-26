package com.example.fitnessapp;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
public class stepsDone extends AppCompatDialogFragment {
    public Padomet data;
    public StepsRecord rec;
    private static final String TAG = "stepsDone";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_steps_done, null);

        builder.setView(view)

                .setNegativeButton("Do more", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("Add to the List", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       float target =data.get();
                       int actualsteps = data.getc();
                        Log.d(TAG, +target+"   "+actualsteps);
                        AddData(target,actualsteps);


                    }
                });

        ImageView imageView = view.findViewById(R.id.imageView);
        Glide.with(this).load(R.drawable.tick2).into(imageView);
        return builder.create();
    }
    public void AddData(float Settarget,int StepsTaken) {
//
        rec= new StepsRecord(this.getContext());
        boolean insertData=rec.addData(Settarget, StepsTaken);

        if (insertData) {
            Toast.makeText(getActivity(),"Successfully Inserted",Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getActivity(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
        }
    }

}