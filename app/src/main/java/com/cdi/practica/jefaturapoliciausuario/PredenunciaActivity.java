package com.cdi.practica.jefaturapoliciausuario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class PredenunciaActivity extends AppCompatActivity {

    private EditText ubi,name,lastName,dni;
    private String nameS, lastNameS, ubiS, dniS;
    private Spinner facts;
    private ImageButton aceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predenuncia);
        //Inicializar variables
        init();
        //Método aceptar una predenuncia
        aceptarPredenuncia();
    }

    private void init() {
        // EditText
        name = (EditText) findViewById(R.id.nombre);
        lastName = (EditText) findViewById(R.id.nombre);
        ubi = (EditText) findViewById(R.id.ubicacion);
        dni = (EditText) findViewById(R.id.dni);
        // Spinner
        facts = (Spinner) findViewById(R.id.fact);
        String[] hechos = { "Hurto", "Robo", "Robo con violencia", "Agresión",
                            "Abuso", "Fraude", "Estafa", "Homicidio", "Difamación",
                            "Injuria", "Lesiones", "Acoso", "Amenazas" };
        facts.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hechos));
        // Button
        aceptar = (ImageButton) findViewById(R.id.aceptarpredenuncia);
    }

    private void aceptarPredenuncia() {
        nameS = name.getText().toString();
        lastNameS = lastName.getText().toString();
        ubiS = ubi.getText().toString();
        dniS = dni.getText().toString();
    }

}
