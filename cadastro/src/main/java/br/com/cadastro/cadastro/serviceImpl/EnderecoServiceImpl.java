package br.com.cadastro.cadastro.serviceImpl;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.cadastro.cadastro.entity.Cliente;
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

	public ResponseEntity<Endereco> getSalvarEndereco(Endereco endereco) {
		   
           ResponseEntity<Endereco> response = null;
		
		if(Objects.isNull(endereco)) {
			
			response = ResponseEntity.notFound().build();
		}else {
		     
		  this.enderecoJpa.save(endereco);
		    
		  response = ResponseEntity.ok().build();
		}
		  
		return response;
	}

	

}
