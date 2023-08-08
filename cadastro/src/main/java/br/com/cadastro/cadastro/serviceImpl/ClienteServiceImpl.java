package br.com.cadastro.cadastro.serviceImpl;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Cliente save(Cliente cliente) {
			
		  Cliente clienteSalvo = new Cliente();
			Endereco enderecoSalvo = this.enderecoRepository
					.save(cliente.getIdEndereco());
			
			Telefone telSalvo = this.telefoneRepository.save(cliente.getIdTelefone());
			
			if((enderecoSalvo.getIdEndereco() != null || Objects.nonNull(enderecoSalvo))
					&& (telSalvo.getIdTelefone() != null || Objects.nonNull(telSalvo))) {
				
				cliente.setIdEndereco(enderecoSalvo);
				cliente.setIdTelefone(telSalvo);
				
				clienteSalvo = this.repositoryCliente.save(cliente);
			}
			
		return clienteSalvo;
	}
	
	
	@Override
	public Cliente findByIdCliente(Integer id) {

		Optional<Cliente> buscarCliente = this.repositoryCliente.findById(id);
		Cliente clienteEncontrado = null;

		if (buscarCliente.isPresent()) {

			clienteEncontrado = buscarCliente.get();
		}

		return clienteEncontrado;
	}




}
