package com.example.nubanco;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalDateTime;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;

import java.time.LocalTime;

public class ActivityTransferenciaRealizada extends AppCompatActivity {

    private TextView valorComprovante;
    private ImageButton closeComprovante;
    private ImageView imageComprovante;
    private TextView horarioComprovante;
    private TextView transferenciaRealizada;
    private TextView descricaoPagamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencia_realizada);

        valorComprovante = findViewById(R.id.valorComprovante);
        closeComprovante = findViewById(R.id.closeComprovante);
        transferenciaRealizada = findViewById(R.id.transferenciaRealizada);
        descricaoPagamento = findViewById(R.id.descricaoPagamento);
        imageComprovante = findViewById(R.id.imageComprovante);
        horarioComprovante = findViewById(R.id.horarioComprovante);

        bloqueiBotaoVoltar();

        /*
        Aqui, essa Activity foi reaproveitada também.
        Quando ocorrer algum tipo de transferência ou pagamento de fatura,
        o usuário é redirecionado para cá.
        Caso seja transferência, a String padrão já definida na ActivityTransferencia será usada.
        Caso seja Pagamento de Fatura, uma nova String irá substituí-la.
        Comforme abaixo.
        */
        configuraNovoComprovante(getIntent().getStringExtra("novoTextoComprovante"), getIntent().getStringExtra("valorComprovante"));

        String comprovante = getIntent().getStringExtra("valorComprovante");
        valorComprovante.setText("R$ " + comprovante);
        horarioComprovante.setText("agora mesmo • " + horaDoSistema());
        closeComprovante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltaActivityInicial();
            }
        });


    }

    public void voltaActivityInicial() {
        /*
        É preciso se certificar que que o pagamento seja realizado, o usuário
        não possa voltar para as ActivityTransferencia e somente para a ActivityPrincipal.
         */
        finish();
        // Cria um novo intent para a atividade principal
        Intent intent = new Intent(ActivityTransferenciaRealizada.this, MainActivity.class);

        // Define flags para limpar a pilha de atividades e iniciar uma nova tarefa
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Inicia a atividade principal
        startActivity(intent);
    }

    public void bloqueiBotaoVoltar() {
        //Só permite o usuário voltar clicando no botão X da Activity
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    public void configuraNovoComprovante(String novoTextoComprovante, String valorComprovante) {
        if (transferenciaRealizada != null && novoTextoComprovante != null) {
            transferenciaRealizada.setText(novoTextoComprovante);
            descricaoPagamento.setText("Recebemos o pagamento de "+valorComprovante+"\nreferente a sua fatura." );
            imageComprovante.setImageResource((R.drawable.comprovantefatura));

        }
    }

    public String horaDoSistema() {
        String time = String.valueOf(LocalTime.now());
        String[] formatandoHora = time.split(":");
        String horaFormatada = "";

        //Apenas pega a hora e os min, descarta os segundos
        int count = 1;
        for (String h : formatandoHora) {
            if (count <= 2) {
                if (count == 2) {
                    horaFormatada += h;
                } else {
                    horaFormatada += h;
                    horaFormatada += ":";
                }
                count++;
            }
        }
        return horaFormatada;
    }

}