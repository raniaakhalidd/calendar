package com.example.calender;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class calenderview extends RecyclerView.Adapter<calender>
{
    private final ArrayList<String>dayOfMonth;
     private final OnItemListner onItemListner;

    public calenderview(ArrayList<String> dayOfMonth, OnItemListner onItemListner)
    {
        this.dayOfMonth = dayOfMonth;
        this.onItemListner = onItemListner;
    }

    @NonNull
    @Override
    public calender onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.calender,parent,false);
        ViewGroup.LayoutParams layoutParams= view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() *0.166666666);
        return new calender(view, onItemListner);
    }

    @Override
    public void onBindViewHolder(@NonNull calender holder, int position) {
        holder.dayOfMonth.setText(dayOfMonth.get(position));

    }

    @Override
    public int getItemCount()
    {
        return dayOfMonth.size();
    }
    public interface OnItemListner
    {
        void onItemClick(int position, String dayText);
    }
}
