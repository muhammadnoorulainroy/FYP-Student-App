package com.example.studentapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ChildAdapterClass extends RecyclerView.Adapter<ChildViewHolderJava> {

    ArrayList<ChildModel> data;

    public ChildAdapterClass(ArrayList<ChildModel> data) {
        this.data = data;
    }

    @NonNull
    @NotNull
    @Override
    public ChildViewHolderJava onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.singlerow, parent, false);
        return new ChildViewHolderJava(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ChildViewHolderJava holder, int position) {
        holder.tv1.setText(data.get(position).getHeader());
        holder.tv2.setText(data.get(position).getDesc());
        holder.img.setImageResource(data.get(position).getImg());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
}
