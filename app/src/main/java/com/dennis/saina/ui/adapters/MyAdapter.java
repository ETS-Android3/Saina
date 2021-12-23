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


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Lesson> lessonArrayList;

    public MyAdapter(Context context, ArrayList<Lesson> lessonArrayList) {
        this.context = context;
        this.lessonArrayList = lessonArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.fragment_video, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Lesson user = lessonArrayList.get(position);

        holder.Name.setText(user.getName());

        Glide.with(holder.Image.getContext()).load(user.getImage()).into(holder.Image);

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
            Name = itemView.findViewById(R.id.lessonTextView);
            Image = itemView.findViewById(R.id.lessonImage);

        }
    }
}

