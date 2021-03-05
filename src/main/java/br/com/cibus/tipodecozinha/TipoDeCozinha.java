package br.com.cibus.tipodecozinha;

import javax.persistence.*;

@Entity
public class TipoDeCozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @Deprecated
    TipoDeCozinha() {}

    public TipoDeCozinha(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
