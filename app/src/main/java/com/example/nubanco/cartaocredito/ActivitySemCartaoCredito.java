package com.example.nubanco.cartaocredito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nubanco.R;

public class ActivitySemCartaoCredito extends AppCompatActivity {

    private Button buttonQueroCartao;
    private Button buttonNaoQuerCartao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_cartao_credito);

        //Muda a cor da Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.roxoSemCartao));
        }
        buttonQueroCartao = findViewById(R.id.buttonQueroCartao);
        buttonNaoQuerCartao = findViewById(R.id.buttonNaoQuerCartao);

        buttonQueroCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actvPedindoCartao = new Intent(ActivitySemCartaoCredito.this, ActivityPedindoCartao.class);
                startActivity(actvPedindoCartao);

            }
        });
        buttonNaoQuerCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}