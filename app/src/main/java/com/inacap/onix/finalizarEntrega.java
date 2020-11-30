package com.inacap.onix;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class finalizarEntrega extends AppCompatActivity {
    private EditText rut;
    private EditText nombre;
    private EditText apellido;
    private Button finalizarEntrega;
    Bundle bundle = new Bundle();
    private Ruta r;
    RequestQueue request;

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
                if(!rut.equals("") && !nombre.equals("") && !apellido.equals("")){
                    msgFinalizarEntrega();
                }
            }
        });


    }

    private void msgFinalizarEntrega() {
        new AlertDialog.Builder(this)
                .setTitle("Finalizar Entrega")
                .setMessage("¿Desea Finalizar su entrega?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fEntrega();
                        Toast.makeText(finalizarEntrega.this, "Se ha finalizado la entrega", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(finalizarEntrega.this, "Sigue activa la entrega", Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    private void fEntrega() {
        request = Volley.newRequestQueue(this);
        bundle = getIntent().getExtras();
        r = bundle.getParcelable("idRu");
        int idRuta = r.getId();
        int idEntrega = bundle.getInt("id");
        String url ="https://onixs.000webhostapp.com/ActActualizarEstadoAndroid.php?id=" + idEntrega + "&idRuta=" + idRuta;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.add(stringRequest);

    }

}