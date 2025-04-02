package com.example.helloword;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int contador = 1;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });


        Button btnShowMessage = findViewById(R.id.btnShowMessage);
        tvMessage = findViewById(R.id.tvMessage);

        btnShowMessage.setOnClickListener(v -> {
            Log.i("MAIN_APP", "Click en botón!!!!");
            tvMessage.setText("Hola clase 2025");
            contador++;
            tvMessage.setText(String.valueOf(contador));
        });





    }
}