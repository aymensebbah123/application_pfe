package com.example.applicationpfe;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applicationpfe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class authentification extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private Button loginButton;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification_utilisateur);

        loginEmail = findViewById(R.id.nom_utilisateur_edit_text);
        loginPassword = findViewById(R.id.mot_de_passe_edit_text);
        loginButton = findViewById(R.id.login_button);

        auth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString();
                String password = loginPassword.getText().toString();

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(authentification.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(email, password);
                }
            }
        });
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            if (user != null) {
                                String userId = user.getUid();
                                // Obtenir le rôle de l'utilisateur
                                getUserRole(userId, email, password);
                            }
                        } else {
                            Toast.makeText(authentification.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void getUserRole(String userId, final String email, final String password) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String role = snapshot.child("role").getValue(String.class);
                    if (role != null) {
                        if (role.equals("client")) {
                            String storedEmail = snapshot.child("email").getValue(String.class);
                            String storedPassword = snapshot.child("password").getValue(String.class);
                            if (storedEmail != null && storedPassword != null && storedEmail.equals(email) && storedPassword.equals(password)) {
                                startActivity(new Intent(authentification.this, CompteClientActivity.class));
                                finish();
                            } else {
                                Toast.makeText(authentification.this, "Invalid client credentials", Toast.LENGTH_SHORT).show();
                            }
                        } else if (role.equals("technicien")) {
                            String storedEmail = snapshot.child("email").getValue(String.class);
                            String storedPassword = snapshot.child("password").getValue(String.class);
                            if (storedEmail != null && storedPassword != null && storedEmail.equals(email) && storedPassword.equals(password)) {
                                startActivity(new Intent(authentification.this, compte_technicien.class));
                                finish();
                            } else {
                                Toast.makeText(authentification.this, "Invalid technician credentials", Toast.LENGTH_SHORT).show();
                            }
                        } else if (role.equals("administrateur")) {
                            String storedEmail = snapshot.child("email").getValue(String.class);
                            String storedPassword = snapshot.child("password").getValue(String.class);
                            if (storedEmail != null && storedPassword != null && storedEmail.equals(email) && storedPassword.equals(password)) {
                                startActivity(new Intent(authentification.this,compte_admin.class));
                                finish();
                            } else {
                                Toast.makeText(authentification.this, "Invalid administrator credentials", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(authentification.this, "User role not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(authentification.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(authentification.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}