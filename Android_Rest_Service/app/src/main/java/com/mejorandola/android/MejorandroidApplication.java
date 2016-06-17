package com.mejorandola.android;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Juan Daniel on 13/05/2015.
 */
public class MejorandroidApplication extends Application{
    //Para ayudarnos con el logcat
    public static final String tag= MejorandroidApplication.class.getSimpleName();
    private boolean ServiceRunningFlag;

    //Getter
    public boolean isServiceRunning(){
        return ServiceRunningFlag;
    }
    //Setter
    public void setServiceRunningFlag(boolean recibo){
        this.ServiceRunningFlag=recibo;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(tag,"Entre a la memoria");
        startService(new Intent(this,UpdaterService.class));
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(tag,"Sali de la pila de memoria");
        stopService(new Intent(this, UpdaterService.class));
    }
}
