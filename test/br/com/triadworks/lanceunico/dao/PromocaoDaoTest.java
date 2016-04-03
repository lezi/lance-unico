package br.com.triadworks.lanceunico.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.triadworks.lanceunico.modelo.Promocao;
import br.com.triadworks.lanceunico.modelo.Status;
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
