package com.inacap.onix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class finalizarEntrega extends AppCompatActivity {
    private EditText rut;
    private EditText nombre;
    private EditText apellido;
    private Button finalizarEntrega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_entrega);

        rut = (EditText) findViewById(R.id.txtRut);
        nombre = (EditText) findViewById(R.id.txtNombre);
        apellido = (EditText) findViewById(R.id.txtApellido);
        finalizarEntrega = (Button) findViewById(R.id.btnFinalizarEntrega);

        finalizarEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}