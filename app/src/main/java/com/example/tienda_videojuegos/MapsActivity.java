package com.example.tienda_videojuegos;


import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;


import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.LayoutInflater;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.maps.android.SphericalUtil;

import java.util.Arrays;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;


        LatLng localizacin_tienda = new LatLng(40.433015, -3.717934);
        map.addMarker(new MarkerOptions().position(localizacin_tienda).title("La tienda está aquí!!"));
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(localizacin_tienda, (float) 14.223);
        map.animateCamera(yourLocation);

        //LatLng localizacion_donde_estamos = new LatLng(40.445475, -3.727576);
        //map.addMarker(new MarkerOptions().position(localizacion_donde_estamos).title("Nosotros estamos aquí"));


        /*
        double distance = SphericalUtil.computeDistanceBetween(localizacion_donde_estamos, localizacin_tienda);
        TextView textView = (TextView) findViewById(R.id.distancia_entre_puntos);
        textView.setText((int) distance);
         */
    }

}
