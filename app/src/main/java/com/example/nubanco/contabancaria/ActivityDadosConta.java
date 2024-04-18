package com.example.nubanco.contabancaria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nubanco.R;
import com.example.nubanco.databinding.ActivityDadosContaBinding;
import com.example.nubanco.databinding.ActivityPrimeiraVezBinding;

public class ActivityDadosConta extends AppCompatActivity {

    private ActivityDadosContaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDadosContaBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Muda a cor da Status Bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getColor(R.color.gray));
        }

        binding.olaNomeConta.setText(ActivityCadastroUser.myBank.getFirstName());
        binding.conta.setText("Conta " + ActivityCadastroUser.myBank.getNumeroConta());
        binding.agencia.setText("Agência " + ActivityCadastroUser.myBank.getAgencia() + " • ");


        binding.closeDadosConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}