package br.com.cadastro.cadastro.serviceImpl;

import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cadastro.cadastro.entity.Cliente;
import br.com.cadastro.cadastro.repository.ClienteRepositoryImpl;
import br.com.cadastro.cadastro.service.ClienteService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepositoryImpl repositoryCliente;

	
	public ResponseEntity<Cliente> getSalvarCliente(Cliente cliente) {
		
		ResponseEntity<Cliente> response = null;
		
		if(Objects.isNull(cliente)) {
			
			response = ResponseEntity.notFound().build();
		}else {
		     
		  this.repositoryCliente.save(cliente);
		    
		  response = ResponseEntity.ok().build();
		}
		  
		return response;
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


}
