package br.com.cadastro.cadastro.serviceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.cadastro.cadastro.entity.CadastroDoCliente;
import br.com.cadastro.cadastro.entity.Cliente;
import br.com.cadastro.cadastro.entity.Endereco;
import br.com.cadastro.cadastro.entity.Telefone;
import br.com.cadastro.cadastro.enumeretion.EnumStatus;
import br.com.cadastro.cadastro.repository.CadastroClienteRepositoryImpl;
import br.com.cadastro.cadastro.service.CadastroClienteService;
import br.com.cadastro.cadastro.service.ClienteService;
import br.com.cadastro.cadastro.service.EnderecoService;
import br.com.cadastro.cadastro.service.TelefoneService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CadastroClienteServiceImpl implements CadastroClienteService {

	@Autowired
	private CadastroClienteRepositoryImpl cadastroRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private TelefoneService telefoneService;

	private String mensagemError;

	@Override
	public Integer getSalvarDadosDoCliente(CadastroDoCliente cliente) {

		cliente.setStatus(EnumStatus.ATIVO.getDescricao());

		CadastroDoCliente cadastroSalvo = this.cadastroRepository.save(cliente);

		if (Objects.nonNull(cadastroSalvo)) {

			this.salvarClienteCadastrado(cadastroSalvo);
			this.salvarEnderecoCadastrado(cadastroSalvo);
			this.salvarTelefoneCadastrado(cadastroSalvo);
		}

		return cadastroSalvo.getIdCadastro();
	}

	private void mensagemDeErros(String mensagem) {

		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, mensagem);
	}
	
	private void salvarClienteCadastrado(CadastroDoCliente cadastroSalvo) {
		
		Cliente dadosCliente = new Cliente();

		dadosCliente.setNome(cadastroSalvo.getNome());
		dadosCliente.setCpf(cadastroSalvo.getCpf());
		dadosCliente.setSexo(cadastroSalvo.getSexo());
		dadosCliente.setEmail(cadastroSalvo.getEmail());
		dadosCliente.setIdcadastro(cadastroSalvo);

		ResponseEntity<Cliente> statusCliente = this.clienteService.getSalvarCliente(dadosCliente);
		
		if (statusCliente.getStatusCode().equals(ResponseEntity.notFound().build())) {
			this.mensagemError = "Não foi salvo os Dados do Cliente na Base";
			this.mensagemDeErros(this.mensagemError);
		}
	}
	
	private void salvarEnderecoCadastrado(CadastroDoCliente cadastroSalvo) {
		
		Endereco enderecoCliente = new Endereco();

		enderecoCliente.setBairro(cadastroSalvo.getBairro());
		enderecoCliente.setCidade(cadastroSalvo.getCidade());
		enderecoCliente.setRua(cadastroSalvo.getRua());
		enderecoCliente.setEstado(cadastroSalvo.getEstado());
		enderecoCliente.setIdcadastro(cadastroSalvo);

		ResponseEntity<Endereco> statusEndereco = this.enderecoService.getSalvarEndereco(enderecoCliente);
		
		if (statusEndereco.getStatusCode().equals(ResponseEntity.notFound().build())) {
			this.mensagemError = "Não foi salvo os Dados de Endereço na Base";
			this.mensagemDeErros(this.mensagemError);
		}
	}
	
	private void salvarTelefoneCadastrado(CadastroDoCliente cadastroSalvo) {
		
		Telefone telCliente = new Telefone();

		telCliente.setNumero(cadastroSalvo.getNumero());
		telCliente.setTipo(cadastroSalvo.getTipo());
		telCliente.setIdcadastro(cadastroSalvo);

		ResponseEntity<Telefone> statusTelefone = this.telefoneService.getSalvarTelefone(telCliente);
		
		if (statusTelefone.getStatusCode().equals(ResponseEntity.notFound().build())) {
			this.mensagemError = "Não foi salvo os Dados de Telefone na Base";
			this.mensagemDeErros(this.mensagemError);
		}
	}
	
	

}
