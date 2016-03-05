package br.com.triadworks.lanceunico.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.triadworks.lanceunico.controller.util.FacesUtils;
import br.com.triadworks.lanceunico.dao.ClienteDao;
import br.com.triadworks.lanceunico.modelo.Cliente;

@ManagedBean
public class ClientesBean {

	private Cliente cliente = new Cliente();
	private List<Cliente> clientes = new ArrayList<>();
	private ClienteDao dao;
	
	@PostConstruct
	public void init() {
		this.dao = new ClienteDao();
		this.clientes = this.dao.lista();
	}
	
	/**
	 * Lista todos os clientes
	 */
	public void lista() {
		this.clientes = dao.lista();
	}
	
	/**
	 * Grava novo cliente no banco
	 */
	public String salva() {
		dao.salva(this.cliente);
		new FacesUtils().info("Cliente adicionado com sucesso!");
		return "lista";
	}
	
	/**
	 * Remove cliente do banco
	 */
	public void remove(Cliente cliente) {
		dao.remove(cliente);
		new FacesUtils().info("Cliente removido com sucesso!");
	}
	
	/**
	 * Carrega cliente para edição
	 */
	public String edita(Integer id) {
		this.cliente = dao.carrega(id);
		return "edita";
	}
	
	/**
	 * Atualiza dados do cliente no banco
	 */
	public String atualiza() {
		dao.atualiza(this.cliente);
		new FacesUtils().info("Cliente atualizado com sucesso!");
		return "lista";
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	
}
