package br.com.triadworks.lanceunico.testes;

import org.junit.Assert;
import org.junit.Test;

import br.com.triadworks.lanceunico.modelo.Cliente;
import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;
import br.com.triadworks.lanceunico.service.Sorteio;

public class SorteioTest {

	@Test
	public void testSorteio_ordemCrescente() {
		// passo 1: monta o cenário
		Cliente rafael = new Cliente("Rafael");
		Cliente rommel = new Cliente("Rommel");
		Cliente handerson = new Cliente("Handerson");
		
		Promocao promocao = new Promocao("Xbox");
		promocao.registra(new Lance(handerson, 250.00));
		promocao.registra(new Lance(rafael, 300.00));
		promocao.registra(new Lance(rommel, 400.00)); 
		
		// passo 2: executa a ação
		Sorteio sorteio = new Sorteio();
		sorteio.sorteia(promocao);
		
		// passo 3: valida o resultado
		double maiorEsperado = 400.00;
		double menorEsperado = 250.00;
		
		Assert.assertEquals(maiorEsperado, sorteio.getMaiorLance(), 0.0001);
		Assert.assertEquals(menorEsperado, sorteio.getMenorLance(), 0.0001);
	}
	
	@Test
	public void testSorteio_ordemDecrescente() {
		// passo 1: monta o cenário
		Cliente rafael = new Cliente("Rafael");
		Cliente rommel = new Cliente("Rommel");
		Cliente handerson = new Cliente("Handerson");
		
		Promocao promocao = new Promocao("Xbox");
		promocao.registra(new Lance(rommel, 400.00));
		promocao.registra(new Lance(rafael, 300.00));
		promocao.registra(new Lance(handerson, 250.00));
		
		// passo 2: executa a ação
		Sorteio sorteio = new Sorteio();
		sorteio.sorteia(promocao);
		
		// passo 3: valida o resultado
		double maiorEsperado = 400.00;
		double menorEsperado = 250.00;
		
		Assert.assertEquals(maiorEsperado, sorteio.getMaiorLance(), 0.0001);
		Assert.assertEquals(menorEsperado, sorteio.getMenorLance(), 0.0001);
	}
	
	@Test
	public void testSorteio_apenasUmLance() {
		// passo 1: monta o cenário
		Cliente rafael = new Cliente("Rafael");
		
		Promocao promocao = new Promocao("Forno Microondas");
		promocao.registra(new Lance(rafael, 600.00));
		
		// passo 2: executa a ação
		Sorteio sorteio = new Sorteio();
		sorteio.sorteia(promocao);
		
		// passo 3: valida o resultado
		Assert.assertEquals(600.00, sorteio.getMaiorLance(), 0.0001);
		Assert.assertEquals(600.00, sorteio.getMenorLance(), 0.0001);
	}
	
	@Test
	public void testSorteio_ordemAleatoria() {
		// passo 1: monta o cenário
		Cliente rafael = new Cliente("Rafael");
		Cliente rommel = new Cliente("Rommel");
		Cliente handerson = new Cliente("Handerson");
		
		Promocao promocao = new Promocao("Xbox + Kinect");
		promocao.registra(new Lance(rafael, 1050.00));
		promocao.registra(new Lance(rommel, 2990.99));
		promocao.registra(new Lance(handerson, 24.70));
		promocao.registra(new Lance(rafael, 477.00));
		promocao.registra(new Lance(rommel, 1.25));
		
		// passo 2: executa a ação
		Sorteio sorteio = new Sorteio();
		sorteio.sorteia(promocao);
		
		// passo 3: valida o resultado
		double maiorEsperado = 2990.99;
		double menorEsperado = 1.25;
		
		Assert.assertEquals(maiorEsperado, sorteio.getMaiorLance(), 0.0001);
		Assert.assertEquals(menorEsperado, sorteio.getMenorLance(), 0.0001);
	}
}
