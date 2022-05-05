package com.ifsp.tosegura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DenunciaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);
    }

    public void abrirMapa(View view){
        Intent i = new Intent(DenunciaActivity.this, MapsActivity.class);
        startActivity(i);

    }


    public void abrirMenu(View view){
        Intent i = new Intent(DenunciaActivity.this, MenuActivity.class);
        startActivity(i);

    }
}
