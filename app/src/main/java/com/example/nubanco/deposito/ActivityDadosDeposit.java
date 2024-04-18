package com.example.nubanco.deposito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nubanco.MainActivity;
import com.example.nubanco.R;
import com.example.nubanco.contabancaria.ActivityCadastroUser;
import com.example.nubanco.databinding.ActivityDadosDepositBinding;
import com.example.nubanco.databinding.ActivityTransferenciaRealizadaBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class ActivityDadosDeposit extends AppCompatActivity {

    private ActivityDadosDepositBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDadosDepositBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.nomeDaConta.setText(ActivityCadastroUser.myBank.getName());
        Double depositando = Double.valueOf(getIntent().getStringExtra("valorDepositado"));

        binding.depositandoValor.setText("R$ " + formataValor(depositando));
        binding.nomeDaConta.setText(ActivityCadastroUser.myBank.getName());

        binding.confirmaDeposito.setOnClickListener(new View.OnClickListener() {
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
        String padrao = "###,##0.00";
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(padrao);

        return decimalFormat.format(valor);
    }
}