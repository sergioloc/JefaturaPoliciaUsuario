package com.cdi.practica.jefaturapoliciausuario;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.ncorti.slidetoact.SlideToActView;

public class MainMenu extends AppCompatActivity {

    private ImageButton predenuncia, gestion, emergencia;
    private Dialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        predenuncia= (ImageButton) findViewById(R.id.predenuncia);
        gestion= (ImageButton) findViewById(R.id.gestion);
        emergencia= (ImageButton) findViewById(R.id.emergencia);

        buttons();


        // custom dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_emergencia);
        Button dialogButtonC = (Button) dialog.findViewById(R.id.dialogButtonCancel);
        final SlideToActView aceptar = (SlideToActView) dialog.findViewById(R.id.aceptarEmergencia);
        aceptar.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideToActView slideToActView) {
                startActivity(new Intent(MainMenu.this,Emergencia.class));
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

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