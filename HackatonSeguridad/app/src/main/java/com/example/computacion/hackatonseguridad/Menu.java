package com.example.computacion.hackatonseguridad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class Menu extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Bundle bundle = getIntent().getExtras();
        Toast.makeText(this, "latitud: "+bundle.getString("latitud"), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "longitud: "+bundle.getString("longitud"), Toast.LENGTH_SHORT).show();

    }

    public void salir(View view){
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
