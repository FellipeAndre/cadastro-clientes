package br.com.cadastro.cadastro.serviceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cadastro.cadastro.entity.Telefone;
import br.com.cadastro.cadastro.repository.TelefoneRepositoryImpl;
import br.com.cadastro.cadastro.service.TelefoneService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class TelefoneServiceImpl implements TelefoneService{
	
	@Autowired
	TelefoneRepositoryImpl repository;

	public ResponseEntity<Telefone> getSalvarTelefone(Telefone telefone) {
		   
        ResponseEntity<Telefone> response = null;
		
		if(Objects.isNull(telefone)) {
			
			response = ResponseEntity.notFound().build();
		}else {
		     
		  this.repository.save(telefone);
		    
		  response = ResponseEntity.ok().build();
		}
		  
		return response;
	}

}
