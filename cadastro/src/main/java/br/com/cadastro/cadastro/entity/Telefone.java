package br.com.cadastro.cadastro.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "telefone")
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@JsonIgnore
	private Integer idTelefone;
	
	@Column(name =  "tipo")
	private String tipo;
	
	@Column(name = "numero")
	private String numero;
	
	@ManyToOne
	@JoinColumn(name ="idCliente")
	@JsonIgnore
	private Cliente id_cliente;

	@Override
	public String toString() {
		return "Telefone [idTelefone=" + idTelefone + ", tipo=" + tipo + ", numero=" + numero + "]";
	}
	
	

}
