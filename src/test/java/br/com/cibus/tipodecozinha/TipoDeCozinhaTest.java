package br.com.cibus.tipodecozinha;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TipoDeCozinhaTest {

    @Test
    void toggleAtivoDeveInverterEstadoInicial() {
        TipoDeCozinha canadense = new TipoDeCozinha("Canadense");
        assertThat(canadense.isAtivo()).isFalse();

        canadense.toggleAtivo();
        assertThat(canadense.isAtivo()).isTrue();

        canadense.toggleAtivo();
        assertThat(canadense.isAtivo()).isFalse();
    }

}