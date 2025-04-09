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

    //-
    private List<ColorItem> colorItems;

    public BasicAdapter(List<ColorItem> colorItems) {
        this.colorItems = colorItems;
    }


    @NonNull
    @Override
    public BasicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_basic, parent, false);
        return new BasicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BasicViewHolder holder, int position) {
        ColorItem colorItem = colorItems.get(position);

        TextView tvColorName = holder.itemView.findViewById(R.id.tvColorName);
        TextView tvHexCode = holder.itemView.findViewById(R.id.tvHexCode);

        tvColorName.setText(colorItem.getColorName());
        tvHexCode.setText(colorItem.getHexCode());

        // Establecer el color de fondo del elemento
        holder.itemView.setBackgroundColor(colorItem.getColorValue());

        // Cambiar el color del texto según el brillo del fondo para mejor legibilidad
        int textColor = isColorDark(colorItem.getColorValue()) ? Color.WHITE : Color.BLACK;
        tvColorName.setTextColor(textColor);
        tvHexCode.setTextColor(textColor);
    }

    @Override
    public int getItemCount() {
        return colorItems.size();
    }

    private boolean isColorDark(int color) {
        double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;
        return darkness >= 0.5;
    }
}