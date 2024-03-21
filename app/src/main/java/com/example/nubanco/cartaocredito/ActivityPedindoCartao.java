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

import java.text.NumberFormat;
import java.util.Locale;

public class ActivityPedindoCartao extends AppCompatActivity {

    private TextView valorLimite;
    private Button obrigadoCredito;
    private TextView textLiberamosLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedindo_cartao);


        valorLimite = findViewById(R.id.valorLimite);
        obrigadoCredito = findViewById(R.id.obrigadoCredito);
        textLiberamosLimit = findViewById(R.id.textLiberamosLimit);

        textLiberamosLimit.setText("Liberamos seu limite, " + ActivityCadastroUser.myBank.getFirstName());
        //Cria o cartão de crédito
        ActivityCadastroUser.myBank.setNumeroCartao();
        ActivityCadastroUser.myBank.setCodigoCartao();
        ActivityCadastroUser.myBank.setValidadeCartao();
        ActivityCadastroUser.myBank.setLimiteCreditoTotal();
        ActivityCadastroUser.myBank.setCartaoExiste(true);
        valorLimite.setText("R$ " + formataValor(ActivityCadastroUser.myBank.getLimiteCreditoTotal()));


        //Botão Voltar fecha a Activity
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                voltaActivityInicial();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        obrigadoCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltaActivityInicial();
            }
        });
    }

    public String formataValor(Double valor){
        Locale locale = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(locale).format(valor);
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