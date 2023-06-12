package com.example.applicationpfe;




import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

    public class ListeComptesRendusActivity extends AppCompatActivity {

        private RecyclerView recyclerView;
        private CompteRendusAdapter compteRenduAdapter;

        private DatabaseReference comptesRendusRef;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_liste_comptes_rendus);

            recyclerView = findViewById(R.id.recycler_view_comptes_rendus);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            compteRenduAdapter = new CompteRendusAdapter();
            recyclerView.setAdapter(compteRenduAdapter);

            comptesRendusRef = FirebaseDatabase.getInstance().getReference().child("comptes_rendus");

            // Récupérer les comptes rendus spécifiques au technicien (par exemple, basé sur l'ID du technicien)
            Query query = comptesRendusRef.orderByChild("technicienId").equalTo("ID_DU_TECHNICIEN");

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    List<CompteRenduclass> comptesRendus = new ArrayList<>();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        CompteRenduclass compteRendu = snapshot.getValue(CompteRenduclass.class);
                        comptesRendus.add(compteRendu);
                    }

                    compteRenduAdapter.setComptesRendus(comptesRendus);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // En cas d'erreur lors de la récupération des données
                }
            });
        }
    }