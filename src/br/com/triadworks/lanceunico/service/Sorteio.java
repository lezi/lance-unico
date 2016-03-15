package br.com.triadworks.lanceunico.service;

import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;

public class Sorteio {
	
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	
	public void sorteia(Promocao promocao) {
		
		for (Lance lance : promocao.getLances()) {
			
			if (lance.getValor() > maiorDeTodos) {
				maiorDeTodos = lance.getValor();
			} else if (lance.getValor() < menorDeTodos) {
				menorDeTodos = lance.getValor();
			}
		}
	}
	
	public double getMaiorLance() {
		return maiorDeTodos;
	}
	
	public double getMenorLance() {
		return menorDeTodos;
	}

}
