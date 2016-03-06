package br.com.triadworks.lanceunico.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class Cupom {

	private String numero;
	
	private Cupom(){}

	public Cupom(String numero) {
		this.numero = numero;
	}
	
	public String getNumero() {
		return numero;
	}
}
