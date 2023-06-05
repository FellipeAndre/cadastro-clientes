package br.com.cadastro.cadastro.service;

import br.com.cadastro.cadastro.dto.DadosCliente;
import br.com.cadastro.cadastro.entity.Cliente;

public interface ClienteService {
	
	public Cliente findByIdCliente(Integer id);

   public DadosCliente buscarDadosDoClientePorId(Integer id);
   
   public DadosCliente excluirDadosDoCliente(Integer id);
}

