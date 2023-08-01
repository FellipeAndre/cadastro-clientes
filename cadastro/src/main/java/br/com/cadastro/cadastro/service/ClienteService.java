package br.com.cadastro.cadastro.service;

import org.springframework.http.ResponseEntity;

import br.com.cadastro.cadastro.entity.Cliente;

public interface ClienteService {
	
	public Cliente findByIdCliente(Integer id);

	//public Cliente save(Cliente cliente);
	
	public ResponseEntity<Cliente> getSalvarCliente(Cliente cliente);
}

