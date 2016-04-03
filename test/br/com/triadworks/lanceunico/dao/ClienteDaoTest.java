package br.com.triadworks.lanceunico.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.triadworks.lanceunico.modelo.Cliente;
import br.com.triadworks.lanceunico.util.JPAUtil;

public class ClienteDaoTest {
	
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
	public void deveEncontrarClientePorEmail() {
		
		// passo 1: cenário
		Cliente principe = new Cliente("Principe do Oceano", "principe@oceano.com");
		entityManager.persist(principe);
		
		// passo 2: ação
		ClienteDao dao = new ClienteDao(entityManager);
		Cliente clienteDoBanco = dao.buscaPorEmail("principe@oceano.com");
		
		// passo 3: validação
		assertEquals(principe.getNome(), clienteDoBanco.getNome());
		assertEquals(principe.getEmail(), clienteDoBanco.getEmail());
	}
	
	@Test
	public void naoDeveEncontrarClientePorEmail() {
		
		// passo 2: ação
		ClienteDao dao = new ClienteDao(entityManager);
		Cliente cliente = dao.buscaPorEmail("principe@oceano.com");
		
		assertNull(cliente);
	}

}
