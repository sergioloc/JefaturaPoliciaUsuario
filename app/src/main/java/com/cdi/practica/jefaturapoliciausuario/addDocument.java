package com.cdi.practica.jefaturapoliciausuario;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class addDocument extends AppCompatActivity {

    Button bt1,bt2,bt3;
    ImageView iv1,iv2,iv3,iv11,iv22,iv33;
    EditText et1, et2;
    TextView tv1;
    int cont=0;

    private static final int CAMERA_PIC_REQUEST = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_document);
        bt1 = (Button) findViewById(R.id.añadirDoc);
        bt2 = (Button) findViewById(R.id.botinfo);

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);

        iv11 = (ImageView) findViewById(R.id.iv11);
        iv22 = (ImageView) findViewById(R.id.iv21);
        iv33 = (ImageView) findViewById(R.id.iv31);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Información añadida",Toast.LENGTH_LONG).show();
                finish();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt2.setVisibility(View.INVISIBLE);
                final Dialog dialog = new Dialog(addDocument.this);
                dialog.setContentView(R.layout.anadir_info);
                dialog.show();

                bt3 = (Button) dialog.findViewById(R.id.acdoc);

                bt3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tv1 = (TextView) findViewById(R.id.texto);
                        et1 = (EditText) dialog.findViewById(R.id.edtxt);

                        String texto = et1.getText().toString();
                        tv1.setText(texto);
                        dialog.dismiss();
                    }
                });
            }
        });

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cont=1;
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cont=2;
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cont=3;
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }
        });

        iv11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog1 = new Dialog(addDocument.this);
                dialog1.setContentView(R.layout.abrir_imagen);
                dialog1.findViewById(R.id.imagen).setBackground(iv1.getDrawable());
                dialog1.show();
            }
        });

        iv22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog1 = new Dialog(addDocument.this);
                dialog1.setContentView(R.layout.abrir_imagen);
                dialog1.findViewById(R.id.imagen).setBackground(iv2.getDrawable());
                dialog1.show();
            }
        });

        iv33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog1 = new Dialog(addDocument.this);
                dialog1.setContentView(R.layout.abrir_imagen);
                dialog1.findViewById(R.id.imagen).setBackground(iv3.getDrawable());
                dialog1.show();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            if(cont==1){
                iv1.setImageBitmap(image);
                iv11.setVisibility(View.VISIBLE);
            }else if(cont==2){
                iv2.setImageBitmap(image);
                iv22.setVisibility(View.VISIBLE);
            }else{
                iv3.setImageBitmap(image);
                iv33.setVisibility(View.VISIBLE);
            }
        }
    }

}
