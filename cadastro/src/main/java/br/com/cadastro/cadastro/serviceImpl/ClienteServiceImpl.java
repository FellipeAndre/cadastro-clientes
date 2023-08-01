package br.com.cadastro.cadastro.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.cadastro.cadastro.entity.CadastroDoCliente;
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
	
	public ResponseEntity<Cliente> getSalvarCliente(CadastroDoCliente cadastro) {
		
		ResponseEntity<Cliente> response = null;
		
		Cliente dadosCliente = new Cliente();

		dadosCliente.setNome(cadastro.getNome());
		dadosCliente.setCpf(cadastro.getCpf());
		dadosCliente.setSexo(cadastro.getSexo());
		dadosCliente.setEmail(cadastro.getEmail());
		dadosCliente.setIdcadastro(cadastro);
			
		Integer idCadastro = cadastro.getIdCadastro();
		
		Optional<Cliente> buscarId = this.repositoryCliente.findById(idCadastro);
		
		if(buscarId.isPresent()) {
			
			response = ResponseEntity.status(HttpStatus.FOUND).build();
			
		}else {
		     
		  this.repositoryCliente.save(dadosCliente);
		    
		  response = ResponseEntity.status(HttpStatus.CREATED).build();
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
