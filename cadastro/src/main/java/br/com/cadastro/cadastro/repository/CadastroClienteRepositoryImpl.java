package br.com.cadastro.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroClienteRepositoryImpl extends JpaRepository<br.com.cadastro.cadastro.entity.CadastroDoCliente, Integer>{

}
