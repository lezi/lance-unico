package br.com.triadworks.lanceunico.cadastros;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CadastroDeClientesTest {
	
	@Test
	public void deveAdicionarNovoCliente() {
		
		WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/lance-unico/pages/clientes/novo.xhtml");

        WebElement nome = driver.findElement(By.name("nome"));
        WebElement email = driver.findElement(By.name("email"));

        nome.sendKeys("Bruce Wayne");
        email.sendKeys("bruce@wayne.com");

        WebElement botao = driver.findElement(By.id("btn-salvar"));
        botao.click();

        assertTrue(driver.getPageSource().contains("Bruce Wayne"));
        assertTrue(driver.getPageSource().contains("bruce@wayne.com"));

        driver.close();
	}
	
	@Test
	public void naoDeveAdicionarNovoClienteSemEmail() {
		
		WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/lance-unico/pages/clientes/novo.xhtml");

        WebElement nome = driver.findElement(By.name("nome"));
        nome.sendKeys("Clark Kent");

        WebElement botao = driver.findElement(By.id("btn-salvar"));
        botao.click();

        assertTrue(driver.getPageSource().contains("Email: campo é obrigatório"));

        driver.close();
	}
	
	@Test
	public void naoDeveAdicionarNovoClienteSemNomeEEmail() {
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://localhost:8080/lance-unico/pages/clientes/novo.xhtml");
		
		WebElement botao = driver.findElement(By.id("btn-salvar"));
		botao.click();
		
		assertTrue(driver.getPageSource().contains("Email: campo é obrigatório"));
		
		driver.close();
	}

}
