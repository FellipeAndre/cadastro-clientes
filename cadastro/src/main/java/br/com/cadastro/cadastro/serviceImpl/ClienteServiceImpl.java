package br.com.cadastro.cadastro.serviceImpl;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.cadastro.cadastro.entity.Cliente;
import br.com.cadastro.cadastro.entity.Endereco;
import br.com.cadastro.cadastro.entity.Telefone;
import br.com.cadastro.cadastro.repository.ClienteRepositoryImpl;
import br.com.cadastro.cadastro.repository.EnderecoRepositoryImpl;
import br.com.cadastro.cadastro.repository.TelefoneRepositoryImpl;
import br.com.cadastro.cadastro.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepositoryImpl repositoryCliente;

	@Autowired
	private EnderecoRepositoryImpl enderecoRepository;

	@Autowired
	private TelefoneRepositoryImpl telefoneRepository;

	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		
		Cliente clienteSalvo = new Cliente();
		
		clienteSalvo = this.repositoryCliente.save(cliente);
		
		Endereco enderecoSalvo = this.enderecoRepository.save(cliente.getPk_Endereco());

		Telefone telSalvo = this.telefoneRepository.save(cliente.getPk_Telefone());

		if ((enderecoSalvo.getIdEndereco() != null || Objects.nonNull(enderecoSalvo))
				&& (telSalvo.getIdTelefone() != null || Objects.nonNull(telSalvo))) {

			cliente.setPk_Endereco(enderecoSalvo);
			cliente.setPk_Telefone(telSalvo);
			
			this.salvarIdCliente(clienteSalvo);
		}

		return clienteSalvo;
	}

	private void salvarIdCliente(Cliente cliente) {

		this.repositoryCliente.findById(cliente.getIdCliente()).map(c -> {

			c.getPk_Endereco().setId_Cliente(cliente);
			c.getPk_Telefone().setId_cliente(cliente);

			this.enderecoRepository.save(c.getPk_Endereco());
			this.telefoneRepository.save(c.getPk_Telefone());

			return cliente;
		});
	}

	@Override
	public Cliente findByIdCliente(Integer id) {

	Cliente buscarDadosCliente = this.repositoryCliente.findById(id)
			     .map(c ->{
			    
			    	 c.setPk_Endereco(this.enderecoRepository.findByIdCliente(id)
			    			 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado")));
			    	 
			    	 c.setPk_Telefone(this.telefoneRepository.findByIdCliente(id)
			    			  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Telefone não encontrado")));
			    	 
			    	 return c;
			     })
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));	

		return buscarDadosCliente;
	}

	@Override
	@Transactional
	public Cliente alterarCliente(Cliente cliente) {
		
	Optional<Cliente> clienteAtualizado =	this.repositoryCliente.findById(cliente.getIdCliente());
		
	     if(clienteAtualizado.isPresent()) {
	    	 
	    	 alterarEnderecoCliente(cliente);	
	 		alterarTelefoneCliente(cliente);
	 		
	     }	
		
		return clienteAtualizado.get();
	}

	private Telefone alterarTelefoneCliente(Cliente id) {
		
	Optional<Telefone> telefoneAtualizado =	this.telefoneRepository.findByIdCliente(id.getIdCliente())
		   .map(t -> {
			   
			   t.setNumero(id.getPk_Telefone().getNumero());
				t.setTipo(id.getPk_Telefone().getTipo());
				
				this.telefoneRepository.save(t);
				return t;
		   });
		
	return telefoneAtualizado.get();
	}

	private Endereco alterarEnderecoCliente(Cliente id) {
	 
	Optional<Endereco> enderecoAtualizado =	this.enderecoRepository.findByIdCliente(id.getIdCliente())
		        .map(e -> {
		        	
		        	e.setRua(id.getPk_Endereco().getRua());
					e.setBairro(id.getPk_Endereco().getBairro());
					e.setCidade(id.getPk_Endereco().getCidade());
					e.setEstado(id.getPk_Endereco().getEstado());
					
					this.enderecoRepository.save(e);
					
					return e;
		        });	
	
	return enderecoAtualizado.get();
	}

}
