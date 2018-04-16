package com.cdi.practica.jefaturapoliciausuario.Fragments;

/**
 * Created by aired on 13/04/2018.
 */

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cdi.practica.jefaturapoliciausuario.R;
import com.ramotion.foldingcell.FoldingCell;

public class gestion_ajenas extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gestion_propias, container, false);

        final FoldingCell fc= rootView.findViewById(R.id.folding_cell);

        fc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                fc.toggle(false);
            }

        });

        Button doc = (Button) rootView.findViewById(R.id.addDoc);
        doc.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent =new Intent(view.getContext(),pop.class);
                startActivity(intent);

            }
        });


        return rootView;
    }



}
