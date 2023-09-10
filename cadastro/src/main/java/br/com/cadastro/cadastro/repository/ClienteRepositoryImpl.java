package br.com.cadastro.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastro.cadastro.entity.Cliente;

@Repository
public interface ClienteRepositoryImpl extends JpaRepository<Cliente, Integer> {

	 
}
