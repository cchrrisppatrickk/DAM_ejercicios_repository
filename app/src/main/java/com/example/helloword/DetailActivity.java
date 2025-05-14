package com.example.helloword;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.helloword.models.PokemonDetail;
import com.example.helloword.services.PokeApiService;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {


    // firebase pokedex:
    private TextInputEditText etLatitude, etLongitude;
    private Button btnRegisterLocation;
    private DatabaseReference databaseReference;
    private String pokemonId;

    ///
    private ImageView imageView;
    private TextView nameView, typesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Configuración inicial de Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("pokedex");


        // Obtener ID del Pokémon de la URL
        String url = getIntent().getStringExtra("url");
        String[] urlParts = url.split("/");
        pokemonId = urlParts[urlParts.length - 1];

        // Inicializar vistas del formulario
        etLatitude = findViewById(R.id.etLatitude);
        etLongitude = findViewById(R.id.etLongitude);
        btnRegisterLocation = findViewById(R.id.btnRegisterLocation);

        btnRegisterLocation.setOnClickListener(v -> saveLocationToFirebase());

        Button btnViewMap = findViewById(R.id.btnViewMap);
        btnViewMap.setOnClickListener(v -> {
            Intent mapIntent = new Intent(DetailActivity.this, MapsActivity.class);
            mapIntent.putExtra("POKEMON_ID", pokemonId);
            startActivity(mapIntent);
        });


        /// End Pokedex

        imageView = findViewById(R.id.imageView);
        nameView = findViewById(R.id.nameView);
        typesView = findViewById(R.id.typesView);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PokeApiService api = retrofit.create(PokeApiService.class);

        api.getPokemonDetail(url).enqueue(new Callback<PokemonDetail>() {
            @Override
            public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PokemonDetail p = response.body();
                    nameView.setText(capitalize(p.getName()));
                    Picasso.get().load(p.getSprites().front_default).into(imageView);

                    StringBuilder types = new StringBuilder("Tipo: ");
                    for (PokemonDetail.TypeSlot t : p.getTypes()) {
                        types.append(capitalize(t.type.name)).append(" ");
                    }
                    typesView.setText(types.toString().trim());
                }
            }

            @Override
            public void onFailure(Call<PokemonDetail> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Error al cargar detalles", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    private void saveLocationToFirebase() {
        String strLatitude = etLatitude.getText().toString().trim();
        String strLongitude = etLongitude.getText().toString().trim();

        if (strLatitude.isEmpty() || strLongitude.isEmpty()) {
            Toast.makeText(this, "Ingresa ambas coordenadas", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double latitude = Double.parseDouble(strLatitude);
            double longitude = Double.parseDouble(strLongitude);

            Map<String, Object> locationData = new HashMap<>();
            locationData.put("latitude", latitude);
            locationData.put("longitude", longitude);

            // Generar nueva clave única para cada ubicación
            databaseReference.child(pokemonId).push()
                    .setValue(locationData)
                    .addOnSuccessListener(aVoid -> Toast.makeText(DetailActivity.this,
                            "Ubicación guardada!", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Toast.makeText(DetailActivity.this,
                            "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Formato inválido", Toast.LENGTH_SHORT).show();
        }
    }



}