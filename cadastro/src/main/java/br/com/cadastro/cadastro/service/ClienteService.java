package br.com.cadastro.cadastro.service;

import br.com.cadastro.cadastro.dto.DadosClienteDTO;
import br.com.cadastro.cadastro.entity.Cliente;

public interface ClienteService {
	
	public Cliente findByIdCliente(Integer id);

   public DadosClienteDTO buscarDadosPorId(DadosClienteDTO id);
}
