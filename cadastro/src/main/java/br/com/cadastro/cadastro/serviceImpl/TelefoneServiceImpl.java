package br.com.cadastro.cadastro.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cadastro.cadastro.entity.CadastroDoCliente;
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
	private TelefoneRepositoryImpl repository;
	
	
	/*
	 * Método responsável por salvar e retornar a resposta no Body
	 * 
	 */

	public ResponseEntity<Telefone> getSalvarTelefone(CadastroDoCliente cadastro) {
		   
        ResponseEntity<Telefone> response = null;
        
        Telefone telCliente = new Telefone();

		telCliente.setNumero(cadastro.getNumero());
		telCliente.setTipo(cadastro.getTipo());
		telCliente.setIdcadastro(cadastro);

        
      Integer  idCadastro  = cadastro.getIdCadastro();
      
      Optional<Telefone> buscarPorId =  this.repository.findById(idCadastro);
		
		if(buscarPorId.isPresent()) {
			
			response = ResponseEntity.status(HttpStatus.FOUND).build();
			
		}else {
		     
		  this.repository.save(telCliente);
		    
		  response = ResponseEntity.status(HttpStatus.CREATED).build();
		}
		  
		return response;
	}

}
