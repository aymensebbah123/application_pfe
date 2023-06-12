package com.example.applicationpfe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

public class AfficherTechnicienActivity extends AppCompatActivity {

    private EditText cinEditText;
    private Button afficherButton;
    private TableLayout tableLayout;

    private DatabaseReference techniciensRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_technicien);

        cinEditText = findViewById(R.id.edit_text_cin);
        afficherButton = findViewById(R.id.button_afficher);
        tableLayout = findViewById(R.id.table_layout);

        techniciensRef = FirebaseDatabase.getInstance().getReference().child("techniciens");

        afficherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cin = cinEditText.getText().toString().trim();
                if (!cin.isEmpty()) {
                    rechercherTechnicien(cin);
                } else {
                    Toast.makeText(AfficherTechnicienActivity.this, "Veuillez entrer le CIN du technicien à afficher", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void rechercherTechnicien(String cin) {
        Query query = techniciensRef.orderByChild("cin").equalTo(cin);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tableLayout.removeAllViews(); // Réinitialiser le tableau avant d'afficher les résultats

                boolean technicienFound = false;
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Technicien technicien = dataSnapshot.getValue(Technicien.class);
                    if (technicien != null) {
                        afficherTechnicien(technicien);
                        technicienFound = true;
                    }
                }
                if (!technicienFound) {
                    Toast.makeText(AfficherTechnicienActivity.this, "Aucun technicien trouvé avec ce CIN", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AfficherTechnicienActivity.this, "Erreur lors de la récupération des techniciens", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void afficherTechnicien(Technicien technicien) {
        TableRow tableRow = new TableRow(this);

        TextView nomTextView = new TextView(this);
        nomTextView.setText(technicien.getName());
        tableRow.addView(nomTextView);

        TextView prenomTextView = new TextView(this);
        prenomTextView.setText(technicien.getPrenom());
        tableRow.addView(prenomTextView);

        TextView emailTextView = new TextView(this);
        emailTextView.setText(technicien.getCin());
        tableRow.addView(emailTextView);

        tableLayout.addView(tableRow);
    }
}