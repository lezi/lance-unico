package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NovoLancePage {

	private WebDriver driver;

	public NovoLancePage(WebDriver driver) {
		this.driver = driver;
	}

	public NovoLancePage selecionaCliente(String cliente) {
		Select combo = new Select(driver.findElement(By.name("cliente")));
        combo.selectByVisibleText(cliente);
		return this;
	}

	public NovoLancePage preencheValor(String valor) {
		WebElement valorMaximo = driver.findElement(By.name("valor"));
		valorMaximo.clear(); // limpa input
		valorMaximo.sendKeys(valor);
		return this;
	}

	public NovoLancePage preencheCupom(String cupom) {
		driver.findElement(By.name("cupom")).sendKeys(cupom);
		return this;
	}

	public void darLance() {
		WebElement botao = driver.findElement(By.id("btn-lance"));
        botao.click();
	}

	public boolean contemMensagem(String msg) {
		// espera até 5s pela mensagem de sucesso
		WebDriverWait wait = new WebDriverWait(driver, 5);
		Boolean registrado = wait.until(ExpectedConditions
								.textToBePresentInElementLocated(By.id("mensagens"), msg));
		
		return registrado;
	}
	
}
