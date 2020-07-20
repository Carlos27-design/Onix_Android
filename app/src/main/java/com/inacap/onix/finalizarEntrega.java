package com.inacap.onix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class finalizarEntrega extends AppCompatActivity {

    Bundle bundle = new Bundle();
    private int ids;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_entrega);
        ids = bundle.getInt("ids");
        id = bundle.getInt("id");
    }
}