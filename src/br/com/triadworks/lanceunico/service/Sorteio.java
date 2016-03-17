package br.com.triadworks.lanceunico.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;

public class Sorteio {
	
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private List<Lance> menores;
	
	public void sorteia(Promocao promocao) {
		
		for (Lance lance : promocao.getLances()) {
			
			if (lance.getValor() > maiorDeTodos) {
				maiorDeTodos = lance.getValor();
			}
			if (lance.getValor() < menorDeTodos) {
				menorDeTodos = lance.getValor();
			}
		}
		
		encontraTresMenoresLancesNa(promocao);
	}

	private void encontraTresMenoresLancesNa(Promocao promocao) {
		menores = new ArrayList<Lance>(promocao.getLances());
        Collections.sort(menores, new Comparator<Lance>() {
            public int compare(Lance o1, Lance o2) {
                if(o1.getValor() < o2.getValor()) return -1;
                if(o1.getValor() > o2.getValor()) return 1;
                return 0;
            }
        });
        menores = menores.subList(0, menores.size() > 3 ? 3 : menores.size());
	}
	
	public List<Lance> getTresMenoresLances() {
		return menores;
	}

	public double getMaiorLance() {
		return maiorDeTodos;
	}
	
	public double getMenorLance() {
		return menorDeTodos;
	}

}
