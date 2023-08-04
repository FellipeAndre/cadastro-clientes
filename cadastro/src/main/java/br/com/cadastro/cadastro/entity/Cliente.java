package br.com.cadastro.cadastro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Setter
@Getter
public class Cliente {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCliente;

    @Column(name = "nome", length = 100)
    private String nome;
    
    @Column(name = "sexo", length = 1)
    private String sexo;
    
    @Column(name = "email", length = 50)
    private String email;
    
    @Column(name = "cpf", length = 11)
    private String cpf;

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", sexo=" + sexo + ", email=" + email + ", cpf="
				+ cpf + "]";
	}
    
    
}
