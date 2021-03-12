package br.com.cibus.tipodecozinha;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoDeCozinhaRepository extends JpaRepository<TipoDeCozinha, Long> {
    boolean existsByNome(String nome);
    boolean existsByNomeAndIdNot(String nome, Long id);
    List<TipoDeCozinha> findByOrderByNomeAsc();
}
