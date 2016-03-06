package br.com.triadworks.lanceunico.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.triadworks.lanceunico.controller.util.FacesUtils;
import br.com.triadworks.lanceunico.dao.PromocaoDao;
import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;
import br.com.triadworks.lanceunico.util.Transacional;

@Named
@RequestScoped
public class PromocoesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Promocao promocao = new Promocao();
	private List<Promocao> promocoes = new ArrayList<>();
	
	@Inject
	private PromocaoDao dao;
	@Inject
	private FacesUtils facesUtils;
	
	@PostConstruct
	public void init() {
		this.promocoes = this.dao.lista();
	}
	
	/**
	 * Lista todos os clientes
	 */
	public void lista() {
		this.promocoes = dao.lista();
	}
	
	/**
	 * Grava nova promoção no banco
	 */
	@Transacional
	public String salva() {
		this.promocao.setData(new Date());
		dao.salva(this.promocao);
		facesUtils.info("Promoção adicionada com sucesso!");
		return "lista?faces-redirect=true";
	}
	
	/**
	 * Carrega promoção para edição
	 */
	public String gerenciar(Integer id) {
		this.promocao = dao.carrega(id);
		return "edita";
	}
	
	/**
	 * Registra novo lance na promoção
	 */
	@Transacional
	public void registra(Lance lance) {
		try {
			Integer id = promocao.getId();
			dao.registraLance(id, lance);
		} catch (Exception e) {
			facesUtils.error(e.getMessage());
		}
	}
	
	public Promocao getPromocao() {
		return promocao;
	}
	public List<Promocao> getPromocoes() {
		return promocoes;
	}
	
}
