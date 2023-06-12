package com.example.applicationpfe;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentReference;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import android.view.View;

public class DemandeInterventionActivity extends AppCompatActivity {

    private EditText mTitreDemandeEditText;
    private EditText mDescriptionEditText;
    private EditText mLieuEditText;
    private EditText mDateEditText;
    private Button mEnvoyerButton;
    private EditText mNomPrenomEditText;
    private EditText mEmailClientEditText;
    private Spinner mTypeDemandeSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_inntervention);

        mNomPrenomEditText = findViewById(R.id.nom_prénom);
        mTitreDemandeEditText = findViewById(R.id.titre_demande_edittex);
        mDescriptionEditText = findViewById(R.id.description_edittext);
        mLieuEditText = findViewById(R.id.lieu_edittext);
        mDateEditText = findViewById(R.id.date_edittext);
        mEnvoyerButton = findViewById(R.id.envoyer_button);
        mTypeDemandeSpinner = findViewById(R.id.type_demande_spinner);

        // Récupérer les données transmises par l'intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String technicienNom = extras.getString("technicien_nom");
            String technicienLocalisation = extras.getString("technicien_localisation");
            double technicienLatitude = extras.getDouble("technicien_latitude");
            double technicienLongitude = extras.getDouble("technicien_longitude");

            // Afficher les informations du technicien dans le formulaire
            mNomPrenomEditText.setText(technicienNom);
            mLieuEditText.setText(technicienLocalisation);
            // Les lignes suivantes sont incorrectes, vous ne pouvez pas utiliser setText() pour les doubles
            // technicienLatitude.setText(technicienLatitude);
            // technicienLongitude.setText(technicienLatitude);

            // ... autres vues et traitements
        }

        // Définir les options du spinner pour le type de demande
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_demande_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mTypeDemandeSpinner.setAdapter(adapter);

        // Ajouter un écouteur de clic pour le bouton Envoyer
        mEnvoyerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs des champs du formulaire
                String clientName = mNomPrenomEditText.getText().toString();
                String clientEmail = mEmailClientEditText.getText().toString();
                String titreDemande = mTitreDemandeEditText.getText().toString();
                String description = mDescriptionEditText.getText().toString();
                String lieu = mLieuEditText.getText().toString();
                String typeDemande = mTypeDemandeSpinner.getSelectedItem().toString();
                String date = mDateEditText.getText().toString();

// Obtenez une référence à la base de données Firebase
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                com.google.firebase.database.DatabaseReference demandeRef = database.getReference("demandes");

                // Créer un objet DemandeIntervention avec les valeurs récupérées
                DemandeIntervention demande = new DemandeIntervention(clientName, clientEmail, titreDemande, description, lieu, typeDemande, date);

                // Envoyer la demande d'intervention au technicien (non implémenté dans cet exemple)
                Toast.makeText(getApplicationContext(), "Demande envoyée au technicien", Toast.LENGTH_SHORT).show();

                // Récupérer une instance de la base de données Firestore
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                //Créez un nouvel ID unique pour la demande
                String iddemande = demandeRef.push().getKey();


                // Créer une référence à la collection "demandes" (ou votre collection spécifique)
                CollectionReference demandesRef = db.collection("demandes");
// Enregistrez la demande dans la base de données sous l'ID généré
                demandeRef.child(iddemande).setValue(demande)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // La demande a été enregistrée avec succès dans la base de données
                                // Envoyez une notification au technicien
                                envoyerNotificationAuTechnicien(iddemande);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Une erreur s'est produite lors de l'enregistrement de la demande dans la base de données
                                // Ajoutez ici le code pour afficher un message d'erreur ou effectuer une action de gestion d'erreur si nécessaire
         }
});
                private void envoyerNotificationAuTechnicien(String demandeId) {
                    // Récupérez les détails de la demande à partir de Firebase Realtime Database
                    DatabaseReference demandeRef = FirebaseDatabase.getInstance().getReference("demandes").child(demandeId);
                    demandeRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            // Récupérer les détails de la demande
                            DemandeIntervention demande = dataSnapshot.getValue(DemandeIntervention.class);

                            // Construire le message de notification
                            String titreNotification = "Nouvelle demande d'intervention";
                            String corpsNotification = "Nom du client : " + demande.getClientName() + "\n" +
                                    "Date : " + demande.getDate() + "\n" +
                                    "Description : " + demande.getDescription()+
                            "clientEmail  : " + demande.getClientEmail()+
                            "lieu  : " + demande.lieu()+
                            "Description : " + demande.getDescription();;

                            // Envoyer la notification au technicien en utilisant FCM
                            // Vous devez implémenter cette partie en utilisant les fonctionnalités spécifiques à votre plateforme et votre configuration FCM

                            // Exemple pour Android avec Firebase Cloud Messaging
                            // Utilisez le code spécifique à votre configuration FCM et votre application Android
                            FirebaseMessaging.getInstance().send(new RemoteMessage.Builder("SENDER_ID")
                                    .setMessageId(Integer.toString(0))
                                    .addData("title", titreNotification)
                                    .addData("body", corpsNotification)
                                    .addData("demandeId", demande.getiddemande() )
                                    .build());

                            // Exemple pour iOS avec Firebase Cloud Messaging
                            // Utilisez le code spécifique à votre configuration FCM et votre application iOS
                            // ...

                            // Vous devez adapter le code ci-dessus en fonction de votre configuration FCM et des spécificités de votre plateforme
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Une erreur s'est produite lors de la récupération des détails de la demande depuis la base de données
                            // Ajoutez ici le code pour afficher un message d'erreur ou effectuer une action de gestion d'erreur si nécessaire
                        }
                    });
                }
                // Utiliser la méthode add() pour générer automatiquement un nouvel identifiant unique pour la demande d'intervention
                demandesRef.add(demande)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                // La demande d'intervention a été enregistrée avec succès
                                // Récupérer l'ID de document généré pour la demande (documentReference.getId())
                                String demandeId = documentReference.getId();

                                // Ajouter les informations du formulaire en tant que champs dans le document
                                documentReference.update(
                                                "clientName", demande.getClientName(),
                                                "clientEmail", demande.getClientEmail(),
                                                "titreDemande", demande.getTitreDemande(),
                                                "description", demande.getDescription(),
                                                "lieu", demande.getLieu(),
                                                "date", demande.getDate()
                                        )
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                // Les informations du formulaire ont été ajoutées en tant que champs dans le document
                                                // Faire quelque chose, comme afficher un message de réussite
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Une erreur s'est produite lors de l'ajout des informations du formulaire dans le document
                                                // Faire quelque chose, comme afficher un message d'erreur
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Une erreur s'est produite lors de l'envoi de la demande d'intervention
                                // Faire quelque chose, comme afficher un message d'erreur
                            }
                        });
            }
        });
    }
}