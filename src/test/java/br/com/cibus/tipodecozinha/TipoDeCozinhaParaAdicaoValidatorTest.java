package br.com.cibus.tipodecozinha;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TipoDeCozinhaParaAdicaoValidatorTest {

    @Test
    void quandoNomeJaExisteDeveDarErro() {
        TipoDeCozinhaForm tipoDeCozinhaForm = new TipoDeCozinhaForm();
        tipoDeCozinhaForm.setNome("Mexicana");

        TipoDeCozinhaRepository repository = mock(TipoDeCozinhaRepository.class);
        when(repository.existsByNome("Mexicana")).thenReturn(true);

    }

    @Test
    void quandoNomeNaoExisteNaoDaErro() {

    }

}