package br.com.cadastro.cadastro.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CadastroDoCliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer idCadastro;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "data_inclusao", nullable = false)
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date data_inclusao;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "sexo", nullable = false)
	private String sexo;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "cpf", unique = true)
	@CPF
	private String cpf;
	
	@Column(name = "data_nasc", nullable = false)
	@JsonFormat(pattern="dd-MM-yyyy")
	private String data_nasc;
	
	@Column(name = "rua", nullable = false)
	private String rua;
	
	@Column(name = "bairro",nullable = false)
	private String bairro;
	
	@Column(name = "cidade", nullable = false)
	private String cidade;
	
	@Column(name = "estado", nullable = false, length = 2)
	private String estado;
	
	@Column(name = "pais", nullable = false)
	private String pais;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "tipo")
	private String tipo;
	

}
