package com.example.juandaniel.mejorandola1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    //Debugging rapido
    private final static String msg="click";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn_msg= (Button) findViewById(R.id.btnLogin);
        btn_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mostrar lo que se hizo
                Toast.makeText(
                        getApplicationContext(),//Obtiene el contexto de la app
                        "Click",//Mensaje
                        Toast.LENGTH_SHORT)
                        .show();//Mostar
                //Obtener valor del EditText y mandarlo al TextView
                EditText txtEntra= (EditText) findViewById(R.id.edt_nombre);
                TextView txtSale=(TextView) findViewById(R.id.txt_msg);

                txtSale.setText("Hola "+txtEntra.getText());
            }
        });

        //Intent
        final Intent i= new Intent(this,activity2.class);

        //Boton entrada y salida
        Button btn_open=(Button) findViewById(R.id.btnEntrada);
        Button btn_close=(Button) findViewById(R.id.btnSalida);

       //Listeners
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }//


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
