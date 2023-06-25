package br.com.cadastro.cadastro.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.cadastro.cadastro.entity.Endereco;
import br.com.cadastro.cadastro.entity.Telefone;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table
@Data
@AllArgsConstructor
public class CadastroDoCliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true)
	private Integer idCadastro;
	
	@Column(name = "data_inclusao" )
	private Date data_inclusao;
	
	@Column
	private String status;
	
	@Column
	@OneToOne
	private Integer id_Cliente;
	
	@Column
	private String nome;
	
	@Column
	private String sexo;
	
	@Column
	private String email;
	
	@Column
	private String cpf;
	
	@Column
	private Date data_nasc;
	
	@Column
	@OneToOne
	private Endereco id_endereco;
	
	@Column
	private String rua;
	
	@Column
	private String bairro;
	
	@Column
	private String cidade;
	
	@Column
	private String estado;
	
	@Column
	private String Pais;
	
	@Column
	@OneToMany
	private Telefone id_telefone;
	
	@Column
	private String tipo;
	
	@Column
	private String numero;
	

}
