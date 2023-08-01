package br.com.cadastro.cadastro.service;

import org.springframework.http.ResponseEntity;

import br.com.cadastro.cadastro.entity.CadastroDoCliente;
import br.com.cadastro.cadastro.entity.Endereco;

public interface EnderecoService {

	ResponseEntity<Endereco> getSalvarEndereco(CadastroDoCliente cadastro);
}
