package com.example.nubanco.transferencia;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nubanco.MainActivity;
import com.example.nubanco.R;
import com.example.nubanco.databinding.ActivityTransferencia3Binding;
import com.example.nubanco.databinding.ActivityTransferenciaRealizadaBinding;

import java.time.LocalTime;

public class ActivityTransferenciaRealizada extends AppCompatActivity {

    private ActivityTransferenciaRealizadaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTransferenciaRealizadaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Bloqueia o botão físico do celular
        //Só é permitido o botão da interface do aplicativo, igual o app Nubank original
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
        binding.valorComprovante.setText("R$ " + comprovante);
        binding.horarioComprovante.setText("agora mesmo • " + horaDoSistema());

        binding.closeComprovante.setOnClickListener(new View.OnClickListener() {
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

        if (binding.transferenciaRealizada != null && novoTextoComprovante != null) {
            binding.transferenciaRealizada.setText(novoTextoComprovante);
            binding.descricaoPagamento.setText("Recebemos o pagamento de " + valorComprovante + "\nreferente a sua fatura.");
            binding.imageComprovante.setImageResource((R.drawable.comprovantefatura));

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