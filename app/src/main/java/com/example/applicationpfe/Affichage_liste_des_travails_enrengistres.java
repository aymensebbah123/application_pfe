package com.example.applicationpfe;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Affichage_liste_des_travails_enrengistres extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private ListView listView;
    private CustomListAdapter listAdapter;
    private List<Travail> travailList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affichage_liste_des_travails_enrengistres);

        // Référence à la base de données Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("travaux");

        // Initialisation des vues
        listView = findViewById(R.id.list_view);
        travailList = new ArrayList<>();
        listAdapter = new CustomListAdapter(this, travailList);

        // Définir l'adaptateur de liste personnalisé
        listView.setAdapter(listAdapter);

        // Récupérer les données de la base de données Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                travailList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Obtenir les données de chaque enregistrement de travail
                    Travail travail = snapshot.getValue(Travail.class);

                    // Ajouter l'enregistrement de travail à la liste
                    travailList.add(travail);
                }

                // Rafraîchir la liste
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gérer les erreurs lors de la récupération des données
            }
        });
    }
}