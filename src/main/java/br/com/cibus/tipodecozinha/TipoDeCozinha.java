package br.com.cibus.tipodecozinha;

import javax.persistence.*;

@Entity
public class TipoDeCozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    private boolean ativo;

    @Deprecated
    protected TipoDeCozinha() {
    }

    public TipoDeCozinha(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void toggleAtivo() {
        this.ativo = !this.ativo;
    }

    @Override
    public String toString() {
        return "TipoDeCozinha{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
