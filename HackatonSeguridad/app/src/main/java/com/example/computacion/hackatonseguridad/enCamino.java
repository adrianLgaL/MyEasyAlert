package com.example.computacion.hackatonseguridad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class enCamino extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_en_camino);
    }

    public void salir(View view){
        finish();
    }
}
