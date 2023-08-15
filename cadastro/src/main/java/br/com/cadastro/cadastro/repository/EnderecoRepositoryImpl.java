package br.com.cadastro.cadastro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.cadastro.cadastro.entity.Endereco;

public interface EnderecoRepositoryImpl extends JpaRepository<Endereco, Integer> {

	@Query(value = "select * from endereco e " 
	+ "inner join cliente c " 
			+ "on e.id_cliente = c.id_cliente "
			+ "and e.id_cliente = :id_cliente", nativeQuery = true)
	public Optional<Endereco> findByIdCliente(@Param("id_cliente") Integer id);

}
