package br.com.cadastro.cadastro.dto;

import java.util.List;

import br.com.cadastro.cadastro.entity.Cliente;
import br.com.cadastro.cadastro.entity.Endereco;
import br.com.cadastro.cadastro.entity.Telefone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DadosCliente {
	
	private Cliente cliente;
	
	private List<Telefone> telefone;
	
	private Endereco endereco;

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Telefone> getTelefone() {
		return telefone;
	}

	public void setTelefone(List<Telefone> telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	

}
