package com.example.ejercicio6_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public abstract class Adaptador extends BaseAdapter {
    private ArrayList<?> entrada;
    private int R_layout_IdView;
    private Context contexto;

    public Adaptador(Context contexto, int R_layout_IdView,  ArrayList<?> entrada){
        super();
        this.contexto = contexto;
        this.entrada = entrada;
        this.R_layout_IdView = R_layout_IdView;
    }

    @Override
    public int getCount() {
        return entrada.size();
    }

    @Override
    public Object getItem(int posicion) {
        return entrada.get(posicion);
    }

    @Override
    public long getItemId(int posicion) {
        return posicion;
    }
    public void onEntrada(Object entrada, View view){

    }

    @Override
    public View getView(int posicion, View view, ViewGroup viewGroup) {
        if (view==null){
            LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R_layout_IdView,null);
        }

        onEntrada(entrada.get(posicion),view);
        return view;
    }
}
