package com.cdi.practica.jefaturapoliciausuario.Fragments;

/**
 * Created by aired on 13/04/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cdi.practica.jefaturapoliciausuario.Objects.Denuncia;
import com.cdi.practica.jefaturapoliciausuario.R;
import com.cdi.practica.jefaturapoliciausuario.addDocument;

import java.util.ArrayList;
import java.util.List;

public class GestionEnviadas extends Fragment{


    private List<Denuncia> persons;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.gestion_propias, container, false);
        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());
        rv.setLayoutManager(llm);

        initializeData();

        RVAdapter adapter = new RVAdapter(persons);
        rv.setAdapter(adapter);

        ImageView cv = (ImageView) rootView.findViewById(R.id.cv);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), addDocument.class));
            }
        });

        return rootView;

    }

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Denuncia("Robo con violencia", "Pulsa para más información"));
        persons.add(new Denuncia("Fraude", "Pulsa para más información"));
        persons.add(new Denuncia("Acoso", "Pulsa para más información"));
    }
}
