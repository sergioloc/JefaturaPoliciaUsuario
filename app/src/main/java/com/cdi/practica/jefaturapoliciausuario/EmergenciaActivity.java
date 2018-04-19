package com.cdi.practica.jefaturapoliciausuario;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;


public class EmergenciaActivity extends AppCompatActivity {

    TextView tiempo;
    private static final String FORMAT = "%02d:%02d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencia);

        tiempo=(TextView)findViewById(R.id.tiempo);

        int t=0;
        int n = getNumeroAleatorio();
        if(n==0)
            t=350000;
        else if(n==1)
            t=400000;
        else if(n==2)
            t=450000;
        else if(n==3)
            t=500000;
        else
            t=550000;

        new CountDownTimer(t, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                tiempo.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                tiempo.setText("Fin");
            }
        }.start();
    }

    private int getNumeroAleatorio(){
        int numero = (int) (Math.random() * 5);
        return numero;
    }


}
