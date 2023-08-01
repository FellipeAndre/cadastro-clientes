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


	@Override
	public Integer getSalvarDadosDoCliente(CadastroDoCliente cliente) {

		cliente.setStatus(EnumStatus.ATIVO.getDescricao());

		CadastroDoCliente cadastroSalvo = this.cadastroRepository.save(cliente);

		if (Objects.nonNull(cadastroSalvo)) {

			this.salvarClienteCadastrado(cadastroSalvo);
			
		}else {
						
			this.mensagemDeErros("Erro durante a persistência do Dado na Base !!!");
		}

		return cadastroSalvo.getIdCadastro();
	}

	
	private void salvarClienteCadastrado(CadastroDoCliente cadastroSalvo) {

		ResponseEntity<Cliente> statusCliente = this.clienteService.getSalvarCliente(cadastroSalvo);
		
		if (statusCliente.getStatusCode().equals(HttpStatus.FOUND)) {
			
			this.mensagemDeErros("ID encontrado na Base os dados não poderam ser persistidos na base");
		}else {
			
			this.salvarEnderecoCadastrado(cadastroSalvo);
		}
	}
	
	private void salvarEnderecoCadastrado(CadastroDoCliente cadastroSalvo) {
		

		ResponseEntity<Endereco> statusEndereco = this.enderecoService.getSalvarEndereco(cadastroSalvo);
		
		if (statusEndereco.getStatusCode().equals(HttpStatus.FOUND)) {
			
			this.mensagemDeErros("ID encontrado na Base os dados não poderam ser persistidos na base");
		}else {
			
			this.salvarTelefoneCadastrado(cadastroSalvo);
		}
	}
	
	private void salvarTelefoneCadastrado(CadastroDoCliente cadastroSalvo) {
		
		ResponseEntity<Telefone> statusTelefone = this.telefoneService.getSalvarTelefone(cadastroSalvo);
		
		if (statusTelefone.getStatusCode().equals(HttpStatus.FOUND)) {
			
			this.mensagemDeErros("ID encontrado na Base os dados não poderam ser persistidos na base");
		}
	}
	
	private void mensagemDeErros(String mensagem) {

		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, mensagem);
	}

}
