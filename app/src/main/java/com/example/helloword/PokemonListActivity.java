package com.example.helloword;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloword.adapters.PokemonAdapter;
import com.example.helloword.entities.Pokemon;
import com.example.helloword.services.PokemonService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonListActivity extends AppCompatActivity {

    private RecyclerView rvPokemons;
    private PokemonAdapter adapter;
    private List<Pokemon> pokemonList = new ArrayList<>();
    private boolean isLoading = false;
    private int currentOffset = 0;
    private final int LIMIT = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pokemon_list);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        rvPokemons = findViewById(R.id.rvPokemons);
        rvPokemons.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PokemonAdapter(pokemonList);
        rvPokemons.setAdapter(adapter);

        loadPokemons();

        // Configurar scroll infinito (similar a tu implementación anterior)
        rvPokemons.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (layoutManager != null && !isLoading) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        loadPokemons();
                    }
                }
            }
        });

    }

    private void loadPokemons() {
        isLoading = true;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokemonService service = retrofit.create(PokemonService.class);

        service.getPokemonList(currentOffset, LIMIT).enqueue(new Callback<Pokemon.PokemonResponse>() {
            @Override
            public void onResponse(Call<Pokemon.PokemonResponse> call, Response<Pokemon.PokemonResponse> response) {
                Log.d("POKEAPI", "Respuesta recibida. Éxito: " + response.isSuccessful());
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("POKEAPI", "Pokémon recibidos: " + response.body().results.size());
                    pokemonList.addAll(response.body().results);
                    adapter.notifyDataSetChanged();
                    currentOffset += LIMIT;
                } else {
                    Log.e("POKEAPI", "Respuesta no exitosa o body null");
                }
                isLoading = false;
            }

            @Override
            public void onFailure(Call<Pokemon.PokemonResponse> call, Throwable t) {
                Log.e("POKEAPI", "Error en la llamada: " + t.getMessage());
                isLoading = false;
            }
        });
    }
}