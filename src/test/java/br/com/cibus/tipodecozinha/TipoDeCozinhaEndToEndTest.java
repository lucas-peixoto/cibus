package br.com.cibus.tipodecozinha;

import org.junit.jupiter.api.Test;
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

    @Test
    void teste1() {
        System.setProperty("webdriver.chrome.driver", "C:/selenium/chromedriver.exe");

        ChromeDriver browser = new ChromeDriver();
        browser.get("http://localhost:" + serverPort + "/admin/tipos-de-cozinha");
        List<WebElement> linhas = browser.findElementsByCssSelector("table.table tbody tr");

        assertThat(linhas).hasSize(4);
//        browser.quit();
    }
}
