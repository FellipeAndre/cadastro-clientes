package br.com.cadastro.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cadastro.cadastro.entity.Endereco;

public interface EnderecoRepositoryImpl extends JpaRepository<Endereco, Integer>{

	@Query(value = "select e.rua, e.bairro, e.cidade, e.estado" + 
			"from endereco e " + 
			"where e.id_cliente = :id;", nativeQuery = true)
	public Endereco buscarEnderecoPorIdCliente(@Param("id") Integer id);
}
