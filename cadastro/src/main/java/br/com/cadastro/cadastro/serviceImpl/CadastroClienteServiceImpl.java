package br.com.cadastro.cadastro.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.cadastro.cadastro.entity.CadastroDoCliente;
import br.com.cadastro.cadastro.entity.Cliente;
import br.com.cadastro.cadastro.entity.Endereco;
import br.com.cadastro.cadastro.entity.Telefone;
import br.com.cadastro.cadastro.enumeretion.EnumStatus;
import br.com.cadastro.cadastro.repository.CadastroClienteRepositoryImpl;
import br.com.cadastro.cadastro.repository.ClienteRepositoryImpl;
import br.com.cadastro.cadastro.repository.EnderecoRepositoryImpl;
import br.com.cadastro.cadastro.repository.TelefoneRepositoryImpl;
import br.com.cadastro.cadastro.service.CadastroCliente;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CadastroClienteServiceImpl implements CadastroCliente{
	
	@Autowired
	private CadastroClienteRepositoryImpl cadastros;
	
	@Autowired
	private ClienteRepositoryImpl clientes;
	
	@Autowired
	private EnderecoRepositoryImpl enderecos;
	
	@Autowired
	private TelefoneRepositoryImpl telefones;
	
	public CadastroClienteServiceImpl (ClienteRepositoryImpl repositoryC, CadastroClienteRepositoryImpl repositoryCa,
			EnderecoRepositoryImpl respositoryE, TelefoneRepositoryImpl repositoryT) {
		
		this.cadastros = repositoryCa;
		this.clientes = repositoryC;
		this.enderecos = respositoryE;
		this.telefones = repositoryT;
	}
	
	@Override
	public CadastroDoCliente save(Integer id) {
		
		Optional<Cliente> buscarCliente = this.clientes.findById(id);
		CadastroDoCliente cadastro = new CadastroDoCliente();
		Cliente clientecadastrando = new Cliente();
		CadastroDoCliente save = null;
		
		if(buscarCliente.isPresent()) {
			
			clientecadastrando = buscarCliente.get();
			
			cadastro.setId_Cliente(clientecadastrando);
			cadastro.setNome(clientecadastrando.getNome());
			cadastro.setSexo(clientecadastrando.getSexo());
			cadastro.setData_nasc(new Date("02/10/1993"));
			cadastro.setEmail(clientecadastrando.getEmail());
			cadastro.setCpf(clientecadastrando.getCpf());
			
			Endereco enderecoDoCliente = this.enderecos.buscarEnderecoPorIdCliente(id);
			
			cadastro.setId_endereco(enderecoDoCliente);
			cadastro.setRua(enderecoDoCliente.getRua());
			cadastro.setBairro(enderecoDoCliente.getBairro());
			cadastro.setCidade(enderecoDoCliente.getCidade());
			cadastro.setEstado(enderecoDoCliente.getEstado());
			cadastro.setPais("Brazil");
			
			List<Telefone>telefoneDoCliente =  this.telefones.buscarTelefoneClientePorId(id);
			
			cadastro.setId_telefone(telefoneDoCliente.get(0));
			cadastro.setTipo(telefoneDoCliente.get(0).getTipo());
			cadastro.setNumero(telefoneDoCliente.get(0).getNumero());
			
			cadastro.setStatus(EnumStatus.ATIVO.getDescricao());
			
			save = this.cadastros.save(cadastro);
			
		}else {
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return save;
	}

}
