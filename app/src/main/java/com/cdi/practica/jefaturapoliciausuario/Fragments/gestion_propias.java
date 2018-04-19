package com.cdi.practica.jefaturapoliciausuario.Fragments;

/**
 * Created by aired on 13/04/2018.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cdi.practica.jefaturapoliciausuario.R;
import com.cdi.practica.jefaturapoliciausuario.addDocument;

import java.util.ArrayList;
import java.util.List;

public class gestion_propias extends Fragment{


    private List<Person> persons;

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

        ImageView cv = (ImageView) rootView.findViewById(R.id.link);
        rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), addDocument.class));
                Toast.makeText(getActivity().getApplicationContext(),"Información añadida",Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;

    }

    private void initializeData(){
        persons = new ArrayList<>();
        persons.add(new Person("Robo con violencia", "Pulsa para más información"));
        persons.add(new Person("Fraude", "Pulsa para más información"));
        persons.add(new Person("Acoso", "Pulsa para más información"));
    }
}
