package br.com.triadworks.lanceunico.dao;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;

import br.com.triadworks.lanceunico.modelo.Cliente;

public class ClienteDaoTest {

	@Test
	public void deveBuscarClientePorEmail() {
		
		// passo 1: cenário
		EntityManager entityManager = mock(EntityManager.class);
		Query query = mock(Query.class);
		
		String jpql = "select c from Cliente c where x.email = :email";
		Cliente cliente = new Cliente("Rafael", "rponte@gmail.com");
		
		when(entityManager.createQuery(jpql)).thenReturn(query);
		when(query.setParameter("email", "rponte@gmail.com")).thenReturn(query);
		when(query.getSingleResult()).thenReturn(cliente);
		
		// passo 2: ação
		ClienteDao dao = new ClienteDao(entityManager);
		Cliente clienteDoBanco = dao.buscaPorEmail("rponte@gmail.com");
		
		// passo 3: validação
		assertEquals(cliente.getNome(), clienteDoBanco.getNome());
		assertEquals(cliente.getEmail(), clienteDoBanco.getEmail());
	}

}
