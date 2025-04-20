package com.example.helloword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.helloword.entities.Color;
import com.example.helloword.services.ColorService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormColorActivity extends AppCompatActivity {

    ColorService service;
    Button btnSave;
    Button btnDelete;
    EditText etColorName;
    EditText etColorHex;

    int colorId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_color);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://67ff051e58f18d7209efd099.mockapi.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ColorService.class);

        setUpViews();
        setUpButtonSave();
        setUpButtonDelete();

        // los getIntent() me permiten obtener la información que se envió desde la actividad anterior
        Intent intent = getIntent();
        String colorName = intent.getStringExtra("colorName");
        String colorHex = intent.getStringExtra("colorHex");
        colorId = intent.getIntExtra("colorId", 0);

        if (colorName != null && colorHex != null) {
            etColorName.setText(colorName);
            etColorHex.setText(colorHex);
        }

        if (colorId == 0) {
            btnDelete.setVisibility(View.GONE);
        }

    }

    private void setUpViews() {
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        etColorName = findViewById(R.id.etColorName);
        etColorHex = findViewById(R.id.etColorHex);
    }

    private void setUpButtonSave() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorId == 0) {
                    save();
                } else {
                    update();
                }
            }
        });
    }

    private void setUpButtonDelete() {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colorId != 0) {
                    delete();
                }
            }
        });
    }

    private void save() {
        Color color = new Color();
        color.nombre = etColorName.getText().toString();
        color.colorHex = etColorHex.getText().toString();

        service.create(color).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Color> call, Response<Color> response) {
                if (response.isSuccessful()) {
                    // finish: Termina la actividad actual (es como hacer atrás en la pantalla)
                    finish();
                    Toast.makeText(getApplicationContext(), "Color creado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al crear color", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Color> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), "Error de red", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void update() {
        Color color = new Color();
        color.nombre = etColorName.getText().toString();
        color.colorHex = etColorHex.getText().toString();

        service.update(colorId, color).enqueue(new Callback<Color>() {
            @Override
            public void onResponse(Call<Color> call, Response<Color> response) {
                if (response.isSuccessful()) {
                    finish();
                    Toast.makeText(getApplicationContext(), "Color actualizado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al crear color", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Color> call, Throwable throwable) {

            }
        });
    }

    private void delete() {
        service.delete(colorId).enqueue(new Callback<Color>() {
            @Override
            public void onResponse(Call<Color> call, Response<Color> response) {
                if (response.isSuccessful()) {
                    finish();
                    Toast.makeText(getApplicationContext(), "Color eliminado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al eliminar color", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Color> call, Throwable throwable) {

            }
        });
    }
}