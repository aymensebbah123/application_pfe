package com.example.applicationpfe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilTechnicien extends AppCompatActivity {

    private TextView nomTextView;
    private TextView prenomTextView;
    private TextView emailTextView;
    private TextView infoTextView;
    private  TextView textViewVille ;
    private Button retourButton;
    private Button modifierButton;

    private DatabaseReference technicienRef;
    private String technicienId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_technicien);

        nomTextView = findViewById(R.id.text_view_nom);

        emailTextView = findViewById(R.id.text_view_email);
        textViewVille = findViewById(R.id.textViewVille);

        retourButton = findViewById(R.id.button_retour);
        modifierButton = findViewById(R.id.button_modifier);

        technicienRef = FirebaseDatabase.getInstance().getReference().child("technicien");

        technicienId = getIntent().getStringExtra("technicien_id");

        technicienRef.child(technicienId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String nom = snapshot.child("nom").getValue(String.class);
                    String prenom = snapshot.child("prenom").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);

                    nomTextView.setText("Nom: " + nom);
                    prenomTextView.setText("Prénom: " + prenom);
                    emailTextView.setText("Email: " + email);
                    infoTextView.setText("Informations récupérées depuis Firebase");
                } else {
                    Toast.makeText(ProfilTechnicien.this, "Technicien non trouvé dans la base de données", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfilTechnicien.this, "Erreur lors de la récupération des données du technicien", Toast.LENGTH_SHORT).show();
            }
        });

        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilTechnicien.this,compte_technicien.class);
                intent.putExtra("technicien_id", technicienId);
                startActivity(intent);

                onBackPressed();
            }
        });

        modifierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilTechnicien.this, ModifierTechnicienActivity.class);
                intent.putExtra("technicien_id", technicienId);
                startActivity(intent);
            }
        });
    }
}