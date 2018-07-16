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
    private TextView numVehicles, numProperties, mensajeError;
    private FancyButton buttonSignUp;
    private Spinner sexo, tipoV, tipoP;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference usersRef;
    private FirebaseUser user;
    private FloatingActionButton addVehicle, addPropertie;
    private EditText matricula, modelo, seguro, itv, direccion;
    private Button aceptarVehiculo,cancelarVehiculo,aceptarPropiedad,cancelarPropiedad,aceptarError;
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
        // ArrayList
        listVehiculos = new ArrayList();
        listPropiedades = new ArrayList();
        avanzar=false;
        // EditText
        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        dni = findViewById(R.id.dni);
        telefono = findViewById(R.id.telefono);
        nacimiento = findViewById(R.id.nacimiento);
        nacionalidad = findViewById(R.id.nacionalidad);
        domicilio = findViewById(R.id.domicilio);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        pass2 = findViewById(R.id.password2);
        //Spinner
        sexo = findViewById(R.id.sexo);
        String[] string_sexo = {"Sexo","Masculino","Femenino"};
        sexo.setAdapter(new ArrayAdapter<String>(this, R.layout.my_spinner_item, string_sexo));
        // TextView
        numVehicles = findViewById(R.id.numVehicles);
        numProperties = findViewById(R.id.numProperties);
        // FloatingActionButton
        addVehicle = findViewById(R.id.addVehicle);
        addPropertie = findViewById(R.id.addPropertie);
        // Button
        buttonSignUp = findViewById(R.id.aceptSignUp);
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

        if(nombreS.equals("")){
            error("Debes introducir tu nombre");
            nombre.setHintTextColor(getResources().getColor(R.color.red));
        }else if(apellidosS.equals("")){
            error("Debes introducir tus apellidos");
            apellidos.setHintTextColor(getResources().getColor(R.color.red));
        }else if(dniS.equals("")){
            error("Debes introducir tu DNI");
            dni.setHintTextColor(getResources().getColor(R.color.red));
        }else if(sexoS.equals("Sexo")){
            error("Debes seleccionar un sexo");
        }else if(telefonoS.equals("")){
            error("Debes introducir tu telefono");
            telefono.setHintTextColor(getResources().getColor(R.color.red));
        }else if(domicilioS.equals("")){
            error("Debes introducir tu domicilio");
            domicilio.setHintTextColor(getResources().getColor(R.color.red));
        }else if(emailS.equals("")){
            error("Debes introducir tu email");
            email.setHintTextColor(getResources().getColor(R.color.red));
        }else if(passS.equals("") || passS.length()<6){
            error("Debes introducir una contraseña de al menos 6 caracteres");
            pass.setHintTextColor(getResources().getColor(R.color.red));
        }else if(pass2S.equals("")){
            error("Debes repetir la contraseña introduida");
            pass2.setHintTextColor(getResources().getColor(R.color.red));
        }else if(!passS.equals(pass2S)){
            error("La contraseña no coincide");
            pass2.setHintTextColor(getResources().getColor(R.color.red));
        }else{
            terminos();
        }

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
        String[] letra = {"Desplegar lista","Turismo","Moto","Camión","Furgoneta"};
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
                if(tipoV.getSelectedItem().toString().equals("Desplegar lista")){
                    toast("Debes seleccionar el tipo de vehiculo");
                }else if(matricula.getText().toString().equals("")||modelo.getText().toString().equals("")||seguro.getText().toString().equals("")||itv.getText().toString().equals("")){
                    toast("Debes rellenar todos los campos");
                    if(matricula.getText().toString().equals(""))
                        matricula.setHintTextColor(getResources().getColor(R.color.red));
                    if(modelo.getText().toString().equals(""))
                        modelo.setHintTextColor(getResources().getColor(R.color.red));
                    if(seguro.getText().toString().equals(""))
                        seguro.setHintTextColor(getResources().getColor(R.color.red));
                    if(itv.getText().toString().equals(""))
                        itv.setHintTextColor(getResources().getColor(R.color.red));
                }else{
                    listVehiculos.add(new Vehiculo(tipoV.getSelectedItem().toString(),
                            matricula.getText().toString(),modelo.getText().toString(),
                            seguro.getText().toString(),itv.getText().toString()));
                    toast("Vehiculo añadido");
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
        String[] letra = {"Desplegar lista","Casa","Comercio","Finca"};
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
                if(tipoP.getSelectedItem().toString().equals("Deplegar lista")){
                    toast("Debes seleccionar el tipo de propiedad");
                }else if(direccion.getText().toString().equals("")){
                    toast("Debes indicar una dirección");
                    direccion.setHintTextColor(getResources().getColor(R.color.red));
                }else{
                    listPropiedades.add(new Propiedad(tipoP.getSelectedItem().toString(),direccion.getText().toString()));
                    numProperties.setText(String.valueOf(Integer.parseInt(numProperties.getText().toString())+1));
                    toast("Propiedad añadida");
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
                    toast("Debes aceptar los términos y condiciones");
            }
        });

        dialog.show();
    }
    private void error(String mensaje){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_registro_error);

        aceptarError = (Button) dialog.findViewById(R.id.aceptarError);
        mensajeError = dialog.findViewById(R.id.mensaje_error);
        mensajeError.setText(mensaje);
        aceptarError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
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

    private void toast(String texto){
        Toast.makeText(getApplicationContext(),texto,Toast.LENGTH_LONG).show();
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
