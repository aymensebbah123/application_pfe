package com.example.applicationpfe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModifierTechnicienActivity extends AppCompatActivity {

    private EditText nomEditText;
    private EditText prenomEditText;
    private EditText emailEditText;
    private EditText villeEditText;
    private Button enregistrerButton;
    private Button retourButton;

    private DatabaseReference technicienRef;
    private String technicienId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_technicien);

        nomEditText = findViewById(R.id.edit_text_nom);

        emailEditText = findViewById(R.id.edit_text_email);
        villeEditText = findViewById(R.id.edit_text_ville);
        enregistrerButton = findViewById(R.id.button_enregistrer);
        retourButton = findViewById(R.id.button_retour);

        technicienRef = FirebaseDatabase.getInstance().getReference().child("technicien");

        technicienId = getIntent().getStringExtra("technicien_id");

        enregistrerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les nouvelles valeurs des champs EditText
                String nouveauNom = nomEditText.getText().toString().trim();
                String nouveauPrenom = prenomEditText.getText().toString().trim();
                String nouvelEmail = emailEditText.getText().toString().trim();
                String nouvelleVille = villeEditText.getText().toString().trim();

                // Mettre à jour les données du technicien dans Firebase
                technicienRef.child(technicienId).child("nom").setValue(nouveauNom);
                technicienRef.child(technicienId).child("prenom").setValue(nouveauPrenom);
                technicienRef.child(technicienId).child("email").setValue(nouvelEmail);
                technicienRef.child(technicienId).child("ville").setValue(nouvelleVille)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ModifierTechnicienActivity.this, "Informations mises à jour avec succès", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ModifierTechnicienActivity.this, "Erreur lors de la mise à jour des informations", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        retourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}