package com.example.nubanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ActivityCadastroUser extends AppCompatActivity {

    //O usuário começará com R$ 1.000,00
    public static Bank myBank = new Bank(1000.0);
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
                    editPrimeiroNome.setError("NOME INVÁLIDO");
                }
                else if (TextUtils.isEmpty(editSobrenome.getText())){
                    editSobrenome.setError("NOME INVÁLIDO");
                }
                else {
                    String nome = editPrimeiroNome.getText().toString() + " " + editSobrenome.getText().toString();
                    myBank.setName(nome);//Define o nome da conta
                    myBank.setFirstName(editPrimeiroNome.getText().toString());
                    myBank.setNumeroConta();//o numero da conta
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