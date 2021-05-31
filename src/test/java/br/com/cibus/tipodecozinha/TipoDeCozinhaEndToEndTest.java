package br.com.cibus.tipodecozinha;

import br.com.cibus.tipodecozinha.pageobjects.AdicionarTipoDeCozinhaPageObject;
import br.com.cibus.tipodecozinha.pageobjects.EditarTipoDeCozinhaPageObject;
import br.com.cibus.tipodecozinha.pageobjects.ListarTipoDeCozinhaPageObject;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.list;

@ActiveProfiles("teste")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TipoDeCozinhaEndToEndTest {

    @LocalServerPort
    private String serverPort;
    private WebDriver browser;

    @Autowired
    Flyway flyway;

    @BeforeEach
    void beforeAll() {
        flyway.clean();
        flyway.migrate();
        browser = new ChromeDriver();
    }

    @AfterEach
    void afterAll() {
        browser.quit();
    }

    @Test
    void lista() {
        ListarTipoDeCozinhaPageObject listagem = abrirListagem();

        assertThat(listagem.tituloDaPagina()).isEqualTo("Tipos de Cozinha");
        assertThat(listagem.tituloDoCabecalho()).isEqualTo("Tipos de Cozinha");
        assertThat(listagem.nomesDasLinhasDaTabela())
                .containsAll(list("Árabe", "Baiana", "Chinesa", "Italiana"));
    }

    @Test
    void adiciona() {
        ListarTipoDeCozinhaPageObject listagem = abrirListagem();
        AdicionarTipoDeCozinhaPageObject paginaAdicionar = listagem.clickAdicionar();

        assertThat(paginaAdicionar.ehPaginaAtual()).isTrue();
        assertThat(paginaAdicionar.tituloDaPagina()).isEqualTo("Adicionar um Tipo de Cozinha");
        assertThat(paginaAdicionar.tituloDoCabecalho()).isEqualTo("Adicionar um Tipo de Cozinha");

        listagem = paginaAdicionar.cadastraTipoDeCozinhaValido("Azerbaijani");

        assertThat(listagem.ehPaginaAtual()).isTrue();
        assertThat(listagem.nomesDasLinhasDaTabela())
                .contains("Azerbaijani")
                .hasSize(5);
    }

    @Test
    void adicionaComNomeInvalido() {
        ListarTipoDeCozinhaPageObject listagem = abrirListagem();
        AdicionarTipoDeCozinhaPageObject paginaAdicionar = listagem.clickAdicionar();

        String nomeVazio = "";
        paginaAdicionar.cadastraTipoDeCozinhaInvalido(nomeVazio);

        assertThat(paginaAdicionar.ehPaginaAtual()).isTrue();

        String nomeJaCadastrado = "Italiana";
        paginaAdicionar.cadastraTipoDeCozinhaInvalido(nomeJaCadastrado);

        assertThat(paginaAdicionar.ehPaginaAtual()).isTrue();
        assertThat(paginaAdicionar.erros()).contains("Nome já existente");
    }

    @Test
    void edita() {
        ListarTipoDeCozinhaPageObject listagem = abrirListagem();

        String nomeAntigo = "Baiana";
        EditarTipoDeCozinhaPageObject paginaEditar = listagem.clickEditar(nomeAntigo);

        assertThat(paginaEditar.ehPaginaAtual()).isTrue();
        assertThat(paginaEditar.tituloDaPagina()).isEqualTo("Editar um Tipo de Cozinha");
        assertThat(paginaEditar.tituloDoCabecalho()).isEqualTo("Editar um Tipo de Cozinha");

        String nomeNovo = "Mexicana";
        listagem = paginaEditar.editaTipoDeCozinhaValido(nomeNovo);

        assertThat(listagem.ehPaginaAtual()).isTrue();
        assertThat(listagem.nomesDasLinhasDaTabela())
                .doesNotContain(nomeAntigo)
                .contains(nomeNovo)
                .hasSize(4);
    }

    @Test
    void editaComNomeInvalido() {
        ListarTipoDeCozinhaPageObject listagem = abrirListagem();

        String nomeAntigo = "Árabe";
        EditarTipoDeCozinhaPageObject paginaEditar = listagem.clickEditar(nomeAntigo);

        String nomeVazio = "";
        paginaEditar.editaTipoDeCozinhaInvalido(nomeVazio);

        assertThat(paginaEditar.ehPaginaAtual()).isTrue();

        String nomeJaCadastrado = "Italiana";
        paginaEditar.editaTipoDeCozinhaInvalido(nomeJaCadastrado);

        assertThat(paginaEditar.ehPaginaAtual()).isTrue();
        assertThat(paginaEditar.erros()).contains("Nome já existente");
    }

    @Test
    void toggleAtivo() {
        ListarTipoDeCozinhaPageObject listagem = abrirListagem();

        String nomeDesativado = "Chinesa";
        listagem.clickDesativar(nomeDesativado);

        assertThat(listagem.ehPaginaAtual()).isTrue();
        assertThat(listagem.nomesDasLinhasInativasDaTabela())
                .contains(nomeDesativado)
                .hasSize(1);

        String nomeAtivado = "Chinesa";
        listagem.clickAtivar(nomeAtivado);

        assertThat(listagem.ehPaginaAtual()).isTrue();
        assertThat(listagem.nomesDasLinhasAtivasDaTabela())
                .contains(nomeAtivado)
                .hasSize(4);
    }

    private String baseURL() {
        return "http://localhost:" + serverPort;
    }

    private ListarTipoDeCozinhaPageObject abrirListagem() {
        ListarTipoDeCozinhaPageObject listarPage = new ListarTipoDeCozinhaPageObject(browser, baseURL());
        listarPage.abrirPagina();

        return listarPage;
    }
}
