package br.com.cibus.tipodecozinha;

import br.com.cibus.tipodecozinha.pageobjects.AdicionarPageObject;
import br.com.cibus.tipodecozinha.pageobjects.ListarPageObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

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
        browser.get(listaURL());
        browser.findElement(By.cssSelector("table.table tbody tr a.link-editar-tipo-de-cozinha")).click();

        String titulo = browser.findElement(By.cssSelector(".titulo")).getText();

        assertThat(browser.getCurrentUrl()).isEqualTo(editaURL());
        assertThat(browser.getTitle()).isEqualTo("Editar um Tipo de Cozinha");
        assertThat(titulo).isEqualTo("Editar um Tipo de Cozinha");

        WebElement form = browser.findElement(By.cssSelector(".form-editar-tipo-de-cozinha"));
        WebElement inputNome = form.findElement(By.id("nome"));

        inputNome.clear();
        inputNome.sendKeys("Mexicana");
        form.findElement(By.cssSelector("input[type='submit']")).click();

        List<WebElement> linhas = browser.findElements(By.cssSelector("table.table tbody tr td.nome-tipo-de-cozinha"));

        assertThat(browser.getCurrentUrl()).isEqualTo(listaURL());
        assertThat(linhas)
                .extracting(WebElement::getText)
                .contains("Mexicana")
                .doesNotContain("Árabe");
    }

    @Test
    void editaComNomeInvalido() {
        browser.get(listaURL());
        browser.findElement(By.cssSelector("table.table tbody tr a.link-editar-tipo-de-cozinha")).click();

        WebElement form = browser.findElement(By.cssSelector(".form-editar-tipo-de-cozinha"));
        WebElement inputNome = form.findElement(By.id("nome"));
        WebElement submitButton = form.findElement(By.cssSelector("input[type='submit']"));

        inputNome.clear();
        submitButton.click();

        assertThat(browser.getCurrentUrl()).isEqualTo(editaURL());

        inputNome.clear();
        inputNome.sendKeys("Chinesa");
        submitButton.click();

        String errors = browser.findElement(By.id("nome.errors")).getText();

        assertThat(browser.getCurrentUrl()).isEqualTo(editaURL());
        assertThat(errors).contains("Nome já existente");
    }

    @Test
    void remove() {
        browser.get(listaURL());
        browser.findElement(By.cssSelector("table.table tbody tr:last-child .button-remover-tipo-de-cozinha")).click();

        List<WebElement> linhas = browser.findElements(By.cssSelector("table.table tbody tr td.nome-tipo-de-cozinha"));

        assertThat(browser.getCurrentUrl()).isEqualTo(listaURL());
        assertThat(linhas)
                .extracting(WebElement::getText)
                .doesNotContain("Italiana");
    }

    private String baseURL() {
        return "http://localhost:" + serverPort;
    }

    private String listaURL() {
        return baseURL() + "/admin/tipos-de-cozinha";
    }

    private String adicionaURL() {
        return baseURL() + "/admin/tipos-de-cozinha/novo";
    }

    private String editaURL() {
        return baseURL() + "/admin/tipos-de-cozinha/editar/1";
    }
}
