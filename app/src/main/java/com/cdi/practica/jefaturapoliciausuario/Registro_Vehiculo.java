package com.cdi.practica.jefaturapoliciausuario;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.cdi.practica.jefaturapoliciausuario.Objects.Vehiculo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro_Vehiculo extends AppCompatActivity {

    private EditText matricula, modelo, seguro, itv;
    private String tipoS, matriculaS, modeloS, seguroS, itvS;
    private Button aceptButton;
    private Spinner tipo;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference usersRef;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_vehiculo);
        init();
        buttons();
    }

    /**Inicializacion de variables**/
    private void init(){
        // EditText
        matricula = (EditText) findViewById(R.id.matricula);
        modelo = (EditText) findViewById(R.id.modelo);
        seguro = (EditText) findViewById(R.id.seguro);
        itv = (EditText) findViewById(R.id.itv);
        // Button
        aceptButton = (Button) findViewById(R.id.aceptarVehiculo);
        // Spinner
        tipo = (Spinner) findViewById(R.id.tipo);
        String[] letra = {"Turismo","Moto","Camión","Furgoneta"};
        tipo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letra));
        // Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("usuarios");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    //iniciado sesion
                }else{
                    //sesion cerrada
                }
            }
        };
    }

    /**Acciones de botones**/
    private void buttons(){
        aceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tipoS = tipo.getSelectedItem().toString();
                matriculaS = matricula.getText().toString();
                modeloS = modelo.getText().toString();
                seguroS = seguro.getText().toString();
                itvS = itv.getText().toString();

                if(!matriculaS.equals("") && !modeloS.equals("") && !seguroS.equals("") && !itvS.equals("")){
                    addVehicleDB();
                    Intent i = new Intent(Registro_Vehiculo.this, Registro.class);
                    i.putExtra("data",true);
                    i.putExtra("name",getIntent().getExtras().getString("name"));
                    i.putExtra("last",getIntent().getExtras().getString("last"));
                    i.putExtra("dni",getIntent().getExtras().getString("dni"));
                    i.putExtra("phone",getIntent().getExtras().getString("phone"));
                    i.putExtra("email",getIntent().getExtras().getString("email"));
                    i.putExtra("pass",getIntent().getExtras().getString("pass"));
                    i.putExtra("numV",String.valueOf(Integer.parseInt(getIntent().getExtras().getString("numV"))+1));
                    i.putExtra("numP",getIntent().getExtras().getString("numP"));

                    startActivity(i);
                }else
                    Toast.makeText(getApplicationContext(),"Debes rellenar todos los campos",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**Meter datos en la BBDD**/
    private void addVehicleDB(){
        Vehiculo v = new Vehiculo(tipoS,matriculaS,modeloS,seguroS,itvS);
        usersRef.child(user.getUid()).child("vehiculos").child(matriculaS).setValue(v);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }
}
