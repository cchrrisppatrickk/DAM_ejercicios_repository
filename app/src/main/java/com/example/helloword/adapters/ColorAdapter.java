package com.example.helloword.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloword.FormColorActivity;
import com.example.helloword.R;
import com.example.helloword.entities.Color;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder>{

    private List<Color> data;
    public ColorAdapter(List<Color> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ColorAdapter.ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_color, parent, false);

        return new ColorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorAdapter.ColorViewHolder holder, int position) {
        Color color = data.get(position);



        TextView tvColorName = holder.itemView.findViewById(R.id.tvColorName);
        TextView tvColorHex = holder.itemView.findViewById(R.id.tvColorHex);
        View vColorBg = holder.itemView.findViewById(R.id.vColorBg);

        tvColorName.setText(color.nombre);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Color: " + color.nombre, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), FormColorActivity.class);

                // los extras me permiten enviar información entre actividades
                intent.putExtra("colorId", color.id);
                intent.putExtra("colorName", color.nombre);
                intent.putExtra("colorHex", color.colorHex);

                v.getContext().startActivity(intent);
            }
        });

        try {
            String hex = color.colorHex;
            tvColorHex.setText(hex);
            vColorBg.setBackgroundColor(android.graphics.Color.parseColor(hex));
        } catch(Exception ex) {
            Log.d("MAIN_APP", "Usando color por defecto");
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ColorViewHolder extends  RecyclerView.ViewHolder {

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
