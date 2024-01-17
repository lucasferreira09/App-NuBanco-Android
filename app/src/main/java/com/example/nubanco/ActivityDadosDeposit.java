package com.example.nubanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Locale;

public class ActivityDadosDeposit extends AppCompatActivity {

    private TextView depositandoValor;
    private TextView nomeDaConta;
    private Button confirmaDeposito;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_deposit);


        depositandoValor = findViewById(R.id.depositandoValor);
        nomeDaConta = findViewById(R.id.nomeDaConta);
        confirmaDeposito = findViewById(R.id.confirmaDeposito);

        nomeDaConta.setText(ActivityCadastroUser.myBank.getName());
        Double depositando = Double.valueOf(getIntent().getStringExtra("valorDepositado"));

        depositandoValor.setText("R$ " + formataValor(depositando));
        nomeDaConta.setText(ActivityCadastroUser.myBank.getName());

        confirmaDeposito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCadastroUser.myBank.deposit(depositando);
                Toast.makeText(ActivityDadosDeposit.this, "Depositado com Sucesso", Toast.LENGTH_SHORT).show();
                voltaActivityInicial();
            }
        });
    }
    public void voltaActivityInicial() {
        /*
        É preciso se certificar que quando o depósito for realizado, o usuário
        não possa voltar para as ActivityAnterior e somente para a ActivityPrincipal.
         */
        finish();
        // Cria um novo intent para a atividade principal
        Intent intent = new Intent(ActivityDadosDeposit.this, MainActivity.class);

        // DefinE flags para limpar a pilha de atividades e iniciar uma nova tarefa
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Inicia a atividade principal
        startActivity(intent);
    }
    public String formataValor(Double valor){
        Locale locale = new Locale("pt", "BR");
        return NumberFormat.getInstance(locale).format(valor);
    }
}