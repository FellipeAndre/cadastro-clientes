package br.com.cadastro.cadastro.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.cadastro.cadastro.entity.CadastroDoCliente;
import br.com.cadastro.cadastro.entity.Cliente;
import br.com.cadastro.cadastro.repository.ClienteRepositoryImpl;
import br.com.cadastro.cadastro.serviceImpl.CadastroClienteServiceImpl;
import br.com.cadastro.cadastro.serviceImpl.ClienteServiceImpl;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@Autowired
	ClienteRepositoryImpl clientes;
	
	@SuppressWarnings("unused")
	private ClienteServiceImpl service;
	
	private CadastroClienteServiceImpl serviceCliente;

	public ClienteController(ClienteRepositoryImpl repository, ClienteServiceImpl service, 
			CadastroClienteServiceImpl serviceC) {
		this.clientes = repository;
		this.service = service;
		this.serviceCliente = serviceC;
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Integer save(@RequestBody CadastroDoCliente cliente) {
		
		return this.serviceCliente.save(cliente);
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Cliente findByCliente(@PathVariable(name = "id") Integer id) {
		
	Optional<Cliente> dadosDoCliente = this.clientes.findById(id);
	
	if(!dadosDoCliente.isPresent()) {
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado !!!");
	}
		return dadosDoCliente.get();
	}
	
	/*
	@GetMapping("/dadosCliente/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public CadastroDoCliente findByDadosDoClientePorId(@PathVariable(name = "id") Integer id) {
		
	CadastroDoCliente dadosDoCliente = this.serviceCliente.save(id);
	
		return dadosDoCliente;
	}
	*/
	
/** No método deletar recebendo o ID do cliente teremos que encontra o Endereco associado
 * 
 *  a esse cliente e se tiver Telefone associado a ele deletar Também
 *  */	
	
	
	@DeleteMapping("/excluir-cliente/{id}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void delete(@PathVariable(value = "id") Integer id) {
		
		this.clientes.deleteById(id);
	}
	
}
