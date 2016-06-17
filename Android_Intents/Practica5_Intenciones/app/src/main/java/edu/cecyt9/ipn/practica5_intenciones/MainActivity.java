package edu.cecyt9.ipn.practica5_intenciones;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {


   public void abrirPaginaWeb(View paginaWeb)
   {
       Intent intent = new Intent(Intent.ACTION_VIEW,
                                  Uri.parse("http://coatl.cecyt9.ipn.mx/eoropeza/home.html"));
       startActivity(intent);
   }
    public void streetView(View pagina){
        Intent abrir= new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com.mx/maps/place/" +
                        "Centro+de+Estudios+Cient%C3%ADficos+y+Tecnol%C3%B3gicos+No.+9+Juan+de+Dios+B%C3%A1tiz+Paredes/" +
                        "@19.45381,-99.174851,3a,75y,270h,90t/" +
                        "data=!3m4!1e1!3m2!1sPBrtV3H1pZqeqy98AwU4yg!2e0!4m2!3m1!1s0x85d1f8bd9777a765:0x57501a5479751d18!6m1!1e1"));
        startActivity(abrir);
    }

    public void servidoresCorreo(View correo){
        Intent servidores= new Intent(Intent.ACTION_SENDTO);
        servidores.setData(Uri.parse("mailto:"));
        servidores.putExtra(Intent.EXTRA_EMAIL, new String[]{"rose.palace@hotmail.com"});
        servidores.putExtra(Intent.EXTRA_SUBJECT, "Asunto: Prueba");
        startActivity(servidores);
    }

    public void llamadaTelefono(View llamada)
    {
        Intent intent = new Intent(Intent.ACTION_CALL,
                Uri.parse("tel:57296000"));
        startActivity(intent);
    }

    public void googleMaps(View maps)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:19.453659, -99.175298"));
        startActivity(intent);
    }

    public void tomarFoto(View maps)
    {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
    }

    public void mandarCorreo(View correo)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Asunto: Prueba");
        intent.putExtra(Intent.EXTRA_TEXT, "Contenido del correo: Prueba");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "rose.palace@hotmail.com"} );
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


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
