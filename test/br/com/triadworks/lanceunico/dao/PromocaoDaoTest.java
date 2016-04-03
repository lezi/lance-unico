package br.com.triadworks.lanceunico.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.triadworks.lanceunico.modelo.Cliente;
import br.com.triadworks.lanceunico.modelo.Promocao;
import br.com.triadworks.lanceunico.modelo.Status;
import br.com.triadworks.lanceunico.util.DateUtils;
import br.com.triadworks.lanceunico.util.JPAUtil;
import builders.CriadorDePromocao;

public class PromocaoDaoTest {
	
	private EntityManager entityManager;
	
	@Before
	public void setUp() {
		entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin(); // inicia transacao
	}
	
	@After
	public void tearDown() {
		entityManager.getTransaction().rollback(); // defaz transacao
		entityManager.close();
	}
	
	@Test
	public void deveEncontrarPromocoesAbertasDoClienteNoPeriodo() {
		
		// passo 1: cenário
		Cliente rafael = new Cliente("Rafael");
		Cliente rommel = new Cliente("Rommel");
		
		Promocao aberta1 = new CriadorDePromocao()
				.para("Notebook DELL")
				.comStatus(Status.ABERTA)
				.naData(DateUtils.novaData("20/03/2016"))
				.comLance(rafael, 1000)
				.cria();
		
		Promocao aberta2 = new CriadorDePromocao()
				.para("Netflix por 3 anos")
				.comStatus(Status.ABERTA)
				.naData(DateUtils.novaData("01/04/2016"))
				.comLance(rommel, 2000)
				.comLance(rafael, 3000)
				.cria();
		
		Promocao encerrada = new CriadorDePromocao()
				.para("Xbox")
				.comStatus(Status.ENCERRADA)
				.naData(DateUtils.novaData("01/04/2016"))
				.comLance(rafael, 2000)
				.cria();
		
		entityManager.persist(rafael);
		entityManager.persist(rommel);
		entityManager.persist(aberta1);
		entityManager.persist(aberta2);
		entityManager.persist(encerrada);
		
		// passo 2: ação
		Date inicio = DateUtils.novaData("19/03/2016");
		
		PromocaoDao dao = new PromocaoDao(entityManager);
		List<Promocao> promocoes = dao.abertasPara(rafael, inicio);
		
		// passo 3: validação
		assertEquals(2, promocoes.size());
		assertEquals(aberta2.getNome(), promocoes.get(0).getNome());
		assertEquals(aberta1.getNome(), promocoes.get(1).getNome());
	}
	
	@Test
	public void deveRemoverUmaPromocao() {
		
		// passo 1: cenário
		Promocao promocao = new CriadorDePromocao()
				.para("Netflix por 3 anos")
				.cria();
		
		entityManager.persist(promocao);
		
		// passo 2: ação
		PromocaoDao dao = new PromocaoDao(entityManager);
		dao.remove(promocao);
		
		// passo 3: validação
		Promocao promocaoDoBanco = dao.carrega(promocao.getId());
		assertNull(promocaoDoBanco);
	}

	@Test
	public void deveContarPromocoesEncerradas() {
		
		// passo 1: cenário
		Promocao aberta = new CriadorDePromocao()
				.para("Notebook DELL")
				.comStatus(Status.ABERTA)
				.cria();
		
		Promocao encerrada = new CriadorDePromocao()
				.para("TV LED 32")
				.comStatus(Status.ENCERRADA)
				.cria();
		
		entityManager.persist(aberta);
		entityManager.persist(encerrada);
		
		// passo 2: ação
		PromocaoDao dao = new PromocaoDao(entityManager);
		Long total = dao.totalDeEncerradas();
		
		// passo 3: validação
		Long totalEsperado = 1L;
		assertEquals(totalEsperado, total);
	}
	
	@Test
	public void deveRetornarZeroQuandoNaoHouverPromocoesEncerradas() {
		
		// passo 1: cenário
		Promocao aberta = new CriadorDePromocao()
				.para("Notebook DELL")
				.comStatus(Status.ABERTA)
				.cria();
		
		Promocao encerrada = new CriadorDePromocao()
				.para("TV LED 32")
				.comStatus(Status.ABERTA)
				.cria();
		
		entityManager.persist(aberta);
		entityManager.persist(encerrada);
		
		// passo 2: ação
		PromocaoDao dao = new PromocaoDao(entityManager);
		Long total = dao.totalDeEncerradas();
		
		// passo 3: validação
		Long totalEsperado = 0L;
		assertEquals(totalEsperado, total);
	}

}
