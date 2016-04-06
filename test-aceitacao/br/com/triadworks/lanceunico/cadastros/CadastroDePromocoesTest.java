package br.com.triadworks.lanceunico.cadastros;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageobjects.PromocoesPage;

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
		
		PromocoesPage listagem = new PromocoesPage(driver);
		listagem.abre()
			.novo()
			.preencheNome("Xbox One")
			.preencheValorMaximo("990,00")
			.selecionaReceberEmDinheiro()
			.selecionaLocalDeEntrega("Loja")
			.submete();
		
        assertTrue(listagem.contemMensagem("Promoção criada com sucesso!"));
	}
}
