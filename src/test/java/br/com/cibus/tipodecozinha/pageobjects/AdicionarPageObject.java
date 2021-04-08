package br.com.cibus.tipodecozinha.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdicionarPageObject extends PageObject {

    public static String caminhoDaPagina = "/admin/tipos-de-cozinha/novo";

    private By titulo = By.className("titulo");
    private By inputNome = By.id("nome");
    private By formAdicionar = By.className("form-adicionar-tipo-de-cozinha");
    private By submitButton = By.cssSelector("input[type='submit']");
    private By nomeErros = By.id("nome.errors");

    public AdicionarPageObject(WebDriver browser, String urlBase) {
        super(browser, urlBase);
    }

    public String tituloDoCabecalho() {
        return browser.findElement(titulo).getText();
    }

    public ListarPageObject cadastraTipoDeCozinhaValido(String nome) {
        cadastraTipoDeCozinha(nome);
        return new ListarPageObject(browser, urlBase);
    }

    public void cadastraTipoDeCozinhaInvalido(String nome) {
        cadastraTipoDeCozinha(nome);
    }

    private void cadastraTipoDeCozinha(String nome) {
        WebElement form = browser.findElement(formAdicionar);
        form.findElement(inputNome).sendKeys(nome);
        form.findElement(submitButton).click();
    }

    public String erros() {
        return browser.findElement(nomeErros).getText();
    }

    @Override
    public String urlDaPagina() {
        return urlBase + caminhoDaPagina;
    }
}
