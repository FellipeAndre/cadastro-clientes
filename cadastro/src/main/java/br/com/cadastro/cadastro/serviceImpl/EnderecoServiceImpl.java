package br.com.cadastro.cadastro.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.cadastro.entity.Endereco;
import br.com.cadastro.cadastro.repository.EnderecoRepositoryImpl;
import br.com.cadastro.cadastro.service.EnderecoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class EnderecoServiceImpl implements EnderecoService{
	
	@Autowired
	private EnderecoRepositoryImpl enderecoJpa;

	@Override
	public void save(Endereco endereco) {
		
		this.enderecoJpa.save(endereco);
		
	}

}
