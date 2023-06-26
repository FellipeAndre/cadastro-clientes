USE CURSO;

CREATE TABLE CLIENTE(
	IDCLIENTE INT PRIMARY KEY AUTO_INCREMENT,
	NOME VARCHAR(30) NOT NULL,
	SEXO ENUM('M','F') NOT NULL,
	EMAIL VARCHAR(50) UNIQUE,
	CPF VARCHAR(15) UNIQUE
);

CREATE TABLE ENDERECO(
	IDENDERECO INT PRIMARY KEY AUTO_INCREMENT, 
	RUA VARCHAR(30) NOT NULL,
	BAIRRO VARCHAR(30) NOT NULL,
	CIDADE VARCHAR(30) NOT NULL,
	ESTADO CHAR(2) NOT NULL,
	ID_CLIENTE INT UNIQUE,

	FOREIGN KEY(ID_CLIENTE)
	REFERENCES CLIENTE(IDCLIENTE)
);

CREATE TABLE TELEFONE(
	IDTELEFONE INT PRIMARY KEY AUTO_INCREMENT, 
	TIPO ENUM('RES','COM','CEL') NOT NULL,
	NUMERO VARCHAR(10) NOT NULL,
	ID_CLIENTE INT,

	FOREIGN KEY(ID_CLIENTE)
	REFERENCES CLIENTE(IDCLIENTE)
);

CREATE TABLE CADASTRO_CLIENTE_ENDERECO (

    IDCADASTRO INT PRIMARY KEY AUTO_INCREMENT,
    DATA_INCLUSAO DATE NOT NULL,
    STATUS VARCHAR(30) NOT NULL,
    FOREIGN KEY(ID_CLIENTE)
	REFERENCES CLIENTE(IDCLIENTE),
    NOME VARCHAR(100) NOT NULL,
    SEXO CHAR(1) NOT NULL,
    EMAIL VARCHAR(50) UNIQUE NOT NULL,
    CPF VARCHAR(15) UNIQUE NOT NULL,
    DATA_DE_NASCIMENTO DATE NOT NULL,
	FOREIGN KEY(ID_ENDERECO)
	REFERENCES ENDERECO(IDENDERECO),
    RUA VARCHAR(100) NOT NULL,
    BAIRRO VARCHAR(100) NOT NULL,
    CIDADE VARCHAR(50) NOT NULL,
    ESTADO CHAR(2)NOT NULL,
    PAIS VARCHAR(100) NOT NULL,
	FOREIGN KEY(ID_TELEFONE)
	REFERENCES TELEFONE(IDTELEFONE),
    TIPO ENUM('RES','COM','CEL') NOT NULL,
	NUMERO VARCHAR(10) NOT NULL
	
);
