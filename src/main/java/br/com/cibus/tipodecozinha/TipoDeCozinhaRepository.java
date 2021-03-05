package br.com.cibus.tipodecozinha;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoDeCozinhaRepository extends JpaRepository<TipoDeCozinha, Long> {
    boolean existsByNome(String nome);
}
