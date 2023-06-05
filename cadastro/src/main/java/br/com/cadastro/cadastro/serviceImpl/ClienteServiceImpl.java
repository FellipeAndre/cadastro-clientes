package br.com.cadastro.cadastro.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.cadastro.cadastro.dto.DadosCliente;
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
public class ClienteServiceImpl implements ClienteService {

	private ClienteRepositoryImpl repositoryCliente;

	private EnderecoRepositoryImpl EnderecoRepository;

	private TelefoneRepositoryImpl TelefoneRepository;

	private DadosCliente dadosCliente = new DadosCliente();
	private Endereco endereco = new Endereco();
	private Cliente cliente = new Cliente();

	private List<Telefone> telefone = new ArrayList<Telefone>();

	public ClienteServiceImpl(ClienteRepositoryImpl cliente, EnderecoRepositoryImpl endereco,
			TelefoneRepositoryImpl telefone) {

		this.repositoryCliente = cliente;
		this.EnderecoRepository = endereco;
		this.TelefoneRepository = telefone;
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

	@Override
	public DadosCliente buscarDadosDoClientePorId(Integer id) {

		Optional<Cliente> buscarDadosCliente = this.repositoryCliente.findById(id);

		if (buscarDadosCliente.isPresent()) {

			this.cliente = buscarDadosCliente.get();
			this.dadosCliente.setCliente(this.cliente);

			Endereco buscarDadosEnderecoCliente = this.EnderecoRepository.buscarEnderecoPorIdCliente(id);

			this.endereco = buscarDadosEnderecoCliente;
			this.dadosCliente.setEndereco(this.endereco);

			List<Telefone> buscarDadosTelefoneCliente = this.TelefoneRepository.buscarTelefoneClientePorId(id);

			this.telefone = buscarDadosTelefoneCliente;
			this.dadosCliente.setTelefone(this.telefone);

		} else {

			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Esse ID não possui registro em nossa Base !!!");
		}

		return this.dadosCliente;
	}

	@Override
	public DadosCliente excluirDadosDoCliente(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}

}
