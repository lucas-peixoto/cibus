package br.com.cibus.tipodecozinha;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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
    private ChromeDriver browser;
    private String base_url;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "C:/selenium/chromedriver.exe");
    }

    @BeforeEach
    void init() {
        browser = new ChromeDriver();
        base_url = "http://localhost:" + serverPort;
    }

    @AfterEach
    void after() {
        browser.quit();
    }

    @Test
    void lista() {
        browser.get(base_url + "/admin/tipos-de-cozinha");

        String headerTitle = browser.getTitle();
        WebElement titulo = browser.findElementByCssSelector(".titulo");
        List<WebElement> linhas = browser.findElementsByCssSelector("table.table tbody tr");

        assertThat(headerTitle).isEqualTo("Tipos de Cozinha");
        assertThat(titulo.getText()).isEqualTo("Tipos de Cozinha");
        // O número de linhas no banco pode mudar de acordo com a ordem de execução dos testes, por isso é utilizado ">= 3"
        assertThat(linhas).hasSizeGreaterThanOrEqualTo(3);
    }

    @Test
    void adiciona() {
        browser.get(base_url + "/admin/tipos-de-cozinha");
        browser.findElementByCssSelector(".link-adicionar-novo-tipo-de-cozinha").click();

        String titulo = browser.findElementByCssSelector(".titulo").getText();

        assertThat(browser.getCurrentUrl()).isEqualTo(base_url + "/admin/tipos-de-cozinha/novo");
        assertThat(browser.getTitle()).isEqualTo("Adicionar um Tipo de Cozinha");
        assertThat(titulo).isEqualTo("Adicionar um Tipo de Cozinha");

        WebElement form = browser.findElementByCssSelector(".form-adicionar-tipo-de-cozinha");
        form.findElement(By.id("nome")).sendKeys("Azerbaijani");
        form.findElement(By.cssSelector("input[type='submit']")).click();

        List<WebElement> linhas = browser.findElementsByCssSelector("table.table tbody tr td.nome-tipo-de-cozinha");

        assertThat(browser.getCurrentUrl()).isEqualTo(base_url + "/admin/tipos-de-cozinha");
        assertThat(linhas).extracting(WebElement::getText).contains("Azerbaijani");
    }

    @Test
    void adicionaComNomeInvalido() {
        browser.get(base_url + "/admin/tipos-de-cozinha");
        browser.findElementByCssSelector(".link-adicionar-novo-tipo-de-cozinha").click();

        WebElement form = browser.findElementByCssSelector(".form-adicionar-tipo-de-cozinha");
        WebElement submitButton = form.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();

        assertThat(browser.getCurrentUrl()).isEqualTo(base_url + "/admin/tipos-de-cozinha/novo");

        form.findElement(By.id("nome")).sendKeys("Chinesa");
        submitButton.click();

        String errors = browser.findElementById("nome.errors").getText();

        assertThat(browser.getCurrentUrl()).isEqualTo(base_url + "/admin/tipos-de-cozinha/novo");
        assertThat(errors).contains("Nome já existente");
    }

    @Test
    void edita() {
        browser.get(base_url + "/admin/tipos-de-cozinha");
        browser.findElementByCssSelector("table.table tbody tr a.link-editar-tipo-de-cozinha").click();

        String titulo = browser.findElementByCssSelector(".titulo").getText();

        assertThat(browser.getCurrentUrl()).isEqualTo(base_url + "/admin/tipos-de-cozinha/editar/1");
        assertThat(browser.getTitle()).isEqualTo("Editar um Tipo de Cozinha");
        assertThat(titulo).isEqualTo("Editar um Tipo de Cozinha");

        WebElement form = browser.findElementByCssSelector(".form-editar-tipo-de-cozinha");
        WebElement inputNome = form.findElement(By.id("nome"));

        inputNome.clear();
        inputNome.sendKeys("Mexicana");
        form.findElement(By.cssSelector("input[type='submit']")).click();

        List<WebElement> linhas = browser.findElementsByCssSelector("table.table tbody tr td.nome-tipo-de-cozinha");

        assertThat(browser.getCurrentUrl()).isEqualTo(base_url + "/admin/tipos-de-cozinha");
        assertThat(linhas)
                .extracting(WebElement::getText)
                .contains("Mexicana")
                .doesNotContain("Árabe");
    }

    @Test
    void editaComNomeInvalido() {
        browser.get(base_url + "/admin/tipos-de-cozinha");
        browser.findElementByCssSelector("table.table tbody tr a.link-editar-tipo-de-cozinha").click();

        WebElement form = browser.findElementByCssSelector(".form-editar-tipo-de-cozinha");
        WebElement inputNome = form.findElement(By.id("nome"));
        WebElement submitButton = form.findElement(By.cssSelector("input[type='submit']"));

        inputNome.clear();
        submitButton.click();

        assertThat(browser.getCurrentUrl()).isEqualTo(base_url + "/admin/tipos-de-cozinha/editar/1");

        inputNome.clear();
        inputNome.sendKeys("Chinesa");
        submitButton.click();

        String errors = browser.findElementById("nome.errors").getText();

        assertThat(browser.getCurrentUrl()).isEqualTo(base_url + "/admin/tipos-de-cozinha/editar/1");
        assertThat(errors).contains("Nome já existente");
    }

    @Test
    void remove() {
        browser.get(base_url + "/admin/tipos-de-cozinha");
        browser.findElementByCssSelector("table.table tbody tr:last-child .button-remover-tipo-de-cozinha").click();

        List<WebElement> linhas = browser.findElementsByCssSelector("table.table tbody tr td.nome-tipo-de-cozinha");

        assertThat(browser.getCurrentUrl()).isEqualTo(base_url + "/admin/tipos-de-cozinha");
        assertThat(linhas)
                .extracting(WebElement::getText)
                .doesNotContain("Italiana");
    }
}
