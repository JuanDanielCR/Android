package com.mejorandola.android;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.mejorandola.android.db.DBHelper;
import com.mejorandola.android.db.DBOperations;
import com.mejorandola.android.models.Tweet;
import com.mejorandola.android.utils.ConstantsUtils;
import com.mejorandola.android.utils.TwitterUtils;

import java.util.ArrayList;

/**
 * Created by Juan Daniel on 13/05/2015.
 */
public class UpdaterService extends Service {
    //Variables
    //Tiempo que tarda en recargar
        static final int DELAY=60000;
    //Valido si hya un servicio corriendo
        private boolean runFlag=false;
    //El thread
        private Updater updater;
    //Union con Applicaiotn
    private MejorandroidApplication application= new MejorandroidApplication();
    //Calse de bd
        private DBOperations db;
        private DBHelper helper;


    public static final String TAG=UpdaterService.class.getSimpleName();
    @Override
    //onCreate me srive para inicializar
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        updater= new Updater();
        helper= new DBHelper(this);
        db= new DBOperations(this);
        application=(MejorandroidApplication)getApplication();

    }
    //Usaod para servicios ligados
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
        runFlag=false;
        application.setServiceRunningFlag(false);
        updater.interrupt();
        //Para liberar memoeria
        application.setServiceRunningFlag(false);
        updater= null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
            Log.d(TAG,"onStartCommand");
        runFlag=true;
        application.setServiceRunningFlag(true);
        updater.start();
        return START_STICKY;
    }

    //CLASE PARA EL HILO
    private class Updater extends Thread{

        ArrayList<Tweet> timeline= new ArrayList<Tweet>();

        public Updater(){

            super("UpdaterService-UpdaterThread");
        }

        @Override
        public void run() {
            //Obtengo mi clase
            UpdaterService up= UpdaterService.this;
            while(up.runFlag){
                Log.d(TAG,"Thread Corriendo");
                try{
                    timeline = TwitterUtils.getTimelineForSearchTerm(ConstantsUtils.MEJORANDROID_TERM);
                    //Creamos la estructura de datos necesarias para guardar
                    ContentValues values = new ContentValues();
                    //Sentencia for en su version for each
                    for(Tweet tweet: timeline){
                        //Asegurarnos de que el value actual este limpio
                        values.clear();
                        //(columna,valor)
                        values.put(helper.C_ID,tweet.getId());
                        values.put(helper.C_NAME,tweet.getName());
                        values.put(helper.C_SCREEN_NAME,tweet.getScreenName());
                        values.put(helper.C_IMAGE_PROFILE_URL,tweet.getProfileImageUrl());
                        values.put(helper.C_TEXT,tweet.getText());
                        values.put(helper.C_CREATED_AT,tweet.getCreatedAt());

                        //Llamo el metodo de clase Operations que guarda
                        db.insertOrIgnore(values);
                    }
                    Thread.sleep(DELAY);
                }
                catch(InterruptedException e){
                    up.runFlag=false;
                    application.setServiceRunningFlag(false);
                }
            }
        }
    }
}
