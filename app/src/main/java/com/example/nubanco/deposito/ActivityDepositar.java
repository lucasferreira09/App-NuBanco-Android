package com.example.nubanco.deposito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.nubanco.R;
import com.example.nubanco.databinding.ActivityDadosDepositBinding;
import com.example.nubanco.databinding.ActivityDepositarBinding;
import com.example.nubanco.transferencia.ActivityTransferencia;

public class ActivityDepositar extends AppCompatActivity {

    private ActivityDepositarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDepositarBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.viewCardDepositPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actvTransferencia = new Intent(ActivityDepositar.this, ActivityTransferencia.class);
                actvTransferencia.putExtra("metodoDeposito", "Qual valor você quer\ndepositar usando Pix?");
                startActivity(actvTransferencia);
            }
        });

        binding.viewCardDepositConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actvDepositConta = new Intent(ActivityDepositar.this, ActivityDepositoPorConta.class);
                startActivity(actvDepositConta);
            }
        });
        binding.viewCardDepositDinheiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityDepositar.this, "OPÇÃO NÃO DISPONÍVEL", Toast.LENGTH_SHORT).show();
            }
        });

        binding.closeDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}