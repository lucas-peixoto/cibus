package br.com.cibus.tipodecozinha;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TipoDeCozinhaController.class)
class TipoDeCozinhaControllerTest {

    @MockBean
    private TipoDeCozinhaRepository tipoDeCozinhaRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void lista() throws Exception {
        List<TipoDeCozinha> tiposDeCozinha = List.of(new TipoDeCozinha("Alemã"), new TipoDeCozinha("Mexicana"));
        when(tipoDeCozinhaRepository.findByOrderByNomeAsc()).thenReturn(tiposDeCozinha);

        mockMvc.perform(get("/admin/tipos-de-cozinha"))
                .andExpect(status().isOk())
                .andExpect(view().name("tipo-de-cozinha/listagem"))
                .andExpect(model().attribute("tiposDeCozinha", tiposDeCozinha));
    }

    @Test
    void formularioAdicionar() {
    }

    @Test
    void deveDarErroQuandoAdicionaComNomeQueJaExiste() throws Exception {
        when(tipoDeCozinhaRepository.existsByNome("Azerbaijani")).thenReturn(true);

        mockMvc.perform(post("/admin/tipos-de-cozinha/novo").param("nome", "Azerbaijani").contentType(APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("tipo-de-cozinha/formulario-adicionar"));

        verify(tipoDeCozinhaRepository, never()).save(any(TipoDeCozinha.class));
    }

    @Test
    void adiciona() {
    }

    @Test
    void formularioEditar() {
    }

    @Test
    void edita() {
    }

    @Test
    void remover() {
    }
}