package br.com.cibus.tipodecozinha;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
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
    void formularioAdicionar() throws Exception {
        mockMvc.perform(get("/admin/tipos-de-cozinha/novo"))
                .andExpect(status().isOk())
                .andExpect(view().name("tipo-de-cozinha/formulario-adicionar"));
    }

    @Test
    void adiciona() throws Exception {
        when(tipoDeCozinhaRepository.existsByNome("Baiana")).thenReturn(false);

        mockMvc.perform(post("/admin/tipos-de-cozinha/novo").param("nome", "Baiana").contentType(APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/admin/tipos-de-cozinha"));

        verify(tipoDeCozinhaRepository).save(any(TipoDeCozinha.class));
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
    void deveDarErroQuandoAdicionaComNomeVazio() throws Exception {
        mockMvc.perform(post("/admin/tipos-de-cozinha/novo").param("nome", "").contentType(APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("tipo-de-cozinha/formulario-adicionar"));

        verify(tipoDeCozinhaRepository, never()).save(any(TipoDeCozinha.class));
    }

    @Test
    void deveDarErroQuandoAdicionaComNomeMuitoGrande() throws Exception {
        mockMvc.perform(post("/admin/tipos-de-cozinha/novo").param("nome", "Lorem ipsum dolor sit amet, consectetur massa nunc.").contentType(APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("tipo-de-cozinha/formulario-adicionar"));

        verify(tipoDeCozinhaRepository, never()).save(any(TipoDeCozinha.class));
    }

    @Test
    void formularioEditar() throws Exception {
        TipoDeCozinha alema = new TipoDeCozinha("Alemã");
        when(tipoDeCozinhaRepository.findById(1L)).thenReturn(Optional.of(alema));

        mockMvc.perform(get("/admin/tipos-de-cozinha/editar/1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("tipoDeCozinha", alema))
                .andExpect(view().name("tipo-de-cozinha/formulario-editar"));
    }

    @Test
    void edita() throws Exception {
        when(tipoDeCozinhaRepository.existsByNomeAndIdNot("Americana", 1L)).thenReturn(false);
        when(tipoDeCozinhaRepository.findById(1L)).thenReturn(Optional.of(new TipoDeCozinha("Alemã")));

        mockMvc.perform(post("/admin/tipos-de-cozinha/editar/1").param("id", "1").param("nome", "Americana").contentType(APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/admin/tipos-de-cozinha"));

        verify(tipoDeCozinhaRepository).save(any(TipoDeCozinha.class));
    }

    @Test
    void deveDarErroQuandoEditaComNomeQueJaExiste() throws Exception {
        when(tipoDeCozinhaRepository.existsByNomeAndIdNot("Mexicana", 1L)).thenReturn(true);

        mockMvc.perform(post("/admin/tipos-de-cozinha/editar/1").param("id", "1").param("nome", "Mexicana").contentType(APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("tipo-de-cozinha/formulario-editar"));

        verify(tipoDeCozinhaRepository, never()).save(any(TipoDeCozinha.class));
    }

    @Test
    void deveDarErroQuandoEditaComNomeVazio() throws Exception {
        mockMvc.perform(post("/admin/tipos-de-cozinha/editar/1").param("id", "1").param("nome", "").contentType(APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("tipo-de-cozinha/formulario-editar"));

        verify(tipoDeCozinhaRepository, never()).save(any(TipoDeCozinha.class));
    }

    @Test
    void deveDarErroQuandoEditaComNomeMuitoGrande() throws Exception {
        mockMvc.perform(post("/admin/tipos-de-cozinha/editar/1").param("id", "1").param("nome", "Lorem ipsum dolor sit amet, consectetur vestibulum.").contentType(APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(view().name("tipo-de-cozinha/formulario-editar"));

        verify(tipoDeCozinhaRepository, never()).save(any(TipoDeCozinha.class));
    }

    @Test
    void ativar() throws Exception {
        TipoDeCozinha egipcia = new TipoDeCozinha("Egípcia");
        egipcia.desativa();

        when(tipoDeCozinhaRepository.findById(1L)).thenReturn(Optional.of(egipcia));

        mockMvc.perform(post("/admin/tipos-de-cozinha/ativar/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/admin/tipos-de-cozinha"));

        assertThat(egipcia.isAtivo()).isTrue();
    }

    @Test
    void desativar() throws Exception {
        TipoDeCozinha egipcia = new TipoDeCozinha("Egípcia");
        egipcia.ativa();

        when(tipoDeCozinhaRepository.findById(1L)).thenReturn(Optional.of(egipcia));

        mockMvc.perform(post("/admin/tipos-de-cozinha/desativar/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/admin/tipos-de-cozinha"));

        assertThat(egipcia.isAtivo()).isFalse();
    }
}