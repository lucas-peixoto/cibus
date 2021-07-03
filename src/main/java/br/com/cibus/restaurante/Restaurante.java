package br.com.cibus.restaurante;

import br.com.cibus.tipodecozinha.TipoDeCozinha;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String nome;

    @NotNull
    @Column(unique = true)
    private String slug;

    @NotNull
    private String cep;

    @NotNull
    private String endereco;

    @NotNull
    // TODO: adicionar validação
    private String cnpj;

    @Type(type = "text")
    private String descricao;

    @NotNull
    @ManyToOne
    private TipoDeCozinha tipoDeCozinha;

    double taxaDeEntrega;
    int tempoMinimoEntrega;
    int tempoMaximoEntrega;

    @Deprecated
    protected Restaurante() {}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }


    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public TipoDeCozinha getTipoDeCozinha() {
        return tipoDeCozinha;
    }

    public void setTipoDeCozinha(TipoDeCozinha tipoDeCozinha) {
        this.tipoDeCozinha = tipoDeCozinha;
    }


    public double getTaxaDeEntrega() {
        return taxaDeEntrega;
    }

    public void setTaxaDeEntrega(double taxaDeEntrega) {
        this.taxaDeEntrega = taxaDeEntrega;
    }


    public int getTempoMinimoEntrega() {
        return tempoMinimoEntrega;
    }

    public void setTempoMinimoEntrega(int tempoMinimoEntrega) {
        this.tempoMinimoEntrega = tempoMinimoEntrega;
    }


    public int getTempoMaximoEntrega() {
        return tempoMaximoEntrega;
    }

    public void setTempoMaximoEntrega(int tempoMaximoEntrega) {
        this.tempoMaximoEntrega = tempoMaximoEntrega;
    }

}
