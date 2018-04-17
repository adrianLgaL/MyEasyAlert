package com.example.computacion.hackatonseguridad;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView txtCurp, txtContra, txtRegistro;
    int a = 0;
    private String latitud, longitud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCurp = (TextView)findViewById(R.id.txtCurp);
        txtContra = (TextView)findViewById(R.id.txtContra);
        txtRegistro = (TextView)findViewById(R.id.txtregistro);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }

    }

    public void iniciarSesion(View view){
        if(txtCurp.getText().toString().equals("")||txtContra.getText().toString().equals("")){
            Toast.makeText(this, "Llenar espacios vacios", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(this,Menu.class);
            i.putExtra("latitud",latitud);
            i.putExtra("longitud",longitud);
            startActivity(i);
            finish();
        }
    }

    public void registrarse(View view){
        Toast.makeText(this, "Registrarse", Toast.LENGTH_SHORT).show();
    }


    ///////////     GPS    /////////////

    private void locationStart(){

        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    public void setLocation(Location loc) {
//Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public class Localizacion implements LocationListener {
        MainActivity mainActivity;
        public MainActivity getMainActivity() {
            return mainActivity;
        }
        public void setMainActivity(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }
        @Override
        public void onLocationChanged(Location loc) {
// Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
// debido a la deteccion de un cambio de ubicacion
            loc.getLatitude();
            loc.getLongitude();

            if(a==0){
                latitud = String.valueOf(loc.getLatitude());
                longitud = String.valueOf(loc.getLongitude());

                Toast.makeText(mainActivity, "Latitud: "+latitud+"", Toast.LENGTH_SHORT).show();
                Toast.makeText(mainActivity, "Longitud: "+longitud+"", Toast.LENGTH_SHORT).show();
                a=1;
            }
            this.mainActivity.setLocation(loc);
        }
        @Override
        public void onProviderDisabled(String provider) {
// Este metodo se ejecuta cuando el GPS es desactivado
        }
        @Override
        public void onProviderEnabled(String provider) {
// Este metodo se ejecuta cuando el GPS es activado
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }



}
