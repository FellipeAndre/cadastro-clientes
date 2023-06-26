package br.com.cadastro.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cadastro.cadastro.entity.CadastroDoCliente;

@Repository
public interface CadastroClienteRepositoryImpl extends JpaRepository<CadastroDoCliente, Integer>{

}
