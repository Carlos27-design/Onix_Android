package com.inacap.onix;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class mostrarEntregas extends AppCompatActivity {

    private ListView lsEntregas;
    private Button iniciarEntregas;
    RequestQueue request;
    private AsyncHttpClient client;
    Bundle bundles = new Bundle();
    private ArrayList<Entrega> lista = new ArrayList<>();
    private Ruta r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_entregas);

        lsEntregas = (ListView) findViewById(R.id.lsEntregas);
        iniciarEntregas = (Button) findViewById(R.id.btnIniciarEntrega);
        client = new AsyncHttpClient();

        lsEntregas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Entrega e = lista.get(position);
                int ID = e.getId();
                Intent finalizarEntrega = new Intent(mostrarEntregas.this, finalizarEntrega.class);
                Bundle bundle = new Bundle();
                finalizarEntrega.putExtra("id", ID);
                finalizarEntrega.putExtra("idRu",r);
                finalizarEntrega.putExtras(bundle);
                startActivity(finalizarEntrega);


            }


        });




        iniciarEntregas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maps = new Intent(mostrarEntregas.this, Maps.class);
                Bundle bundle = new Bundle();
                maps.putExtra("lista", lista);
                maps.putExtra("ruta", r);
                maps.putExtras(bundle);
                startActivity(maps);
            }
        });


        ObtenerEntrega();

    }





    private void ObtenerEntrega() {
        bundles = getIntent().getExtras();
        int id = bundles.getInt("id");
        r = bundles.getParcelable("ruta");

        String url = "https://onixs.000webhostapp.com/ObtenerRutaOrdenada.php?id=" + id;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    ListarEntregas(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void ListarEntregas(String s) {
        lista = new ArrayList<>();
        try {
            JSONArray jsonArreglos = new JSONArray(s);
            for (int i = 0; i < jsonArreglos.length(); i++) {
                Entrega e = new Entrega();
                e.setId(jsonArreglos.getJSONObject(i).getInt("id"));
                e.setUsuario_id(jsonArreglos.getJSONObject(i).getInt("usuario_id"));
                e.setVehiculo_id(jsonArreglos.getJSONObject(i).getInt("vehiculo_id"));
                e.setRuta_id(jsonArreglos.getJSONObject(i).getInt("ruta_id"));
                e.setEstado_id(jsonArreglos.getJSONObject(i).getInt("estado_id"));
                e.setDireccionEntregaNombre(jsonArreglos.getJSONObject(i).getString("direccionEntregaNombre"));
                e.setDireccionEntrega(jsonArreglos.getJSONObject(i).getString("direccionEntrega"));
                e.setIndicaciones(jsonArreglos.getJSONObject(i).getString("indicaciones"));
                e.setNroDocumentoEntregado(jsonArreglos.getJSONObject(i).getString("nroDocumentoEntregado"));
                e.setFechaInicio(jsonArreglos.getJSONObject(i).getString("fechaInicio"));
                e.setFechaEntrega(jsonArreglos.getJSONObject(i).getString("fechaEntregado"));
                e.setRecibidoRut(jsonArreglos.getJSONObject(i).getString("recibidoRut"));
                e.setRecibidoNombre(jsonArreglos.getJSONObject(i).getString("recibidoNombre"));
                e.setRecibidoApellido(jsonArreglos.getJSONObject(i).getString("recibidoApellido "));

                lista.add(e);
            }
            ArrayAdapter adapter = new ArrayAdapter(mostrarEntregas.this, android.R.layout.simple_list_item_1, lista);
            lsEntregas.setAdapter(adapter);
            Toast.makeText(mostrarEntregas.this, "Se han cargado los datos", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(mostrarEntregas.this, "No llegan los datos de la base de datos", Toast.LENGTH_LONG).show();
            e.getMessage();
        }
    }


}