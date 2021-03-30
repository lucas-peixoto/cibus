package br.com.cibus.tipodecozinha;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
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
        TipoDeCozinha azerbaijani = tipoDeCozinhaRepository.save(new TipoDeCozinha("Azerbaijani"));

        boolean existe = tipoDeCozinhaRepository.existsByNomeAndIdNot("Azerbaijani", 0L);
        assertThat(existe).isTrue();
    }

    @Test
    void naoDeveConfirmarQuandoUmNomeExisteComOIdDiferente() {
        TipoDeCozinha americana = tipoDeCozinhaRepository.save(new TipoDeCozinha("Americana"));

        boolean existe = tipoDeCozinhaRepository.existsByNomeAndIdNot("Americana", americana.getId());
        assertThat(existe).isFalse();
    }

    @Test
    void deveRetornarTudoOrdenadoPorNome() {
        tipoDeCozinhaRepository.save(new TipoDeCozinha("Portuguesa"));
        tipoDeCozinhaRepository.save(new TipoDeCozinha("Francesa"));

        List<TipoDeCozinha> tiposDeCozinha = tipoDeCozinhaRepository.findByOrderByNomeAsc();

        assertThat(tiposDeCozinha)
                .hasSize(6)
                .extracting("nome")
                .isSorted();
    }

}