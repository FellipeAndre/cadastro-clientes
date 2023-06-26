package br.com.cadastro.cadastro.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.cadastro.cadastro.entity.Endereco;
import br.com.cadastro.cadastro.entity.Telefone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastroDoCliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer idCadastro;
	
	@Column(name = "data_inclusao" )
	private Date data_inclusao;
	
	@Column(name = "status")
	private String status;
	
	@JoinColumn
	@JsonIgnore
	@OneToOne
	private Cliente id_Cliente;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "cof")
	@CPF
	private String cpf;
	
	@Column(name = "data_nasc")
	private Date data_nasc;
	
	@JoinColumn
	@JsonIgnore
	@ManyToOne
	private Endereco id_endereco;
	
	@Column(name = "rua")
	private String rua;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "pais")
	private String pais;
	
	@JoinColumn
	@JsonIgnore
	@ManyToOne
	private Telefone id_telefone;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "numero")
	private String numero;
	

}
