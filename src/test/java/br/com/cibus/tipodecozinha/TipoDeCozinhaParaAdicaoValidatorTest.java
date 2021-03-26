package br.com.cibus.tipodecozinha;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

import static org.mockito.Mockito.*;

class TipoDeCozinhaParaAdicaoValidatorTest {

    private TipoDeCozinhaRepository repository;
    private TipoDeCozinhaParaAdicaoValidator tipoDeCozinhaParaAdicaoValidator;
    private TipoDeCozinhaForm tipoDeCozinhaForm;
    private Errors errors;

    @BeforeEach
    void init() {
        //STUB
        tipoDeCozinhaForm = mock(TipoDeCozinhaForm.class);

        //STUB
        repository = mock(TipoDeCozinhaRepository.class);
        when(repository.existsByNome("Mexicana")).thenReturn(true);

        //MOCK
        errors = mock(Errors.class);

        tipoDeCozinhaParaAdicaoValidator = new TipoDeCozinhaParaAdicaoValidator(repository);
    }

    @Test
    void quandoNomeJaExisteDeveDarErro() {
        when(tipoDeCozinhaForm.getNome()).thenReturn("Mexicana");

        tipoDeCozinhaParaAdicaoValidator.validate(tipoDeCozinhaForm, errors);

        verify(errors).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }

    @Test
    void quandoNomeNaoExisteNaoDaErro() {
        when(tipoDeCozinhaForm.getNome()).thenReturn("Baiana");

        tipoDeCozinhaParaAdicaoValidator.validate(tipoDeCozinhaForm, errors);

        verify(errors, never()).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }

}