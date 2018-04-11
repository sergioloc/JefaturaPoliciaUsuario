package com.cdi.practica.jefaturapoliciausuario;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.cdi.practica.jefaturapoliciausuario.Objects.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    private EditText name, lastName, dni, phone, email, pass, pass2;
    private String nameS, lastNameS, dniS, phoneS, emailS, passS, pass2S;
    private TextView addVehicle, addPropertie, numVehicles, numProperties;
    private Button buttonSignUp;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference usersRef;
    private FirebaseUser user;
    private Boolean data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        buttons();
    }

    /**Inicializacion de variables**/
    private void init() {
        // EditText
        name = (EditText) findViewById(R.id.name);
        lastName = (EditText) findViewById(R.id.lastName);
        dni = (EditText) findViewById(R.id.dni);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        pass2 = (EditText) findViewById(R.id.password2);
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
        nameS = name.getText().toString();
        lastNameS = lastName.getText().toString();
        dniS = dni.getText().toString();
        phoneS = phone.getText().toString();
        emailS = email.getText().toString();
        passS = pass.getText().toString();
        pass2S = pass2.getText().toString();

        if(!nameS.equals("") && !lastNameS.equals("") && !dniS.equals("") && !phoneS.equals("") && !emailS.equals("") && !passS.equals("") && !pass2S.equals("")){
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
                    startActivity(new Intent(SignUp.this,MainMenu.class));
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
        nameS = name.getText().toString();
        lastNameS = lastName.getText().toString();
        dniS = dni.getText().toString();
        phoneS = phone.getText().toString();
        emailS = email.getText().toString();
        passS = pass.getText().toString();
        pass2S = pass2.getText().toString();

        if(!nameS.equals("") && !lastNameS.equals("") && !dniS.equals("") && !phoneS.equals("") && !emailS.equals("") && !passS.equals("") && !pass2S.equals("")){
            if(passS.equals(pass2S))
                if(pass.length()>=6){
                    if(!data){ // Si se viene de MainActivity
                        signUp(emailS,passS);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                addInfoDB();
                                Intent i = new Intent(SignUp.this, SignUp_Vehicle.class);
                                i.putExtra("name",nameS);
                                i.putExtra("last",lastNameS);
                                i.putExtra("dni",dniS);
                                i.putExtra("phone",phoneS);
                                i.putExtra("email",emailS);
                                i.putExtra("pass",passS);
                                i.putExtra("numV",numVehicles.getText().toString());
                                i.putExtra("numP",numProperties.getText().toString());
                                startActivity(i);
                            }
                        }, 2000);
                    }else{ // Si se viene de SignUp_Propertie o SignUp_Vehicle
                        Intent i = new Intent(SignUp.this, SignUp_Vehicle.class);
                        i.putExtra("name",nameS);
                        i.putExtra("last",lastNameS);
                        i.putExtra("dni",dniS);
                        i.putExtra("phone",phoneS);
                        i.putExtra("email",emailS);
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
        nameS = name.getText().toString();
        lastNameS = lastName.getText().toString();
        dniS = dni.getText().toString();
        phoneS = phone.getText().toString();
        emailS = email.getText().toString();
        passS = pass.getText().toString();
        pass2S = pass2.getText().toString();

        if(!nameS.equals("") && !lastNameS.equals("") && !dniS.equals("") && !phoneS.equals("") && !emailS.equals("") && !passS.equals("") && !pass2S.equals("")){
            if(passS.equals(pass2S))
                if(pass.length()>=6){
                    if(!data){ // Si se viene de MainActivity
                        signUp(emailS,passS);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                addInfoDB();
                                Intent i = new Intent(SignUp.this, SignUp_Propertie.class);
                                i.putExtra("name",nameS);
                                i.putExtra("last",lastNameS);
                                i.putExtra("dni",dniS);
                                i.putExtra("phone",phoneS);
                                i.putExtra("email",emailS);
                                i.putExtra("pass",passS);
                                i.putExtra("numV",numVehicles.getText().toString());
                                i.putExtra("numP",numProperties.getText().toString());
                                startActivity(i);
                            }
                        }, 2000);
                    }else{ // Si se viene de SignUp_Propertie o SignUp_Vehicle
                        Intent i = new Intent(SignUp.this, SignUp_Propertie.class);
                        i.putExtra("name",nameS);
                        i.putExtra("last",lastNameS);
                        i.putExtra("dni",dniS);
                        i.putExtra("phone",phoneS);
                        i.putExtra("email",emailS);
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
        User u = new User(nameS,lastNameS,dniS,phoneS,emailS);
        usersRef.child(user.getUid()).child("info").setValue(u);
    }

    /**Recuperar info del usuario**/
    private void getInfo() {
        name.setText(getIntent().getExtras().getString("name"));
        lastName.setText(getIntent().getExtras().getString("last"));
        dni.setText(getIntent().getExtras().getString("dni"));
        phone.setText(getIntent().getExtras().getString("phone"));
        email.setText(getIntent().getExtras().getString("email"));
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
