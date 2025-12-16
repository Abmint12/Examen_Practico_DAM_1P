package com.practicas.miperfilug;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText etNombre;
    Button btnGuardar, btnVerPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("CICLO", "onCreate");

        etNombre = findViewById(R.id.etNombre);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnVerPerfil = findViewById(R.id.btnVerPerfil);

        btnGuardar.setOnClickListener(v -> guardarPerfil());

        btnVerPerfil.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Ingrese un nombre", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, ProfileActivity.class);
            intent.putExtra("usuario", nombre);
            startActivity(intent);
        });
    }

    private void guardarPerfil() {
        String nombre = etNombre.getText().toString().trim();

        if (nombre.isEmpty()) {
            Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        // SharedPreferences
        SharedPreferences prefs = getSharedPreferences("datos", MODE_PRIVATE);
        prefs.edit().putString("usuario", nombre).apply();

        // Fecha actual
        String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                .format(new Date());

        // Archivo interno
        String contenido = "Nombre: " + nombre + " FechaRegistro: " + fecha;

        try {
            FileOutputStream fos = openFileOutput("perfil.txt", MODE_PRIVATE);
            fos.write(contenido.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Perfil guardado", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("CICLO", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CICLO", "onResume");
    }
}