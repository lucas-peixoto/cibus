package br.com.cibus.tipodecozinha.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ListarPageObject extends PageObject {

    public static String caminhoDaPagina = "/admin/tipos-de-cozinha";

    public ListarPageObject(WebDriver browser, String urlBase) {
        super(browser, urlBase);
    }

    public String tituloDoCabecalho() {
        return browser.findElement(By.className("titulo")).getText();
    }

    public List<WebElement> linhasDaTabela() {
        return browser.findElements(By.cssSelector("table.table tbody tr"));
    }

    public List<String> nomesDasLinhasDaTabela() {
        return linhasDaTabela().stream()
                .map(el -> el.findElement(By.className("nome-tipo-de-cozinha")).getText())
                .collect(Collectors.toList());
    }

    @Override
    public String urlDaPagina() {
        return urlBase + caminhoDaPagina;
    }
}
