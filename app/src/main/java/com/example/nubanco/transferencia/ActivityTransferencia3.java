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
import com.example.nubanco.databinding.ActivityTransferencia2Binding;
import com.example.nubanco.databinding.ActivityTransferencia3Binding;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class ActivityTransferencia3 extends AppCompatActivity {
    public static final int OPCAO1 = 1;
    public static int escolhaTransferencia = 0;
    public static final int OPCAO2 = 2;

    private ActivityTransferencia3Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTransferencia3Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        Double valorTransferidoDouble = getIntent().getDoubleExtra("valorTransferidoDouble", 0);
        String destinoTransferencia = getIntent().getStringExtra("destinoTransferencia");
        Double meuSaldo = getIntent().getDoubleExtra("meuSaldo", 0);

        binding.reviewValorTransferido.setText("R$ " + formataValor(valorTransferidoDouble));
        binding.enviadoPara.setText("Para " + destinoTransferencia);


        binding.opcaoSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolhaTransferencia = 1;
            }
        });
        binding.opcaoCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escolhaTransferencia = 2;

                //Se não tiver Cartão, é redirecionado para pedir um
                if (!ActivityCadastroUser.myBank.getCartaoExiste()){
                    startActivity(new Intent(ActivityTransferencia3.this, ActivitySemCartaoCredito.class));
                }
            }
        });

        Intent actvComprovante = new Intent(new Intent(ActivityTransferencia3.this, ActivityTransferenciaRealizada.class));
        actvComprovante.putExtra("valorComprovante", formataValor(valorTransferidoDouble));

        binding.confirmaTransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (escolhaTransferencia == 1){

                    //Opcao Saldo escolhida
                    if (valorTransferidoDouble > meuSaldo){
                        binding.opcaoSaldo.setError("SALDO INSUFICIENTE!");
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
                        binding.opcaoCredito.setError("LIMITE INSUFICIENTE");
                        Toast.makeText(ActivityTransferencia3.this, "LIMITE INSUFICIENTE!", Toast.LENGTH_SHORT).show();
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
        String padrao = "###,##0.00";
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(padrao);

        return decimalFormat.format(valor);
    }
}