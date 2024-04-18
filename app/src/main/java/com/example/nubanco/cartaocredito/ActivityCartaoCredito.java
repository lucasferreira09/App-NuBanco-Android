package com.example.nubanco.cartaocredito;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nubanco.contabancaria.ActivityCadastroUser;
import com.example.nubanco.R;
import com.example.nubanco.databinding.ActivityCadastroUserBinding;
import com.example.nubanco.databinding.ActivityCartaoCreditoBinding;

public class ActivityCartaoCredito extends AppCompatActivity {

    private ActivityCartaoCreditoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCartaoCreditoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.validadeCartao.setText(ActivityCadastroUser.myBank.getDataValidadeCartao());
        binding.nomeCartao.setText(ActivityCadastroUser.myBank.getName());
        binding.numeroCartao.setText(ActivityCadastroUser.myBank.getNumeroCartaoCredito());


        binding.closeCartaoCredito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}