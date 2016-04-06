package br.com.triadworks.lanceunico.cadastros;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CadastroDeClientesTest {
	
	private static WebDriver driver;
	
	@BeforeClass
	public static void inicializa() {
		driver = new FirefoxDriver();
	}
	
	@AfterClass
	public static void finaliza() {
		 driver.close();
	}
	
	@Test
	public void deveAdicionarNovoCliente() {
		
        driver.get("http://localhost:8080/lance-unico/pages/clientes/novo.xhtml");

        WebElement nome = driver.findElement(By.name("nome"));
        WebElement email = driver.findElement(By.name("email"));

        nome.sendKeys("Bruce Wayne");
        email.sendKeys("bruce@wayne.com");

        WebElement botao = driver.findElement(By.id("btn-salvar"));
        botao.click();

        WebElement mensagens = driver.findElement(By.id("mensagens"));
        assertTrue(mensagens.getText().contains("Cliente criado com sucesso!"));
	}
	
	@Test
	public void naoDeveAdicionarNovoClienteSemEmail() {
		
        driver.get("http://localhost:8080/lance-unico/pages/clientes/novo.xhtml");

        WebElement nome = driver.findElement(By.name("nome"));
        nome.sendKeys("Clark Kent");

        WebElement botao = driver.findElement(By.id("btn-salvar"));
        botao.click();

        WebElement mensagens = driver.findElement(By.id("mensagens"));
        assertTrue(mensagens.getText().contains("Email: campo é obrigatório"));
	}
	
	@Test
	public void naoDeveAdicionarNovoClienteSemNomeEEmail() {
		
		driver.get("http://localhost:8080/lance-unico/pages/clientes/novo.xhtml");
		
		WebElement botao = driver.findElement(By.id("btn-salvar"));
		botao.click();
		
		WebElement mensagens = driver.findElement(By.id("mensagens"));
		assertTrue(mensagens.getText().contains("Nome: campo é obrigatório"));
		assertTrue(mensagens.getText().contains("Email: campo é obrigatório"));
	}

}
