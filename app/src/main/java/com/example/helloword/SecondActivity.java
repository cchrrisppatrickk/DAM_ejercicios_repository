package com.example.helloword;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloword.adapters.BasicAdapter;
import com.example.helloword.Contacto;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    //-
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<ColorItem> colorItems = new ArrayList<>();
        // Añadir 20 colores diferentes
        colorItems.add(new ColorItem("Rojo", "#FF0000", Color.RED));
        colorItems.add(new ColorItem("Verde", "#00FF00", Color.GREEN));
        colorItems.add(new ColorItem("Azul", "#0000FF", Color.BLUE));
        colorItems.add(new ColorItem("Amarillo", "#FFFF00", Color.YELLOW));
        colorItems.add(new ColorItem("Cian", "#00FFFF", Color.CYAN));
        colorItems.add(new ColorItem("Magenta", "#FF00FF", Color.MAGENTA));
        colorItems.add(new ColorItem("Negro", "#000000", Color.BLACK));
        colorItems.add(new ColorItem("Blanco", "#FFFFFF", Color.WHITE));
        colorItems.add(new ColorItem("Gris", "#808080", Color.GRAY));
        colorItems.add(new ColorItem("Gris claro", "#C0C0C0", Color.LTGRAY));
        colorItems.add(new ColorItem("Rojo oscuro", "#8B0000", Color.parseColor("#8B0000")));
        colorItems.add(new ColorItem("Verde oscuro", "#006400", Color.parseColor("#006400")));
        colorItems.add(new ColorItem("Azul oscuro", "#00008B", Color.parseColor("#00008B")));
        colorItems.add(new ColorItem("Naranja", "#FFA500", Color.parseColor("#FFA500")));
        colorItems.add(new ColorItem("Rosa", "#FFC0CB", Color.parseColor("#FFC0CB")));
        colorItems.add(new ColorItem("Morado", "#800080", Color.parseColor("#800080")));
        colorItems.add(new ColorItem("Turquesa", "#40E0D0", Color.parseColor("#40E0D0")));
        colorItems.add(new ColorItem("Oro", "#FFD700", Color.parseColor("#FFD700")));
        colorItems.add(new ColorItem("Plateado", "#C0C0C0", Color.parseColor("#C0C0C0")));
        colorItems.add(new ColorItem("Marron", "#A52A2A", Color.parseColor("#A52A2A")));

        RecyclerView rvBasic = findViewById(R.id.rvBasic);
        BasicAdapter adapter = new BasicAdapter(colorItems);
        rvBasic.setAdapter(adapter);

        rvBasic.setLayoutManager(new LinearLayoutManager(this));
    }
}