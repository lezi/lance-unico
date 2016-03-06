package br.com.triadworks.lanceunico.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Promocao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String nome;
	private double valorMaximoPermitido;
	private Date data;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Lance> lances = new ArrayList<>();
	
	public Promocao(){}
	
	public Promocao(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValorMaximoPermitido() {
		return valorMaximoPermitido;
	}
	public void setValorMaximoPermitido(double valorMaximoPermitido) {
		this.valorMaximoPermitido = valorMaximoPermitido;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public List<Lance> getLances() {
		return lances;
	}
	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}
	
	/**
	 * Registra um novo lance
	 */
	public void registra(Lance lance) {
		this.lances.add(lance);
	}
	
}
