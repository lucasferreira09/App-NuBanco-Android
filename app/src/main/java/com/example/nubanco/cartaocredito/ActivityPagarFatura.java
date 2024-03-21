package com.example.nubanco.cartaocredito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nubanco.contabancaria.ActivityCadastroUser;
import com.example.nubanco.transferencia.ActivityTransferenciaRealizada;
import com.example.nubanco.MainActivity;
import com.example.nubanco.R;

import java.text.NumberFormat;
import java.util.Locale;

public class ActivityPagarFatura extends AppCompatActivity {

    private View viewPagarFatura;
    private ImageButton closePagarFatura;
    private TextView faturaAPagar;
    private TextView fatura;
    private TextView descricaoPagarFatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagar_fatura);
        viewPagarFatura = findViewById(R.id.viewPagarFatura);
        closePagarFatura = findViewById(R.id.closePagarFatura);
        faturaAPagar = findViewById(R.id.faturaAPagar);
        fatura= findViewById(R.id.fatura);
        descricaoPagarFatura = findViewById(R.id.descricaoPagarFatura);



        Double valorFatura = getIntent().getDoubleExtra("valorFatura", 0);
        Double meuSaldoDouble = getIntent().getDoubleExtra("meuSaldoDisponivel", 0);
        faturaAPagar.setText("R$ " + formataValor(valorFatura));
        fatura.setText("R$ " + formataValor(valorFatura));

        if ( valorFatura > meuSaldoDouble){
            descricaoPagarFatura.setText("O saldo de R$ " + formataValor(meuSaldoDouble) + " não é suficiente para\neste pagamento.");
            viewPagarFatura.setEnabled(false);
        }
        else{
            descricaoPagarFatura.setText("O saldo de R$ " + formataValor(meuSaldoDouble) + " é suficiente para\neste pagamento.");
        }
        viewPagarFatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCadastroUser.myBank.pagarFatura(valorFatura);

                Intent actvComprovante = new Intent(ActivityPagarFatura.this, ActivityTransferenciaRealizada.class);
                actvComprovante.putExtra("novoTextoComprovante", "Pagamento Realizado");
                actvComprovante.putExtra("valorComprovante", formataValor(valorFatura));
                startActivity(actvComprovante);

            }

        });
        closePagarFatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    public String formataValor(Double valor){
        Locale locale = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(locale).format(valor);
    }
    public void voltaActivityInicial() {
        finish();
        // Criar um novo intent para a atividade principal
        Intent intent = new Intent(ActivityPagarFatura.this, MainActivity.class);

        // Definir flags para limpar a pilha de atividades e iniciar uma nova tarefa
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Iniciar a atividade principal
        startActivity(intent);
    }
}