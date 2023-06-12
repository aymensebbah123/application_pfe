package com.example.applicationpfe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class compte_technicien extends AppCompatActivity {

    private Button buttonPro;
    private Button buttonNo;
    private Button buttonDis;
    private Button compteRendue;
    private Button déconexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte_technicien);

        buttonPro = findViewById(R.id.buttonpro);
        buttonNo = findViewById(R.id.buttonno);
        buttonDis = findViewById(R.id.buttondis);
        compteRendue = findViewById(R.id.compte_rendue);
        déconexion = findViewById(R.id.déconnexion);

        buttonPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Actions à effectuer lorsque le bouton "les informations" est cliqué
                Toast.makeText(compte_technicien.this, "Bouton 'les informations' cliqué", Toast.LENGTH_SHORT).show();
                // Ajoutez ici votre code pour l'action souhaitée
                Intent intent = new Intent(compte_technicien.this, ProfilTechnicien.class);
                startActivity(intent);
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Actions à effectuer lorsque le bouton "notifications" est cliqué
                Toast.makeText(compte_technicien.this, "Bouton 'notifications' cliqué", Toast.LENGTH_SHORT).show();
                // Ajoutez ici votre code pour l'action souhaitée
                Intent intent = new Intent(compte_technicien.this,NotificationActivity.class);
                startActivity(intent);
            }
        });

        buttonDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Actions à effectuer lorsque le bouton "enrengistrement travail" est cliqué
                Toast.makeText(compte_technicien.this, "Bouton 'enrengistrement travail' cliqué", Toast.LENGTH_SHORT).show();
                // Ajoutez ici votre code pour l'action souhaitée
                Intent intent = new Intent(compte_technicien.this,EnregistrementTravailActivity.class);
                startActivity(intent);
            }
        });

        compteRendue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Actions à effectuer lorsque le bouton "créer compte rendue" est cliqué
                Toast.makeText(compte_technicien.this, "Bouton 'créer compte rendue' cliqué", Toast.LENGTH_SHORT).show();
                // Ajoutez ici votre code pour l'action souhaitée
                Intent intent = new Intent(compte_technicien.this,CompteRenduActivity.class);
                startActivity(intent);
            }
        });

        déconexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Actions à effectuer lorsque le bouton "déconnexion" est cliqué
                Toast.makeText(compte_technicien.this, "Bouton 'déconnexion' cliqué", Toast.LENGTH_SHORT).show();
                // Ajoutez ici votre code pour l'action souhaitée
                Intent intent = new Intent(compte_technicien.this,authentification.class);
                startActivity(intent);
            }
        });
    }
}