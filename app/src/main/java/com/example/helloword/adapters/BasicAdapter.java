package com.example.helloword.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloword.R;
import com.example.helloword.Contacto;
import com.example.helloword.adapters.BasicViewHolder;

import java.util.List;

public class BasicAdapter extends RecyclerView.Adapter<BasicViewHolder> {

    private List<Contacto> contactos;

    public BasicAdapter(List<Contacto> contactos) {
        this.contactos = contactos;
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
        Contacto contacto = contactos.get(position);

        TextView tvNombre = holder.itemView.findViewById(R.id.tvNombre);
        TextView tvTelefono = holder.itemView.findViewById(R.id.tvTelefono);

        tvNombre.setText(contacto.getNombre());
        tvTelefono.setText(contacto.getTelefono());
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }
}