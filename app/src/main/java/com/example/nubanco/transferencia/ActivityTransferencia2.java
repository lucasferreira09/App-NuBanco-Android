package com.example.nubanco.transferencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nubanco.R;
import com.example.nubanco.databinding.ActivityTransferencia2Binding;
import com.example.nubanco.databinding.ActivityTransferenciaBinding;

import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Locale;

public class ActivityTransferencia2 extends AppCompatActivity {

    private ActivityTransferencia2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTransferencia2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        Double valorTransferidoDouble = getIntent().getDoubleExtra("valorTransferidoDouble", 0);
        Double meuSaldoDouble = getIntent().getDoubleExtra("meuSaldo", 0);

        binding.textValorEnviado.setText("Para quem quer transferir\nR$ " + formataValor(valorTransferidoDouble));

        binding.buttonTransferir2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actvTransfer3= new Intent(ActivityTransferencia2.this, ActivityTransferencia3.class);
                actvTransfer3.putExtra("meuSaldo", meuSaldoDouble);
                actvTransfer3.putExtra("valorTransferidoDouble", valorTransferidoDouble);
                actvTransfer3.putExtra("destinoTransferencia", binding.destinoTransferencia.getText().toString());
                startActivity(actvTransfer3);
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