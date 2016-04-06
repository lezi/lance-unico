package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClientesPage {

	private WebDriver driver;

	public ClientesPage(WebDriver driver) {
		this.driver = driver;
	}

	public ClientesPage abre() {
		driver.get("http://localhost:8080/lance-unico/pages/clientes/lista.xhtml");
		return this;
	}

	public NovoClientePage novo() {
		// clica no link de novo cliente
        driver.findElement(By.linkText("Novo")).click();
        // retorna a classe que representa a nova pagina
        return new NovoClientePage(driver);
	}

	public boolean contemMensagem(String msg) {
		WebElement mensagens = driver.findElement(By.id("mensagens"));
		return mensagens.getText().contains(msg);
	}

}
