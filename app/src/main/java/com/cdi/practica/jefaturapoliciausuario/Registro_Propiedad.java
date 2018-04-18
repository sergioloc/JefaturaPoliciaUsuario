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
import com.cdi.practica.jefaturapoliciausuario.Objects.Propiedad;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro_Propiedad extends AppCompatActivity {

    private Spinner type;
    private EditText address;
    private String typeS, addressS;
    private Button aceptButton;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference usersRef;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_propiedad);
        init();
        buttons();
    }

    /**Inicializacion de variables**/
    private void init(){
        // Spinner
        type = (Spinner) findViewById(R.id.tipo);
        String[] letra = {"Casa","Comercio","Finca"};
        type.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, letra));
        // EditText
        address = (EditText) findViewById(R.id.direccion);
        // Buttons
        aceptButton = (Button) findViewById(R.id.aceptarPropiedad);
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
                typeS = type.getSelectedItem().toString();
                addressS = address.getText().toString();
                if(!addressS.equals("")){
                    addPropertieDB();
                    Intent i = new Intent(Registro_Propiedad.this, Registro.class);
                    i.putExtra("data",true);
                    i.putExtra("name",getIntent().getExtras().getString("name"));
                    i.putExtra("last",getIntent().getExtras().getString("last"));
                    i.putExtra("dni",getIntent().getExtras().getString("dni"));
                    i.putExtra("phone",getIntent().getExtras().getString("phone"));
                    i.putExtra("email",getIntent().getExtras().getString("email"));
                    i.putExtra("pass",getIntent().getExtras().getString("pass"));
                    i.putExtra("numV",getIntent().getExtras().getString("numV"));
                    i.putExtra("numP",String.valueOf(Integer.parseInt(getIntent().getExtras().getString("numP"))+1));
                    startActivity(i);
                }else
                    Toast.makeText(getApplicationContext(),"Debes rellenar todos los campos",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**Meter datos en la BBDD**/
    private void addPropertieDB(){
        Propiedad p = new Propiedad(typeS,addressS);
        usersRef.child(user.getUid()).child("propiedades").child(typeS).setValue(p);
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
