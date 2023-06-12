package com.example.applicationpfe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AfficherClientActivity extends AppCompatActivity {

    private EditText cinEditText;
    private Button afficherButton;
    private TableLayout tableLayout;

    private DatabaseReference clientsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_client);

        cinEditText = findViewById(R.id.edit_text_cin);
        afficherButton = findViewById(R.id.button_afficher);
        tableLayout = findViewById(R.id.table_layout);

        clientsRef = FirebaseDatabase.getInstance().getReference().child("clients");

        afficherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cin = cinEditText.getText().toString().trim();
                if (!cin.isEmpty()) {
                    rechercherClient(cin);
                } else {
                    Toast.makeText(AfficherClientActivity.this, "Veuillez entrer le CIN du client à afficher", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void rechercherClient(String cin) {
        Query query = clientsRef.orderByChild("cin").equalTo(cin);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tableLayout.removeAllViews(); // Réinitialiser le tableau avant d'afficher les résultats

                boolean clientFound = false;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Client client = dataSnapshot.getValue(Client.class);
                    if (client != null) {
                        afficherClient(client);
                        clientFound = true;
                    }
                }
                if (!clientFound) {
                    Toast.makeText(AfficherClientActivity.this, "Aucun client trouvé avec ce CIN", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AfficherClientActivity.this, "Erreur lors de la récupération des clients", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void afficherClient(Client client) {
        TableRow tableRow = new TableRow(this);

        TextView nomTextView = new TextView(this);
        nomTextView.setText(client.getNom());
        tableRow.addView(nomTextView);

        TextView prenomTextView = new TextView(this);
        prenomTextView.setText(client.getPrenom());
        tableRow.addView(prenomTextView);

        TextView cinTextView = new TextView(this);
        cinTextView .setText(client.getcin());
        tableRow.addView(cinTextView );

        tableLayout.addView(tableRow);
    }
}