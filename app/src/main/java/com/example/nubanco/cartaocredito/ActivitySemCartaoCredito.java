package com.example.nubanco.cartaocredito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nubanco.R;
import com.example.nubanco.databinding.ActivityPedindoCartaoBinding;
import com.example.nubanco.databinding.ActivitySemCartaoCreditoBinding;

public class ActivitySemCartaoCredito extends AppCompatActivity {

    private ActivitySemCartaoCreditoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySemCartaoCreditoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        //Muda a cor da Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.roxoSemCartao));
        }

        binding.buttonQueroCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actvPedindoCartao = new Intent(ActivitySemCartaoCredito.this, ActivityPedindoCartao.class);
                startActivity(actvPedindoCartao);

            }
        });
        binding.buttonNaoQuerCartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}