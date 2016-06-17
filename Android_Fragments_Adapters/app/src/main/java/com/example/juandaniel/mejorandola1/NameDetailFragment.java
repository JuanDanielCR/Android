package com.example.juandaniel.mejorandola1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Juan Daniel on 25/04/2015.
 */
public class NameDetailFragment extends Fragment{
    //Este textView sera llenado
    private TextView txt_nombre;

    public void setName(String name){
        txt_nombre.setText(name);
    }

    //Actividad ha sido creada
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments=getArguments();
        if(arguments!=null){
            String name=(String) arguments.get(activity2.name_person);
            setName(name);
        }


    }

    //El fragmento pertenece a una vista
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Creación de un objeto view para poder acceder al archivo xml que usara el fragmento para mostrar sus datos.
        //El metodo inflater, Instantiates a layout XML file into its corresponding View objects
        View view = inflater.inflate(R.layout.fragment_listado, null);
        //Inicializamos y  modificamos el textView creado anteriormente
        //Usamos un findViewById pero ahora del objeto VIEW------important!
        txt_nombre=(TextView) view.findViewById(R.id.txtFragmentText);
        return view;
       // return super.onCreateView(inflater, container, savedInstanceState);
    }
}
