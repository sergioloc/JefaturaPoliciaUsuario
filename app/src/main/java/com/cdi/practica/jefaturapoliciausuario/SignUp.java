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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    private EditText name, lastname, dni, phone, email, pass, pass2;
    private String nameS, lastNameS, dniS, phoneS, emailS, passS, pass2S;
    private TextView addVehicle, addPropertie, numVehicles, numProperties;
    private Button aceptSignUp;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference usersRef, infoRef, vehRef, propRef;
    private FirebaseUser user;
    private Boolean addDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
        buttons();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();

                if(user!=null){
                    //sesion abierta
                    Toast.makeText(getApplicationContext(),user.getUid(),Toast.LENGTH_SHORT).show();
                    addDB=true;
                }else{
                    //sesion cerrada
                }
            }
        };


        if(user!=null)
            getInfo();


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
        // Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");
        //Boolean
        addDB = false;
    }
    private void buttons(){
        aceptSignUp.setOnClickListener(new View.OnClickListener() {
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

    private void aceptButton(){
        nameS = name.getText().toString();
        lastNameS = lastname.getText().toString();
        dniS = dni.getText().toString();
        phoneS = phone.getText().toString();
        emailS = email.getText().toString();
        passS = pass.getText().toString();
        pass2S = pass2.getText().toString();

        if(!nameS.equals("") && !lastNameS.equals("") && !dniS.equals("") && !phoneS.equals("") && !emailS.equals("") && !passS.equals("") && !pass2S.equals("")){
            if(passS.equals(pass2S))
                if(pass.length()>=6){
                    signUp(emailS,passS);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            addInfoDB();
                        }
                    }, 2000);
                }
                else
                    Toast.makeText(getApplicationContext(),"La contraseña debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"La contraseña no coincide",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getApplicationContext(),"Debes rellenar todos los campos",Toast.LENGTH_SHORT).show();
    }
    private void vehicleButton(){
        nameS = name.getText().toString();
        lastNameS = lastname.getText().toString();
        dniS = dni.getText().toString();
        phoneS = phone.getText().toString();
        emailS = email.getText().toString();
        passS = pass.getText().toString();
        pass2S = pass2.getText().toString();

        if(!nameS.equals("") && !lastNameS.equals("") && !dniS.equals("") && !phoneS.equals("") && !emailS.equals("") && !passS.equals("") && !pass2S.equals("")){
            if(passS.equals(pass2S))
                if(pass.length()>=6){
                    signUp(emailS,passS);
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            addInfoDB();
                            startActivity(new Intent(SignUp.this, SignUp_Vehicle.class));
                        }
                    }, 2000);
                }
                else
                    Toast.makeText(getApplicationContext(),"La contraseña debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(),"La contraseña no coincide",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getApplicationContext(),"Debes rellenar todos los campos",Toast.LENGTH_SHORT).show();
    }
    private void propertieButton(){
        //startActivity(new Intent(SignUp.this, SignUp_Propertie.class));
    }

    private void signUp(String email, String pass){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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

    private void addInfoDB(){
        User u = new User(nameS,lastNameS,dniS,phoneS,emailS);
        usersRef.child(user.getUid()).child("info").setValue(u);
    }

    private void getInfo(){

        usersRef.child(user.getUid()).child("info").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                name.setText(u.getName());
                lastname.setText(u.getLastName());
                dni.setText(u.getDni());
                phone.setText(u.getPhone());
                email.setText(u.getEmail());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
