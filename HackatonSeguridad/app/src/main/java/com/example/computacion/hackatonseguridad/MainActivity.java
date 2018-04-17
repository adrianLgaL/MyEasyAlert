package com.example.computacion.hackatonseguridad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView txtCurp, txtContra, txtRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCurp = (TextView)findViewById(R.id.txtCurp);
        txtContra = (TextView)findViewById(R.id.txtContra);
        txtRegistro = (TextView)findViewById(R.id.txtregistro);

    }

    public void iniciarSesion(View view){
        if(txtCurp.getText().toString().equals("")||txtContra.getText().toString().equals("")){
            Toast.makeText(this, "Llenar espacios vacios", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent i = new Intent(this,Menu.class);
            startActivity(i);
            finish();
        }
    }

    public void registrarse(View view){
        Toast.makeText(this, "Registrarse", Toast.LENGTH_SHORT).show();
    }

}
