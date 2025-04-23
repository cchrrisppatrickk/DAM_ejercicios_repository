package com.example.helloword.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloword.R;
import com.example.helloword.entities.Pokemon;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>{

    private List<Pokemon> data;

    public PokemonAdapter(List<Pokemon> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public PokemonAdapter.PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.PokemonViewHolder holder, int position) {
        Pokemon pokemon = data.get(position);

        // Extraemos el ID de la URL (la URL es como: https://pokeapi.co/api/v2/pokemon/1/)
        String[] urlParts = pokemon.url.split("/");
        int pokemonId = Integer.parseInt(urlParts[urlParts.length - 1]);

        // Construimos la URL de la imagen
        String imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemonId + ".png";

        holder.tvPokemonName.setText(pokemon.name);
        Picasso.get().load(imageUrl).into(holder.ivPokemonImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPokemonImage;
        TextView tvPokemonName;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPokemonImage = itemView.findViewById(R.id.ivPokemonImage);
            tvPokemonName = itemView.findViewById(R.id.tvPokemonName);
        }
    }
}
