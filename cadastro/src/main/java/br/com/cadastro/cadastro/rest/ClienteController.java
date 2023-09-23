package br.com.cadastro.cadastro.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.cadastro.cadastro.entity.Cliente;
import br.com.cadastro.cadastro.repository.ClienteRepositoryImpl;
import br.com.cadastro.cadastro.service.ClienteService;

@RestController
@RequestMapping("/api/cadastrar-cliente")
public class ClienteController {
	
	@Autowired
	ClienteRepositoryImpl clientes;
	
	@Autowired
	ClienteService service;

	public ClienteController(ClienteRepositoryImpl repository, ClienteService service) {
		this.clientes = repository;
		this.service = service;
	}
	
	/***
	 * 
	 * @param cliente
	 * @return ID do Cliente
	 * 
	 * Método com Requisição POST responsável por salvar os dados do Cliente
	 * 
	 */
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Integer save(@RequestBody Cliente cliente) {
		
		Cliente dadosCliente = this.service.save(cliente);
		
		return dadosCliente.getIdCliente();
	}
	
	/***
	 * 
	 * @param id
	 * @return 
	 * 
	 * Método com a Requisição GET responsável por buscar o cliente pelo sei id,
	 * caso não encontrado retornará um status https 404.
	 *  
	 */
	
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cliente findByCliente(@PathVariable Integer id) {
		
	Cliente clienteEncontrado = this.service.findByIdCliente(id);
	
		return clienteEncontrado;
	}
	
	/***
	 * Método com a Requisição PUT  responsável por atualizar os dados do Cliente recebido no corpo do
	 * JSON.
	 * Caso o Cliente não seja encontrado retornará um HTTP 404
	 * 
	 * @param cliente
	 * @return
	 */
	@PutMapping
	@ResponseStatus(value = HttpStatus.OK)
	public Cliente update(@RequestBody Cliente cliente) {
		
	Cliente clienteAtualizado = this.service.alterarCliente(cliente);
	
		return clienteAtualizado;
	}
	
	
	@DeleteMapping
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		  
		this.clientes.findById(id)
		      .map( clienteEncontrado -> {
		    	  this.clientes.delete(clienteEncontrado);
		    	  return clienteEncontrado;
		    			  
		      }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
}
