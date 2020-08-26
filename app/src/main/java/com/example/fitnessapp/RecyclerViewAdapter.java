package com.example.fitnessapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
   public ImageView image;
   public TextView text;
    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        image = (ImageView)itemView.findViewById(R.id.exc_img);
        text = (TextView)itemView.findViewById(R.id.exc_name);
        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition());

    }
}
class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{
    private List<Excercise> excerciseList;
    private Context context;

    public RecyclerViewAdapter(List<Excercise> excerciseList, Context context) {
        this.excerciseList = excerciseList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemview = inflater.inflate(R.layout.list_excercise,parent,false);
        return new RecyclerViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.image.setImageResource(excerciseList.get(position).getImage_id());
        holder.text.setText(excerciseList.get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {

            @Override
            public void onClick(View view, int position) {
               //Toast.makeText(context,"clicked on"+excerciseList.get(position).getName(),Toast.LENGTH_SHORT);

                Intent intent = new Intent(context ,ViewExcercise.class);
                intent.putExtra("image_id",excerciseList.get(position).getImage_id());
                intent.putExtra("name",excerciseList.get(position).getName());
                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return excerciseList.size();
    }
}
