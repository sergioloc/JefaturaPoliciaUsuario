package com.cdi.practica.jefaturapoliciausuario.Fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cdi.practica.jefaturapoliciausuario.Objects.Denuncia;
import com.cdi.practica.jefaturapoliciausuario.R;
import com.cdi.practica.jefaturapoliciausuario.AddDocument;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    private Context context;

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView personName;
        TextView personAge;

        PersonViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
        }
    }

    List<Denuncia> persons;

    RVAdapter(List<Denuncia> persons){
        this.persons = persons;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).fecha);
        personViewHolder.personAge.setText(persons.get(i).tipo);
        context = personViewHolder.personAge.getContext();
        personViewHolder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.activity_add_document);
                // aqui


                dialog.show();*/
                context.startActivity(new Intent(context,AddDocument.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

}
