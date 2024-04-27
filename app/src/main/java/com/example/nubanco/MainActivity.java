package com.example.nubanco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nubanco.cartaocredito.ActivityCartaoCredito;
import com.example.nubanco.cartaocredito.ActivityFaturaCartao;
import com.example.nubanco.cartaocredito.ActivityPedindoCartao;
import com.example.nubanco.contabancaria.ActivityCadastroUser;
import com.example.nubanco.contabancaria.ActivityDadosConta;
import com.example.nubanco.databinding.ActivityMainBinding;
import com.example.nubanco.deposito.ActivityDepositar;
import com.example.nubanco.AcoesBancarias.AcaoBancaria;
import com.example.nubanco.AcoesBancarias.AdapterAcoes;
import com.example.nubanco.AcoesBancarias.ClickAcoesBancaria;
import com.example.nubanco.transferencia.ActivityTransferencia;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements ClickAcoesBancaria {

    private ActivityMainBinding binding;
    private int notificationId = 0;
    private final String CHANNEL_ID = "CHANNEL_ID";

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

        implementasAcoesBancarias();

        Locale locale = new Locale("pt", "BR");
        Double meuSaldoDouble = ActivityCadastroUser.myBank.getSaldoDouble();
        String saldo = formataValor(meuSaldoDouble);

        binding.saldoAtual.setText("R$ " + saldo);
        binding.ola.setText("Olá, " + ActivityCadastroUser.myBank.getFirstName());
        binding.valorFatura.setText("R$ " + formataValor(ActivityCadastroUser.myBank.getValorFaturaAtual()));


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
                    startActivity(actvFatura);
                }
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
                    recreate();
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

    private void implementasAcoesBancarias() {
        //RecyclerView com as Ações como Pix, Deposito, etc...
        List<AcaoBancaria> acaoBancarias = new ArrayList<>();
        acaoBancarias.add(new AcaoBancaria(R.drawable.pixbolinha, "Área Pix", "pix"));
        acaoBancarias.add(new AcaoBancaria(R.drawable.bar, "Pagar", "pagar"));
        acaoBancarias.add(new AcaoBancaria(R.drawable.recarga, "Recarga de\ncelular", "recarga"));
        acaoBancarias.add(new AcaoBancaria(R.drawable.transf, "Depositar", "deposito"));
        acaoBancarias.add(new AcaoBancaria(R.drawable.transferencaconta, "Transferir", "transferencia"));
        acaoBancarias.add(new AcaoBancaria(R.drawable.deposito_auto, "Depósito\nAutomático", "deposito_auto"));

        AdapterAcoes adapterAcoes = new AdapterAcoes(acaoBancarias, this, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false);

        binding.recyclerView.setAdapter(adapterAcoes);
        binding.recyclerView.setLayoutManager(layoutManager);
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

    public void criaNotificacao(Double valor) {

        Intent intent = new Intent(this, ActivityTransferencia.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.nubank)
                .setContentTitle(getString(R.string.transferencia_recebida))
                .setContentText(getString(R.string.valor_recebido) + formataValor(valor))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationId++;// É preciso mudar o Id da notificação

        criaCanalDeNotificacao();// É importante fazer isso
        // Checa as permissões de notificação
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED) {
            // Handle permission request or return if you decide not to display the
            // notification without permission.
            return;
        }

        // Cada notificação será tratada como diferente
        // Pois o id de cada será diferente
        notificationManager.notify(notificationId, builder.build());
    }
    private void criaCanalDeNotificacao() {

        //Cria um canal de notificação
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Transferências";
            String description = "Transferências";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void clickAcaoBancaria(String funcaoItemClicado) {
        switch (funcaoItemClicado) {
            case ("pix"):
                Intent actvTransfer1 = new Intent(MainActivity.this, ActivityTransferencia.class);
                actvTransfer1.putExtra("meuSaldoFormatado", ActivityCadastroUser.myBank.getSaldoFormatado());
                actvTransfer1.putExtra("meuSaldoDouble", ActivityCadastroUser.myBank.getSaldoDouble());
                startActivity(actvTransfer1);
                break;

            case ("pagar"):
                Toast.makeText(MainActivity.this, "OPÇÃO NÃO DISPONÍVEL", Toast.LENGTH_SHORT).show();
                break;

            case ("deposito"):
                Intent actvDeposit = new Intent(MainActivity.this, ActivityDepositar.class);
                startActivity(actvDeposit);
                break;

            case ("transferencia"):
                Intent actvTransfer = new Intent(MainActivity.this, ActivityTransferencia.class);
                actvTransfer.putExtra("meuSaldoFormatado", ActivityCadastroUser.myBank.getSaldoFormatado());
                actvTransfer.putExtra("meuSaldoDouble", ActivityCadastroUser.myBank.getSaldoDouble());
                startActivity(actvTransfer);
                break;

            case ("recarga"):
                Toast.makeText(MainActivity.this, "OPÇÃO NÃO DISPONÍVEL", Toast.LENGTH_SHORT).show();
                break;

            case ("deposito_auto"):
                Random random = new Random();
                Double valorRecebido = 200 + random.nextDouble()*100;
                this.criaNotificacao(valorRecebido);
                ActivityCadastroUser.myBank.deposit(valorRecebido);
                break;
        }
    }
}