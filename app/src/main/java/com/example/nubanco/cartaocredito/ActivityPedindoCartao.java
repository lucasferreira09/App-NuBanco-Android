package com.example.nubanco.cartaocredito;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nubanco.contabancaria.ActivityCadastroUser;
import com.example.nubanco.MainActivity;
import com.example.nubanco.R;
import com.example.nubanco.databinding.ActivityPagarFaturaBinding;
import com.example.nubanco.databinding.ActivityPedindoCartaoBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class ActivityPedindoCartao extends AppCompatActivity {

    private ActivityPedindoCartaoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPedindoCartaoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.textLiberamosLimit.setText("Liberamos seu limite, " + ActivityCadastroUser.myBank.getFirstName());

        //Cria o cartão de crédito
        ActivityCadastroUser.myBank.geraCartãoCredito();
        binding.valorLimite.setText("R$ " + formataValor(ActivityCadastroUser.myBank.getLimiteCreditoTotal()));


        //Botão Voltar fecha a Activity
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                voltaActivityInicial();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);


        binding.obrigadoCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltaActivityInicial();
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
    public void voltaActivityInicial() {
        finish();
        // Cria um novo intent para a atividade principal
        Intent intent = new Intent(ActivityPedindoCartao.this, MainActivity.class);

        // Define flags para limpar a pilha de atividades e iniciar uma nova tarefa
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Inicia a atividade principal
        startActivity(intent);
    }
}