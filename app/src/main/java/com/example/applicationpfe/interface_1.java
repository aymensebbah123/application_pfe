
package com.example.applicationpfe;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class interface_1 extends AppCompatActivity {

    private Button OuvrirButton1;
    private Button OuvrirButton2;
    private Button OuvrirButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface1);

        OuvrirButton1 = findViewById(R.id.client_btn);
        OuvrirButton2 = findViewById(R.id.tech_btn);
        OuvrirButton3= findViewById(R.id.admin_btn);

        OuvrirButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(interface_1.this, AuthentificationActivity.class);
                startActivity(intent);
            }
        });

    }
}