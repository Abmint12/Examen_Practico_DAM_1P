package com.practicas.miperfilug;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    TextView tvSaludo;
    Button btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvSaludo = findViewById(R.id.tvSaludo);
        btnEliminar = findViewById(R.id.btnEliminar);

        String nombre = getIntent().getStringExtra("usuario");
        tvSaludo.setText("Bienvenido, " + nombre);

        btnEliminar.setOnClickListener(v -> eliminarPerfil());
    }

    private void eliminarPerfil() {
        // Eliminar archivo interno
        deleteFile("perfil.txt");

        // Eliminar SharedPreferences
        SharedPreferences prefs = getSharedPreferences("datos", MODE_PRIVATE);
        prefs.edit().remove("usuario").apply();

        Toast.makeText(this, "Perfil eliminado", Toast.LENGTH_SHORT).show();
        finish();
    }
}