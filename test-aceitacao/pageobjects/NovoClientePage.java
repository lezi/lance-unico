package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NovoClientePage {

	private WebDriver driver;

	public NovoClientePage(WebDriver driver) {
		this.driver = driver;
	}

	public NovoClientePage preencheNome(String nome) {
		WebElement input = driver.findElement(By.name("nome"));
		input.sendKeys(nome);
		return this;
	}

	public NovoClientePage preencheEmail(String email) {
		WebElement input = driver.findElement(By.name("email"));
		input.sendKeys(email);
		return this;
	}

	public void submete() {
		WebElement botao = driver.findElement(By.id("btn-salvar"));
        botao.click();
	}

}
