package br.com.triadworks.lanceunico.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Promocao {

	private Integer id;
	
	private String nome;
	
	private double valorMaximoPermitido;
	
	private Date data;
	
	private List<Lance> lances = new ArrayList<>();
	
	public Promocao(){}
	
	public Promocao(String nome) {
		this.nome = nome;
	}


	/**
	 * Registra um novo lance
	 */
	public void registra(Lance lance) {
		this.lances.add(lance);
	}

	public List<Lance> getLances() {
		return lances;
	}
}
