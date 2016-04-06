package br.com.triadworks.lanceunico.cadastros;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CadastroDePromocoesTest {
	
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
	public void deveAdicionarNovaPromocao() {
		
		driver.get("http://localhost:8080/lance-unico/pages/promocoes/novo.xhtml");

        WebElement nome = driver.findElement(By.name("nome"));
        nome.sendKeys("XBox One");
        
        WebElement valorMaximo = driver.findElement(By.name("valor"));
        valorMaximo.clear(); // limpa input
        valorMaximo.sendKeys("990,00");
        
        // checkbox
        WebElement dinheiro = driver.findElement(By.name("dinheiro"));
        dinheiro.click();
        
        // combobox
        WebElement entrega = driver.findElement(By.name("entrega"));
        Select combo = new Select(entrega);
        combo.selectByVisibleText("Loja");

        WebElement botao = driver.findElement(By.id("btn-salvar"));
        botao.click();

        WebElement mensagens = driver.findElement(By.id("mensagens"));
        assertTrue(mensagens.getText().contains("Promoção criada com sucesso!"));
	}
}
