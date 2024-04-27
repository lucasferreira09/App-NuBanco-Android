package com.example.nubanco.contabancaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.nubanco.MainActivity;
import com.example.nubanco.R;
import com.example.nubanco.databinding.ActivityCadastroUserBinding;
import com.example.nubanco.databinding.ActivityDadosContaBinding;

public class ActivityCadastroUser extends AppCompatActivity {

    //O usuário começará com R$ 20.000,00
    public static Bank myBank = new Bank(15700.0);

    private ActivityCadastroUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCadastroUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(binding.editPrimeiroNome.getText())){
                    binding.editPrimeiroNome.setError("INVÁLIDO");

                }
                else if (TextUtils.isEmpty(binding.editSobrenome.getText())){
                    binding.editSobrenome.setError("INVÁLIDO");
                }
                else {

                    String nome = nomeFormatado(
                            binding.editPrimeiroNome.getText().toString()) + " " +
                            nomeFormatado(binding.editSobrenome.getText().toString());

                    String primeiroNome = nomeFormatado(binding.editPrimeiroNome.getText().toString());

                    myBank.setName(nome);//Define o Nome da conta
                    myBank.setFirstName(primeiroNome);
                    myBank.setNumeroConta();//o Número da conta
                    myBank.setAgencia();//e a Agência

                    Intent actvInicio = new Intent(ActivityCadastroUser.this, MainActivity.class);
                    actvInicio.putExtra("userPrimeiroNome", primeiroNome);
                    actvInicio.putExtra("userSobrenome", nomeFormatado(binding.editSobrenome.getText().toString()));
                    actvInicio.putExtra("agencia", ActivityCadastroUser.myBank.getAgencia());
                    startActivity(actvInicio);
                    finish();

                }
            }
        });
    }

    public String nomeFormatado(String nome) {
        String primeiraLetra = nome.substring(0, 1);
        String nomeFormatado = primeiraLetra.toUpperCase() + nome.substring(1);

        return nomeFormatado;

    }
}