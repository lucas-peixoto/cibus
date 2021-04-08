package br.com.cibus.tipodecozinha.pageobjects;

import org.openqa.selenium.WebDriver;

public abstract class PageObject {

    protected WebDriver browser;
    protected final String urlBase;

    public PageObject(WebDriver browser, String urlBase) {
        this.browser = browser;
        this.urlBase = urlBase;
    }

    public String tituloDaPagina() {
        return browser.getTitle();
    }

    public String urlAtual() {
        return browser.getCurrentUrl();
    }

    public void abrirPagina() {
        this.browser.get(urlDaPagina());
    }

    public boolean ehPaginaAtual() {
        return urlAtual().equals(urlDaPagina());
    }

    public abstract String urlDaPagina();
}
