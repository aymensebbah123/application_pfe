package com.example.applicationpfe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationpfe.ModifierClientActivity;
import com.example.applicationpfe.ModifierTechnicienActivity;
import com.example.applicationpfe.R;

public class compte_admin extends AppCompatActivity {

    private Button afficherClientButton;
    private Button afficherTechnicienButton;
    private Button supprimerClientButton;
    private Button supprimerTechnicienButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte_admin);

        afficherClientButton = findViewById(R.id.modifierclient);
        afficherTechnicienButton = findViewById(R.id.modifiertechnicien);
        supprimerClientButton = findViewById(R.id.supprimertechnicien);
        supprimerTechnicienButton = findViewById(R.id.supprimertechnicien);

        afficherClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(compte_admin.this, AfficherClientActivity.class);
                startActivity(intent);
            }
        });

        afficherTechnicienButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(compte_admin.this, AfficherTechnicienActivity.class);
                startActivity(intent);
            }
        });

        supprimerClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(compte_admin.this, SupprimerClientActivity.class);
                startActivity(intent);
            }
        });

        supprimerTechnicienButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(compte_admin.this, SupprimerTechnicienActivity.class);
                startActivity(intent);
            }
        });
    }
}