package br.com.cadastro.cadastro.dto;

import br.com.cadastro.cadastro.entity.Endereco;
import br.com.cadastro.cadastro.entity.Telefone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DadosCliente {
	
	private Integer id_Cliente;
	
	private Endereco idEndereco;
	
	private Telefone idTelefone;
	
	
}
