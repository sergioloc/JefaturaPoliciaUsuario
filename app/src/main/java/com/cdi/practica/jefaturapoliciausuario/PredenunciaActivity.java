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
import android.widget.Toast;

import com.cdi.practica.jefaturapoliciausuario.Objects.Predenuncia;
import com.cdi.practica.jefaturapoliciausuario.Objects.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class PredenunciaActivity extends AppCompatActivity {

    private EditText nombre,apellidos,ubi,dni;
    private String nombreS,apellidosS,ubiS,dniS,tipoS;
    private Spinner tipo;
    private Button aceptar;
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
        tipo.setAdapter(new ArrayAdapter<String>(this, R.layout.my_spinner_item, hechos));
        // Button
        aceptar = (Button) findViewById(R.id.aceptarPredenuncia);
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
                if(tipoS.equals("")||nombreS.equals("")||apellidosS.equals("")||ubiS.equals("")||dniS.equals("")){
                    Toast.makeText(getApplicationContext(),"Debes rellenar todos los campos",Toast.LENGTH_SHORT).show();
                }else{
                    Predenuncia p = new Predenuncia(tipoS,nombreS,apellidosS,ubiS,dniS,getHora());
                    predRef.child("pendientes").push().setValue(p);
                    startActivity(new Intent(PredenunciaActivity.this, MainMenu.class));
                    Toast.makeText(getApplicationContext(),"Tu predenuncia ha sido notificada",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private String getHora(){
        long msTime = System.currentTimeMillis();
        Date curDateTime = new Date(msTime);
        String hora, minutos,resultado;
        if(curDateTime.getHours()<10)
            hora="0"+curDateTime.getHours();
        else
            hora=""+curDateTime.getHours();

        if(curDateTime.getMinutes()<10)
            minutos="0"+curDateTime.getMinutes();
        else
            minutos=""+curDateTime.getMinutes();
        resultado = hora+":"+minutos;
        return resultado;
    }

}
