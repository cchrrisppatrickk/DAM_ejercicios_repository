package com.example.helloword.adapters;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloword.ColorItem;
import com.example.helloword.R;
import com.example.helloword.Contacto;

import java.util.List;

public class BasicAdapter extends RecyclerView.Adapter<BasicViewHolder> {

    // new BasicAdapter(data);
    private final List<String> data;

    public BasicAdapter(List<String> data){
        this.data = data;
    }


    @NonNull
    @Override
    public com.example.helloword.adapters.BasicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_basic, parent, false);

        return new BasicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.helloword.adapters.BasicViewHolder holder, int position) {
        TextView tvText = holder.itemView.findViewById(R.id.tvText);
        String text = data.get(position);
        tvText.setText(text);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


}