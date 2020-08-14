package com.inacap.onix;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap, googleMap;
    private double latitudOrigen;
    private double longitudOrigen;
    private double lat;
    private double lng;
    Boolean actualPosition = true;
    JSONObject json;
    Bundle bundle = new Bundle();
    ArrayList<Entrega> lista = new ArrayList<>();
    Ruta r;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


        bundle = getIntent().getExtras();
        r = bundle.getParcelable("ruta");
        lista = bundle.getParcelableArrayList("lista");

        String[] coordenadasRuta = r.getDireccionInicio().split(",");
        Double latR = Double.parseDouble(coordenadasRuta[0]);
        Double lngR = Double.parseDouble(coordenadasRuta[1]);
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latR, lngR))
                .title("Punto de Inicio "+r.getDireccionInicioNombre()));

        for (int i = 0; i < lista.size(); i++) {
            String direccionEntrega = lista.get(i).getDireccionEntrega();
            String[] coordenadas = direccionEntrega.split(",");
            Double lat = Double.parseDouble(coordenadas[0]);
            Double lng = Double.parseDouble(coordenadas[1]);
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(lat, lng))
                    .title(lista.get(i).getDireccionEntregaNombre()));
        }
        // Add a marker in Sydney and move the camera
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
            return;
        }


        mMap.setMyLocationEnabled(true);


        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                if (actualPosition) {
                    latitudOrigen = location.getLatitude();
                    longitudOrigen = location.getLongitude();
                    actualPosition = false;

                    LatLng miPosicion = new LatLng(latitudOrigen, longitudOrigen);
                    mMap.addMarker(new MarkerOptions().position(miPosicion).title("Esta es tu ubicacion"));
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(new LatLng(latitudOrigen, longitudOrigen))
                            .zoom(14)
                            .bearing(30)
                            .build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                    String key = "AIzaSyCPbmbwnzr6pTvylADbwuOy1ycWS6aSGmQ";
                    //VERIFICAR SI SON MÁS DE DOS ENTREGAS, NECESITA WAYPOINTS
                    //ULTIMA ENTREGA, DEBE ESTAR EN DESTINO

                    if (lista.size() >= 1 && lista.size() <= 2) {

                        String origen = r.getDireccionInicio();
                        String destino = lista.get(lista.size() - 1).getDireccionEntrega();
                        url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + origen + "&destination=" + destino;
                        url += "&waypoints=";
                        int contador = 0;
                        for (int i = 0; i < (lista.size() - 1); i++) {
                            contador += 1;
                            if(contador < lista.size() - 1){
                                url += lista.get(i).getDireccionEntrega() + "|";
                            }else{
                                url += lista.get(i).getDireccionEntrega();
                            }

                        }
                        url += "&key=AIzaSyCPbmbwnzr6pTvylADbwuOy1ycWS6aSGmQ";
                    }
                    if (lista.size() > 2) {
                        System.out.println("aqui");
                        String origen = r.getDireccionInicio();
                        String destino = lista.get(lista.size() - 1).getDireccionEntrega();
                        url = "https://maps.googleapis.com/maps/api/directions/json?origin=" + origen + "&destination=" + destino;
                        url += "&waypoints=";
                        int contador = 0;
                        for (int i = 0; i < (lista.size() - 1); i++) {
                            contador += 1;
                            if(contador < lista.size() - 1){
                                url += lista.get(i).getDireccionEntrega() + "|";
                            }else{
                                url += lista.get(i).getDireccionEntrega();
                            }

                        }
                        url += "&key=AIzaSyCPbmbwnzr6pTvylADbwuOy1ycWS6aSGmQ";
                    }
                    System.out.println("====================================");
                    System.out.println("TEST");
                    System.out.println("test"+url);
                    //String url = "https://maps.googleapis.com/maps/api/directions/json?origin=-40.91572950760404,-73.15556645393373&destination=-40.91131702279448,-73.15750837326051&waypoints=-40.91562816366299,-73.1557238104142&key=AIzaSyCPbmbwnzr6pTvylADbwuOy1ycWS6aSGmQ";
                    //String url = "https://maps.googleapis.com/maps/api/directions/json?origin=-40.91572950760404,-73.15556645393373&destination=-40.91131702279448,-73.15750837326051&waypoints=-40.91562816366299,-73.1557238104142&key=AIzaSyCPbmbwnzr6pTvylADbwuOy1ycWS6aSGmQ";
                    RequestQueue request = Volley.newRequestQueue(Maps.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                json = new JSONObject(response);
                                trazarRuta(json);
                            } catch (JSONException e) {
                                e.printStackTrace();
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


        });


    }

    private void CoordGPS(String entregas, Context context, String nombre) {
        Geocoder gc = new Geocoder(context, Locale.ENGLISH);
        List<Address> addresses;
        try {
            addresses = gc.getFromLocationName(entregas, 5);
            if (addresses.size() > 0) {
                this.lat = addresses.get(0).getLatitude();
                this.lng = addresses.get(0).getLongitude();
                LatLng entrega = new LatLng(lat, lng);
                mMap.addMarker(new MarkerOptions().position(entrega).title(nombre));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void trazarRuta(JSONObject json) {
        JSONArray jRoutes;
        JSONArray jLegs;
        JSONArray jSteps;


        try {
            jRoutes = json.getJSONArray("routes");

            for (int i = 0; i < jRoutes.length(); i++) {
                jLegs = ((JSONObject) (jRoutes.get(i))).getJSONArray("legs");

                for (int j = 0; j < jLegs.length(); j++) {
                    jSteps = ((JSONObject) jLegs.get(j)).getJSONArray("steps");

                    for (int k = 0; k < jSteps.length(); k++) {


                        String polyline = "" + ((JSONObject) ((JSONObject) jSteps.get(k)).get("polyline")).get("points");
                        Log.i("end", "" + polyline);
                        List<LatLng> list = PolyUtil.decode(polyline);
                        mMap.addPolyline(new PolylineOptions().addAll(list).color(Color.BLUE).width(5));
                    }
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }


}