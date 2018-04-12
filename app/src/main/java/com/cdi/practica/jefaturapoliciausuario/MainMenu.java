package com.cdi.practica.jefaturapoliciausuario;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainMenu extends AppCompatActivity {

    private ImageButton predenuncia, gestion, emergencia;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        predenuncia= (ImageButton) findViewById(R.id.predenuncia);
        gestion= (ImageButton) findViewById(R.id.gestion);
        emergencia= (ImageButton) findViewById(R.id.emergencia);
        buttons();
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
        Toast.makeText(getApplicationContext(),"Tu denuncia ha sido notificada",Toast.LENGTH_SHORT).show();
    }
}
