package com.example.applicationpfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profil_client extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewEmail;
    private TextView textViewVille;
    private TextView textViewTelephone;
    private Button buttonModifier;
    private Button buttonRetour;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_client);

        textViewName = findViewById(R.id.textViewName);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewVille = findViewById(R.id.textViewVille);
        textViewTelephone = findViewById(R.id.textViewTelephone);
        buttonModifier = findViewById(R.id.buttonModifier);
        buttonRetour = findViewById(R.id.buttonRetour);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();

            mDatabase.getReference("clients").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String nom = dataSnapshot.child("nom").getValue(String.class);
                        String email = dataSnapshot.child("email").getValue(String.class);
                        String ville = dataSnapshot.child("ville").getValue(String.class);
                        String telephone = dataSnapshot.child("telephone").getValue(String.class);

                        textViewName.setText(nom);
                        textViewEmail.setText(email);
                        textViewVille.setText(ville);
                        textViewTelephone.setText(telephone);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Gérer les erreurs de lecture depuis Firebase
                }
            });
        }

        buttonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ajouter le code pour ouvrir l'activité de modification des informations du client
                Intent intent = new Intent(profil_client.this, ModifierClientActivity.class);
                startActivity(intent);
            }
        });
        buttonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  retourner à l'activité compte_client
                onBackPressed();
            }
        });
    }
}