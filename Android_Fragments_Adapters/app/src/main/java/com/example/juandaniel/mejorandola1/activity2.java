package com.example.juandaniel.mejorandola1;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import static android.R.layout.simple_list_item_1;


public class activity2 extends FragmentActivity {
    //Creando lista a partir de una arrayList

    private static final ArrayList <String> names= new ArrayList<String>();
    public static final String name_person="name_person";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        //Obtener valor del edit Text
        final EditText input= (EditText) findViewById(R.id.txtLista);
        Button btnLlenar= (Button) findViewById(R.id.btnList);
        ListView lista= (ListView) findViewById(R.id.listado);

        //Creando el adaptador para mandar los datos a la lista
        final ArrayAdapter <String> adapter= new ArrayAdapter<String>(getApplicationContext(),
                                            simple_list_item_1, names);
        //mandar el adaptador a la lista
        lista.setAdapter(adapter);

        //Agregar listener al boton
        btnLlenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=input.getText().toString();
                if(!names.contains(name)){
                    names.add(name);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        //Agregando un listener a un item de la lista par aobtenre su valor
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Recuperar de la lista atraves del adapter
                String rec=adapter.getItem(position);
            //Recuperando la orientacion para saber que hacer con el fragmento
                if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){

                Intent action;
                action = new Intent(getApplicationContext(),NameDetail.class);
                action.putExtra(name_person, rec);
                startActivity(action);
               }
                //Si el celular esta horizontal traigo al fragmento aqui
               else{
               FragmentManager fm= getSupportFragmentManager();
                //Instanciamos la clase que maneja al fragmento
               NameDetailFragment frag=(NameDetailFragment)fm.findFragmentById(R.id.detail_fragment);
               frag.setName(rec);
            }

      }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
