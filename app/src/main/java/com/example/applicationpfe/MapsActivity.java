package com.example.applicationpfe;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.applicationpfe.databinding.ActivityMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    private Marker selectedTechnicianMarker;

    private ActivityMapsBinding binding;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    double myLatitude;
    double myLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Obtient le fragment de la carte et notifie lorsque la carte est prête à être utilisée
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Affiche la localisation de l'utilisateur sur la carte
        enableMyLocation();

        // Récupère les coordonnées des techniciens depuis la base de données Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference techniciansRef = database.getReference("technicien");

        techniciansRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot technicianSnapshot : dataSnapshot.getChildren()) {
                    // Récupère les données du technicien
                    String latitude = technicianSnapshot.child("latitude").getValue(String.class);
                    String longitude = technicianSnapshot.child("longitude").getValue(String.class);

                    // Faites quelque chose avec les coordonnées de latitude et de longitude (par exemple, calculez la distance)
                    double technicianLatitude = Double.parseDouble(latitude);
                    double technicianLongitude = Double.parseDouble(longitude);

                    // Calcule la distance entre la position de l'utilisateur et la position du technicien
                    Location myLocation = new Location("");
                    myLocation.setLatitude(myLatitude);
                    myLocation.setLongitude(myLongitude);

                    Location technicianLocation = new Location("");
                    technicianLocation.setLatitude(technicianLatitude);
                    technicianLocation.setLongitude(technicianLongitude);

                    float distance = myLocation.distanceTo(technicianLocation);

                    // Utilise la distance comme vous le souhaitez (par exemple, l'affiche dans une info-bulle du marqueur)
                    String distanceText = String.format(Locale.getDefault(), "%.2f m", distance);

                    // Ajoute le marqueur du technicien sur la carte avec la distance
                    LatLng technicianLatLng = new LatLng(technicianLatitude, technicianLongitude);
                    mMap.addMarker(new MarkerOptions().position(technicianLatLng).title("Technicien").snippet(distanceText));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Gère les erreurs de lecture des données depuis la base de données
            }
        });
    }
// Méthode appelée lorsque l'utilisateur sélectionne un technicien sur la carte
    private void onTechnicianSelected(Technicien technician) {
        // Créez un intent pour démarrer l'activité de demande d'intervention
        Intent intent = new Intent(MapsActivity.this, DemandeInterventionActivity.class);
        intent.putExtra("technicien", technicien);
        StartActivity(in)
    }
    private void enableMyLocation() {
        // Vérifie et demande les autorisations de localisation
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            // Autorisations de localisation déjà accordées
            mMap.setMyLocationEnabled(true);

            // Obtient la dernière position connue de l'utilisateur
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    // Utilise les coordonnées de latitude et de longitude pour afficher la position de l'utilisateur sur la carte
                    LatLng currentLocation = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(currentLocation).title("Ma position"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                }
            });
        } else {
            // Les autorisations de localisation ne sont pas accordées, les demande à l'utilisateur
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // L'autorisation de localisation a été accordée par l'utilisateur
                // On peut maintenant accéder à la localisation
                enableMyLocation();
            } else {
                // L'autorisation de localisation a été refusée par l'utilisateur
                // Gère le cas où l'utilisateur refuse d'accorder les autorisations de localisation
            }
        }
    }
}