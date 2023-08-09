package br.com.cadastro.cadastro.serviceImpl;

import java.util.Objects;
import java.util.Optional;

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
	public Cliente save(Cliente cliente) {

		Cliente clienteSalvo = new Cliente();
		Endereco enderecoSalvo = this.enderecoRepository.save(cliente.getIdEndereco());

		Telefone telSalvo = this.telefoneRepository.save(cliente.getIdTelefone());

		if ((enderecoSalvo.getIdEndereco() != null || Objects.nonNull(enderecoSalvo))
				&& (telSalvo.getIdTelefone() != null || Objects.nonNull(telSalvo))) {

			cliente.setIdEndereco(enderecoSalvo);
			cliente.setIdTelefone(telSalvo);

			clienteSalvo = this.repositoryCliente.save(cliente);
			this.salvarIdCliente(clienteSalvo);
		}

		return clienteSalvo;
	}

	private void salvarIdCliente(Cliente cliente) {

		this.repositoryCliente.findById(cliente.getIdCliente()).map(c -> {

			c.getIdEndereco().setId_Cliente(cliente);
			c.getIdTelefone().setId_cliente(cliente);

			this.enderecoRepository.save(c.getIdEndereco());
			this.telefoneRepository.save(c.getIdTelefone());

			return cliente;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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
