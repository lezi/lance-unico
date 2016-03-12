package br.com.triadworks.lanceunico.service;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;

public class Sorteio {
	
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	
	private Lance menorLanceUnico = null;

	public void sorteio(Promocao promocao) {
		
		if (promocao.getLances().isEmpty()) {
			throw new RuntimeException("Promoção não possui lances");
		}
		
		for (Lance lance : promocao.getLances()) {
			
			if (lance.getValor() > maiorDeTodos) {
				maiorDeTodos = lance.getValor();
			}
			
			if (lance.getValor() < menorDeTodos) {
				menorDeTodos = lance.getValor();
			}
		}
		
		menorLanceUnicoNa(promocao);
	}
	
	private void menorLanceUnicoNa(Promocao promocao) {
		SortedSet<Lance> unicos = new TreeSet<>(new Comparator<Lance>() {
			@Override
			public int compare(Lance o1, Lance o2) {
				if (o1.getValor() < o2.getValor()) return -1;
				if (o1.getValor() > o2.getValor()) return 1;
				return 0;
			}
		});
		this.menorLanceUnico = unicos.first();
	}

	public double getMaiorDeTodos() {
		return maiorDeTodos;
	}
	
	public double getMenorDeTodos() {
		return menorDeTodos;
	}
	
	public Lance getMenorLanceUnico() {
		return menorLanceUnico;
	}
	
}
