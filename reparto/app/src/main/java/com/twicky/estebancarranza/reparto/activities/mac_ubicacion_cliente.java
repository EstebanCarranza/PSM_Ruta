package com.twicky.estebancarranza.reparto.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.database.helpers.clienteSQL;
import com.twicky.estebancarranza.reparto.models.cliente;

import java.util.ArrayList;
import java.util.List;

public class mac_ubicacion_cliente extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_ubicacion_cliente);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    public void agregarUbicacionClientes()
    {

        clienteSQL db = new clienteSQL(getApplicationContext());
        ArrayList<cliente> clientes = new ArrayList<>();
        clientes = db.getCliente(1,1);

        for(int i= 0; i < clientes.size(); i++)
        {
            mMap.addMarker(new MarkerOptions().position(clientes.get(i).getCoordenada()).title(clientes.get(i).getNombre()));
        }
    }

    LocationManager locationManager;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);



        agregarUbicacionClientes();




        //mMap.moveCamera(CameraUpdateFactory.newLatLng(mty));

        // Si estamos en Android 6.0+ tenemos que pedir permisos en tiempo de ejecucion
        // Si estamos debajo de Android 6.0 solo hace falta pedir permisos desde el AndroidManifest
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            checkPermissions();
        else
            moveMapCameraToUserLocation();
    }
    private void checkPermissions() {
        // Apartir de Android 6.0+ necesitamos pedir el permiso de ubicacion
        // directamente en tiempo de ejecucion de la app
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Si no tenemos permiso para la ubicacion
            // Solicitamos permiso
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        } else {
            // Ya se han concedido los permisos anteriormente
            moveMapCameraToUserLocation();
        }
    }
    private void moveMapCameraToUserLocation() {
        // Continuamos obteniendo la ubicacion del usuario para despues mostrar esa ubicacion en el mapa por default
        // pero cuando no se encuentre la ubicacion entonces pondremos una ubicacion fija.
        LatLng currentLocation = null;
        try {
            // Muestra el boton de "Mi Ubicacion" en el mapa (El tipico circulo azul de google)
            mMap.setMyLocationEnabled(true);

            // Utilizamos el metodo que desarrollamos para obtener la ubicacion del usuario
            currentLocation = getCurrentLocation();

        } catch (SecurityException e) {
            e.printStackTrace();
        }

        // Si se pudo obtener la ubicacion del usuario
        if (currentLocation != null) { // .. cambiar if

            // Movemos la camara para que apunte a otra coordenada diferente e la default
            float factorZoom = 16;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, factorZoom));
        } else { // Si no se pudo obtener la ubicacion

            // Ponemos una ubicacion fija
            LatLng myLocation = new LatLng(25.65, -100.29);
            float factorZoom = 16;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, factorZoom));
        }
    }
    private LatLng getCurrentLocation() throws SecurityException {
        /* PARCIAL 3 ********************************************************************************************** */
        //Proveedores: wifi, datos, gps, bluetooth


        //EXAMEN 3 UBICACION PARA EL USUARIO: LOCATION MANAGER (locationManager) ***********************************************
        //todo lo que tiene que ver con geolocalizacion es locationManager
        List<String> providers = locationManager.getProviders(true);

        /*
        * android service location
        *
        * Location Receive Location updates
        *
        * */

        Location bestLocation = null;

        for(String provider : providers) {
            //te regresa la ultima ubicación conocida por alguna app
            Location l = locationManager.getLastKnownLocation(provider);

            if (l == null) {
                continue;
            }

            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                bestLocation = l;
            }

        }

        if(bestLocation == null)
        {
            //showToast("No se pudo obtener tu ubicación");
            return null;
        }
        else
            return new LatLng(bestLocation.getLatitude(), bestLocation.getLongitude());

    }
}
