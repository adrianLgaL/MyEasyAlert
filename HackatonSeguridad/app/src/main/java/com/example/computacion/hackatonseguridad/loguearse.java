package com.example.computacion.hackatonseguridad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class loguearse extends AppCompatActivity {
    private EditText txtCurp, txtContra;
    private String latitud, longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loguearse);

        txtCurp = (EditText) findViewById(R.id.txtCurp);
        txtContra = (EditText) findViewById(R.id.txtContra);

        Bundle bundle = getIntent().getExtras();
        latitud = bundle.getString("latitud");
        longitud =  bundle.getString("longitud");
    }

    public void iniciarSesion(View view){
        if(txtCurp.getText().toString().equals("")||txtContra.getText().toString().equals("")){
            Toast.makeText(this, "Llenar espacios vacios", Toast.LENGTH_SHORT).show();
        }
        else {
            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putString("sesion", "1");  // si es 1, es sesión iniciada
            editor.commit();

            Intent i = new Intent(this,Menu.class);
            i.putExtra("latitud",latitud);
            i.putExtra("longitud",longitud);
            startActivity(i);
            finish();
            }
    }

    public void registrarse(View view){
        Intent i = new Intent(this, Registro.class);
        i.putExtra("latitud", latitud);
        i.putExtra("longitud", longitud);
        startActivity(i);
        finish();
    }

}
