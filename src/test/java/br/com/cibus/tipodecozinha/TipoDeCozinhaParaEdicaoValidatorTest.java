package br.com.cibus.tipodecozinha;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

class TipoDeCozinhaParaEdicaoValidatorTest {

    private TipoDeCozinhaParaEdicaoForm tipoDeCozinhaParaEdicaoForm;
    private TipoDeCozinhaRepository repository;
    private TipoDeCozinhaParaEdicaoValidator tipoDeCozinhaParaEdicaoValidator;
    private Errors errors;

    @BeforeEach
    void init() {
        tipoDeCozinhaParaEdicaoForm = mock(TipoDeCozinhaParaEdicaoForm.class);

        repository = mock(TipoDeCozinhaRepository.class);

        tipoDeCozinhaParaEdicaoValidator = new TipoDeCozinhaParaEdicaoValidator(repository);
        when(repository.existsByNomeAndIdNot(eq("Mexicana"), not(eq(1L)))).thenReturn(true);

        errors = mock(Errors.class);
    }

    /*
    1) quando o usuario salvar um tipo de cozinha com nome Baiana e id 1L
       entao não deve retornar erro

    2) quando o usuario salvar um tipo de cozinha com nome Mexicana e id 1L
       entao não deve retornar erro

    3) quando o usuario salvar um tipo de cozinha com nome Mexicana e id 2L
       DEVE DAR ERRO!

     4) id nao existe
     */

    @Test
    void quandoNomeNaoExisteMasIdExisteNaoDeveDarErro() {
        when(tipoDeCozinhaParaEdicaoForm.getId()).thenReturn(1L);
        when(tipoDeCozinhaParaEdicaoForm.getNome()).thenReturn("Baiana");

        tipoDeCozinhaParaEdicaoValidator.validate(tipoDeCozinhaParaEdicaoForm, errors);

        verify(errors, never()).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }


    @Test
    void quandoNomeJaExisteEOIdForOMesmoNaoDeveDarErro() {
        when(tipoDeCozinhaParaEdicaoForm.getId()).thenReturn(1L);
        when(tipoDeCozinhaParaEdicaoForm.getNome()).thenReturn("Mexicana");

        tipoDeCozinhaParaEdicaoValidator.validate(tipoDeCozinhaParaEdicaoForm, errors);

        verify(errors, never()).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }

    @Test
    void quandoNomeJaExisteEOIdForDiferenteDeveDarErro() {
        when(tipoDeCozinhaParaEdicaoForm.getId()).thenReturn(2L);
        when(tipoDeCozinhaParaEdicaoForm.getNome()).thenReturn("Mexicana");

        tipoDeCozinhaParaEdicaoValidator.validate(tipoDeCozinhaParaEdicaoForm, errors);

        verify(errors).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }

}