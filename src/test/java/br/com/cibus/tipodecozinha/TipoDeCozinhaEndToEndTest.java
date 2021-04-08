package br.com.cibus.tipodecozinha;

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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TipoDeCozinhaEndToEndTest {

    @LocalServerPort
    private int serverPort;
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
        browser.get(listaURL());

        String headerTitle = browser.getTitle();
        WebElement titulo = browser.findElement(By.cssSelector(".titulo"));
        List<WebElement> linhas = browser.findElements(By.cssSelector("table.table tbody tr"));

        assertThat(headerTitle).isEqualTo("Tipos de Cozinha");
        assertThat(titulo.getText()).isEqualTo("Tipos de Cozinha");
        // O número de linhas no banco pode mudar de acordo com a ordem de execução dos testes, por isso é utilizado ">= 3"
        assertThat(linhas).hasSizeGreaterThanOrEqualTo(3);
    }

    @Test
    void adiciona() {
        browser.get(listaURL());
        browser.findElement(By.cssSelector(".link-adicionar-novo-tipo-de-cozinha")).click();

        String titulo = browser.findElement(By.cssSelector(".titulo")).getText();

        assertThat(browser.getCurrentUrl()).isEqualTo(adicionaURL());
        assertThat(browser.getTitle()).isEqualTo("Adicionar um Tipo de Cozinha");
        assertThat(titulo).isEqualTo("Adicionar um Tipo de Cozinha");

        WebElement form = browser.findElement(By.cssSelector(".form-adicionar-tipo-de-cozinha"));
        form.findElement(By.id("nome")).sendKeys("Azerbaijani");
        form.findElement(By.cssSelector("input[type='submit']")).click();

        List<WebElement> linhas = browser.findElements(By.cssSelector("table.table tbody tr td.nome-tipo-de-cozinha"));

        assertThat(browser.getCurrentUrl()).isEqualTo(listaURL());
        assertThat(linhas).extracting(WebElement::getText).contains("Azerbaijani");
    }

    @Test
    void adicionaComNomeInvalido() {
        browser.get(listaURL());
        browser.findElement(By.cssSelector(".link-adicionar-novo-tipo-de-cozinha")).click();

        WebElement form = browser.findElement(By.cssSelector(".form-adicionar-tipo-de-cozinha"));
        WebElement submitButton = form.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        assertThat(browser.getCurrentUrl()).isEqualTo(adicionaURL());

        form.findElement(By.id("nome")).sendKeys("Chinesa");
        submitButton.click();

        String errors = browser.findElement(By.id("nome.errors")).getText();

        assertThat(browser.getCurrentUrl()).isEqualTo(adicionaURL());
        assertThat(errors).contains("Nome já existente");
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
