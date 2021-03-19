package br.com.cibus.tipodecozinha;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class TipoDeCozinhaParaEdicaoValidatorTest {

    private TipoDeCozinhaParaEdicaoForm tipoDeCozinhaParaEdicaoForm;
    private TipoDeCozinhaRepository repository;
    private TipoDeCozinhaParaEdicaoValidator tipoDeCozinhaParaEdicaoValidator;
    private Errors errors;

    @BeforeEach
    void init() {
        tipoDeCozinhaParaEdicaoForm = new TipoDeCozinhaParaEdicaoForm();
        tipoDeCozinhaParaEdicaoForm.setId(1L);
        tipoDeCozinhaParaEdicaoForm.setNome("Mexicana");

        repository = mock(TipoDeCozinhaRepository.class);
        tipoDeCozinhaParaEdicaoValidator = new TipoDeCozinhaParaEdicaoValidator(repository);
        errors = mock(Errors.class);
    }

    @Test
    void quandoIdExisteENomeNaoExisteNaoDaErro() {
        when(repository.existsByNomeAndIdNot("Mexicana", 1L)).thenReturn(false);

        tipoDeCozinhaParaEdicaoValidator.validate(tipoDeCozinhaParaEdicaoForm, errors);

        verify(errors, never()).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }

    @Test
    void quandoIdNaoExisteENomeExisteDeveDarErro() {
        when(repository.existsByNomeAndIdNot("Mexicana", 1L)).thenReturn(true);

        tipoDeCozinhaParaEdicaoValidator.validate(tipoDeCozinhaParaEdicaoForm, errors);

        verify(errors).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }

    @Test
    void quandoIdExisteENomeExisteDeveDarErro() {
        when(repository.existsByNomeAndIdNot("Mexicana", 1L)).thenReturn(true);

        tipoDeCozinhaParaEdicaoValidator.validate(tipoDeCozinhaParaEdicaoForm, errors);

        verify(errors).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }

    @Test
    void quandoIdNaoExisteENomeNaoExisteDeveDarErro() {
        when(repository.existsByNomeAndIdNot("Mexicana", 1L)).thenReturn(true);

        tipoDeCozinhaParaEdicaoValidator.validate(tipoDeCozinhaParaEdicaoForm, errors);

        verify(errors).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }
}