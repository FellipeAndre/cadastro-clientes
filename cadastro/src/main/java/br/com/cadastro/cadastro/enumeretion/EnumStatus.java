package br.com.cadastro.cadastro.enumeretion;

public enum EnumStatus {
      
	
	
	ATIVO("ATIVO"),
	INATIVO("INATIVO"),
	ALTERADO("A"),
	INCLUSO("I");
	
	private String descricao;
	
	private EnumStatus(String descricao) {
		this.setDescricao(descricao);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
