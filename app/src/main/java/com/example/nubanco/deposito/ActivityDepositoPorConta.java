package com.example.nubanco.deposito;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nubanco.R;
import com.example.nubanco.contabancaria.ActivityCadastroUser;
import com.example.nubanco.databinding.ActivityDepositarBinding;
import com.example.nubanco.databinding.ActivityDepositoPorContaBinding;

public class ActivityDepositoPorConta extends AppCompatActivity {

    private ActivityDepositoPorContaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDepositoPorContaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.nome.setText(ActivityCadastroUser.myBank.getName());
        binding.agencia.setText(ActivityCadastroUser.myBank.getAgencia());
        binding.conta.setText(ActivityCadastroUser.myBank.getNumeroConta());

        binding.closeDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}