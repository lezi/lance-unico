package br.com.triadworks.lanceunico.service;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import static org.mockito.Mockito.*;

import br.com.triadworks.lanceunico.dao.PromocaoDao;
import br.com.triadworks.lanceunico.modelo.Promocao;
import br.com.triadworks.lanceunico.util.DateUtils;
import builders.CriadorDePromocao;

public class EncerradorDePromocoesTest {
	
	@Test
	public void deveEncerrarPromocoesForaDaVigencia() {
		
		Date antiga = DateUtils.novaData("01/01/2015");
		
		Promocao ps3 = new CriadorDePromocao()
				.para("Playstation 3")
				.naData(antiga)
				.cria();
		
		Promocao tv = new CriadorDePromocao()
				.para("TV LED 52'")
				.naData(antiga)
				.cria();
		
		List<Promocao> promocoes = Arrays.asList(ps3, tv);
		
		PromocaoDao daoFalso = mock(PromocaoDao.class);
		when(daoFalso.abertas()).thenReturn(promocoes);
		
		EncerradorDePromocoes encerrador = new EncerradorDePromocoes(daoFalso);
		encerrador.encerra();
		
		assertTrue("promoção 1 encerrada", ps3.isEncerrada());
		assertTrue("promoção 2 encerrada", tv.isEncerrada());
	}

}
