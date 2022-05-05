package com.ifsp.tosegura;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String[] permissoes = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //VALIDAR AS PERMISSÕES
        Permissoes.validarPermissoes(permissoes, this, 1);

        //CONFIGURANDO O OBJETO LOCATIONMANAGER (LOCALIZA O USUÁRIO)
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        //CONFIGURANDO O OBJETO LOCATIONLISTENER
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Localização", "onLocationChanged: " + location.toString());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //MUDAR OS MODOS DE EXIBIÇÃO DO MAPA
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //DEFININFO UM PONTO NO MAPA COM LATITUDE/LONGITUDE
        LatLng ifsp = new LatLng(-23.523602, -46.622386);

        /*//INCLUINDO FORMAS NO MAPA
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(ifsp); //ponto central
        circleOptions.radius(1000); //distância em metros
        circleOptions.fillColor(Color.argb(128,255, 0, 0));

        mMap.addCircle(circleOptions);*/

        // ADD MARCADOR
        mMap.addMarker(new MarkerOptions().position(ifsp).title("IFSP"));

        //CONFIGURANDO O ZOOM DA CÂMERA
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ifsp, 14));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //CASO A PERMISSÃO SEJA NEGADA
        for (int permissaoResultado : grantResults) {
            if (permissaoResultado == PackageManager.PERMISSION_DENIED) {

                //MENSAGEM DE ALERTA
                alertaValidacaoPermissao();
            } else if (permissaoResultado == PackageManager.PERMISSION_GRANTED) {

                //RECUPERAR A LOCALIZAÇÃO DO USUÁRIO
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 1, locationListener);

                }
            }
        }
    }

    private void alertaValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void abrirLogin(View view){
        Intent i = new Intent(MapsActivity.this, LoginActivity.class);
        startActivity(i);

    }


    public void abrirMenu(View view){
        Intent i = new Intent(MapsActivity.this, MenuActivity.class);
        startActivity(i);

    }
}
