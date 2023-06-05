package br.com.cadastro.cadastro.rest;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	private EnderecoRepositoryImpl enderecos;
	
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
	
	
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public Endereco update(@PathVariable Integer id, @RequestBody Endereco endereco) {
			  
	 Endereco novoEndereco = this.enderecos.findById(id).map(enderecoEncontrado -> {
			  endereco.setId_Cliente(enderecoEncontrado.getId_Cliente());
			  endereco.setBairro(enderecoEncontrado.getBairro());
			  endereco.setCidade(enderecoEncontrado.getCidade());
			  endereco.setEstado(enderecoEncontrado.getEstado());
			  endereco.setIdEndereco(enderecoEncontrado.getIdEndereco());
			  
			  this.enderecos.save(endereco);
			  return enderecoEncontrado;
		  }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"id informado não existente na base !!!"));  
	  
	 
		return novoEndereco;
	}
}

