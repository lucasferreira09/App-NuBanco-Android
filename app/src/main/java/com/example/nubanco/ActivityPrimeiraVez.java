package com.example.nubanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityPrimeiraVez extends AppCompatActivity {

    private Button btnComecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeira_vez);

        //Muda a cor da Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.corTelaInicial2));
        }

        btnComecar = findViewById(R.id.btnComecar);

        btnComecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actvCadastro = new Intent(ActivityPrimeiraVez.this, ActivityCadastroUser.class);
                startActivity(actvCadastro);
                finish();
            }
        });
    }
}