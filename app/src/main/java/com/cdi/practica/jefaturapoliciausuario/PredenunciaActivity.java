package com.cdi.practica.jefaturapoliciausuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.cdi.practica.jefaturapoliciausuario.Objects.Predenuncia;
import com.cdi.practica.jefaturapoliciausuario.Objects.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PredenunciaActivity extends AppCompatActivity {

    private EditText nombre,apellidos,ubi,dni;
    private String nombreS,apellidosS,ubiS,dniS,tipoS;
    private Spinner tipo;
    private ImageButton aceptar;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference predRef;
    private FirebaseUser user;


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
        nombre = (EditText) findViewById(R.id.nombre);
        apellidos = (EditText) findViewById(R.id.apellidos);
        ubi = (EditText) findViewById(R.id.ubicacion);
        dni = (EditText) findViewById(R.id.dni);
        // Spinner
        tipo = (Spinner) findViewById(R.id.tipo);
        String[] hechos = { "Hurto", "Robo", "Robo con violencia", "Agresión",
                            "Abuso", "Fraude", "Estafa", "Homicidio", "Difamación",
                            "Injuria", "Lesiones", "Acoso", "Amenazas" };
        tipo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hechos));
        // Button
        aceptar = (ImageButton) findViewById(R.id.aceptarPredenuncia);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        predRef = database.getReference("predenuncias");
    }

    private void aceptarPredenuncia() {
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipoS = tipo.getSelectedItem().toString();
                nombreS = nombre.getText().toString();
                apellidosS = apellidos.getText().toString();
                ubiS = ubi.getText().toString();
                dniS = dni.getText().toString();
                Predenuncia p = new Predenuncia(tipoS,nombreS,apellidosS,dniS,ubiS);
                predRef.child("nueva").push().setValue(p);
                startActivity(new Intent(PredenunciaActivity.this, MainMenu.class));
                finish();
            }
        });
    }

}
