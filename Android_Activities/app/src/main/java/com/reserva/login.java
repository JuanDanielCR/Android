package com.reserva;

/**
 * Created by Juan Daniel on 20/04/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login extends Activity{
    //Variables

    //Metodo onCreate interface de Activity, permite crear una actividad
    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Bundle recibe=new Bundle();
        recibe=this.getIntent().getExtras();
        //Vista
        TextView Welcome=(TextView) findViewById(R.id.txtWlecome);
        TextView name=(TextView) findViewById(R.id.lblNombre);
        TextView pass=(TextView) findViewById(R.id.lblContrasena);

        String nombre=recibe.getString("cliente");
        String contra=recibe.getString("contra");

        if(nombre.equals("2013090591")&contra.equals("rocio")){

            name.setText("Número de Cliente: "+nombre);
            pass.setText("Contraseña: "+contra);
            Welcome.setVisibility(View.VISIBLE);
            name.setVisibility(View.VISIBLE);
            pass.setVisibility(View.VISIBLE);
        }
        else{
            name.setVisibility(View.INVISIBLE);
            pass.setVisibility(View.INVISIBLE);
            Welcome.setVisibility(View.VISIBLE);
            TextView error=(TextView) findViewById(R.id.txtError);
            Button volver=(Button) findViewById(R.id.btnVolver);
            Button cerrar=(Button) findViewById(R.id.button2);
            cerrar.setVisibility(View.INVISIBLE);
            error.setVisibility(View.VISIBLE);
            volver.setVisibility(View.VISIBLE);
        }

    }
    public void volverError(View v){
        Intent sesion=new Intent(this,MainActivity.class);
        finish();
        startActivity(sesion);
    }
}
