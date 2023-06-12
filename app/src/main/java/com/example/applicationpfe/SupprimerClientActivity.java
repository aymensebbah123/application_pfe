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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SupprimerClientActivity extends AppCompatActivity {

    private EditText cinEditText;
    private Button supprimerButton;

    private DatabaseReference clientsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer_client);

        cinEditText = findViewById(R.id.edit_text_cin);
        supprimerButton = findViewById(R.id.button_supprimer);

        // Référence à la table "clients" dans la base de données Firebase
        clientsRef = FirebaseDatabase.getInstance().getReference().child("clients");

        supprimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cin = cinEditText.getText().toString().trim();
                if (!cin.isEmpty()) {
                    supprimerClient(cin);
                } else {
                    Toast.makeText(SupprimerClientActivity.this, "Veuillez entrer le CIN du client à supprimer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void supprimerClient(String cin) {
        clientsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean clientFound = false;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Client client = dataSnapshot.getValue(Client.class);
                    if (client != null && client.getCin().equals(cin)) {
                        clientFound = true;
                        dataSnapshot.getRef().removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(SupprimerClientActivity.this, "Client supprimé avec succès", Toast.LENGTH_SHORT).show();
                                        cinEditText.setText("");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SupprimerClientActivity.this, "Erreur lors de la suppression du client", Toast.LENGTH_SHORT).show();
                                    }
                                });
                        break;
                    }
                }
                if (!clientFound) {
                    Toast.makeText(SupprimerClientActivity.this, "Aucun client trouvé avec ce CIN", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SupprimerClientActivity.this, "Erreur lors de la récupération des clients", Toast.LENGTH_SHORT).show();
            }
        });
    }
}