package com.example.applicationpfe;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.applicationpfe.databinding.CompteClientBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class CompteClientActivity extends AppCompatActivity implements LocationListener {

    private Button buttonPro;
    private Button buttonChercher;
    private Button buttonDemande;
    private Button buttonDeconnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte_client);

        buttonPro = findViewById(R.id.buttonproclient);
        buttonChercher = findViewById(R.id.button_cherche);
        buttonDemande = findViewById(R.id.demande_intervention);
        buttonDeconnexion = findViewById(R.id.deconnexioncl);

        buttonPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Actions à effectuer lorsque le bouton "les informations" est cliqué
                Toast.makeText(CompteClientActivity.this, "Bouton 'les informations' cliqué", Toast.LENGTH_SHORT).show();
                // Ajoutez ici votre code pour l'action souhaitée
                Intent intent = new Intent(CompteClientActivity.this, profil_client.class);
                startActivity(intent);
            }
        });

        buttonChercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Actions à effectuer lorsque le bouton "notifications" est cliqué
                Toast.makeText(CompteClientActivity.this, "Bouton 'notifications' cliqué", Toast.LENGTH_SHORT).show();
                // Ajoutez ici votre code pour l'action souhaitée
                Intent intent = new Intent(CompteClientActivity.this, chercher_technicien.class);
                startActivity(intent);
            }
        });

        buttonDemande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Actions à effectuer lorsque le bouton "enrengistrement travail" est cliqué
                Toast.makeText(CompteClientActivity.this, "Bouton 'enrengistrement travail' cliqué", Toast.LENGTH_SHORT).show();
                // Ajoutez ici votre code pour l'action souhaitée
                Intent intent = new Intent(CompteClientActivity.this, DemandeInterventionActivity.class);
                startActivity(intent);
            }
        });

        buttonDeconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Actions à effectuer lorsque le bouton "déconnexion" est cliqué
                Toast.makeText(CompteClientActivity.this, "Bouton 'déconnexion' cliqué", Toast.LENGTH_SHORT).show();
                // Ajoutez ici votre code pour l'action souhaitée
                Intent intent = new Intent(CompteClientActivity.this, Déconexion.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        // Méthode appelée lorsque la position change
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // Méthode appelée lorsque le statut du fournisseur de localisation change
    }

    @Override
    public void onProviderEnabled(String provider) {
        // Méthode appelée lorsque le fournisseur de localisation est activé
    }

    @Override
    public void onProviderDisabled(String provider) {
        // Méthode appelée lorsque le fournisseur de localisation est désactivé
    }
}
