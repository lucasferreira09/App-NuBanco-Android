package com.example.nubanco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.nubanco.Bank;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

import java.text.NumberFormat;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private TextView saldoAtual;
    private ImageButton btnPix;
    private ImageButton btnDeposit;
    private ImageButton btnTransfer;
    private ImageButton btnPagar;
    private ImageButton abaCartaoCredito;
    private TextView ola;
    private TextView textLimiteDisponivel;
    private TextView pedeCartao;
    private LinearLayout imageCard;
    private ImageView imageMyCard;
    private ImageButton ocultaSaldo;
    private TextView valorFatura;
    private ImageButton usuario;

    //Necessário para Ocultar o Saldo, caso o usuário deseje
    public static boolean ocultaSaldoEsta = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Muda a cor da Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.roxo));
        }

        saldoAtual = findViewById(R.id.saldoAtual);
        btnPix = findViewById(R.id.btnPix);
        btnPagar = findViewById(R.id.btnPagar);
        btnDeposit = findViewById(R.id.btnDeposit);
        btnTransfer = findViewById(R.id.btnTransfer);
        abaCartaoCredito = findViewById(R.id.abaCartaoCredito);
        ola = findViewById(R.id.ola);
        imageMyCard = findViewById(R.id.imageMyCard);
        imageCard = findViewById(R.id.imageCard);
        pedeCartao = findViewById(R.id.pedeCartao);
        textLimiteDisponivel = findViewById(R.id.textLimiteDisponivel);
        ocultaSaldo = findViewById(R.id.ocultaSaldo);
        valorFatura = findViewById(R.id.valorFatura);
        usuario = findViewById(R.id.usuario);

        //Formata o saldo Ex.: 1.200,00
        Locale locale = new Locale("pt", "BR");
        Double meuSaldoDouble = ActivityCadastroUser.myBank.getSaldo();
        String saldo = NumberFormat.getInstance(locale).format(meuSaldoDouble);
        saldoAtual.setText("R$ " + saldo);

        String userPrimeiroNome = getIntent().getStringExtra("userPrimeiroNome");
        String userSobrenome = getIntent().getStringExtra("userSobrenome");
        ola.setText("Olá, " + ActivityCadastroUser.myBank.getFirstName());
        valorFatura.setText("R$ " + formataValor(ActivityCadastroUser.myBank.getValorFaturaAtual()));

        Intent actvTransfer1 = new Intent(MainActivity.this, ActivityTransferencia.class);
        actvTransfer1.putExtra("meuSaldoFormatado", saldo);
        actvTransfer1.putExtra("meuSaldoDouble", ActivityCadastroUser.myBank.getSaldo());

        btnPix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(actvTransfer1);
            }

        });
        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(actvTransfer1);
            }
        });
        btnPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "ESTA OPÇÃO NÃO\nESTÁ DISPONÍVEL", Toast.LENGTH_SHORT).show();
            }
        });

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actvDeposit = new Intent(MainActivity.this, ActivityDepositar.class);
                startActivity(actvDeposit);
            }
        });

        //Muda o texto do Cartão caso, o usuário já tenha pedido
        if (ActivityCadastroUser.myBank.getCartaoExiste() == true){
            pedeCartao.setText("Cartão de Crédito");
        }

        textLimiteDisponivel.setText("Limite Disponível R$ " + formataValor(ActivityCadastroUser.myBank.getLimiteCreditoDisponivel()));


        abaCartaoCredito.setOnClickListener(new View.OnClickListener() {
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


        imageCard.setOnClickListener(new View.OnClickListener() {
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
        ocultaSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se o botão Oculta Saldo tá desativado,
                // ele é ativado e o saldo da conta é ocultado.
                if (ocultaSaldoEsta == false){
                    saldoAtual.setText("••••");
                    valorFatura.setText("••••");
                    textLimiteDisponivel.setText("Limite Disponível R$ ••••");
                    ocultaSaldoEsta = true;
                }
                else {
                    saldoAtual.setText("R$ " + saldo);
                    ocultaSaldoEsta = false;
                    valorFatura.setText("R$ " + formataValor(ActivityCadastroUser.myBank.getValorFaturaAtual()));
                    textLimiteDisponivel.setText("Limite Disponível R$ " + formataValor(ActivityCadastroUser.myBank.getLimiteCreditoDisponivel()));
                }
            }
        });
        //Mostra os dados da conta
        usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        return NumberFormat.getInstance(locale).format(valor);
    }

}