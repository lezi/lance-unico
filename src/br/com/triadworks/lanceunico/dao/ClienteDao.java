package br.com.triadworks.lanceunico.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.triadworks.lanceunico.modelo.Cliente;

public class ClienteDao {

	public List<Cliente> lista() {
		List<Cliente> clientes = new ArrayList<>();
		clientes.add(new Cliente("Rafael", "rponte@gmail.com"));
		clientes.add(new Cliente("Rommel", "rommel@gmail.com"));
		clientes.add(new Cliente("Handerson", "handerson@gmail.com"));
		return clientes;
	}

	public void salva(Cliente cliente) {
		
	}

	public void remove(Cliente cliente) {
	}

	public Cliente carrega(Integer id) {
		return new Cliente("Rommel", "rommel@gmail.com"); 
	}

	public void atualiza(Cliente cliente) {
		
	}

}
