package br.com.cibus.tipodecozinha;

import javax.validation.constraints.*;

class TipoDeCozinhaForm {
    @NotBlank
    @Size(max = 50)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    TipoDeCozinha toEntity() {
        return new TipoDeCozinha(this.nome);
    }
}
