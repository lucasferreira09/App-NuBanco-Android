package com.example.nubanco;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ActivityDadosConta extends AppCompatActivity {

    private TextView olaNomeConta;
    private TextView conta;
    private TextView agencia;
    private ImageButton closeDadosConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_conta);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.gray));
        }

        olaNomeConta = findViewById(R.id.olaNomeConta);
        conta = findViewById(R.id.conta);
        agencia = findViewById(R.id.agencia);
        closeDadosConta = findViewById(R.id.closeDadosConta);

        olaNomeConta.setText(ActivityCadastroUser.myBank.getFirstName());
        conta.setText("Conta " + ActivityCadastroUser.myBank.getNumeroConta());
        agencia.setText("Agência " + ActivityCadastroUser.myBank.getAgencia() + " • ");


        closeDadosConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}