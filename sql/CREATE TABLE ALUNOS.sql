CREATE TABLE Alunos (
	id int,
    nome varchar(40) not null unique,
    idade smallint not null,
    cpf numeric(11) not null unique,
    email varchar(320) not null unique,
    
    primary key(cpf)
    
)