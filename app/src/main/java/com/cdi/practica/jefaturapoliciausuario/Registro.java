package com.cdi.practica.jefaturapoliciausuario;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cdi.practica.jefaturapoliciausuario.Objects.Propiedad;
import com.cdi.practica.jefaturapoliciausuario.Objects.Usuario;
import com.cdi.practica.jefaturapoliciausuario.Objects.Vehiculo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;

public class Registro extends AppCompatActivity {

    private EditText nombre,apellidos,dni,telefono,nacimiento,nacionalidad,domicilio,email,pass,pass2;
    private String nombreS,apellidosS,dniS,sexoS,telefonoS,nacimientoS,nacionalidadS,domicilioS,emailS,passS,pass2S;
    private TextView numVehicles, numProperties;
    private FancyButton buttonSignUp;
    private Spinner sexo, tipoV, tipoP;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference usersRef;
    private FirebaseUser user;
    private Boolean data;
    private FloatingActionButton addVehicle, addPropertie;
    private EditText matricula, modelo, seguro, itv, direccion;
    private Button aceptarVehiculo, cancelarVehiculo, aceptarPropiedad, cancelarPropiedad;
    private ArrayList<Vehiculo> listVehiculos;
    private ArrayList<Propiedad> listPropiedades;
    private boolean avanzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        init();
        botones();
    }

    /**Inicializacion de variables**/
    private void init() {
        listVehiculos = new ArrayList();
        listPropiedades = new ArrayList();
        avanzar=false;
        // EditText
        nombre = (EditText) findViewById(R.id.nombre);
        apellidos = (EditText) findViewById(R.id.apellidos);
        dni = (EditText) findViewById(R.id.dni);
        telefono = (EditText) findViewById(R.id.telefono);
        nacimiento = (EditText) findViewById(R.id.nacimiento);
        nacionalidad = (EditText) findViewById(R.id.nacionalidad);
        domicilio = (EditText) findViewById(R.id.domicilio);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        pass2 = (EditText) findViewById(R.id.password2);
        //Spinner
        sexo = (Spinner) findViewById(R.id.sexo);
        String[] string_sexo = { "Masculino", "Femenino"};
        sexo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, string_sexo));
        // TextView
        addVehicle = (FloatingActionButton) findViewById(R.id.addVehicle);
        addPropertie = (FloatingActionButton) findViewById(R.id.addPropertie);
        numVehicles = (TextView) findViewById(R.id.numVehicles);
        numProperties = (TextView) findViewById(R.id.numProperties);
        // Button
        buttonSignUp = (FancyButton) findViewById(R.id.aceptSignUp);
        //Boolean
        data = getIntent().getExtras().getBoolean("data");
        // Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("usuarios");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    //sesion abierta
                    //startActivity(new Intent(Registro.this,MainMenu.class));
                }else{
                    //sesion cerrada
                }
            }
        };

        // Si se regresa de registro de vehiculo o propiedad reescribe los datos en los EditText
        if(data) getInfo();


    }

    /**Acciones de botones**/
    private void botones() {
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonAceptar();
            }
        });
        addVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonVehiculo();
            }
        });
        addPropertie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                botonPropiedad();
            }
        });
    }
    private void botonAceptar() {
        // Cogemos los valores de los EditText
        nombreS = nombre.getText().toString();
        apellidosS = apellidos.getText().toString();
        dniS = dni.getText().toString();
        sexoS = sexo.getSelectedItem().toString();
        telefonoS = telefono.getText().toString();
        nacimientoS = nacimiento.getText().toString();
        nacionalidadS = nacionalidad.getText().toString();
        domicilioS = domicilio.getText().toString();
        emailS = email.getText().toString();
        passS = pass.getText().toString();
        pass2S = pass2.getText().toString();

        /*if(!nombreS.equals("") && !apellidosS.equals("") && !dniS.equals("") && !sexoS.equals("")
                && !telefonoS.equals("") && !emailS.equals("") && !nacimientoS.equals("")
                && !nacionalidadS.equals("") && !domicilioS.equals("") && !passS.equals("")
                && !pass2S.equals("")){*/

            if(passS.equals(pass2S)){
                if(pass.length()>=6){
                    terminos();
                }else
                    Toast.makeText(getApplicationContext(),"La contraseña debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();
            }else
                Toast.makeText(getApplicationContext(),"La contraseña no coincide",Toast.LENGTH_SHORT).show();
       /* }else
            Toast.makeText(getApplicationContext(),"Debes rellenar todos los campos",Toast.LENGTH_SHORT).show();*/
    }
    private void botonVehiculo(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_registro_vehiculo);

        matricula = (EditText) dialog.findViewById(R.id.matricula);
        modelo = (EditText) dialog.findViewById(R.id.modelo);
        seguro = (EditText) dialog.findViewById(R.id.seguro);
        itv = (EditText) dialog.findViewById(R.id.itv);
        aceptarVehiculo = (Button) dialog.findViewById(R.id.aceptarVehiculo);
        cancelarVehiculo = (Button) dialog.findViewById(R.id.cancelarVehiculo);
        tipoV = (Spinner) dialog.findViewById(R.id.tipo);
        String[] letra = {"Turismo","Moto","Camión","Furgoneta"};
        tipoV.setAdapter(new ArrayAdapter<String>(this, R.layout.my_spinner_item, letra));

        cancelarVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        aceptarVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(matricula.getText().toString().equals("")||modelo.getText().toString().equals("")||seguro.getText().toString().equals("")||itv.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    listVehiculos.add(new Vehiculo(tipoV.getSelectedItem().toString(),
                            matricula.getText().toString(),modelo.getText().toString(),
                            seguro.getText().toString(),itv.getText().toString()));
                    Toast.makeText(getApplicationContext(), "Vehiculo añadido", Toast.LENGTH_SHORT).show();
                    numVehicles.setText(String.valueOf(Integer.parseInt(numVehicles.getText().toString())+1));
                    dialog.dismiss();
                }

            }
        });

        dialog.show();
    }
    private void botonPropiedad(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_registro_propiedad);

        direccion = (EditText) dialog.findViewById(R.id.direccion);
        aceptarPropiedad = (Button) dialog.findViewById(R.id.aceptarPropiedad);
        cancelarPropiedad = (Button) dialog.findViewById(R.id.cancelarPropiedad);
        tipoP = (Spinner) dialog.findViewById(R.id.tipo);
        String[] letra = {"Casa","Comercio","Finca"};
        tipoP.setAdapter(new ArrayAdapter<String>(this, R.layout.my_spinner_item, letra));

        cancelarPropiedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        aceptarPropiedad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(direccion.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
                }else{
                    listPropiedades.add(new Propiedad(tipoP.getSelectedItem().toString(),direccion.getText().toString()));
                    numProperties.setText(String.valueOf(Integer.parseInt(numProperties.getText().toString())+1));
                    Toast.makeText(getApplicationContext(), "Propiedad añadida", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        dialog.show();
    }
    private void terminos(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_terminos);
        final CheckBox acuerdo = (CheckBox) dialog.findViewById(R.id.acuerdo);
        Button botonAceptar = (Button) dialog.findViewById(R.id.botonTerminos);
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(acuerdo.isChecked()){
                    dialog.dismiss();
                    signUp(emailS,passS);
                }
                else
                    Toast.makeText(getApplicationContext(),"Debes aceptar los terminos y condiciones",Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    /**Registro**/
    private void signUp(String email, String pass) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Registro completado",Toast.LENGTH_SHORT).show();
                    addInfoDB();
                    startActivity(new Intent(Registro.this,MainMenu.class));
                }else{
                    Toast.makeText(getApplicationContext(),"No se ha podido registrar",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**Meter datos en la BBDD**/
    private void addInfoDB() {
        Toast.makeText(getApplicationContext(),"Añadir a BBDD",Toast.LENGTH_SHORT).show();
        Usuario u = new Usuario(nombreS,apellidosS,dniS,sexoS,telefonoS,emailS,nacimientoS,nacionalidadS,domicilioS);
        usersRef.child(user.getUid()).child("info").setValue(u);

        //Vehiculos
        for(int i=0;i<listVehiculos.size();i++){
            usersRef.child(user.getUid()).child("vehiculos").child(listVehiculos.get(i).getMatricula()).setValue(listVehiculos.get(i));
        }
        //Propiedades
        for(int i=0;i<listPropiedades.size();i++){
            usersRef.child(user.getUid()).child("propiedades").child(listPropiedades.get(i).getDireccion()).setValue(listPropiedades.get(i));
        }
    }

    /**Recuperar info del usuario**/
    private void getInfo() {
        nombre.setText(getIntent().getExtras().getString("name"));
        apellidos.setText(getIntent().getExtras().getString("last"));
        dni.setText(getIntent().getExtras().getString("dni"));
        if(sexoS.equals("Masculino"))
            sexo.setSelection(1);
        else
            sexo.setSelection(2);
        telefono.setText(getIntent().getExtras().getString("phone"));
        email.setText(getIntent().getExtras().getString("email"));
        nacimiento.setText(getIntent().getExtras().getString("nacimiento"));
        nacionalidad.setText(getIntent().getExtras().getString("nacionalidad"));
        domicilio.setText(getIntent().getExtras().getString("domicilio"));
        pass.setText(getIntent().getExtras().getString("pass"));
        pass2.setText(getIntent().getExtras().getString("pass"));
        numVehicles.setText(getIntent().getExtras().getString("numV"));
        numProperties.setText(getIntent().getExtras().getString("numP"));
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
