package br.com.cibus.tipodecozinha;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class TipoDeCozinhaRepositoryTest {

    @Autowired
    private TipoDeCozinhaRepository tipoDeCozinhaRepository;

    @Test
    void deveConfirmarQuandoONomeExiste( ) {
        tipoDeCozinhaRepository.save(new TipoDeCozinha("Alemã"));

        boolean existe = tipoDeCozinhaRepository.existsByNome("Alemã");
        assertThat(existe).isTrue();
    }

    @Test
    void deveConfirmarQuandoONomeNaoExiste( ) {
        boolean existe = tipoDeCozinhaRepository.existsByNome("Americana");
        assertThat(existe).isFalse();
    }

}