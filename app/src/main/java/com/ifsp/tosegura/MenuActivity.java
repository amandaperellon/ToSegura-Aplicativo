package com.ifsp.tosegura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void abrirMapa(View view){
        Intent i = new Intent(MenuActivity.this, MapsActivity.class);
        startActivity(i);

    }

    public void abrirDenuncia(View view){
        Intent i = new Intent(MenuActivity.this, DenunciaActivity.class);
        startActivity(i);

    }
}
