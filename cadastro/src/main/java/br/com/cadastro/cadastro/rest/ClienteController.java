package br.com.cadastro.cadastro.rest;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.cadastro.cadastro.entity.CadastroDoCliente;
import br.com.cadastro.cadastro.service.CadastroClienteService;

@RestController
@RequestMapping("/api/cadastrar-cliente")
public class ClienteController {
	
	@Autowired
	private CadastroClienteService serviceCliente;

	public ClienteController(CadastroClienteService serviceC) {
		
		this.serviceCliente = serviceC;
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Integer save(@RequestBody CadastroDoCliente cadastroCliente) {
		
		Calendar data = Calendar.getInstance();
		cadastroCliente.setData_inclusao(data.getTime());
		
		this.serviceCliente.getSalvarDadosDoCliente(cadastroCliente);
		
		return cadastroCliente.getIdCadastro();
	}
	
	/*
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Cliente findByCliente(@PathVariable(name = "id") Integer id) {
		
	Optional<Cliente> dadosDoCliente = this.serviceCliente;
	
	if(!dadosDoCliente.isPresent()) {
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado !!!");
	}
		return dadosDoCliente.get();
	}
	*/
	
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
	
/*	
	@DeleteMapping("/excluir-cliente/{id}")
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void delete(@PathVariable(value = "id") Integer id) {
		
		this.clientes.deleteById(id);
	}
	*/
	
}
