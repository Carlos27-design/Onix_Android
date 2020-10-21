package com.inacap.onix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private Button iniciarSesion;
    private EditText txtrut, txtcontrasena;

    RequestQueue request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtrut = (EditText) findViewById(R.id.txtRut);
        txtcontrasena = (EditText) findViewById(R.id.txtContrasena);
        iniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);

        iniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarSesion();
            }
        });

    }

    private void IniciarSesion() {
        request = Volley.newRequestQueue(this);
        String url = "https://onixs.000webhostapp.com/Login.php?user=" + txtrut.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    String contrasena = jsonArray.getString(0);

                    if (contrasena.equals(txtcontrasena.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Bienvenido a Onix", Toast.LENGTH_LONG).show();
                        Intent menuTransportista = new Intent(MainActivity.this, menuTransportista.class);
                        Bundle bundle = new Bundle();
                        menuTransportista.putExtra("rut", txtrut.getText().toString());
                        menuTransportista.putExtras(bundle);
                        startActivity(menuTransportista);
                    } else {
                        Toast.makeText(MainActivity.this, "Verifique su rut / Contrasena", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "El usuario no se encuentra en la base de datos", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.add(stringRequest);
    }
}