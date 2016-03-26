package br.com.triadworks.lanceunico.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.triadworks.lanceunico.modelo.Cliente;
import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;
import br.com.triadworks.lanceunico.service.Sorteio;
import builders.CriadorDePromocao;

public class SorteioTest {
	
	private Sorteio sorteio;
	
	private Cliente rafael;
	private Cliente rommel;
	private Cliente handerson;

	@Before
	public void setUp() {
		this.sorteio = new Sorteio();
		this.rafael = new Cliente("Rafael");
		this.rommel = new Cliente("Rommel");
		this.handerson = new Cliente("Handerson");
	}

	@Test
	public void deveSortearLancesEmOrdemCrescente() {
		// passo 1: monta o cenário
		Promocao promocao = new CriadorDePromocao()
			.para("Xbox")
			.comLance(handerson, 250.00)
			.comLance(rafael, 300.00)
			.comLance(rommel, 400.00)
			.cria();
		
		// passo 2: executa a ação
		sorteio.sorteia(promocao);
		
		// passo 3: valida o resultado
		double maiorEsperado = 400.00;
		double menorEsperado = 250.00;
		
		assertEquals(maiorEsperado, sorteio.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, sorteio.getMenorLance(), 0.0001);
	}
	
	@Test
	public void deveSortearLancesEmOrdemDecrescente() {
		// passo 1: monta o cenário
		Promocao promocao = new CriadorDePromocao()
				.para("Xbox")
				.comLance(rommel, 400.00)
				.comLance(rafael, 300.00)
				.comLance(handerson, 250.00)
				.cria();
		
		// passo 2: executa a ação
		sorteio.sorteia(promocao);
		
		// passo 3: valida o resultado
		double maiorEsperado = 400.00;
		double menorEsperado = 250.00;
		
		assertEquals(maiorEsperado, sorteio.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, sorteio.getMenorLance(), 0.0001);
	}
	
	@Test
	public void deveSortearQuandoHouverApenasUmLance() {
		// passo 1: monta o cenário
		Promocao promocao = new CriadorDePromocao()
				.para("Forno Microondas")
				.comLance(rafael, 600.0)
				.cria();
		
		// passo 2: executa a ação
		sorteio.sorteia(promocao);
		
		// passo 3: valida o resultado
		assertEquals(600.00, sorteio.getMaiorLance(), 0.0001);
		assertEquals(600.00, sorteio.getMenorLance(), 0.0001);
	}
	
	@Test
	public void deveSortearLancesEmOrdemAleatoria() {
		// passo 1: monta o cenário
		Promocao promocao = new CriadorDePromocao()
				.para("Xbox + Kinect")
				.comLance(rafael, 1050.0)
				.comLance(rommel, 2990.99)
				.comLance(handerson, 24.70)
				.comLance(rafael, 477.0)
				.comLance(rommel, 1.25)
				.cria();
		
		// passo 2: executa a ação
		sorteio.sorteia(promocao);
		
		// passo 3: valida o resultado
		double maiorEsperado = 2990.99;
		double menorEsperado = 1.25;
		
		assertEquals(maiorEsperado, sorteio.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, sorteio.getMenorLance(), 0.0001);
	}
	
	@Test
	public void deveSortearOsTresMenoresLances() {
		
		// passo 1: monta o cenário
		Promocao promocao = new CriadorDePromocao()
				.para("Xbox + Kinect")
				.comLance(rafael, 300.0)
				.comLance(rommel, 100.0)
				.comLance(handerson, 20.0)
				.comLance(rafael, 440.0)
				.comLance(rommel, 1.25)
				.cria();

		// passo 2: executa a ação
		sorteio.sorteia(promocao);
		
		// passo 3: valida o resultado
		List<Lance> menores = sorteio.getTresMenoresLances();
		
		assertEquals(3, menores.size());
		assertEquals(1.25, menores.get(0).getValor(), 0.00001);
		assertEquals(20.0, menores.get(1).getValor(), 0.00001);
		assertEquals(100.0, menores.get(2).getValor(), 0.00001);
	}
	
	@Test
	public void deveSortearTodosOsLancesQuandoHouverMenosDe3Lances() {
		// passo 1: monta o cenário
		Promocao promocao = new CriadorDePromocao()
				.para("Xbox + Kinect")
				.comLance(rafael, 300.0)
				.comLance(rommel, 100.0)
				.cria();

		// passo 2: executa a ação
		sorteio.sorteia(promocao);

		// passo 3: valida o resultado
		List<Lance> menores = sorteio.getTresMenoresLances();

		assertEquals(2, menores.size());
		assertEquals(100.0, menores.get(0).getValor(), 0.00001);
		assertEquals(300.0, menores.get(1).getValor(), 0.00001);
	}

	@Test
	public void naoDeveSortearQuandoNaoHouverLances() {
		// passo 1: monta o cenário
		Promocao promocao = new CriadorDePromocao()
				.para("Xbox + Kinect")
				.cria();

		// passo 2: executa a ação
		Sorteio sorteio = new Sorteio();
		sorteio.sorteia(promocao);

		// passo 3: valida o resultado
		List<Lance> menores = sorteio.getTresMenoresLances();

		assertEquals(0, menores.size());
	}
	
}
