package com.mejorandola.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.mejorandola.android.list.TweetAdapter;
import com.mejorandola.android.models.Tweet;
import com.mejorandola.android.utils.ConstantsUtils;
import com.mejorandola.android.utils.TwitterUtils;

import java.util.ArrayList;

public class TimelineActivity extends Activity {

    public ListView lvtimeline;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);

        lvtimeline=(ListView) findViewById(R.id.lv_timeline);
        new HacerHilo().execute();

	}

    //Agregando al list view en el meotdo postExceute(en es tememtdodo se reciben el array de tweets) sera llamado
    private void updateList(ArrayList<Tweet> tweet){
        //Instanciamos el adaptador que creamos
        lvtimeline.setAdapter(new TweetAdapter(this, R.layout.row_tweet,tweet));

    }


        //Hacer una clase dentro para controlar
    class HacerHilo extends AsyncTask<Object, Void, ArrayList<Tweet>>{

            //Variabel para el dialogo
        private ProgressDialog cargador;

            //Metodo executado antes del hilo
        @Override
        protected  void onPreExecute(){
            super.onPreExecute();
            cargador= new ProgressDialog(TimelineActivity.this);
                //Obtengo un recurso con metodo getResources ´+ getEspecifico
            cargador.setTitle(getResources().getString(R.string.label_tweet_search_loader));
            cargador.show();
        }

            //Creacionde un nuevo hilo en el background
        @Override
        protected ArrayList<Tweet> doInBackground(Object... objects) {
               return TwitterUtils.getTimelineForSearchTerm(ConstantsUtils.MEJORANDROID_TERM);
               //El codigo que estaba aqui, me lo lleve a Tweet Adapter por Best Practice
        }

            //Quitar la Interfaz
        @Override
        protected void onPostExecute(ArrayList<Tweet> Result){
            super.onPostExecute(Result);
            cargador.dismiss();
            //Valido que traigo tweets
            if(Result.isEmpty()){
                Toast.makeText(TimelineActivity.this,getResources().getString(R.string.label_tweets_not_found),Toast.LENGTH_SHORT).show();
            }else{
                updateList(Result);
                Toast.makeText(TimelineActivity.this,getResources().getString(R.string.label_tweets_downloaded),Toast.LENGTH_SHORT).show();
            }
        }
    }

}
