package com.ifsp.tosegura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
    }

    public void abrirMapa(View view){
        Intent i = new Intent(FormularioActivity.this, MapsActivity.class);
        startActivity(i);

    }
}
