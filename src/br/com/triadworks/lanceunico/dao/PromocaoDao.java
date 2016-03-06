package br.com.triadworks.lanceunico.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;

public class PromocaoDao {
	
	private EntityManager entityManager;
	
	@Inject
	public PromocaoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<Promocao> lista() {
		List<Promocao> promocoes = entityManager
				.createQuery("select p from Promocao p", Promocao.class)
				.getResultList();
		
		return promocoes;
	}

	public Promocao carrega(Integer id) {
		return entityManager.find(Promocao.class, id);
	}

	public void salva(Promocao promocao) {
		entityManager.persist(promocao);		
	}
	
	/**
	 * Registra novo lance numa promoção existente
	 */
	public void registraLance(Integer id, Lance lance) {
		
		Promocao promocao = carrega(id);
		promocao.registra(lance);
		
		entityManager.merge(promocao);
	}

}
