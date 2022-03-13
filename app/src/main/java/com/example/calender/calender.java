package com.example.calender;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class calender extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView dayOfMonth;
    private final calenderview.OnItemListner onItemListner;
    public calender(@NonNull View itemView, calenderview.OnItemListner onItemListner) {
        super(itemView);
        dayOfMonth= itemView.findViewById(R.id.celldaytext);
        this.onItemListner = onItemListner;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
    onItemListner.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
