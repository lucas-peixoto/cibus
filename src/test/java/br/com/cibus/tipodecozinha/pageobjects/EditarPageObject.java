package br.com.cibus.tipodecozinha.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditarPageObject extends PageObject {

    public String id;
    public static String caminhoDaPagina = "/admin/tipos-de-cozinha/editar/";

    public EditarPageObject(WebDriver browser, String urlBase, String id) {
        super(browser, urlBase);
        this.id = id;
    }

    public String tituloDoCabecalho() {
        return browser.findElement(By.className("titulo")).getText();
    }

    public ListarPageObject editaTipoDeCozinhaValido(String nomeTipoDeCozinha) {
        editaTipoDeCozinha(nomeTipoDeCozinha);
        return new ListarPageObject(browser, urlBase);
    }

    public void editaTipoDeCozinhaInvalido(String nomeTipoDeCozinha) {
        editaTipoDeCozinha(nomeTipoDeCozinha);
    }

    public void editaTipoDeCozinha(String nomeTipoDeCozinha) {
        WebElement form = browser.findElement(By.className("form-editar-tipo-de-cozinha"));
        WebElement inputNome = form.findElement(By.id("nome"));

        inputNome.clear();
        inputNome.sendKeys(nomeTipoDeCozinha);
        form.findElement(By.cssSelector("input[type='submit']")).click();
    }

    public String erros() {
        return browser.findElement(By.id("nome.errors")).getText();
    }

    @Override
    public String urlDaPagina() {
        return urlBase + caminhoDaPagina + id;
    }
}
