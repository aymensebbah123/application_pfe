package com.example.applicationpfe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SupprimerTechnicienActivity extends AppCompatActivity {

    private EditText cinEditText;
    private Button supprimerButton;

    private DatabaseReference techniciensRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer_technicien);

        cinEditText = findViewById(R.id.edit_text_cin);
        supprimerButton = findViewById(R.id.button_supprimer);

        // Référence à la table "techniciens" dans la base de données Firebase
        techniciensRef = FirebaseDatabase.getInstance().getReference().child("technicien");

        supprimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cin = cinEditText.getText().toString().trim();
                if (!cin.isEmpty()) {
                    supprimerTechnicien(cin);
                } else {
                    Toast.makeText(SupprimerTechnicienActivity.this, "Veuillez entrer le CIN du technicien à supprimer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void supprimerTechnicien(String cin) {
        techniciensRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean technicienFound = false;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Technicien technicien = dataSnapshot.getValue(Technicien.class);
                    if (technicien != null && technicien.getCin().equals(cin)) {
                        technicienFound = true;
                        dataSnapshot.getRef().removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(SupprimerTechnicienActivity.this, "Technicien supprimé avec succès", Toast.LENGTH_SHORT).show();
                                        cinEditText.setText("");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SupprimerTechnicienActivity.this, "Erreur lors de la suppression du technicien", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        break;
                    }
                }
                if (!technicienFound) {
                    Toast.makeText(SupprimerTechnicienActivity.this, "Aucun technicien trouvé avec ce CIN", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SupprimerTechnicienActivity.this, "Erreur lors de la récupération des techniciens", Toast.LENGTH_SHORT).show();
            }
        });
    }
}