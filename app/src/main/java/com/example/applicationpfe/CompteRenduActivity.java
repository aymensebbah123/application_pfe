package com.example.applicationpfe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CompteRenduActivity extends AppCompatActivity {

    private EditText titreEditText;
    private EditText descriptionEditText;
    private Button enregistrerButton;
    private Button listeComptesRendusButton;

    private DatabaseReference comptesRendusRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compterenduedetechnicien);


        titreEditText = findViewById(R.id.edit_text_titre);
        descriptionEditText = findViewById(R.id.titre_demande_edittex);
        enregistrerButton = findViewById(R.id.button_enregistrer);
        listeComptesRendusButton = findViewById(R.id.button_acceder);

        comptesRendusRef = FirebaseDatabase.getInstance().getReference().child("comptes_rendus");

        enregistrerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enregistrerCompteRendu();
            }
        });

        listeComptesRendusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompteRenduActivity.this, ListeComptesRendusActivity.class);
                startActivity(intent);
            }
        });
    }

    private void enregistrerCompteRendu() {
        String titre = titreEditText.getText().toString();
        String description = descriptionEditText.getText().toString();

        // Créer un nouvel objet CompteRendu avec les données saisies
        CompteRenduclass compteRendu = new CompteRenduclass(titre, description);

        // Générer une clé unique pour le compte rendu
        String compteRenduId = comptesRendusRef.push().getKey();

        // Enregistrer le compte rendu dans la base de données Firebase
        comptesRendusRef.child(compteRenduId).setValue(compteRendu)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CompteRenduActivity.this, "Compte rendu enregistré avec succès", Toast.LENGTH_SHORT).show();

                        // Effacer les champs de saisie
                        titreEditText.setText("");
                        descriptionEditText.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CompteRenduActivity.this, "Erreur lors de l'enregistrement du compte rendu", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}