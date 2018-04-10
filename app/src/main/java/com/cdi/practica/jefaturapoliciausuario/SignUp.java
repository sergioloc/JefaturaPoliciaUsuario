package com.cdi.practica.jefaturapoliciausuario;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    private EditText name, lastname, dni, phone, email, pass, pass2;
    private TextView addVehicle, addPropertie, numVehicles, numProperties;
    private Button aceptSignUp;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
        buttons();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null){
                    //sesion abierta
                }else{
                    //sesion cerrada
                }
            }
        };

    }

    private void init(){
        // EditText
        name = (EditText) findViewById(R.id.name);
        lastname = (EditText) findViewById(R.id.lastname);
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
        aceptSignUp = (Button) findViewById(R.id.aceptSignUp);
    }

    private void buttons(){
        aceptSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameS = name.getText().toString();
                String lastS = lastname.getText().toString();
                String dniS = dni.getText().toString();
                String phoneS = phone.getText().toString();
                String emailS = email.getText().toString();
                String passS = pass.getText().toString();
                String pass2S = pass2.getText().toString();

                if(!nameS.equals("") && !lastS.equals("") && !dniS.equals("") && !phoneS.equals("") && !emailS.equals("") && !passS.equals("") && !pass2S.equals("")){
                    if(passS.equals(pass2S))
                        if(pass.length()>=6)
                            signUp(emailS,passS);
                        else
                            Toast.makeText(getApplicationContext(),"La contraseña debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(),"La contraseña no coincide",Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getApplicationContext(),"Debes rellenar todos los campos",Toast.LENGTH_SHORT).show();
            }
        });
        addVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Añadir vehiculo",Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(SignUp.this, SignUp_Vehicle.class));
            }
        });
        addPropertie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Añadir propiedad",Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(SignUp.this, SignUp_Propertie.class));
            }
        });
    }

    private void signUp(String dni, String pass){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(dni,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Registro completado",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"No se ha podido registrar",Toast.LENGTH_SHORT).show();
                }
            }
        });
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
