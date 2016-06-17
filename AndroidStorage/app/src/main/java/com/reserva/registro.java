package com.reserva;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class registro extends Activity {
    Button reg;
    EditText pass, name;
    String contra, nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        reg= (Button) findViewById(R.id.btnRegistro);
        pass= (EditText) findViewById(R.id.Pass);

        name= (EditText) findViewById(R.id.Name);

    }
    public void altaBD(View v){
        contra=pass.getText().toString();
        nombre=name.getText().toString();
        //		Almacenamiento en BD
        // Definimos el nombre de la base de datos
		SQLHelp sql = new SQLHelp(this,"DB_Restaurant", null, 1);
		// Ocupamos la base para escribir
		final SQLiteDatabase db = sql.getWritableDatabase();

		ContentValues datos;
        datos = new ContentValues();
        datos.put("Nombre", nombre);
		datos.put("Pass", contra);


		// Si la "conexion" se realiza
		if(db != null){
			try{
				db.insert("Reservacion", null, datos);

			} catch (Exception e){
				Toast.makeText(getApplicationContext(),
						"Error en Insert " + e,
						Toast.LENGTH_LONG).show();
			}
		}else{
			Toast.makeText(getApplicationContext(),
					"No existe la base de datos",
					Toast.LENGTH_LONG).show();
		}
        Toast.makeText(this,"Te has resgitrado con exito",Toast.LENGTH_SHORT).show();
        Intent n = new Intent(this, MainActivity.class);
        finish();
        startActivity(n);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro, menu);
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
