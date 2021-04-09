package br.com.cibus.tipodecozinha;

import br.com.cibus.tipodecozinha.pageobjects.AdicionarTipoDeCozinhaPageObject;
import br.com.cibus.tipodecozinha.pageobjects.EditarTipoDeCozinhaPageObject;
import br.com.cibus.tipodecozinha.pageobjects.ListarTipoDeCozinhaPageObject;
import org.junit.jupiter.api.*;
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
    private WebDriver browser;

    @BeforeEach
    void beforeAll() {
        browser = new ChromeDriver();
    }

    @AfterEach
    void afterAll() {
        browser.quit();
    }

    @Test
    void lista() {
        ListarTipoDeCozinhaPageObject listarPage = ListarTipoDeCozinhaPageObject.iniciarPagina(browser, baseURL());

        assertThat(listarPage.tituloDaPagina()).isEqualTo("Tipos de Cozinha");
        assertThat(listarPage.tituloDoCabecalho()).isEqualTo("Tipos de Cozinha");
        assertThat(listarPage.nomesDasLinhasDaTabela())
                .containsAll(list("Árabe", "Italiana"));
    }

    @Test
    void adiciona() {
        ListarTipoDeCozinhaPageObject listarPage = ListarTipoDeCozinhaPageObject.iniciarPagina(browser, baseURL());
        AdicionarTipoDeCozinhaPageObject adicionarPage = listarPage.clickAdicionar();

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

        ListarTipoDeCozinhaPageObject listarPage = ListarTipoDeCozinhaPageObject.iniciarPagina(browser, baseURL());
        AdicionarTipoDeCozinhaPageObject adicionarPage = listarPage.clickAdicionar();

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

        ListarTipoDeCozinhaPageObject listarPage = ListarTipoDeCozinhaPageObject.iniciarPagina(browser, baseURL());
        EditarTipoDeCozinhaPageObject editarPage = listarPage.clickEditar(nomeAntigo);

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

        ListarTipoDeCozinhaPageObject listarPage = ListarTipoDeCozinhaPageObject.iniciarPagina(browser, baseURL());
        EditarTipoDeCozinhaPageObject editarPage = listarPage.clickEditar(nomeAntigo);

        editarPage.editaTipoDeCozinhaInvalido(nomeVazio);

        assertThat(editarPage.ehPaginaAtual()).isTrue();

        editarPage.editaTipoDeCozinhaInvalido(nomeJaCadastrado);

        assertThat(editarPage.ehPaginaAtual()).isTrue();
        assertThat(editarPage.erros()).contains("Nome já existente");
    }

    @Test
    void remove() {
        String nomeRemovido = "Chinesa";

        ListarTipoDeCozinhaPageObject listarPage = ListarTipoDeCozinhaPageObject.iniciarPagina(browser, baseURL());
        listarPage.clickRemover(nomeRemovido);

        assertThat(listarPage.ehPaginaAtual()).isTrue();
        assertThat(listarPage.nomesDasLinhasDaTabela())
                .doesNotContain(nomeRemovido);
    }

    private String baseURL() {
        return "http://localhost:" + serverPort;
    }
}
