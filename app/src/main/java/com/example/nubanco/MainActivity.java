package com.example.nubanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nubanco.cartaocredito.ActivityCartaoCredito;
import com.example.nubanco.cartaocredito.ActivityFaturaCartao;
import com.example.nubanco.cartaocredito.ActivityPedindoCartao;
import com.example.nubanco.contabancaria.ActivityCadastroUser;
import com.example.nubanco.contabancaria.ActivityDadosConta;
import com.example.nubanco.databinding.ActivityMainBinding;
import com.example.nubanco.deposito.ActivityDepositar;
import com.example.nubanco.transferencia.ActivityTransferencia;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private static boolean ocultaSaldoEsta = false;//Necessário para Ocultar o Saldo, caso o usuário deseje

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Muda a cor da Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.roxo));
        }


        //Formata o saldo Ex.: 1.200,00
        Locale locale = new Locale("pt", "BR");
        Double meuSaldoDouble = ActivityCadastroUser.myBank.getSaldo();
        String saldo = formataValor(meuSaldoDouble);

        binding.saldoAtual.setText("R$ " + saldo);
        binding.ola.setText("Olá, " + ActivityCadastroUser.myBank.getFirstName());
        binding.valorFatura.setText("R$ " + formataValor(ActivityCadastroUser.myBank.getValorFaturaAtual()));

        Intent actvTransfer1 = new Intent(MainActivity.this, ActivityTransferencia.class);
        actvTransfer1.putExtra("meuSaldoFormatado", saldo);
        actvTransfer1.putExtra("meuSaldoDouble", ActivityCadastroUser.myBank.getSaldo());

        binding.btnPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(actvTransfer1);
            }

        });
        binding.btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(actvTransfer1);
            }
        });
        binding.btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "OPÇÃO NÃO DISPONÍVEL", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actvDeposit = new Intent(MainActivity.this, ActivityDepositar.class);
                startActivity(actvDeposit);
            }
        });

        //Muda o texto do Cartão caso, o usuário já tenha pedido
        if (ActivityCadastroUser.myBank.getCartaoExiste() == true){
            binding.pedeCartao.setText("Cartão de Crédito");
        }

        binding.textLimiteDisponivel.setText(
                "Limite Disponível R$ " + formataValor(ActivityCadastroUser.myBank.getLimiteCreditoDisponivel())
        );

        binding.abaCartaoCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Caso o usuário ainda não tenha pedidio Cartão
                if (ActivityCadastroUser.myBank.getCartaoExiste() == false){
                    Intent actvPedirCartao = new Intent(MainActivity.this, ActivityPedindoCartao.class);
                    startActivity(actvPedirCartao);
                }
                else{
                    Intent actvFatura = new Intent(MainActivity.this, ActivityFaturaCartao.class);
                    actvFatura.putExtra("valorFatura", ActivityCadastroUser.myBank.getValorFaturaAtual());
                    actvFatura.putExtra("meuSaldoDouble", meuSaldoDouble);
                    actvFatura.putExtra("meuLimiteDisponivel", ActivityCadastroUser.myBank.getLimiteCreditoDisponivel());
                    startActivity(actvFatura);}
            }
        });

        binding.imageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //A aba Meus Cartões só está disponível se o Cartão existir
                if (ActivityCadastroUser.myBank.getCartaoExiste() == true){
                    verCartaoCredito();
                }
                else{
                    Toast.makeText(MainActivity.this, "VOCÊ AINDA NÃO TEM CARTÃO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Botão de ocultar saldo
        binding.ocultaSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se o botão Oculta Saldo tá desativado,
                // ele é ativado e o saldo da conta é ocultado.
                if (ocultaSaldoEsta == false){
                    binding.saldoAtual.setText("••••");
                    binding.valorFatura.setText("••••");
                    binding.textLimiteDisponivel.setText("Limite Disponível R$ ••••");
                    ocultaSaldoEsta = true;
                }
                else {
                    binding.saldoAtual.setText("R$ " + saldo);
                    ocultaSaldoEsta = false;
                    binding.valorFatura.setText("R$ " + formataValor(ActivityCadastroUser.myBank.getValorFaturaAtual()));
                    binding.textLimiteDisponivel.setText("Limite Disponível R$ " + formataValor(ActivityCadastroUser.myBank.getLimiteCreditoDisponivel()));
                }
            }
        });
        //Mostra os dados da conta
        binding.usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userPrimeiroNome = getIntent().getStringExtra("userPrimeiroNome");

                Intent actvUsuario = new Intent(MainActivity.this, ActivityDadosConta.class);
                actvUsuario.putExtra("nomeConta", userPrimeiroNome);
                actvUsuario.putExtra("agencia", getIntent().getStringExtra("agencia"));
                startActivity(actvUsuario);
            }
        });
    }

    public void verCartaoCredito(){
        Intent actVerCartao = new Intent(MainActivity.this, ActivityCartaoCredito.class);
        startActivity(actVerCartao);
    }

    public String formataValor(Double valor){
        Locale locale = new Locale("pt", "BR");
        String padrao = "###,##0.00";
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(padrao);

        return decimalFormat.format(valor);
    }


}