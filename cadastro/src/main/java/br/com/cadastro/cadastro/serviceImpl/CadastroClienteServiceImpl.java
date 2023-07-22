package br.com.cadastro.cadastro.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.cadastro.entity.CadastroDoCliente;
import br.com.cadastro.cadastro.repository.CadastroClienteRepositoryImpl;
import br.com.cadastro.cadastro.service.CadastroClienteService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CadastroClienteServiceImpl implements CadastroClienteService{
	
	@Autowired
	private CadastroClienteRepositoryImpl cadastros;
	
	private ClienteServiceImpl clientes;
	
	private EnderecoServiceImpl enderecos;
	
	private TelefoneServiceImpl telefones;
	
	
	@Override
	public Integer save(CadastroDoCliente cliente) {			
		
		return cliente.getIdCadastro();
	}

}
