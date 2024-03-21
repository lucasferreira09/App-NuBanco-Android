package com.example.nubanco.transferencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nubanco.deposito.ActivityDadosDeposit;
import com.example.nubanco.R;

public class ActivityTransferencia extends AppCompatActivity {

    private ImageButton buttonTransferir;
    private EditText valorEnviar;
    private TextView saldoDisponivel;
    private TextView textTransferenciaPix;
    private ImageButton closeTranferencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia);

        Double meuSaldoDouble = getIntent().getDoubleExtra("meuSaldoDouble", 0);
        String meuSaldoFormatado = getIntent().getStringExtra("meuSaldoFormatado");

        buttonTransferir = findViewById(R.id.buttonTransferir);
        valorEnviar = findViewById(R.id.valorEnviar);
        saldoDisponivel = findViewById(R.id.saldoDisponivel);
        textTransferenciaPix = findViewById(R.id.textTransferenciaPix);
        closeTranferencia = findViewById(R.id.closeTranferencia);

        /*
        Aqui, essa Activity foi reaproveitada pela a Activity de déposito.
        Quando clicar em depositar usando Pix, será redirecionado para cá.

        //Muda a String de "Transferir por Pix" para "Depósitar usando Pix"
        */
        configurarTexto(getIntent().getStringExtra("metodoDeposito"));

        saldoDisponivel.setText("Saldo disponível R$ " + meuSaldoFormatado);
        buttonTransferir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textTransferenciaPix.getText().toString().equals(
                        getIntent().getStringExtra("metodoDeposito"))) {
                    Intent actvDepositDados = new Intent(ActivityTransferencia.this, ActivityDadosDeposit.class);
                    actvDepositDados.putExtra("valorDepositado", valorEnviar.getText().toString());
                    startActivity(actvDepositDados);

                }
                else {
                    if (TextUtils.isEmpty(valorEnviar.getText())){
                        valorEnviar.setError("Número Inválido");
                    }
                    else {
                        Double valorEnviado = Double.parseDouble(valorEnviar.getText().toString());
                        Intent actvTransf2= new Intent(ActivityTransferencia.this, ActivityTransferencia2.class);
                        actvTransf2.putExtra("valorTransferidoDouble", valorEnviado);
                        actvTransf2.putExtra("meuSaldo", meuSaldoDouble);
                        startActivity(actvTransf2);
                    }
                }
            }
    });

        closeTranferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void configurarTexto(String novoTexto) {
        if (textTransferenciaPix != null && novoTexto != null) {
            textTransferenciaPix.setText(novoTexto);

            //Como será Depósito, não é preciso mostrar o saldo
            saldoDisponivel.setVisibility(View.INVISIBLE);
        }
    }

}