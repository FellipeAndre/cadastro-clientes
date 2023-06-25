package br.com.cadastro.cadastro.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.cadastro.cadastro.dto.DadosCliente;
import br.com.cadastro.cadastro.dto.DadosClienteDTO;
import br.com.cadastro.cadastro.entity.Cliente;
import br.com.cadastro.cadastro.repository.ClienteRepositoryImpl;
import br.com.cadastro.cadastro.serviceImpl.ClienteServiceImpl;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	ClienteRepositoryImpl clientes;
	
	private ClienteServiceImpl service;

	public ClienteController(ClienteRepositoryImpl repository, ClienteServiceImpl service) {
		this.clientes = repository;
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Integer save(@RequestBody Cliente cliente) {
		
		this.clientes.save(cliente);
		
		return cliente.getIdCliente();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Cliente findByCliente(@PathVariable(name = "id") Integer id) {
		
	Cliente dadosDoCliente = this.clientes.bringCustomerData(id);
	
	if(Objects.isNull(dadosDoCliente)) {
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Dados não encontrado !!!");
	}
		return dadosDoCliente;
	}
	
	@GetMapping("/dadosCliente/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public DadosCliente findByDadosDoClientePorId(@PathVariable Integer id) {
		
	DadosCliente dadosDoCliente = this.service.buscarDadosDoClientePorId(id);
	
	if(Objects.isNull(dadosDoCliente)) {
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Dados não encontrado do cliente !!!");
	}
		return dadosDoCliente;
	}
	
}
