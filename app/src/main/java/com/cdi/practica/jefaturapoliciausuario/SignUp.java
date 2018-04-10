package com.cdi.practica.jefaturapoliciausuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    EditText name, lastname, dni, address, phone;
    TextView addVehicle, addPropertie, numVehicles, numProperties;
    Button aceptSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
        buttons();

    }

    private void init(){
        // EditText
        name = (EditText) findViewById(R.id.name);
        lastname = (EditText) findViewById(R.id.lastname);
        dni = (EditText) findViewById(R.id.dni);
        address = (EditText) findViewById(R.id.address);
        phone = (EditText) findViewById(R.id.phone);
        // TextView
        addVehicle = (TextView) findViewById(R.id.addVehicle);
        addPropertie = (TextView) findViewById(R.id.addPropertie);
        numVehicles = (TextView) findViewById(R.id.numVehicles);
        numProperties = (TextView) findViewById(R.id.numProperties);
        // Button
        aceptSignUp = (Button) findViewById(R.id.aceptSignUp);
    }

    private void buttons(){
        aceptSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, MainActivity.class));
            }
        });
        addVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, SignUp_Vehicle.class));
            }
        });
        addPropertie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, SignUp_Propertie.class));
            }
        });
    }
}
