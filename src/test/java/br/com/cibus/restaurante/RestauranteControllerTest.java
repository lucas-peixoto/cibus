package br.com.cibus.restaurante;

import br.com.cibus.tipodecozinha.TipoDeCozinha;
import br.com.cibus.tipodecozinha.TipoDeCozinhaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestauranteController.class)
class RestauranteControllerTest {

    @MockBean
    private RestauranteRepository restauranteRepository;

    @MockBean
    private TipoDeCozinhaRepository tipoDeCozinhaRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void formularioEditar() throws Exception {
        Restaurante almanara = mock(Restaurante.class);
        List<TipoDeCozinha> tiposDeCozinha = List.of(mock(TipoDeCozinha.class), mock(TipoDeCozinha.class));
        when(restauranteRepository.findBySlug("almanara")).thenReturn(Optional.of(almanara));
        when(tipoDeCozinhaRepository.findByOrderByNomeAsc()).thenReturn(tiposDeCozinha);

        mockMvc.perform(get("/restaurantes/editar/almanara"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("restaurante", almanara))
                .andExpect(model().attribute("tiposDeCozinha", tiposDeCozinha))
                .andExpect(view().name("restaurante/formulario-editar"));
    }

    @Test
    void edita() throws Exception {
        Restaurante almanara = mock(Restaurante.class);
        TipoDeCozinha baiana = mock(TipoDeCozinha.class);
        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(almanara));
        when(tipoDeCozinhaRepository.findById(1L)).thenReturn(Optional.of(baiana));

        mockMvc.perform(
                post("/restaurantes/editar/almanara")
                        .param("id", "1")
                        .param("nome", "Almanara")
                        .param("cnpj", "60909215000151")
                        .param("cep", "01046020")
                        .param("endereco", "R Basilio da Gama, 70 - Republica - Sao Paulo - SP")
                        .param("descricao", "Quando se estabeleceu na cidade, em 1950, a culinária árabe ainda não fazia parte do dia a dia paulistano, ou das grandes metrópoles brasileiras, que nem hoje em dia. Com receitas originais da família fundadora, trazidas do Líbano, e uma deliciosa cozinha artesanal, a marca preserva a riqueza de sabores de uma culinária legítima e irresistível. No cardápio, opções como falafel e frango com arroz e amêndoas. No nosso cardápio você encontra as melhores esfihas, kibes e opções árabes de São Paulo")
                        .param("tipoDeCozinhaId", "1")
                        .param("taxaDeEntrega", "15")
                        .param("tempoMinimoEntrega", "20")
                        .param("tempoMaximoEntrega", "40")
                        .contentType(APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/restaurantes/editar/almanara"));

        verify(restauranteRepository).save(any(Restaurante.class));
    }

    @Test
    void deveDarErroQuandoEditaComNomeQueJaExiste() throws Exception {
        Restaurante almanara = mock(Restaurante.class);
        TipoDeCozinha baiana = mock(TipoDeCozinha.class);
        when(restauranteRepository.findById(1L)).thenReturn(Optional.of(almanara));
        when(tipoDeCozinhaRepository.findById(1L)).thenReturn(Optional.of(baiana));
        when(restauranteRepository.existsByNomeAndIdNot("Chinakim", 1L)).thenReturn(true);

        mockMvc.perform(
                post("/restaurantes/editar/almanara")
                        .param("id", "1")
                        .param("nome", "Chinakim")
                        .param("cnpj", "60909215000151")
                        .param("cep", "01046020")
                        .param("endereco", "R Basilio da Gama, 70 - Republica - Sao Paulo - SP")
                        .param("descricao", "Quando se estabeleceu na cidade, em 1950, a culinária árabe ainda não fazia parte do dia a dia paulistano, ou das grandes metrópoles brasileiras, que nem hoje em dia. Com receitas originais da família fundadora, trazidas do Líbano, e uma deliciosa cozinha artesanal, a marca preserva a riqueza de sabores de uma culinária legítima e irresistível. No cardápio, opções como falafel e frango com arroz e amêndoas. No nosso cardápio você encontra as melhores esfihas, kibes e opções árabes de São Paulo")
                        .param("tipoDeCozinhaId", "1")
                        .param("taxaDeEntrega", "15")
                        .param("tempoMinimoEntrega", "20")
                        .param("tempoMaximoEntrega", "40")
                        .contentType(APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().isOk())
                .andExpect(view().name("restaurante/formulario-editar"));

        verify(restauranteRepository, never()).save(any(Restaurante.class));
    }

}
