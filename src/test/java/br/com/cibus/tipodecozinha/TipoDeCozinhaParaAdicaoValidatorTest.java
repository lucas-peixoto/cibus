package br.com.cibus.tipodecozinha;

import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class TipoDeCozinhaParaAdicaoValidatorTest {

    @Test
    void quandoNomeJaExisteDeveDarErro() {
        TipoDeCozinhaForm tipoDeCozinhaForm = new TipoDeCozinhaForm();
        tipoDeCozinhaForm.setNome("Mexicana");

        TipoDeCozinhaRepository repository = mock(TipoDeCozinhaRepository.class);
        when(repository.existsByNome("Mexicana")).thenReturn(true);

        TipoDeCozinhaParaAdicaoValidator tipoDeCozinhaParaAdicaoValidator = new TipoDeCozinhaParaAdicaoValidator(repository);
        Errors errors = mock(Errors.class);

        tipoDeCozinhaParaAdicaoValidator.validate(tipoDeCozinhaForm, errors);

        verify(errors).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }

    @Test
    void quandoNomeNaoExisteNaoDaErro() {
        TipoDeCozinhaForm tipoDeCozinhaForm = new TipoDeCozinhaForm();
        tipoDeCozinhaForm.setNome("Mexicana");

        TipoDeCozinhaRepository repository = mock(TipoDeCozinhaRepository.class);
        when(repository.existsByNome("Mexicana")).thenReturn(false);

        TipoDeCozinhaParaAdicaoValidator tipoDeCozinhaParaAdicaoValidator = new TipoDeCozinhaParaAdicaoValidator(repository);
        Errors errors = mock(Errors.class);

        tipoDeCozinhaParaAdicaoValidator.validate(tipoDeCozinhaForm, errors);

        verify(errors, never()).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }

}