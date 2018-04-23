package com.cdi.practica.jefaturapoliciausuario;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cdi.practica.jefaturapoliciausuario.Objects.Emergencia;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ncorti.slidetoact.SlideToActView;

import java.util.Date;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainMenu extends AppCompatActivity {

    private FancyButton predenuncia, gestion, emergencia;
    private Dialog dialog;
    private DatabaseReference refEmg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        predenuncia= (FancyButton) findViewById(R.id.predenuncia);
        gestion= (FancyButton) findViewById(R.id.gestion);
        emergencia= (FancyButton) findViewById(R.id.emergencia);

        buttons();
        // Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        refEmg = database.getReference("emergencias").child("pendientes");
        // Ubicaciones
        final String ubi[] = {"c/ azul 11","c/ verde 12","c/ amarillo 13","c/ morado 24","c/ rojo 19",
                "c/ violeta 21","c/ magenta 7","c/ cian 3","c/ rosa 6","c/ blanco 10","c/ negro 2",
                "c/ marron 45","c/ gris 40","c/ naranja 9","c/ dorado 70","c/ plateado 22",
                "c/ azul 71","c/ verde 46","c/ amarillo 3","c/ morado 14","c/ rojo 60",
                "c/ violeta 5","c/ magenta 14","c/ cian 30","c/ rosa 15","c/ blanco 2","c/ negro 4",
                "c/ marron 5","c/ gris 80","c/ naranja 99","c/ dorado 15","c/ plateado 6"};//32


        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_emergencia);
        Button dialogButtonC = (Button) dialog.findViewById(R.id.dialogButtonCancel);
        final SlideToActView aceptar = (SlideToActView) dialog.findViewById(R.id.aceptarEmergencia);
        aceptar.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideToActView slideToActView) {

                refEmg.push().setValue(new Emergencia(ubi[getNumeroAleatorio()],user.getUid(),getHora()));
                startActivity(new Intent(MainMenu.this,EmergenciaActivity.class));
                dialog.dismiss();
                aceptar.resetSlider();
            }
        });
        dialogButtonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });



    }

    private int getNumeroAleatorio(){
        int numero = (int) (Math.random() * 32);
        return numero;
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

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("¿Seguro que quiere salir?")
                .setCancelable(false)
                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.about:
                Toast.makeText(getApplicationContext(), "App creada por:\nSergio López\nCarlos Tarancón\nJoaquín Capel\nJavier Sangil",Toast.LENGTH_LONG).show();
                break;
            case R.id.signOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainMenu.this,MainActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void buttons(){
        //boton predenuncia
        predenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, PredenunciaActivity.class));
            }
        });
        //boton gestion
        gestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, GestionActivity.class));

            }
        });
        //boton emergencia
        emergencia.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.show();
                    }
                });
    }
}