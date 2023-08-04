package br.com.cadastro.cadastro.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/api/cadastrar-cliente")
public class ClienteController {
	
	@Autowired
	ClienteRepositoryImpl clientes;

	public ClienteController(ClienteRepositoryImpl repository) {
		this.clientes = repository;
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
		
		this.clientes.save(cliente);
		
		return cliente.getIdCliente();
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
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public Cliente findByCliente(@PathVariable Integer id) {
		
	Cliente clienteEncontrado = this.clientes.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	
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
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cliente update(@RequestBody Cliente cliente) {
		
	Cliente  clienteAtualizado = this.clientes.findById(cliente.getIdCliente()).
		map(clienteAtualizar ->{
			
			clienteAtualizar.setNome(cliente.getNome());
			clienteAtualizar.setEmail(cliente.getEmail());
			clienteAtualizar.setSexo(cliente.getSexo());
			clienteAtualizar.setCpf(cliente.getCpf());
			
			this.clientes.save(clienteAtualizar);
			
			return clienteAtualizar;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	
		return clienteAtualizado;
	}
	
	
	
}
