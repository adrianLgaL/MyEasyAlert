package com.example.computacion.hackatonseguridad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    private EditText txtNombre, txtCurp, txtContraseña, txtSeguro, txtTelefono, txtCorreo;
    private String latitud, longitud;
    private Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtNombre = (EditText)findViewById(R.id.txtNombre);
        txtCurp = (EditText) findViewById(R.id.txtCurp);
        txtContraseña = (EditText)findViewById(R.id.txtContraseña);
        txtSeguro = (EditText)findViewById(R.id.txtSeguro);
        txtTelefono = (EditText)findViewById(R.id.txtTelefono);
        txtCorreo=(EditText)findViewById(R.id.txtCorreo);
        btnRegistrar=(Button)findViewById(R.id.btnRegistrar);

        Bundle bundle = getIntent().getExtras();
        latitud = bundle.getString("latitud");
        longitud = bundle.getString("longitud");
        Toast.makeText(this, "latitud: "+latitud, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "longitud: "+longitud, Toast.LENGTH_SHORT).show();

    }



    public void Registrar (View view){
        if(txtNombre.getText().toString().equals("")|| txtCurp.getText().toString().equals("")||txtContraseña.getText().toString().equals("")
                ||txtSeguro.getText().toString().equals("")||txtTelefono.getText().toString().equals("")||txtCorreo.getText().toString().equals("")){
            Toast.makeText(this, "Llenar espacios vacios", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(this,loguearse.class);
            i.putExtra("latitud",latitud);
            i.putExtra("longitud",longitud);
            startActivity(i);
            finish();
        }
    }
}
