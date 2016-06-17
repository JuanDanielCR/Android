package com.example.juandaniel.mejorandola1;


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;


public class NameDetail extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_detail);

        Intent i= getIntent();
        String nombre=i.getStringExtra(activity2.name_person);
       //Instanciamos al fragmento dentro de la actividad
        //Obtnemos un fragment manager
        FragmentManager fm= getSupportFragmentManager();
        //Instanciamos la clase que maneja al fragmento
        NameDetailFragment frag=(NameDetailFragment)fm.findFragmentById(R.id.detail_fragment);
        frag.setName(nombre);

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
