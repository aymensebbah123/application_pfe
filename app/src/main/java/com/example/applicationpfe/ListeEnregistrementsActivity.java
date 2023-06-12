package com.example.applicationpfe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListeEnregistrementsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EnregistrementTravailAdapter adapter;
    private DatabaseReference enregistrementRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_enregistrements);

        recyclerView = findViewById(R.id.recyclerview_enregistrements);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        enregistrementRef = FirebaseDatabase.getInstance().getReference().child("enregistrements_travail");

        enregistrementRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Travail> enregistrements = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Travail travail = snapshot.getValue(Travail.class);
                    enregistrements.add(travail);
                }

                adapter = new EnregistrementTravailAdapter(enregistrements);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListeEnregistrementsActivity.this, "Erreur lors de la récupération des données", Toast.LENGTH_SHORT).show();
            }
        });
    }
}