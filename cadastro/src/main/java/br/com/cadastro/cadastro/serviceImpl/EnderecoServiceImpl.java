package br.com.cadastro.cadastro.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cadastro.cadastro.entity.CadastroDoCliente;
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
	private EnderecoRepositoryImpl repository;

	public ResponseEntity<Endereco> getSalvarEndereco(CadastroDoCliente cadastro) {
		
		Endereco enderecoCliente = new Endereco();

		enderecoCliente.setBairro(cadastro.getBairro());
		enderecoCliente.setCidade(cadastro.getCidade());
		enderecoCliente.setRua(cadastro.getRua());
		enderecoCliente.setEstado(cadastro.getEstado());
		enderecoCliente.setIdcadastro(cadastro);
		   
           ResponseEntity<Endereco> response = null;
           
           Integer idCadastro = cadastro.getIdCadastro();
           
          Optional<Endereco> buscarPorId = this.repository.findById(idCadastro);
		
		if(buscarPorId.isPresent()) {
			 
			response = ResponseEntity.status(HttpStatus.FOUND).build();
		}else {
		     
		  this.repository.save(enderecoCliente);
		    
		  response = ResponseEntity.status(HttpStatus.CREATED).build();
		}
		  
		return response;
	}

	

}
