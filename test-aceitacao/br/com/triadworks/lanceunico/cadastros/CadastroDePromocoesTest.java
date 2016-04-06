package br.com.triadworks.lanceunico.cadastros;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pageobjects.ClientesPage;
import pageobjects.NovoLancePage;
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
	
	@Before
	public void setUp() {
		driver.get("http://localhost:8080/lance-unico/pages/limpa-banco.xhtml");
	}
	
	@Test
	public void deveRegistrarNovoLance() {
		
		// cria nova promocao
		PromocoesPage promocoes = new PromocoesPage(driver);
		promocoes.abre()
			.novo()
			.preencheNome("Apple TV")
			.preencheValorMaximo("800,00")
			.selecionaReceberEmDinheiro()
			.selecionaLocalDeEntrega("Loja")
			.submete();
		
		// cria novo cliente
		ClientesPage clientes = new ClientesPage(driver);
		clientes.abre()
			.novo()
			.preencheNome("Barry Allen")
			.preencheEmail("the@flash.com")
			.submete();
		
		// seleciona 1a promocao na grid de listagem
		NovoLancePage lance = promocoes.abre().gerenciar();
		
		// dá novo lance
		lance
			.selecionaCliente("Barry Allen")
			.preencheValor("1,99")
			.preencheCupom("C3P0XPTO")
			.darLance();
		
		assertTrue(lance.contemMensagem("Lance registrado com sucesso!"));
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
