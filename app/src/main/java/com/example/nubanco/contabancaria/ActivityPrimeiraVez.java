package com.example.nubanco.contabancaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nubanco.MainActivity;
import com.example.nubanco.R;
import com.example.nubanco.databinding.ActivityDepositoPorContaBinding;
import com.example.nubanco.databinding.ActivityPrimeiraVezBinding;

public class ActivityPrimeiraVez extends AppCompatActivity {

    private ActivityPrimeiraVezBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrimeiraVezBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);


        //Muda a cor da Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.corTelaInicial2));
        }

        binding.btnComecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actvCadastro = new Intent(ActivityPrimeiraVez.this, ActivityCadastroUser.class);
                startActivity(actvCadastro);
                finish();
            }
        });
    }
}