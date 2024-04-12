package com.example.nubanco.cartaocredito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nubanco.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class ActivityFaturaCartao extends AppCompatActivity {

    private TextView valorFatura;
    private TextView limiteDisponivelFatura;
    private ImageButton buttonPagar;
    private Button btnPagar;
    private ImageButton closeFaturaCartao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatura_cartao);

        valorFatura = findViewById(R.id.valorFatura);
        limiteDisponivelFatura = findViewById(R.id.limiteDisponivelFatura);
        buttonPagar = findViewById(R.id.buttonPagar);
        closeFaturaCartao = findViewById(R.id.closeFaturaCartao);
        btnPagar = findViewById(R.id.btnPagar);

        Double fatura = getIntent().getDoubleExtra("valorFatura", 0);
        Double meuSaldoDisponivel = getIntent().getDoubleExtra("meuSaldoDouble", 0);


        valorFatura.setText("R$ " + formataValor(Double.valueOf(fatura)));
        limiteDisponivelFatura.setText("Limite disponível " + formataValor(getIntent().getDoubleExtra("meuLimiteDisponivel", 0)));

        buttonPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fatura == 0.0) {
                    buttonPagar.setEnabled(false);
                    Toast.makeText(ActivityFaturaCartao.this, "SEM FATURA NO MOMENTO", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent actvPagandoFatura = new Intent(ActivityFaturaCartao.this, ActivityPagarFatura.class);
                    actvPagandoFatura.putExtra("valorFatura", fatura);
                    actvPagandoFatura.putExtra("meuSaldoDisponivel", meuSaldoDisponivel);
                    startActivity(actvPagandoFatura);
                }

            }
        });
        //FAZ O MESMO QUE O buttonPagar (Logo Acima)
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fatura == 0.0) {
                    buttonPagar.setEnabled(false);
                    Toast.makeText(ActivityFaturaCartao.this, "SEM FATURA NO MOMENTO", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent actvPagandoFatura = new Intent(ActivityFaturaCartao.this, ActivityPagarFatura.class);
                    actvPagandoFatura.putExtra("valorFatura", fatura);
                    actvPagandoFatura.putExtra("meuSaldoDisponivel", meuSaldoDisponivel);
                    startActivity(actvPagandoFatura);
                }
            }
        });

        closeFaturaCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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