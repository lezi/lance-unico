package br.com.triadworks.lanceunico.modelo;

public class Lance {

	private Integer id;
	
	private Cliente cliente;
	private double valor;
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
	
	public double getValor() {
		return valor;
	}
}
