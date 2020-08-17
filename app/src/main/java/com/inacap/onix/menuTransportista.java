package com.inacap.onix;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class menuTransportista extends AppCompatActivity {

    private Button mostrarRuta, cerrarSesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_transportista);

        mostrarRuta = (Button) findViewById(R.id.btnMostrarRuta);
        cerrarSesion = (Button) findViewById(R.id.btnCerrarSesion);

        mostrarRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mostrarRuta = new Intent(menuTransportista.this, mostrarRuta.class);
                Bundle bundle = new Bundle();
                String Rut = bundle.toString();
                mostrarRuta.putExtra(Rut, "rut");
                mostrarRuta.putExtras(bundle);
                startActivity(mostrarRuta);
            }
        });

        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CerrarSesion();
            }
        });
    }

    private void CerrarSesion() {
        new AlertDialog.Builder(this)
                .setTitle("Cerrar Sesion")
                .setMessage("¿Desea Cerrar Sesion?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("Mensaje", "Se mantiene la sesion");
                    }
                })
                .show();
    }
}