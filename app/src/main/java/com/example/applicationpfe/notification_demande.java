package com.example.applicationpfe;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseError;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.recyclerview.widget.RecyclerView;


public class notification_demande extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private NotificationAdapter mAdapter;
    private DatabaseReference notificationRef;
    private ValueEventListener notificationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_demande);

        // Référence à la base de données Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // ID du technicien connecté (peut être récupéré à partir de l'authentification)
        String technicienId = "technicien123"; // Remplacez par l'ID du technicien réel

        // Référence à l'emplacement des notifications des clients pour le technicien spécifique
        notificationRef = database.getReference("techniciens").child(technicienId).child("notifications");

        // RecyclerView pour afficher la liste des notifications des clients
        mRecyclerView = findViewById(R.id.notification_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new NotificationAdapter();
        mRecyclerView.setAdapter(mAdapter);

        // Ajouter un ValueEventListener pour récupérer les notifications des clients
        notificationListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                // Parcourir les snapshots des notifications et ajouter à l'adaptateur
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Obtenir les données de notification à partir du snapshot
                    NotificationClient notification = snapshot.getValue(NotificationClient.class);

                    // Ajouter la notification à l'adaptateur
                    mAdapter.addNotification(notification);
                }

                // Actualiser l'interface utilisateur pour afficher les nouvelles notifications
                mAdapter.notifyDataSetChanged();
            }
            // Mettre à jour l'adaptateur du RecyclerView




        @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Gérer les erreurs lors de la récupération des notifications
                Toast.makeText(notification_demande.this, "Erreur : " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        // Ajouter le ValueEventListener à la référence des notifications
        notificationRef.addValueEventListener(notificationListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Retirer le ValueEventListener lorsque l'activité est détruite
        if (notificationListener != null) {
            notificationRef.removeEventListener(notificationListener);
        }
    }
}