package com.example.applicationpfe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class enrengistrementtravail extends AppCompatActivity {
    private EditText etTitle, etNomClient, etDate, etType, etLocalisation;
    private Button enregistrerButton;
    private DatabaseReference enregistrementRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrengistrementtravail);

        etTitle = findViewById(R.id.edit_title);
        etNomClient = findViewById(R.id.titre_demande_edittex);
        etDate = findViewById(R.id.edit_date);
        etType = findViewById(R.id.edit_typetravail);
        etLocalisation = findViewById(R.id.edit_location);
        enregistrerButton = findViewById(R.id.button_enregistrer);

        enregistrementRef = FirebaseDatabase.getInstance().getReference().child("enregistrements_travail");

        enregistrerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String nomClient = etNomClient.getText().toString();
                String date = etDate.getText().toString();
                String type = etType.getText().toString();
                String localisation = etLocalisation.getText().toString();

                String travailId = enregistrementRef.push().getKey();
                Travail travail = new Travail(travailId, title, nomClient, date, type, localisation);

                enregistrementRef.child(travailId).setValue(travail)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(enrengistrementtravail.this, "Enregistrement de travail enregistré avec succès", Toast.LENGTH_SHORT).show();

                                etTitle.setText("");
                                etNomClient.setText("");
                                etDate.setText("");
                                etType.setText("");
                                etLocalisation.setText("");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(enrengistrementtravail.this, "Erreur lors de l'enregistrement du travail", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}