package com.cdi.practica.jefaturapoliciausuario.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cdi.practica.jefaturapoliciausuario.R;

/**
 * Created by aired on 16/04/2018.
 */

public class pop extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.widthPixels;

        getWindow().setLayout((int) (width*.9),(int) (height*.9));

        EditText et1 = (EditText) findViewById(R.id.edittext1);
        Button bt1 = (Button) findViewById(R.id.botaceptar);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Añadida la documentación",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}
