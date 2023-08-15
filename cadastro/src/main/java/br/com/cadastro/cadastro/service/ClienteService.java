package br.com.cadastro.cadastro.service;

import br.com.cadastro.cadastro.entity.Cliente;

public interface ClienteService {
	
	public Cliente findByIdCliente(Integer id);
	
	public Cliente save(Cliente cliente);


}

