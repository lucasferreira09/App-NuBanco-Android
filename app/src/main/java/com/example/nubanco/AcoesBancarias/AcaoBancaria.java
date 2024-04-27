package com.example.nubanco.AcoesBancarias;

public class AcaoBancaria {

    private int idImageAcaoBancaria;
    private String descricaoAcaoBancaria;
    private String funcao;

    public AcaoBancaria(int idImageAcaoBancaria, String descricaoAcaoBancaria, String funcao) {
        this.idImageAcaoBancaria = idImageAcaoBancaria;
        this.descricaoAcaoBancaria = descricaoAcaoBancaria;
        this.funcao = funcao;
    }

    public void setDescricaoAcaoBancaria(String descricaoAcaoBancaria) {
        this.descricaoAcaoBancaria = descricaoAcaoBancaria;
    }
    public String getDescricaoAcaoBancaria() {
        return descricaoAcaoBancaria;
    }

    public void setIdImageAcaoBancaria(int idImageAcaoBancaria) {
        this.idImageAcaoBancaria = idImageAcaoBancaria;
    }
    public int getIdImageAcaoBancaria() {
        return idImageAcaoBancaria;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    public String getFuncao() {
        return funcao;
    }

}
