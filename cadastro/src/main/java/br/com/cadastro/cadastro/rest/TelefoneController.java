package br.com.cadastro.cadastro.rest;

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
import br.com.cadastro.cadastro.entity.Telefone;
import br.com.cadastro.cadastro.repository.TelefoneRepositoryImpl;
import br.com.cadastro.cadastro.serviceImpl.ClienteServiceImpl;

@RestController
@RequestMapping("/api/telefone")
public class TelefoneController {

	@Autowired
	TelefoneRepositoryImpl repository;
	
	private ClienteServiceImpl serviceCliente;
	
	public TelefoneController(TelefoneRepositoryImpl repository, ClienteServiceImpl serviceCliente) {
		
		this.repository = repository;
		this.serviceCliente = serviceCliente;
	}
	
	
	@PostMapping("/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public Integer save(@RequestBody Telefone tel, @PathVariable(name = "id") Integer id) {
		
		Cliente buscarCliente = this.serviceCliente.findByIdCliente(id);
		Telefone contatos = null;
		
		if(Objects.nonNull(buscarCliente)) {
			
			tel.setId_cliente(buscarCliente);
			contatos = this.repository.save(tel);
			
		}else {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontado");
		}
		
		return contatos.getIdTelefone();
	}

}
