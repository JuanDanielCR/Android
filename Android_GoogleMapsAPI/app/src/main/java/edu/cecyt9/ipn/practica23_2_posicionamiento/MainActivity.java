package edu.cecyt9.ipn.practica23_2_posicionamiento;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity   extends FragmentActivity implements GoogleMap.OnMapClickListener {
    //Variable de tipo LatLng que recibe las coordenadas de algun marcador
    private final LatLng CECYT9 = new LatLng(39.481106, -0.340987);
    //Variable de tipo Google Map
    private GoogleMap mapa;
    //Metodo onCreate de la Actividad
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mapa= fragmento que usara el mapa para desplegarse, obteng el map del xml y lo asigno
       mapa = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();
        //Propiedad de map, le envio un ggogle map
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(CECYT9, 15));
        mapa.setMyLocationEnabled(true);
        mapa.getUiSettings().setZoomControlsEnabled(false);
        mapa.getUiSettings().setCompassEnabled(true);
        mapa.addMarker(new MarkerOptions()
                .position(CECYT9)
                .title("CECYT9")
                .snippet("CECYT 9 \"Juan de Dios Batiz\" ")
                .icon(BitmapDescriptorFactory
                        .fromResource(android.R.drawable.ic_menu_compass))
                .anchor(0.5f, 0.5f));
        mapa.setOnMapClickListener(this);
    }

    public void moveCamera(View view) {
        mapa.moveCamera(CameraUpdateFactory.newLatLng(CECYT9));
    }

    public void animateCamera(View view) {
       if (mapa.getMyLocation() != null)
            mapa.animateCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng( mapa.getMyLocation().getLatitude(), mapa.getMyLocation().getLongitude()), 15));
    }

    public void addMarker(View view) {
        mapa.addMarker(new MarkerOptions().position(
                new LatLng(mapa.getCameraPosition().target.latitude,
                        mapa.getCameraPosition().target.longitude)));
    }

    @Override
    public void onMapClick(LatLng puntoPulsado) {
        mapa.addMarker(new MarkerOptions().position(puntoPulsado).
                icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
    }
}