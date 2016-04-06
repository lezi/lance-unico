package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NovaPromocaoPage {

	private WebDriver driver;

	public NovaPromocaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public NovaPromocaoPage preencheNome(String nome) {
		WebElement input = driver.findElement(By.name("nome"));
		input.sendKeys(nome);
		return this;
	}

	public NovaPromocaoPage preencheValorMaximo(String valor) {
		WebElement valorMaximo = driver.findElement(By.name("valor"));
		valorMaximo.clear(); // limpa input
		valorMaximo.sendKeys(valor);
		return this;
	}

	public NovaPromocaoPage selecionaReceberEmDinheiro() {
		WebElement checkbox = driver.findElement(By.name("dinheiro"));
        checkbox.click();
		return this;
	}

	public NovaPromocaoPage selecionaLocalDeEntrega(String entrega) {
        Select combo = new Select(driver.findElement(By.name("entrega")));
        combo.selectByVisibleText(entrega);
		return this;
	}

	public void submete() {
		WebElement botao = driver.findElement(By.id("btn-salvar"));
        botao.click();		
	}

}
