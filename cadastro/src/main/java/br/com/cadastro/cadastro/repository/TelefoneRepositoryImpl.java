package br.com.cadastro.cadastro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cadastro.cadastro.entity.Telefone;

@Repository
public interface TelefoneRepositoryImpl extends JpaRepository<Telefone, Integer> {

	@Query(value = "select * from Telefone t " 
	+ "inner join cliente c " 
			+ "on t.id_cliente = c.id_cliente "
			+ "and t.id_cliente = :id_cliente", nativeQuery = true)
	public Optional<Telefone> findByIdCliente(@Param("id_cliente") Integer id);

}
