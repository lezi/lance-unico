package br.com.triadworks.lanceunico.modelo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import builders.CriadorDePromocao;

public class PromocaoTest {
	
	private Cliente rafael;
	private Cliente handerson;

	@Before
	public void setUp() {
		this.rafael = new Cliente("Rafael");
		this.handerson = new Cliente("Handerson");
	}

	@Test
	public void deveRegistrarUmLance() {
		
		Promocao promocao = new CriadorDePromocao()
				.para("iPad Mini")
				.comLance(rafael, 1000.0)
				.cria();
		
		List<Lance> lances = promocao.getLances();
		assertEquals(1, lances.size());
		assertEquals(1000.0, lances.get(0).getValor(), 0.0001);
	}
	
	@Test
	public void deveRegistrarVariosLances() {
		
		Promocao promocao = new CriadorDePromocao()
				.para("iPad Mini")
				.comLance(rafael, 1000.0)
				.comLance(handerson, 2000.0)
				.cria();
		
		List<Lance> lances = promocao.getLances();
		assertEquals(2, lances.size());
		assertEquals(1000.0, lances.get(0).getValor(), 0.0001);
		assertEquals(2000.0, lances.get(1).getValor(), 0.0001);
	}

}
