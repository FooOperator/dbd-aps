CREATE TABLE Alunos (
    id int,
    nome varchar(40) not null unique,
    idade smallint not null,
    cpf numeric(11) not null unique,
    email varchar(320) not null unique,
    curso varchar(40) not null,
    genero varchar(10),
	
    primary key(cpf)
    
)
