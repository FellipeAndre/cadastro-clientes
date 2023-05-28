package br.com.cadastro.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cadastro.cadastro.entity.Telefone;

@Repository
public interface TelefoneRepositoryImpl extends JpaRepository<Telefone, Integer>{

	@Query(value = "select t.numero, t.tipo" + 
			"from telefone t" + 
			"where t.id_cliente = :id_cliente;", nativeQuery = true)
	public Telefone buscarTelefoneClientePorId(@Param("id_cliente") Integer id);
	
}
