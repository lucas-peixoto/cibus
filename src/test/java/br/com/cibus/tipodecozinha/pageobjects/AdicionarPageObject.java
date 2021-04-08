package br.com.cibus.tipodecozinha.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdicionarPageObject extends PageObject {

    public static String caminhoDaPagina = "/admin/tipos-de-cozinha/novo";

    public AdicionarPageObject(WebDriver browser, String urlBase) {
        super(browser, urlBase);
    }

    public String tituloDoCabecalho() {
        return browser.findElement(By.className("titulo")).getText();
    }

    public ListarPageObject cadastraTipoDeCozinhaValido(String nome) {
        cadastraTipoDeCozinha(nome);
        return new ListarPageObject(browser, urlBase);
    }

    public void cadastraTipoDeCozinhaInvalido(String nome) {
        cadastraTipoDeCozinha(nome);
    }

    private void cadastraTipoDeCozinha(String nome) {
        WebElement form = browser.findElement(By.cssSelector(".form-adicionar-tipo-de-cozinha"));
        form.findElement(By.id("nome")).sendKeys(nome);
        form.findElement(By.cssSelector("input[type='submit']")).click();
    }

    public String erros() {
        return browser.findElement(By.id("nome.errors")).getText();
    }

    @Override
    public String urlDaPagina() {
        return urlBase + caminhoDaPagina;
    }
}
