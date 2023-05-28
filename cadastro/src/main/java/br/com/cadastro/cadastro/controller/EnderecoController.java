package br.com.cadastro.cadastro.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.cadastro.cadastro.entity.Cliente;
import br.com.cadastro.cadastro.entity.Endereco;
import br.com.cadastro.cadastro.repository.EnderecoRepositoryImpl;
import br.com.cadastro.cadastro.serviceImpl.ClienteServiceImpl;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

	@Autowired
	EnderecoRepositoryImpl enderecos;
	
	private ClienteServiceImpl serviceCliente;

	public EnderecoController(EnderecoRepositoryImpl repository, ClienteServiceImpl serviceCliente) {
        
		this.serviceCliente = serviceCliente;
		this.enderecos = repository;
	}

	@PostMapping("/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Integer save(@RequestBody Endereco endereco, @PathVariable(name = "id") Integer id) {
		
		Cliente buscarCliente = this.serviceCliente.findByIdCliente(id);
		
		if(Objects.nonNull(buscarCliente)) {
			
			endereco.setId_Cliente(buscarCliente);
			this.enderecos.save(endereco);
			
		}else {	
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
		}	
		return endereco.getIdEndereco();
	}
	
	
}
