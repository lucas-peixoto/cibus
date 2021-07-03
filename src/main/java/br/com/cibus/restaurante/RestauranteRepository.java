package br.com.cibus.restaurante;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    Optional<Restaurante> findBySlug(String slug);

    boolean existsByNomeAndIdNot(String nome, Long id);
}
