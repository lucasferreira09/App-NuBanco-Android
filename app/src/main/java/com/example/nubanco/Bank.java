package com.example.nubanco;

import java.security.SecureRandom;

public class Bank {

    private SecureRandom gerador = new SecureRandom();


    //VARIÁVEIS CONTA BANCÁRIA
    private String name;
    private String firstName;
    private Double saldo;
    private String numeroConta;
    private String agencia;
    private final String numeroBanco = "0260";


    //VARIÁVEIS CARTÃO DE CRÉDITO
    private String numeroCartaoCredito;
    private String dataValidadeCartao;
    private String codigoCartao;
    private Double limiteCreditoDisponivel = 0.0;
    private Double limiteCreditoTotal = 0.0;
    private Double valorFaturaAtual = 0.0;
    private boolean cartaoExiste;


    //MÉTODOS CONTA BANCÁRIA
    public Bank(Double saldo){
        this.saldo = saldo;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    public Double getSaldo() {
        return saldo;
    }

    public void setNumeroConta(){
        String conta = "";
        int n = 1;
        while (n <= 9) {
            int digito = gerador.nextInt(10);
            if (n == 8) {
                conta += Integer.toString(digito);
                conta += "-";
            } else {
                conta += Integer.toString(digito);
            }
            n++;
        }
        numeroConta = conta;

    }

    public String getNumeroConta(){
        return numeroConta;
    }
    public void setAgencia(){
        String digitoAgencia = "";
        int digito = 1000 + (gerador.nextInt(1001));
        digitoAgencia += Integer.toString(digito);
        agencia = digitoAgencia;
    }
    public String getAgencia(){
        return agencia;
    }
    public String getNumeroBanco(){
        return numeroBanco;
    }
    public void deposit(Double deposit) {
        saldo += deposit;
    }
    public void transfere(Double valor) {
        saldo -= valor;
    }


    //MÉTODOS CARTÃO CRÉDITO
    public boolean getCartaoExiste(){
        return cartaoExiste;
    }

    public void setLimiteCreditoTotal() {
        //Gera um valor entre 1000 e 2000;
        Double limiteTotal = 1000.0 + (gerador.nextDouble()*1000);
        limiteCreditoTotal = limiteTotal;
        limiteCreditoDisponivel = limiteTotal;

    }
    public void compraCartaoCredito(Double valorCompra){
        valorFaturaAtual += valorCompra;
        limiteCreditoDisponivel -= valorCompra;
    }
    public Double getLimiteCreditoDisponivel(){
        return limiteCreditoDisponivel;
    }
    public Double getLimiteCreditoTotal(){
        return limiteCreditoTotal;
    }


    public void setCartaoExiste(boolean value){
        if (value == true){
            cartaoExiste = true;
        }
        else if (value == false) {
            cartaoExiste = false;
        }
    }

    public String getNumeroCartaoCredito() {
        return numeroCartaoCredito;
    }

    public void setCodigoCartao() {
        String codigo = "";
        int n = 1;
        while (n <= 3) {
            int numero = gerador.nextInt(9);
            codigo += Integer.toString(numero);
            n++;
        }
        codigoCartao = codigo;
    }

    public String getCodigoCartao() {
        return codigoCartao;
    }

    public void setValidadeCartao() {

        String validadeCartao = "";
        //Gera o mês
        int numero = 1 + gerador.nextInt(13);
        validadeCartao += Integer.toString(numero);
        validadeCartao += "/";
        //Gera o ano
        numero = 26 + gerador.nextInt(5);
        validadeCartao += Integer.toString(numero);

        dataValidadeCartao = validadeCartao;
    }

    public String getDataValidadeCartao(){
        return dataValidadeCartao;
    }

    public void setNumeroCartao() {
        String numeroCartao = "";
        int n = 1;
        while (n <= 16) {
            int numero = gerador.nextInt(9);
            if (n == 4 || n == 8 || n == 12 || n == 16) {
                numeroCartao += Integer.toString(numero);
                numeroCartao += " ";

            } else {
                numeroCartao += Integer.toString(numero);
            }
            n++;
        }
        numeroCartaoCredito = numeroCartao;

    }

    public void setValorFaturaAtual(Double valor){
        valorFaturaAtual += valor;
    }
    public Double getValorFaturaAtual(){
        return valorFaturaAtual;
    }

    public void pagarFatura(Double valor){
        saldo -= valor;
        valorFaturaAtual -= valor;
        limiteCreditoDisponivel += valor;
    }


}
