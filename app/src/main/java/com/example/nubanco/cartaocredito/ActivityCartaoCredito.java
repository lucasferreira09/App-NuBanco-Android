package com.example.nubanco.cartaocredito;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nubanco.contabancaria.ActivityCadastroUser;
import com.example.nubanco.R;

public class ActivityCartaoCredito extends AppCompatActivity {

    private TextView nomeCartao;
    private TextView validadeCartao;
    private ImageButton closecartaoCredito;
    private TextView numeroCartao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao_credito);

        validadeCartao = findViewById(R.id.validadeCartao);
        nomeCartao = findViewById(R.id.nomeCartao);
        closecartaoCredito = findViewById(R.id.closeCartaoCredito);
        numeroCartao = findViewById(R.id.numeroCartao);


        validadeCartao.setText(ActivityCadastroUser.myBank.getDataValidadeCartao());
        nomeCartao.setText(ActivityCadastroUser.myBank.getName());
        numeroCartao.setText(ActivityCadastroUser.myBank.getNumeroCartaoCredito());

        closecartaoCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}