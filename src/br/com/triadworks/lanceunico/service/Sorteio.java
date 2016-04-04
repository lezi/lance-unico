package br.com.triadworks.lanceunico.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import br.com.triadworks.lanceunico.modelo.Lance;
import br.com.triadworks.lanceunico.modelo.Promocao;

public class Sorteio {
	
	private double maiorDeTodos = Double.NEGATIVE_INFINITY;
	private double menorDeTodos = Double.POSITIVE_INFINITY;
	private List<Lance> menores;
	private Lance menorLanceUnico;
	
	public void sorteia(Promocao promocao) {
		
		if (promocao.getLances().isEmpty())
			throw new RuntimeException("Impossível sortear promoção sem lances.");
		
		for (Lance lance : promocao.getLances()) {
			
			if (lance.getValor() > maiorDeTodos) {
				maiorDeTodos = lance.getValor();
			}
			if (lance.getValor() < menorDeTodos) {
				menorDeTodos = lance.getValor();
			}
		}
		
		encontraTresMenoresLancesNa(promocao);
		encontraMenorLanceUnico(promocao);
	}

	private void encontraMenorLanceUnico(Promocao promocao) {
		// TODO: podemos melhorar o codigo caso Lance implemente Comparable
		SortedSet<Lance> unicos = new TreeSet<Lance>(new Comparator<Lance>() {
            public int compare(Lance o1, Lance o2) {
                if(o1.getValor() < o2.getValor()) return -1;
                if(o1.getValor() > o2.getValor()) return 1;
                return 0;
            }
        });
		
        Set<Lance> dups = new HashSet<Lance>();
        for (Lance lance : promocao.getLances()) {
        		if (!unicos.add(lance)) {
        			dups.add(lance);
        		}
        }
        unicos.removeAll(dups);
		this.menorLanceUnico = unicos.first();
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
	
	public Lance getMenorLanceUnico() {
		return menorLanceUnico;
	}

}
