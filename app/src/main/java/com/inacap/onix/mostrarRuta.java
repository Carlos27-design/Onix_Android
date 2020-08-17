package com.inacap.onix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class mostrarRuta extends AppCompatActivity {

    private ListView lsRuta;
    private AsyncHttpClient client;
    private ArrayList<Ruta> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_ruta);

        lsRuta = (ListView) findViewById(R.id.lsRuta);
        client = new AsyncHttpClient();

        lsRuta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ruta r = lista.get(position);
                Intent mostrarEntrega = new Intent(mostrarRuta.this, mostrarEntregas.class);
                Bundle bundle = new Bundle();
                mostrarEntrega.putExtra("id", r.getId());
                mostrarEntrega.putExtra("ruta",r);
                mostrarEntrega.putExtras(bundle);
                startActivity(mostrarEntrega);
            }
        });

        ObtenerRuta();

    }

    private void ObtenerRuta() {
        Bundle bundle = new Bundle();
        String rut = bundle.toString();
        String url = "https://onixs.000webhostapp.com/mostrarRuta.php?rut="+ rut;
        client.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    ListarRuta(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void ListarRuta(String s) {
        lista = new ArrayList<>();
        try {
            JSONArray jsonArreglo = new JSONArray(s);
            for (int i = 0; i < jsonArreglo.length(); i++) {
                Ruta r = new Ruta();
                r.setId(jsonArreglo.getJSONObject(i).getInt("id"));
                r.setDireccionInicioNombre(jsonArreglo.getJSONObject(i).getString("direccionInicioNombre"));
                r.setDireccionInicio(jsonArreglo.getJSONObject(i).getString("direccionInicio"));
                r.setDireccionFinalNombre(jsonArreglo.getJSONObject(i).getString("direccionFinalNombre"));
                r.setDireccionFinal(jsonArreglo.getJSONObject(i).getString("direccionFinal"));
                r.setDistancia(jsonArreglo.getJSONObject(i).getString("distancia"));
                r.setFechaInicio(jsonArreglo.getJSONObject(i).getString("fechaInicio"));
                r.setFechaFin(jsonArreglo.getJSONObject(i).getString("fechaFin"));

                lista.add(r);
            }
            ArrayAdapter adapter = new ArrayAdapter(mostrarRuta.this, android.R.layout.simple_list_item_1, lista);
            lsRuta.setAdapter(adapter);
            Toast.makeText(mostrarRuta.this, "Los datos se cargaron", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(mostrarRuta.this, "No llegan los datos", Toast.LENGTH_LONG).show();
        }
    }
}