package com.example.applicationpfe;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class isncripition_utilisateur extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText signupEmail, signupPassword, signupNomUtilisateur, signupRole;
    private Button signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_isncription_utilisateur);

        auth = FirebaseAuth.getInstance();
        signupNomUtilisateur = findViewById(R.id.signup_nomuser);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupRole = findViewById(R.id.signup_role);
        signupButton = findViewById(R.id.signup_button);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = signupEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();
                String nomUtilisateur = signupNomUtilisateur.getText().toString().trim();
                String role = signupRole.getText().toString().trim();

                if (user.isEmpty()) {
                    signupEmail.setError("Email cannot be empty");
                } else if (pass.isEmpty()) {
                    signupPassword.setError("Password cannot be empty");
                } else if (nomUtilisateur.isEmpty()) {
                    signupNomUtilisateur.setError("Name cannot be empty");
                } else if (role.isEmpty()) {
                    signupRole.setError("Role cannot be empty");
                } else {
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser currentUser = auth.getCurrentUser();
                                if (currentUser != null) {
                                    String userUid = currentUser.getUid();
                                    saveUserToFirebaseDatabase(userUid, nomUtilisateur, pass, role);
                                    Toast.makeText(isncripition_utilisateur.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(isncripition_utilisateur.this, authentification.class));
                                }
                            } else {
                                Toast.makeText(isncripition_utilisateur.this, "SignUp Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void saveUserToFirebaseDatabase(String userUid, String nomUtilisateur, String motDePasse, String role) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
        user user = new user(userUid, nomUtilisateur, motDePasse, role);
        DatabaseReference roleRef = FirebaseDatabase.getInstance().getReference().child(role.toLowerCase() + "s");
        roleRef.child(userUid).setValue(user);
        usersRef.child(userUid).setValue(user);
    }
}

