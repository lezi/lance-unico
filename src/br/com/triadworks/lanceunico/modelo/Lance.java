package br.com.triadworks.lanceunico.modelo;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private Cliente cliente;
	
	private double valor;
	
	@Embedded
	private Cupom cupom;
	
	public Lance(){}
	
	public Lance(Cliente cliente, double valor) {
		this.cliente = cliente;
		this.valor = valor;
	}

	public Lance(Cliente cliente, double valor, Cupom cupom) {
		this.cliente = cliente;
		this.valor = valor;
		this.cupom = cupom;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Cupom getCupom() {
		return cupom;
	}
	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}
	
}
