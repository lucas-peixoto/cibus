package br.com.cibus.tipodecozinha;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.Mockito.*;

class TipoDeCozinhaParaAdicaoValidatorTest {

    private TipoDeCozinhaForm tipoDeCozinhaForm;
    private TipoDeCozinhaRepository repository;
    private TipoDeCozinhaParaAdicaoValidator tipoDeCozinhaParaAdicaoValidator;
    private Errors errors;

    @BeforeEach
    void init() {
        this.tipoDeCozinhaForm = new TipoDeCozinhaForm();
        this.repository = mock(TipoDeCozinhaRepository.class);
        this.tipoDeCozinhaParaAdicaoValidator = new TipoDeCozinhaParaAdicaoValidator(this.repository);
        this.errors = mock(Errors.class);
    }

    @Test
    void quandoNomeJaExisteDeveDarErro() {
        tipoDeCozinhaForm.setNome("Mexicana");
        when(repository.existsByNome("Mexicana")).thenReturn(true);

        tipoDeCozinhaParaAdicaoValidator.validate(tipoDeCozinhaForm, errors);

        verify(errors).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }

    @Test
    void quandoNomeNaoExisteNaoDaErro() {
        tipoDeCozinhaForm.setNome("Mexicana");
        when(repository.existsByNome("Mexicana")).thenReturn(false);

        tipoDeCozinhaParaAdicaoValidator.validate(tipoDeCozinhaForm, errors);

        verify(errors, never()).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }

}