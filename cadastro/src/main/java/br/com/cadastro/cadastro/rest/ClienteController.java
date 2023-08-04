package br.com.cadastro.cadastro.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastro.cadastro.entity.Cliente;
import br.com.cadastro.cadastro.repository.ClienteRepositoryImpl;

@RestController
@RequestMapping("/api/cadastrar-cliente")
public class ClienteController {
	
	@Autowired
	ClienteRepositoryImpl clientes;

	public ClienteController(ClienteRepositoryImpl repository) {
		this.clientes = repository;
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Integer save(@RequestBody Cliente cliente) {
		
		this.clientes.save(cliente);
		
		return cliente.getIdCliente();
	}
	
	
	
}
