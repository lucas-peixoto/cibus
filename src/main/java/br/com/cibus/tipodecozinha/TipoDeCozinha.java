package br.com.cibus.tipodecozinha;

import javax.persistence.*;

@Entity
public class TipoDeCozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;
}
