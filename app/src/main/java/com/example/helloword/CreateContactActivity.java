package com.example.helloword;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.helloword.entities.Contact;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateContactActivity extends AppCompatActivity {

    private TextInputEditText etNombre, etApellido, etCelular;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCelular = findViewById(R.id.etCelular);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> saveContact());
    }

    private void saveContact() {
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String celular = etCelular.getText().toString().trim();

        if (validateInputs(nombre, apellido, celular)) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference contactsRef = database.getReference("contacts");

            // Generar ID único PRIMERO
            String contactId = contactsRef.push().getKey(); // <--- Declarar contactId aquí

            Contact newContact = new Contact();
            newContact.setId(contactId); // Asignar el ID generado
            newContact.setNombre(nombre);
            newContact.setApellidos(apellido);
            newContact.setNumeroCelular(celular);

            if (contactId != null) {
                contactsRef.child(contactId).setValue(newContact)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(this, "Contacto guardado", Toast.LENGTH_SHORT).show();
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(this, "Error al guardar", Toast.LENGTH_SHORT).show();
                        });
            }
        }
    }

    private boolean validateInputs(String nombre, String apellido, String celular) {
        if (nombre.isEmpty()) {
            etNombre.setError("Ingrese nombre");
            return false;
        }
        if (apellido.isEmpty()) {
            etApellido.setError("Ingrese apellido");
            return false;
        }
        if (celular.isEmpty() || !Patterns.PHONE.matcher(celular).matches()) {
            etCelular.setError("Número inválido");
            return false;
        }
        return true;
    }
}