package com.example.applicationpfe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ModifierClientActivity extends AppCompatActivity {

    private EditText nomEditText;
    private EditText prenomEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText villeEditText;
    private Button modifierButton;
    private Button retourButton;


    private DatabaseReference clientsRef;
    private String clientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_profil);

        nomEditText = findViewById(R.id.edit_text_nom);

        emailEditText = findViewById(R.id.edit_text_email);
        passwordEditText = findViewById(R.id.edit_text_password);
        villeEditText = findViewById(R.id.edit_text_ville);
        modifierButton = findViewById(R.id.button_modifier);
        retourButton = findViewById(R.id.button_retour);


        // Référence à la table "client" dans la base de données Firebase
        clientsRef = FirebaseDatabase.getInstance().getReference().child("client");

        // Récupérer l'identifiant du client passé à l'activité
        clientId = getIntent().getStringExtra("client_id");

        // Écouter les changements de données pour le client spécifié
        clientsRef.child(clientId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Récupérer les données du client depuis Firebase
                    String nom = snapshot.child("nom").getValue(String.class);
                    String prenom = snapshot.child("prenom").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    String password = snapshot.child("password").getValue(String.class);
                    String ville = snapshot.child("ville").getValue(String.class);

                    // Remplir les champs EditText avec les données récupérées
                    nomEditText.setText(nom);
                    prenomEditText.setText(prenom);
                    emailEditText.setText(email);
                    passwordEditText.setText(password);
                    villeEditText.setText(ville);
                } else {
                    Toast.makeText(ModifierClientActivity.this, "Client non trouvé dans la base de données", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ModifierClientActivity.this, "Erreur lors de la récupération des données du client", Toast.LENGTH_SHORT).show();
            }
        });
        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retourner au fragment
                onBackPressed();
            }
        });


        modifierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les nouvelles valeurs des champs EditText
                String nouveauNom = nomEditText.getText().toString().trim();
                String nouveauPrenom = prenomEditText.getText().toString().trim();
                String nouvelEmail = emailEditText.getText().toString().trim();
                String nouveauPassword = passwordEditText.getText().toString().trim();
                String nouvelleVille = villeEditText.getText().toString().trim();

                // Mettre à jour les données du client dans Firebase
                clientsRef.child(clientId).child("nom").setValue(nouveauNom);
                clientsRef.child(clientId).child("prenom").setValue(nouveauPrenom);
                clientsRef.child(clientId).child("email").setValue(nouvelEmail);
                clientsRef.child(clientId).child("password").setValue(nouveauPassword);
                clientsRef.child(clientId).child("ville").setValue(nouvelleVille)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ModifierClientActivity.this, "Client modifié avec succès", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ModifierClientActivity.this, "Erreur lors de la modification du client", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}