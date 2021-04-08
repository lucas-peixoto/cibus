package br.com.cibus.tipodecozinha;

import br.com.cibus.tipodecozinha.pageobjects.AdicionarPageObject;
import br.com.cibus.tipodecozinha.pageobjects.EditarPageObject;
import br.com.cibus.tipodecozinha.pageobjects.ListarPageObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.list;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TipoDeCozinhaEndToEndTest {

    @LocalServerPort
    private String serverPort;
    private static WebDriver browser;

    @BeforeAll
    static void beforeAll() {
        browser = new ChromeDriver();
    }

    @AfterAll
    static void afterAll() {
        browser.quit();
    }

    @Test
    void lista() {
        ListarPageObject listarPage = new ListarPageObject(browser, baseURL());
        listarPage.abrirPagina();

        assertThat(listarPage.tituloDaPagina()).isEqualTo("Tipos de Cozinha");
        assertThat(listarPage.tituloDoCabecalho()).isEqualTo("Tipos de Cozinha");
        assertThat(listarPage.nomesDasLinhasDaTabela())
                .containsAll(list("Árabe", "Italiana"));
    }

    @Test
    void adiciona() {
        ListarPageObject listarPage = new ListarPageObject(browser, baseURL());
        listarPage.abrirPagina();
        AdicionarPageObject adicionarPage = listarPage.clickAdicionar();

        assertThat(adicionarPage.ehPaginaAtual()).isTrue();
        assertThat(adicionarPage.tituloDaPagina()).isEqualTo("Adicionar um Tipo de Cozinha");
        assertThat(adicionarPage.tituloDoCabecalho()).isEqualTo("Adicionar um Tipo de Cozinha");

        listarPage = adicionarPage.cadastraTipoDeCozinhaValido("Azerbaijani");

        assertThat(listarPage.ehPaginaAtual()).isTrue();
        assertThat(listarPage.nomesDasLinhasDaTabela()).contains("Azerbaijani");
    }

    @Test
    void adicionaComNomeInvalido() {
        String nomeVazio = "";
        String nomeJaCadastrado = "Italiana";

        ListarPageObject listarPage = new ListarPageObject(browser, baseURL());
        listarPage.abrirPagina();
        AdicionarPageObject adicionarPage = listarPage.clickAdicionar();

        adicionarPage.cadastraTipoDeCozinhaInvalido(nomeVazio);

        assertThat(adicionarPage.ehPaginaAtual()).isTrue();

        adicionarPage.cadastraTipoDeCozinhaInvalido(nomeJaCadastrado);

        assertThat(adicionarPage.ehPaginaAtual()).isTrue();
        assertThat(adicionarPage.erros()).contains("Nome já existente");
    }

    @Test
    void edita() {
        String nomeAntigo = "Baiana";
        String nomeNovo = "Mexicana";

        ListarPageObject listarPage = new ListarPageObject(browser, baseURL());
        listarPage.abrirPagina();
        EditarPageObject editarPage = listarPage.clickEditar(nomeAntigo);

        assertThat(editarPage.ehPaginaAtual()).isTrue();
        assertThat(editarPage.tituloDaPagina()).isEqualTo("Editar um Tipo de Cozinha");
        assertThat(editarPage.tituloDoCabecalho()).isEqualTo("Editar um Tipo de Cozinha");

        listarPage = editarPage.editaTipoDeCozinhaValido(nomeNovo);

        assertThat(listarPage.ehPaginaAtual()).isTrue();
        assertThat(listarPage.nomesDasLinhasDaTabela())
                .doesNotContain(nomeAntigo)
                .contains(nomeNovo);
    }

    @Test
    void editaComNomeInvalido() {
        String nomeAntigo = "Árabe";
        String nomeVazio = "";
        String nomeJaCadastrado = "Italiana";

        ListarPageObject listarPage = new ListarPageObject(browser, baseURL());
        listarPage.abrirPagina();
        EditarPageObject editarPage = listarPage.clickEditar(nomeAntigo);

        editarPage.editaTipoDeCozinhaInvalido(nomeVazio);

        assertThat(editarPage.ehPaginaAtual()).isTrue();

        editarPage.editaTipoDeCozinhaInvalido(nomeJaCadastrado);

        assertThat(editarPage.ehPaginaAtual()).isTrue();
        assertThat(editarPage.erros()).contains("Nome já existente");
    }

    @Test
    void remove() {
        String nomeRemovido = "Chinesa";

        ListarPageObject listarPage = new ListarPageObject(browser, baseURL());
        listarPage.abrirPagina();
        listarPage.clickRemover(nomeRemovido);

        assertThat(listarPage.ehPaginaAtual()).isTrue();
        assertThat(listarPage.nomesDasLinhasDaTabela())
                .doesNotContain(nomeRemovido);
    }

    private String baseURL() {
        return "http://localhost:" + serverPort;
    }
}
