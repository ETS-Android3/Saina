package com.dennis.saina.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dennis.saina.R;
import com.dennis.saina.ui.Lesson;

import java.util.ArrayList;
import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.MyViewHolder> {

    Context context;
    List<Lesson> lessonArrayList;

    public ExercisesAdapter(Context context, List<Lesson> lessonArrayList) {
        this.context = context;
        this.lessonArrayList = lessonArrayList;
    }

    @NonNull
    @Override
    public ExercisesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.fragment_exercise, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesAdapter.MyViewHolder holder, int position) {
        Lesson lesson = lessonArrayList.get(position);

        holder.Name.setText(lesson.getName());


        Glide.with(holder.Image.getContext()).load(lesson.getImage()).into(holder.Image);

    }

    @Override
    public int getItemCount() {
        return lessonArrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Name;
        ImageView Image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.Name);
            Image = itemView.findViewById(R.id.Image);

        }
    }
}


