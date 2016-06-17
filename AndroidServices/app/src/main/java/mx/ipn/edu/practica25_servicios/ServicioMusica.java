package mx.ipn.edu.practica25_servicios;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class ServicioMusica extends Service {
    MediaPlayer reproductor;
    private NotificationManager nm;
    private static final int ID_NOTIFICACION_CREAR = 1;
    private LocationManager manejador;
    public Notification notificacion;
    public PendingIntent intencionPendiente;
    public boolean nice;
    @Override
    public void onCreate() {
        nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Toast.makeText(this, "Servicio creado",
                Toast.LENGTH_SHORT).show();
        reproductor = MediaPlayer.create(this, R.raw.audio);
        manejador = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

//    START_STICKY: Cuando sea posible el sistema tratar� de recrear el servicio, se realizar� una llamada a onStartCommand() pero con el par�metro intencion igual a null. Esto tiene sentido cuando el servicio puede arrancar sin informaci�n adicional, como por ejemplo, el servicio mostrado para la reproducci�n de m�sica de fondo.
//    START_NOT_STICKY: El sistema no tratar� de volver a crear el servicio, por lo tanto el par�metro intencion nunca podr� ser igual a null. Esto tiene sentido cuando el servicio no puede reanudarse una vez interrumpido.
//    START_REDELIVER_INTENT: El sistema tratar� de volver a crear el servicio. El par�metro intencion ser� el que se utiliz� en la �ltima llamada startService(Intent).

    @Override
    public int onStartCommand(Intent intenc, int flags, int idArranque) {

        Toast.makeText(this,"Servicio arrancado "+ idArranque,
                Toast.LENGTH_SHORT).show();

         notificacion = new Notification(
                R.mipmap.ic_launcher,
                "Armando notifica bien",//Texto arriba
                System.currentTimeMillis() );
        //Agregando sonido a la notificacion
            notificacion.defaults|=Notification.DEFAULT_SOUND;
        //Vibracion
            notificacion.defaults|=Notification.DEFAULT_VIBRATE;
        //led
            notificacion.defaults|=Notification.DEFAULT_LIGHTS;

        intencionPendiente = PendingIntent.getActivity(
                this, 0, new Intent(this, MainActivity.class), 0);
        //Texto abajo
        notificacion.setLatestEventInfo(this, "Emergencia familiar",
                "El paciente murio", intencionPendiente);

        nm.notify(ID_NOTIFICACION_CREAR, notificacion);
        Criteria criterio = new Criteria();
        criterio.setCostAllowed(false);
        criterio.setAltitudeRequired(false);
        criterio.setAccuracy(Criteria.ACCURACY_FINE);

        Location localizacion = manejador.getLastKnownLocation(manejador.getBestProvider(criterio, true));
        Double longitude=localizacion.getLongitude();
        Double latitude=localizacion.getLatitude();
        String pos="Latitud: "+longitude.toString()+"\n Longitud: "+latitude.toString();
        Toast.makeText(getApplicationContext(),pos,Toast.LENGTH_SHORT).show();


        Ciclo ciclo= new Ciclo();
        ciclo.start();
        //reproductor.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        nm.cancel(ID_NOTIFICACION_CREAR);
        Toast.makeText(this,"Servicio detenido",
                Toast.LENGTH_SHORT).show();
        nice=false;
    }

    @Override
    public IBinder onBind(Intent intencion) {
        return null;
    }

    //Clase para actualizar
    public class Ciclo extends Thread{
        @Override
        public void run() {
            //Obtengo mi clase
            nice=true;
            while(nice==true){
                //Posicion
                Criteria criterio = new Criteria();
                criterio.setCostAllowed(false);
                criterio.setAltitudeRequired(false);
                criterio.setAccuracy(Criteria.ACCURACY_FINE);
                nm.notify(ID_NOTIFICACION_CREAR, notificacion);
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}