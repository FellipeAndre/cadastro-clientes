package br.com.cadastro.cadastro.service;

import org.springframework.http.ResponseEntity;

import br.com.cadastro.cadastro.entity.CadastroDoCliente;
import br.com.cadastro.cadastro.entity.Telefone;

public interface TelefoneService {

	 ResponseEntity<Telefone> getSalvarTelefone(CadastroDoCliente cadastro);
}
