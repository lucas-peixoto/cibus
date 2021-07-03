package br.com.cibus.restaurante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Mockito.*;

class RestauranteParaEdicaoValidatorTest {

    private RestauranteRepository restauranteRepository;
    private RestauranteParaEdicaoForm restauranteParaEdicaoForm;
    private RestauranteParaEdicaoValidator restauranteParaEdicaoValidator;
    private Errors errors;

    @BeforeEach
    void beforeEach() {
        this.restauranteParaEdicaoForm = mock(RestauranteParaEdicaoForm.class);
        this.errors = mock(Errors.class);
        this.restauranteRepository = mock(RestauranteRepository.class);
        when(restauranteRepository.existsByNomeAndIdNot(eq("Almanara"), not(eq(2L)))).thenReturn(true);

        this.restauranteParaEdicaoValidator = new RestauranteParaEdicaoValidator(restauranteRepository);
    }

    @Test
    void quandoTempoMinimoDeEntregaForMenorQueMaximoNaoDeveDarErro() {
        when(restauranteParaEdicaoForm.getTempoMinimoEntrega()).thenReturn(10);
        when(restauranteParaEdicaoForm.getTempoMaximoEntrega()).thenReturn(15);

        restauranteParaEdicaoValidator.validate(restauranteParaEdicaoForm, errors);

        verify(errors, never()).rejectValue("tempoMinimoEntrega", "tempoMinimoEntrega.maior.que.maximo", "Tempo mínimo de entrega não pode ser maior que o tempo máximo de entrega");
    }

    @Test
    void quandoTempoMinimoDeEntregaForMaiorQueMaximoDeveDarErro() {
        when(restauranteParaEdicaoForm.getTempoMinimoEntrega()).thenReturn(15);
        when(restauranteParaEdicaoForm.getTempoMaximoEntrega()).thenReturn(10);

        restauranteParaEdicaoValidator.validate(restauranteParaEdicaoForm, errors);

        verify(errors).rejectValue("tempoMinimoEntrega", "tempoMinimoEntrega.maior.que.maximo", "Tempo mínimo de entrega não pode ser maior que o tempo máximo de entrega");
    }

    @Test
    void quandoNomeNaoExisteMasIdExisteNaoDeveDarErro() {
        when(restauranteParaEdicaoForm.getId()).thenReturn(2L);
        when(restauranteParaEdicaoForm.getNome()).thenReturn("Novo Almanara");

        restauranteParaEdicaoValidator.validate(restauranteParaEdicaoForm, errors);

        verify(errors, never()).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }


    @Test
    void quandoNomeJaExisteEOIdForOMesmoNaoDeveDarErro() {
        when(restauranteParaEdicaoForm.getId()).thenReturn(2L);
        when(restauranteParaEdicaoForm.getNome()).thenReturn("Almanara");

        restauranteParaEdicaoValidator.validate(restauranteParaEdicaoForm, errors);

        verify(errors, never()).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }

    @Test
    void quandoNomeJaExisteEOIdForDiferenteDeveDarErro() {
        when(restauranteParaEdicaoForm.getId()).thenReturn(1L);
        when(restauranteParaEdicaoForm.getNome()).thenReturn("Almanara");

        restauranteParaEdicaoValidator.validate(restauranteParaEdicaoForm, errors);

        verify(errors).rejectValue("nome", "nome.ja.existente", "Nome já existente");
    }

}