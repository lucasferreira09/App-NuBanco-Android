package com.example.nubanco.transferencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nubanco.contabancaria.ActivityCadastroUser;
import com.example.nubanco.cartaocredito.ActivitySemCartaoCredito;
import com.example.nubanco.R;

import java.text.NumberFormat;
import java.util.Locale;

public class ActivityTransferencia3 extends AppCompatActivity {
    public static final int OPCAO1 = 1;
    public static int escolhaTransferencia = 0;
    public static final int OPCAO2 = 2;
    private Button confirmaTransferencia;
    private TextView textTransferenciaFinal;
    private TextView enviadoPara;
    private TextView reviewValorTransferido;
    private RadioGroup radioGroupTransferencia;
    private RadioButton opcaoCredito;
    private RadioButton opcaoSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia3);

        confirmaTransferencia = findViewById(R.id.confirmaTransferencia);
        textTransferenciaFinal = findViewById(R.id.textTransferenciaFinal);
        enviadoPara = findViewById(R.id.enviadoPara);
        radioGroupTransferencia = findViewById(R.id.radioGroupTransferencia);
        reviewValorTransferido = findViewById(R.id.reviewValorTransferido);
        opcaoCredito = findViewById(R.id.opcaoCredito);
        opcaoSaldo = findViewById(R.id.opcaoSaldo);


        Double valorTransferidoDouble = getIntent().getDoubleExtra("valorTransferidoDouble", 0);

        reviewValorTransferido.setText("R$ " + formataValor(valorTransferidoDouble));
        String destinoTransferencia = getIntent().getStringExtra("destinoTransferencia");
        enviadoPara.setText("Para " + destinoTransferencia);
        Double meuSaldo = getIntent().getDoubleExtra("meuSaldo", 0);


        opcaoSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolhaTransferencia = 1;
            }
        });
        opcaoCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolhaTransferencia = 2;

                //Se não tiver Cartão, é redirecionado para pedir um
                if (ActivityCadastroUser.myBank.getCartaoExiste() == false){
                    startActivity(new Intent(ActivityTransferencia3.this, ActivitySemCartaoCredito.class));
                }
            }
        });

        Intent actvComprovante = new Intent(new Intent(ActivityTransferencia3.this, ActivityTransferenciaRealizada.class));
        actvComprovante.putExtra("valorComprovante", formataValor(valorTransferidoDouble));

        confirmaTransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (escolhaTransferencia == 1){
                    //Opcao Saldo escolhida
                    if (valorTransferidoDouble > meuSaldo){
                        opcaoSaldo.setError("SALDO INSUFICIENTE!");
                        Toast.makeText(ActivityTransferencia3.this, "SALDO INSUFICIENTE!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else{
                        startActivity(actvComprovante);
                        ActivityCadastroUser.myBank.transfere(valorTransferidoDouble);
                    }

                }
                else if (escolhaTransferencia == 2) {
                    //Opcao Crédito escolhida
                    if (valorTransferidoDouble > ActivityCadastroUser.myBank.getLimiteCreditoDisponivel()){
                        opcaoCredito.setError("LIMITE INSUFICIENTE");
                        return;
                    }
                    else{
                        startActivity(actvComprovante);
                        ActivityCadastroUser.myBank.compraCartaoCredito(valorTransferidoDouble);
                    }
                }
            }
        });

    }
    public String formataValor(Double valor){
        Locale locale = new Locale("pt", "BR");
        return NumberFormat.getInstance(locale).format(valor);
    }
}