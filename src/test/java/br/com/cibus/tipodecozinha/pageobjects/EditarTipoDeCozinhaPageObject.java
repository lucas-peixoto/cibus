package br.com.cibus.tipodecozinha.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditarTipoDeCozinhaPageObject extends PageObject {

    public String id;
    public static String caminhoDaPagina = "/admin/tipos-de-cozinha/editar/";

    private By titulo = By.className("titulo");
    private By inputNome = By.id("nome");
    private By formEditar = By.className("form-editar-tipo-de-cozinha");
    private By submitButton = By.cssSelector("input[type='submit']");
    private By nomeErros = By.id("nome.errors");

    public EditarTipoDeCozinhaPageObject(WebDriver browser, String urlBase, String id) {
        super(browser, urlBase);
        this.id = id;
    }

    public String tituloDoCabecalho() {
        return browser.findElement(titulo).getText();
    }

    public ListarTipoDeCozinhaPageObject editaTipoDeCozinhaValido(String nomeTipoDeCozinha) {
        editaTipoDeCozinha(nomeTipoDeCozinha);
        return new ListarTipoDeCozinhaPageObject(browser, urlBase);
    }

    public void editaTipoDeCozinhaInvalido(String nomeTipoDeCozinha) {
        editaTipoDeCozinha(nomeTipoDeCozinha);
    }

    private void editaTipoDeCozinha(String nomeTipoDeCozinha) {
        WebElement form = browser.findElement(formEditar);
        WebElement input = form.findElement(inputNome);

        input.clear();
        input.sendKeys(nomeTipoDeCozinha);
        form.findElement(submitButton).click();
    }

    public String erros() {
        return browser.findElement(nomeErros).getText();
    }

    @Override
    public String urlDaPagina() {
        return urlBase + caminhoDaPagina + id;
    }
}
