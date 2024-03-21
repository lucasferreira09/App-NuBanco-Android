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

public class ActivityCadastroUser extends AppCompatActivity {

    //O usuário começará com R$ 15.000,00
    public static Bank myBank = new Bank(15000.0);
    private ImageButton btnCadastrar;
    private EditText editPrimeiroNome;
    private EditText editSobrenome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);

        btnCadastrar = findViewById(R.id.btnCadastrar);
        editPrimeiroNome = findViewById(R.id.editPrimeiroNome);
        editSobrenome = findViewById(R.id.editSobrenome);



        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editPrimeiroNome.getText())){
                    editPrimeiroNome.setError("INVÁLIDO");

                }
                else if (TextUtils.isEmpty(editSobrenome.getText())){
                    editSobrenome.setError("INVÁLIDO");
                }
                else {
                    String nome = editPrimeiroNome.getText().toString() + " " + editSobrenome.getText().toString();
                    myBank.setName(nome);//Define o Nome da conta
                    myBank.setFirstName(editPrimeiroNome.getText().toString());
                    myBank.setNumeroConta();//o Número da conta
                    myBank.setAgencia();//e a Agência

                    Intent actvInicio = new Intent(ActivityCadastroUser.this, MainActivity.class);
                    actvInicio.putExtra("userPrimeiroNome", editPrimeiroNome.getText().toString());
                    actvInicio.putExtra("userSobrenome", editSobrenome.getText().toString());
                    actvInicio.putExtra("agencia", ActivityCadastroUser.myBank.getAgencia());
                    startActivity(actvInicio);
                    finish();

                }
            }
        });
    }
}