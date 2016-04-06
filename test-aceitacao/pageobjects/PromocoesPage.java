package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PromocoesPage {

	private WebDriver driver;

	public PromocoesPage(WebDriver driver) {
		this.driver = driver;
	}

	public PromocoesPage abre() {
		driver.get("http://localhost:8080/lance-unico/pages/promocoes/lista.xhtml");
		return this;
	}

	public NovaPromocaoPage novo() {
		// clica no link de nova promoção
        driver.findElement(By.linkText("Novo")).click();
        // retorna a classe que representa a nova pagina
		return new NovaPromocaoPage(driver);
	}

	public boolean contemMensagem(String msg) {
		WebElement mensagens = driver.findElement(By.id("mensagens"));
		return mensagens.getText().contains(msg);
	}

	public NovoLancePage gerenciar() {
		 List<WebElement> links = driver.findElements(By.linkText("gerenciar"));
		 links.get(0).click();
		 return new NovoLancePage(driver);
	}

}
