package br.com.cibus.admin;

import br.com.cibus.tipodecozinha.RestaurantesPorTipoDeCozinha;
import br.com.cibus.tipodecozinha.TipoDeCozinhaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {

    @MockBean
    private TipoDeCozinhaRepository tipoDeCozinhaRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void dashboard() throws Exception {
        RestaurantesPorTipoDeCozinha arabe = mock(RestaurantesPorTipoDeCozinha.class);
        RestaurantesPorTipoDeCozinha baiana = mock(RestaurantesPorTipoDeCozinha.class);
        RestaurantesPorTipoDeCozinha mexicana = mock(RestaurantesPorTipoDeCozinha.class);

        List<RestaurantesPorTipoDeCozinha> restaurantesPorTipoDeCozinha = List.of(arabe, baiana, mexicana);
        when(tipoDeCozinhaRepository.contaRestaurantesPorTipoDeCozinha()).thenReturn(restaurantesPorTipoDeCozinha);

        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("restaurantesPorTipoDeCozinha", restaurantesPorTipoDeCozinha))
                .andExpect(view().name("admin/dashboard"));
    }
}
