package br.com.cadastro.cadastro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.cadastro.cadastro.entity.Cliente;

@Repository
public interface ClienteRepositoryImpl extends JpaRepository<Cliente, Integer> {

	 
}
