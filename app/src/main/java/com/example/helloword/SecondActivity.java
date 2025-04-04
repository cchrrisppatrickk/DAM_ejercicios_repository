package com.example.helloword;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloword.adapters.BasicAdapter;
import com.example.helloword.Contacto;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

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

        List<Contacto> contactos = new ArrayList<>();
        contactos.add(new Contacto("Juan Pérez", "555-1234567"));
        contactos.add(new Contacto("María García", "555-7654321"));
        contactos.add(new Contacto("Carlos López", "555-9876543"));
        contactos.add(new Contacto("Ana Martínez", "555-4567890"));

        RecyclerView rvBasic = findViewById(R.id.rvBasic);
        BasicAdapter adapter = new BasicAdapter(contactos);
        rvBasic.setAdapter(adapter);
    }
}