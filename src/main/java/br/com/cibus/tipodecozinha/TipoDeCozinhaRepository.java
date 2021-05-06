package br.com.cibus.tipodecozinha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TipoDeCozinhaRepository extends JpaRepository<TipoDeCozinha, Long> {
    boolean existsByNome(String nome);
    boolean existsByNomeAndIdNot(String nome, Long id);
    List<TipoDeCozinha> findByOrderByNomeAsc();

    @Query("SELECT tc.id AS id, tc.nome AS nome, COUNT(r.id) AS totalDeRestaurantes FROM TipoDeCozinha tc LEFT JOIN tc.restaurantes r GROUP BY tc.id ORDER BY tc.nome ASC")
    List<RestaurantesPorTipoDeCozinha> contaRestaurantesPorTipoDeCozinha();
}
