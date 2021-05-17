package br.com.cibus.tipodecozinha;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("teste")
class TipoDeCozinhaRepositoryTest {

    @Autowired
    private TipoDeCozinhaRepository tipoDeCozinhaRepository;

    @Test
    void deveConfirmarQuandoONomeExiste() {
        tipoDeCozinhaRepository.save(new TipoDeCozinha("Alemã"));

        boolean existe = tipoDeCozinhaRepository.existsByNome("Alemã");
        assertThat(existe).isTrue();
    }

    @Test
    void deveConfirmarQuandoONomeNaoExiste() {
        boolean existe = tipoDeCozinhaRepository.existsByNome("Americana");
        assertThat(existe).isFalse();
    }

    @Test
    void deveConfirmarQuandoUmNomeExisteComUmIdDiferente() {
        tipoDeCozinhaRepository.save(new TipoDeCozinha("Azerbaijani"));

        boolean existe = tipoDeCozinhaRepository.existsByNomeAndIdNot("Azerbaijani", 0L);
        assertThat(existe).isTrue();
    }

    @Test
    void naoDeveConfirmarQuandoUmNomeExisteComOMesmoId() {
        TipoDeCozinha americana = tipoDeCozinhaRepository.save(new TipoDeCozinha("Americana"));

        boolean existe = tipoDeCozinhaRepository.existsByNomeAndIdNot("Americana", americana.getId());
        assertThat(existe).isFalse();
    }

    @Test
    void deveRetornarTudoOrdenadoPorNome() {
        tipoDeCozinhaRepository.save(new TipoDeCozinha("Portuguesa"));
        tipoDeCozinhaRepository.save(new TipoDeCozinha("Francesa"));
        tipoDeCozinhaRepository.save(new TipoDeCozinha("alemã"));

        List<TipoDeCozinha> tiposDeCozinha = tipoDeCozinhaRepository.findByOrderByNomeAsc();

        assertThat(tiposDeCozinha)
                .hasSize(7)
                .extracting("nome")
                .containsExactly("alemã", "Árabe", "Baiana", "Chinesa", "Francesa", "Italiana", "Portuguesa");
    }

    @Test
    void deveRetornarOTotalDeRestaurantesPorTipoDeCozinhaOrdenadoPorNome() {
        tipoDeCozinhaRepository.save(new TipoDeCozinha("Finlandesa"));
        List<RestaurantesPorTipoDeCozinha> restaurantesPorTipoDeCozinha = tipoDeCozinhaRepository.contaRestaurantesPorTipoDeCozinha();

        assertThat(restaurantesPorTipoDeCozinha)
                .hasSize(5)
                .extracting("nomeDoTipoDeCozinha")
                .containsExactly("Árabe", "Baiana", "Chinesa", "Finlandesa", "Italiana");

        assertThat(restaurantesPorTipoDeCozinha)
                .extracting("totalDeRestaurantes")
                .containsExactly(3L, 2L, 3L, 0L, 4L);
    }
}
