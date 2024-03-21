package com.example.nubanco.deposito;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nubanco.R;
import com.example.nubanco.contabancaria.ActivityCadastroUser;

public class ActivityDepositoPorConta extends AppCompatActivity {

    private TextView nome;
    private TextView agencia;
    private TextView conta;
    private ImageButton closeDeposit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposito_por_conta);

        nome = findViewById(R.id.nome);
        agencia = findViewById(R.id.agencia);
        conta = findViewById(R.id.conta);
        closeDeposit = findViewById(R.id.closeDeposit);

        nome.setText(ActivityCadastroUser.myBank.getName());
        agencia.setText(ActivityCadastroUser.myBank.getAgencia());
        conta.setText(ActivityCadastroUser.myBank.getNumeroConta());

        closeDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}