package br.com.cibus.tipodecozinha;

import br.com.cibus.restaurante.Restaurante;

import javax.persistence.*;
import java.util.List;

@Entity
public class TipoDeCozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "tipoDeCozinha", cascade = CascadeType.ALL)
    private List<Restaurante> restaurantes;

    @Deprecated
    protected TipoDeCozinha() {}

    public TipoDeCozinha(String nome) {
        this.nome = nome;
    }

    public TipoDeCozinha(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TipoDeCozinha{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
