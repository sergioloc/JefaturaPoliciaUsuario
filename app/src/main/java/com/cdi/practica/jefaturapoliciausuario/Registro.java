package com.cdi.practica.jefaturapoliciausuario;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.cdi.practica.jefaturapoliciausuario.Objects.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registro extends AppCompatActivity {

    private EditText nombre,apellidos,dni,telefono,nacimiento,nacionalidad,domicilio,email,pass,pass2;
    private String nombreS,apellidosS,dniS,sexoS,telefonoS,nacimientoS,nacionalidadS,domicilioS,emailS,passS,pass2S;
    private TextView addVehicle, addPropertie, numVehicles, numProperties;
    private Button buttonSignUp;
    private Spinner sexo;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference usersRef;
    private FirebaseUser user;
    private Boolean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        init();
        buttons();
    }

    /**Inicializacion de variables**/
    private void init() {
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
        addVehicle = (TextView) findViewById(R.id.addVehicle);
        addPropertie = (TextView) findViewById(R.id.addPropertie);
        numVehicles = (TextView) findViewById(R.id.numVehicles);
        numProperties = (TextView) findViewById(R.id.numProperties);
        // Button
        buttonSignUp = (Button) findViewById(R.id.aceptSignUp);
        //Boolean
        data = getIntent().getExtras().getBoolean("data");
        // Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    //sesion abierta
                }else{
                    //sesion cerrada
                }
            }
        };

        // Si se regresa de registro de vehiculo o propiedad reescribe los datos en los EditText
        if(data) getInfo();


    }

    /**Acciones de botones**/
    private void buttons() {
        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aceptButton();
            }
        });
        addVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vehicleButton();
            }
        });
        addPropertie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                propertieButton();
            }
        });
    }
    private void aceptButton() {
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

        if(!nombreS.equals("") && !apellidosS.equals("") && !dniS.equals("") && !sexoS.equals("")
                && !telefonoS.equals("") && !emailS.equals("") && !nacimientoS.equals("")
                && !nacionalidadS.equals("") && !domicilioS.equals("") && !passS.equals("")
                && !pass2S.equals("")){

            if(passS.equals(pass2S))
                if(pass.length()>=6){
                    if(!data){ // Si se viene de MainActivity
                        signUp(emailS,passS);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                addInfoDB();
                            }
                        }, 2000);
                    }
                    startActivity(new Intent(Registro.this,MainMenu.class));
                }
                else
                    Toast.makeText(getApplicationContext(),"La contraseña debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"La contraseña no coincide",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getApplicationContext(),"Debes rellenar todos los campos",Toast.LENGTH_SHORT).show();
    }
    private void vehicleButton() {
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

        if(!nombreS.equals("") && !apellidosS.equals("") && !dniS.equals("") && !sexoS.equals("")
                && !telefonoS.equals("") && !emailS.equals("") && !nacimientoS.equals("")
                && !nacionalidadS.equals("") && !domicilioS.equals("") && !passS.equals("")
                && !pass2S.equals("")){

            if(passS.equals(pass2S))
                if(pass.length()>=6){
                    if(!data){ // Si se viene de MainActivity
                        signUp(emailS,passS);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                addInfoDB();
                                Intent i = new Intent(Registro.this, Registro_Vehiculo.class);
                                i.putExtra("nombre",nombreS);
                                i.putExtra("apellidos",apellidosS);
                                i.putExtra("dni",dniS);
                                i.putExtra("sexo",sexoS);
                                i.putExtra("telefono",telefonoS);
                                i.putExtra("email",emailS);
                                i.putExtra("nacimiento",nacimientoS);
                                i.putExtra("nacionalidad",nacionalidadS);
                                i.putExtra("domicilio",domicilioS);
                                i.putExtra("pass",passS);
                                i.putExtra("numV",numVehicles.getText().toString());
                                i.putExtra("numP",numProperties.getText().toString());
                                startActivity(i);
                            }
                        }, 2000);
                    }else{ // Si se viene de Registro_Propiedad o Registro_Vehiculo
                        Intent i = new Intent(Registro.this, Registro_Vehiculo.class);
                        i.putExtra("nombre",nombreS);
                        i.putExtra("apellidos",apellidosS);
                        i.putExtra("dni",dniS);
                        i.putExtra("sexo",sexoS);
                        i.putExtra("telefono",telefonoS);
                        i.putExtra("email",emailS);
                        i.putExtra("nacimiento",nacimientoS);
                        i.putExtra("nacionalidad",nacionalidadS);
                        i.putExtra("domicilio",domicilioS);
                        i.putExtra("pass",passS);
                        i.putExtra("numV",numVehicles.getText().toString());
                        i.putExtra("numP",numProperties.getText().toString());
                        startActivity(i);
                    }


                }
                else
                    Toast.makeText(getApplicationContext(),"La contraseña debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"La contraseña no coincide",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getApplicationContext(),"Debes rellenar todos los campos",Toast.LENGTH_SHORT).show();
    }
    private void propertieButton() {
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

        if(!nombreS.equals("") && !apellidosS.equals("") && !dniS.equals("") && !sexoS.equals("")
                && !telefonoS.equals("") && !emailS.equals("") && !nacimientoS.equals("")
                && !nacionalidadS.equals("") && !domicilioS.equals("") && !passS.equals("")
                && !pass2S.equals("")){

            if(passS.equals(pass2S))
                if(pass.length()>=6){
                    if(!data){ // Si se viene de MainActivity
                        signUp(emailS,passS);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                addInfoDB();
                                Intent i = new Intent(Registro.this, Registro_Propiedad.class);
                                i.putExtra("nombre",nombreS);
                                i.putExtra("apellidos",apellidosS);
                                i.putExtra("dni",dniS);
                                i.putExtra("sexo",sexoS);
                                i.putExtra("telefono",telefonoS);
                                i.putExtra("email",emailS);
                                i.putExtra("nacimiento",nacimientoS);
                                i.putExtra("nacionalidad",nacionalidadS);
                                i.putExtra("domicilio",domicilioS);
                                i.putExtra("pass",passS);
                                i.putExtra("numV",numVehicles.getText().toString());
                                i.putExtra("numP",numProperties.getText().toString());
                                startActivity(i);
                            }
                        }, 2000);
                    }else{ // Si se viene de Registro_Propiedad o Registro_Vehiculo
                        Intent i = new Intent(Registro.this, Registro_Propiedad.class);
                        i.putExtra("nombre",nombreS);
                        i.putExtra("apellidos",apellidosS);
                        i.putExtra("dni",dniS);
                        i.putExtra("sexo",sexoS);
                        i.putExtra("telefono",telefonoS);
                        i.putExtra("email",emailS);
                        i.putExtra("nacimiento",nacimientoS);
                        i.putExtra("nacionalidad",nacionalidadS);
                        i.putExtra("domicilio",domicilioS);
                        i.putExtra("pass",passS);
                        i.putExtra("numV",numVehicles.getText().toString());
                        i.putExtra("numP",numProperties.getText().toString());
                        startActivity(i);
                    }
                }
                else
                    Toast.makeText(getApplicationContext(),"La contraseña debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"La contraseña no coincide",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getApplicationContext(),"Debes rellenar todos los campos",Toast.LENGTH_SHORT).show();
    }

    /**Registro**/
    private void signUp(String email, String pass) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //Toast.makeText(getApplicationContext(),"Registro completado",Toast.LENGTH_SHORT).show();
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
