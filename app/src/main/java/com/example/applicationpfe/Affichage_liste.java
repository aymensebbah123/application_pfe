package com.example.applicationpfe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Affichage_liste extends AppCompatActivity {

    private RecyclerView recyclerView;
    private technicien_adapter technicienAdapter;
    private List<Technicien> techniciensList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_liste);

        recyclerView = findViewById(R.id.recyclerView);
        techniciensList = new ArrayList<>();
        technicienAdapter = new technicien_adapter(techniciensList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(technicienAdapter);

        retrieveTechniciensFromFirebase();


    }
    private void retrieveTechniciensFromFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference techniciensRef = database.getReference("technicien");

        techniciensRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot technicienSnapshot : dataSnapshot.getChildren()) {
                    // Récupérer les données du technicien
                    String nom = technicienSnapshot.child("nom").getValue(String.class);
                    double latitude = technicienSnapshot.child("latitude").getValue(Double.class);
                    double longitude = technicienSnapshot.child("longitude").getValue(Double.class);

                    // Créer une instance de la classe Technicien avec les informations récupérées
                    Technicien technicien = new Technicien(nom, latitude, longitude);

                    // Ajouter le technicien à la liste des techniciens
                    techniciensList.add(technicien);
                }

                // Mettre à jour l'adaptateur de la RecyclerView avec les nouvelles données
                technicienAdapter.notifyDataSetChanged();
            }


        });
    }}

