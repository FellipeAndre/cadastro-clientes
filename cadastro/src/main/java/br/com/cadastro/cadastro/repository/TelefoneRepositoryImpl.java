package br.com.cadastro.cadastro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cadastro.cadastro.entity.Telefone;

@Repository
public interface TelefoneRepositoryImpl extends JpaRepository<Telefone, Integer>{

	@Query(value = "select t.id_telefone, t.numero, t.tipo, t.id_cliente " + 
			"from telefone t " + 
			"where t.id_cliente = :id_cliente", nativeQuery = true)
	public List<Telefone> buscarTelefoneClientePorId(@Param("id_cliente") Integer id);
	
}
