package com.example.nubanco.deposito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nubanco.R;
import com.example.nubanco.transferencia.ActivityTransferencia;

public class ActivityDepositar extends AppCompatActivity {

    private View viewCardDepositPix;
    private View viewCardDepositConta;
    private View viewCardDepositDinheiro;
    private ImageButton closeDeposit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depositar);

        viewCardDepositPix = findViewById(R.id.viewCardDepositPix);
        viewCardDepositConta = findViewById(R.id.viewCardDepositConta);
        viewCardDepositDinheiro = findViewById(R.id.viewCardDepositDinheiro);
        closeDeposit = findViewById(R.id.closeDeposit);

        viewCardDepositPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actvTransferencia = new Intent(ActivityDepositar.this, ActivityTransferencia.class);
                actvTransferencia.putExtra("metodoDeposito", "Qual valor você quer\ndepositar usando Pix?");
                startActivity(actvTransferencia);
            }
        });
        viewCardDepositConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actvDepositConta = new Intent(ActivityDepositar.this, ActivityDepositoPorConta.class);
                startActivity(actvDepositConta);
            }
        });
        viewCardDepositDinheiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityDepositar.this, "OPÇÃO NÃO DISPONÍVEL", Toast.LENGTH_SHORT).show();
            }
        });

        closeDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}