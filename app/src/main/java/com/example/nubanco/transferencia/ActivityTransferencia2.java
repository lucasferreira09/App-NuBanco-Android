package com.example.nubanco.transferencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nubanco.R;

import java.text.NumberFormat;
import java.util.Locale;

public class ActivityTransferencia2 extends AppCompatActivity {

    private TextView textValorEnviado;
    private ImageButton buttonTransferir2;
    private EditText destinoTransferencia;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia2);

        buttonTransferir2 = findViewById(R.id.buttonTransferir2);
        destinoTransferencia = findViewById(R.id.destinoTransferencia);
        textValorEnviado = findViewById(R.id.textValorEnviado);

        Double valorTransferidoDouble = getIntent().getDoubleExtra("valorTransferidoDouble", 0);
        Double meuSaldoDouble = getIntent().getDoubleExtra("meuSaldo", 0);

        textValorEnviado.setText("Para quem quer transferir\nR$ " + formataValor(valorTransferidoDouble));

        buttonTransferir2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actvTransfer3= new Intent(ActivityTransferencia2.this, ActivityTransferencia3.class);
                actvTransfer3.putExtra("meuSaldo", meuSaldoDouble);
                actvTransfer3.putExtra("valorTransferidoDouble", valorTransferidoDouble);
                actvTransfer3.putExtra("destinoTransferencia", destinoTransferencia.getText().toString());
                startActivity(actvTransfer3);
            }
        });

    }
    public String formataValor(Double valor){
        Locale locale = new Locale("pt", "BR");
        return NumberFormat.getInstance(locale).format(valor);
    }
}