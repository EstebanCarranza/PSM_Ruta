package com.twicky.estebancarranza.reparto.activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.twicky.estebancarranza.reparto.R;
import com.twicky.estebancarranza.reparto.database.helpers.clienteSQL;
import com.twicky.estebancarranza.reparto.models.cliente;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class frg_getUbicacionCliente extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button btnGuardarUbicacion;
    Button btnRegresar;
    TextView lblLongitudVal;
    TextView lblLatitudeVal;
    LatLng LatLngLocal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_frg_get_ubicacion_cliente);




        btnGuardarUbicacion = (Button) findViewById(R.id.btnGuardarUbicacion);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);
        lblLongitudVal = (TextView) findViewById(R.id.lblLongitudVal);
        lblLatitudeVal = (TextView) findViewById(R.id.lblLatitudeVal);


        btnGuardarUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LatLngLocal != null) {
                    Toast.makeText(frg_getUbicacionCliente.this, "click", Toast.LENGTH_SHORT).show();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("Latitude", String.valueOf(LatLngLocal.latitude));
                    returnIntent.putExtra("Longitude", String.valueOf(LatLngLocal.longitude));

                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();

                }
                else
                    Toast.makeText(frg_getUbicacionCliente.this, "Fijar un punto en el mapa", Toast.LENGTH_SHORT).show();

            }
        });
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });

       
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


    LocationManager locationManager;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Listener para detectar los eventos "Click" dentro del mapa
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            // Este evento nos devuelve la cooordenada geografica donde se dio click dentro del mapa
            @Override
            public void onMapClick(LatLng latLng) {

                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title("Nuevo cliente"));

                LatLngLocal = latLng;
                lblLatitudeVal.setText(String.valueOf(latLng.latitude));
                lblLongitudVal.setText(String.valueOf(latLng.longitude));
                
                
            }
        });

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
            //LatLngLocal = myLocation;
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

    // Extra: Lo utilizamos para agregar marcadores al mapa
    List<LatLng> markerPositions;
    private void addMarker(String title, LatLng position, boolean clean, boolean polys) {
        if (clean) {
            mMap.clear();
        }

        // De esta manera se pueden agregar marcadores al mapa
        MarkerOptions opts = new MarkerOptions();
        opts.position(position);
        opts.title(title);

        // La clase GoogleMap tiene el metodo addMarker
        mMap.addMarker(opts);

        if (!polys)
            return;

        if (markerPositions == null)
            markerPositions = new ArrayList<>();

        // EXTRA: Tambien se pueden poner lineas dentro del mapa
        PolylineOptions line = new PolylineOptions();
        line.width(8);
        line.color(Color.BLUE);

        if (markerPositions.size() > 0) {
            LatLng latLng = markerPositions.get(markerPositions.size() - 1);
            line.add(latLng);
        }
        line.add(position);
        markerPositions.add(position);

        // Muestra una linea en el mapa
        mMap.addPolyline(line);
    }
}
