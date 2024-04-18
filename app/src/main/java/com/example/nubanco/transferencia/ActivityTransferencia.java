package com.example.nubanco.transferencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nubanco.databinding.ActivityTransferenciaBinding;
import com.example.nubanco.deposito.ActivityDadosDeposit;
import com.example.nubanco.R;

public class ActivityTransferencia extends AppCompatActivity {

    private ActivityTransferenciaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTransferenciaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Double meuSaldoDouble = getIntent().getDoubleExtra("meuSaldoDouble", 0);
        String meuSaldoFormatado = getIntent().getStringExtra("meuSaldoFormatado");

        /*
        Aqui, essa Activity foi reaproveitada pela a Activity de déposito.
        Quando clicar em depositar usando Pix, será redirecionado para cá.

        //Muda a String de "Transferir por Pix" para "Depósitar usando Pix"
        */
        configurarTexto(getIntent().getStringExtra("metodoDeposito"));

        binding.saldoDisponivel.setText("Saldo disponível R$ " + meuSaldoFormatado);
        binding.buttonTransferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.textTransferenciaPix.getText().toString().equals(
                        getIntent().getStringExtra("metodoDeposito"))) {

                    Intent actvDepositDados = new Intent(ActivityTransferencia.this, ActivityDadosDeposit.class);
                    actvDepositDados.putExtra("valorDepositado", binding.valorEnviar.getText().toString());
                    startActivity(actvDepositDados);

                }
                else {
                    if (TextUtils.isEmpty(binding.valorEnviar.getText())){
                        binding.valorEnviar.setError("Número Inválido");
                    }
                    else {
                        Double valorEnviado = Double.parseDouble(binding.valorEnviar.getText().toString());

                        Intent actvTransf2= new Intent(ActivityTransferencia.this, ActivityTransferencia2.class);
                        actvTransf2.putExtra("valorTransferidoDouble", valorEnviado);
                        actvTransf2.putExtra("meuSaldo", meuSaldoDouble);
                        startActivity(actvTransf2);
                    }
                }
            }
    });

        binding.closeTranferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void configurarTexto(String novoTexto) {
        if (binding.textTransferenciaPix != null && novoTexto != null) {
            binding.textTransferenciaPix.setText(novoTexto);

            //Como será Depósito, não é preciso mostrar o saldo
            binding.saldoDisponivel.setVisibility(View.INVISIBLE);
        }
    }

}