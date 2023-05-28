package br.com.cadastro.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cadastro.cadastro.entity.Cliente;

@Repository
public interface ClienteRepositoryImpl extends JpaRepository<Cliente, Integer> {

	@Query(value = "SELECT c.id_cliente, c.nome, c.sexo, c.email, c.cpf, " +
	        "e.rua, e.bairro, e.cidade, e.estado, " +
	        "t.tipo, t.numero " +
	        "FROM cliente c " +
	        "INNER JOIN endereco e ON c.id_cliente = e.id_cliente " +
	        "INNER JOIN telefone t ON c.id_cliente = t.id_cliente " +
	        "WHERE c.id_cliente = :id_cliente", nativeQuery = true)
	public Cliente bringCustomerData(@Param("id_cliente") Integer id);
}
