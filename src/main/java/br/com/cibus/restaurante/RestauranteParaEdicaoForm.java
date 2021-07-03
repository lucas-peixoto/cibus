package br.com.cibus.restaurante;

import br.com.cibus.exceptions.NotFoundException;
import br.com.cibus.geral.validacao.Cnpj;
import br.com.cibus.tipodecozinha.TipoDeCozinha;
import br.com.cibus.tipodecozinha.TipoDeCozinhaRepository;

import javax.validation.constraints.*;

public class RestauranteParaEdicaoForm {

    @NotNull
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String nome;

    @NotBlank
    private String slug;

    @NotBlank
    private String cep;

    @NotBlank
    @Size(max = 200)
    private String endereco;

    @NotBlank
    @Cnpj
    private String cnpj;

    private String descricao;

    @NotNull
    private Long tipoDeCozinhaId;

    double taxaDeEntrega;
    int tempoMinimoEntrega;
    int tempoMaximoEntrega;


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


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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


    public Long getTipoDeCozinhaId() {
        return tipoDeCozinhaId;
    }

    public void setTipoDeCozinhaId(Long tipoDeCozinhaId) {
        this.tipoDeCozinhaId = tipoDeCozinhaId;
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


    public Restaurante geraEntidade(RestauranteRepository restauranteRepository, TipoDeCozinhaRepository tipoDeCozinhaRepository) {
        Restaurante restaurante = restauranteRepository.findById(id).orElseThrow(NotFoundException::new);
        TipoDeCozinha tipoDeCozinha = tipoDeCozinhaRepository.findById(tipoDeCozinhaId).orElseThrow(NotFoundException::new);

        restaurante.setNome(nome);
        restaurante.setCep(cep);
        restaurante.setEndereco(endereco);
        restaurante.setCnpj(cnpj);
        restaurante.setDescricao(descricao);
        restaurante.setTipoDeCozinha(tipoDeCozinha);
        restaurante.setTaxaDeEntrega(taxaDeEntrega);
        restaurante.setTempoMinimoEntrega(tempoMinimoEntrega);
        restaurante.setTempoMaximoEntrega(tempoMaximoEntrega);

        return restaurante;
    }
}
