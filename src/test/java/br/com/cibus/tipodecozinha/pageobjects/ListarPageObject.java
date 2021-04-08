package br.com.cibus.tipodecozinha.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class ListarPageObject extends PageObject {

    public static String caminhoDaPagina = "/admin/tipos-de-cozinha";

    private By titulo = By.className("titulo");
    private By nomeDaTabela = By.className("nome-tipo-de-cozinha");
    private By linhasDaTabela = By.cssSelector("table.table tbody tr");
    private By linkAdicionarNovo = By.className("link-adicionar-novo-tipo-de-cozinha");
    private By linkEditar = By.className("link-editar-tipo-de-cozinha");
    private By botaoRemover = By.className("button-remover-tipo-de-cozinha");

    public ListarPageObject(WebDriver browser, String urlBase) {
        super(browser, urlBase);
    }

    public static ListarPageObject iniciarPagina(WebDriver browser, String urlBase) {
        ListarPageObject listarPage = new ListarPageObject(browser, urlBase);
        listarPage.abrirPagina();

        return listarPage;
    }

    public String tituloDoCabecalho() {
        return browser.findElement(titulo).getText();
    }

    public AdicionarPageObject clickAdicionar() {
        browser.findElement(linkAdicionarNovo).click();
        return new AdicionarPageObject(browser, urlBase);
    }

    public EditarPageObject clickEditar(String nomeTipoDeCozinha) {
        WebElement linha = linhasDaTabela().stream()
                .filter(el -> el.findElement(nomeDaTabela).getText().equals(nomeTipoDeCozinha))
                .findFirst()
                .orElseThrow(NotFoundException::new);

        WebElement botao = linha.findElement(linkEditar);
        String id = botao.getAttribute("href").replace(urlBase + EditarPageObject.caminhoDaPagina, "");
        botao.click();

        return new EditarPageObject(browser, urlBase, id);
    }

    public void clickRemover(String nomeTipoDeCozinha) {
        WebElement linha = linhasDaTabela().stream()
                .filter(el -> el.findElement(nomeDaTabela).getText().equals(nomeTipoDeCozinha))
                .findFirst()
                .orElseThrow(NotFoundException::new);

        linha.findElement(botaoRemover).click();
    }

    public List<WebElement> linhasDaTabela() {
        return browser.findElements(linhasDaTabela);
    }

    public List<String> nomesDasLinhasDaTabela() {
        return linhasDaTabela().stream()
                .map(el -> el.findElement(nomeDaTabela).getText())
                .collect(Collectors.toList());
    }

    @Override
    public String urlDaPagina() {
        return urlBase + caminhoDaPagina;
    }
}
