package com.example.applicationpfe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class chercher_technicien extends AppCompatActivity {

    private TextView textViewLocalisation;
    private Button buttonAfficherListe;
    private Button buttonAfficherCarte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chercher_technicien);

        textViewLocalisation = findViewById(R.id.demande);
        buttonAfficherListe = findViewById(R.id.login_button);
        buttonAfficherCarte = findViewById(R.id.singup_button);

        buttonAfficherListe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherLocalisationsListe();
            }
        });

        buttonAfficherCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherLocalisationsCarte();
            }
        });
    }

    private void afficherLocalisationsListe() {
        // Ajoutez le code pour ouvrir l'activité d'affichage des localisations des techniciens sous forme de liste
        Intent intent = new Intent(this, Affichage_liste.class);
        startActivity(intent);
    }

    private void afficherLocalisationsCarte() {
        // Ajoutez le code pour ouvrir l'activité d'affichage des localisations des techniciens sur une carte
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}
