package br.com.cadastro.cadastro.serviceImpl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.cadastro.cadastro.dto.DadosClienteDTO;
import br.com.cadastro.cadastro.entity.Cliente;
import br.com.cadastro.cadastro.entity.Endereco;
import br.com.cadastro.cadastro.entity.Telefone;
import br.com.cadastro.cadastro.repository.ClienteRepositoryImpl;
import br.com.cadastro.cadastro.repository.EnderecoRepositoryImpl;
import br.com.cadastro.cadastro.repository.TelefoneRepositoryImpl;
import br.com.cadastro.cadastro.service.ClienteService;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ClienteServiceImpl implements ClienteService{
	
	private ClienteRepositoryImpl repositoryCliente;
	
	private EnderecoRepositoryImpl EnderecoRepository;
	
	private TelefoneRepositoryImpl TelefoneRepository;
	
	private Endereco endereco = new Endereco();
	
	private Cliente cliente = new Cliente();
	
	private Telefone telefone = new Telefone();
	
	public ClienteServiceImpl(ClienteRepositoryImpl cliente, EnderecoRepositoryImpl endereco, TelefoneRepositoryImpl telefone) {
         
		this.repositoryCliente = cliente;
		this.EnderecoRepository = endereco;
		this.TelefoneRepository = telefone;
	}

	@Override
	public Cliente findByIdCliente(Integer id) {
		
		Optional<Cliente> buscarCliente = this.repositoryCliente.findById(id);
		Cliente clienteEncontrado = null;
		
		if(buscarCliente.isPresent()) {
			
			clienteEncontrado = buscarCliente.get();
		}
		
		return clienteEncontrado;
	}

	@Override
	public DadosClienteDTO buscarDadosPorId(DadosClienteDTO id) {
		
	Optional<Cliente> buscarDadosCliente = this.repositoryCliente.findById(id.getIdCliente());
	this.cliente = buscarDadosCliente.get();
	
	if(this.cliente.getIdCliente().equals(id.getIdCliente())) {
		
		id.setCliente(this.cliente);
	}	
	
	Optional<Endereco>	buscarDadosEnderecoCliente = this.EnderecoRepository.findById(id.getIdEndereco());
	this.endereco = buscarDadosEnderecoCliente.get();
	
	if(this.endereco.getId_Cliente().equals(id.getCliente()) && buscarDadosEnderecoCliente.isPresent()) {
		
		id.setEndereco(this.endereco);
	}else {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"ID Endereco não é desse cliente Cliente!!");
	}
		
	Optional<Telefone> buscarDadosTelefoneCliente = this.TelefoneRepository.findById(id.getIdTelefone());
	this.telefone = buscarDadosTelefoneCliente.get();
	
	if(this.telefone.getId_cliente().equals(id.getCliente())) {
		
		id.setTelefone(this.telefone);
	}
	
		return id;
	}

}
