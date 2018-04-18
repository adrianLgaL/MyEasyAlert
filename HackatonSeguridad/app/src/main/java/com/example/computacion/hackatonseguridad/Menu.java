package com.example.computacion.hackatonseguridad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;

import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Menu extends AppCompatActivity {
    private String curp, latitud, longitud;
    int horas, min, seg;
    String tiempo, fecha;

    private Socket msocket;
    {
        try{
            msocket= IO.socket("http://192.168.8.62:90");  // ip del servidor
        }catch (URISyntaxException e){
            Toast.makeText(this, "Error: fallo en la conexión", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle bundle = getIntent().getExtras();
        latitud = bundle.getString("latitud");
        longitud = bundle.getString("longitud");
        Toast.makeText(this, "latitud: "+latitud, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "longitud: "+longitud, Toast.LENGTH_SHORT).show();

        msocket.on("ubicacion",ubicacion); // escuchador
        msocket.connect(); //conecta con socket,

        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        curp = preferencias.getString("curp","0");
        Toast.makeText(this, curp, Toast.LENGTH_SHORT).show();

        // -----------------------------  CALCULA HORA ------------------------------
        Date dt = new Date();
        horas = dt.getHours();
        min = dt.getMinutes();
        seg = dt.getSeconds();

        tiempo = horas+":"+min+":"+seg;

        Toast.makeText(this, "hora: "+tiempo, Toast.LENGTH_SHORT).show();

        //------------------------------  CALCULA FECHA  --------------------------------
        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        fecha = df.format(c.getTime());

        Toast.makeText(this, "fecha: "+fecha, Toast.LENGTH_SHORT).show();


    }

    public void pedirAmbulancia(View view)
    {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.curp = curp;
        ubicacion.emergencia = "ambulancia";
        ubicacion.fecha = fecha;
        ubicacion.hora = tiempo;
        ubicacion.latitud = latitud;
        ubicacion.longitud = longitud;
        ubicacion.numeroCelular = "a";

        Gson gson=new Gson();
        msocket.emit("ubicacion",gson.toJson(ubicacion)); //envia los datos

        Intent i = new Intent(this,enCamino.class);
        startActivity(i);
        finish();
    }

    public void pedirBomberos(View view)
    {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.curp = curp;
        ubicacion.emergencia = "bomberos";
        ubicacion.fecha = fecha;
        ubicacion.hora = tiempo;
        ubicacion.latitud = latitud;
        ubicacion.longitud = longitud;
        ubicacion.numeroCelular = "a";

        Gson gson=new Gson();
        msocket.emit("ubicacion",gson.toJson(ubicacion)); //envia los datos


        Intent i = new Intent(this,enCamino.class);
        startActivity(i);
        finish();
    }

    public void pedirSalvavidas(View view)
    {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.curp = curp;
        ubicacion.emergencia = "salvavidas";
        ubicacion.fecha = fecha;
        ubicacion.hora = tiempo;
        ubicacion.latitud = latitud;
        ubicacion.longitud = longitud;
        ubicacion.numeroCelular = "a";

        Gson gson=new Gson();
        msocket.emit("ubicacion",gson.toJson(ubicacion)); //envia los dato

        Intent i = new Intent(this,enCamino.class);
        startActivity(i);
        finish();
    }

    public void pedirPolicia(View view)
    {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.curp = curp;
        ubicacion.emergencia = "policia";
        ubicacion.fecha = fecha;
        ubicacion.hora = tiempo;
        ubicacion.latitud = latitud;
        ubicacion.longitud = longitud;
        ubicacion.numeroCelular = "a";

        Gson gson=new Gson();
        msocket.emit("ubicacion",gson.toJson(ubicacion)); //envia los datos

        Intent i = new Intent(this,enCamino.class);
        startActivity(i);
        finish();
    }

    public void salir(View view){
        finish();
    }

    private Emitter.Listener ubicacion = new Emitter.Listener() //recibe informacion  NO SE NECESITA AQUÍ
    {
        @Override
        public void call(Object... args)
        {
            Gson gson = new Gson();
            Ubicacion ubicacion = gson.fromJson(args[0].toString(),Ubicacion.class);
        }
    };
}
