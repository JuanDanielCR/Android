package com.example.juandaniel.mejorandolainterfaz;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private boolean icono = true;
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
       switch(item.getItemId()){
           //Creacion de n Drawable

        case R.id.action_important:
                Toast.makeText(this,"Importante",Toast.LENGTH_SHORT).show();
                Drawable figura;
                if(icono){
                    figura= getResources().getDrawable(R.drawable.ic_action_important);
                }else{
                    figura=getResources().getDrawable(R.drawable.ic_action_half_important);
                }
                item.setIcon(figura);
                icono= !icono;
            return true;
       case R.id.action_good:
                Toast.makeText(this,"Good", Toast.LENGTH_SHORT).show();
                return true;
       case R.id.action_share:
                Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
               //Intent para compartir
               Intent i= new Intent();
                   i.setAction(i.ACTION_SEND);
                   i.putExtra(i.EXTRA_TEXT,getResources().getString(R.string.titleIntent));
                   i.setType("image/JPGE");
                   Uri url=Uri.parse("android.resource://"+getPackageName()+"/drawable/"+R.drawable.hotel1);
                   i.putExtra(i.EXTRA_STREAM, url);
               startActivity(Intent.createChooser(i, "Elige una Opción"));
                return true;
           default: return super.onOptionsItemSelected(item);
       }
    }
}
