package com.example.juandaniel.materialdesign;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();

    }
    public void Iniciar(View view){
        Intent inicio= new Intent(getApplicationContext(),Activity2.class);
        startActivity(inicio);
    }
    public void setToolbar(){
        Toolbar toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Soy Material");
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Soy Bátiz");
        toolbar.setTitleTextColor(Color.rgb(117,0,42));
        toolbar.setLogo(R.mipmap.iconazo);
        setSupportActionBar(toolbar);

    }
}
