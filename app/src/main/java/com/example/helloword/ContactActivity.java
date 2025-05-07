package com.example.helloword;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloword.adapters.ContactAdapter;
import com.example.helloword.entities.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private RecyclerView rvContacts;
    private FloatingActionButton fabAddContact;
    private ContactAdapter adapter;
    private List<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        rvContacts = findViewById(R.id.rvListContacts);
        fabAddContact = findViewById(R.id.btnAgregarContacto);

        // Configurar RecyclerView
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ContactAdapter(contacts, this);
        rvContacts.setAdapter(adapter);

        // Cargar datos de Firebase
        loadContacts();

        fabAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(ContactActivity.this, CreateContactActivity.class);
            startActivity(intent);
        });
    }

    private void loadContacts() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("contacts");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                contacts.clear();
                for (DataSnapshot contactSnapshot : snapshot.getChildren()) {
                    Contact contact = contactSnapshot.getValue(Contact.class);
                    if (contact != null) {
                        contacts.add(contact);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ContactActivity.this, "Error cargando contactos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}