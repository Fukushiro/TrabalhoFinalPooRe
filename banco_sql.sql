create database trabalho_final_poo;

use trabalho_final_poo;


select * from usuarios;
create table usuarios(
	id int not null auto_increment,
    nome varchar(100) not null unique,
    senha varchar(100) not null,
    tipo int not null default 0,
    saldo double not null default 0,
    criptografado int not null default 1,
    primary key(id)
);



create table empresas(
	id int not null auto_increment,
    nome varchar(100) not null unique,
    
    primary key(id)
);
create table generos(
	id int not null auto_increment,
    nome varchar(100) not null unique,
    primary key(id)
);
create table consoles(
	id int not null auto_increment,
    nome varchar(100) not null unique,
    preco double not null default 0,
    quantidade int not null default 0,
    empresa int,
    
    foreign key (empresa) references empresas(id),
    primary key(id)
);

create table jogos(
	id int not null auto_increment,
    nome varchar(100) not null unique,
    preco double not null default 0,
    quantidade int not null default 0,
    genero int not null,
    
    foreign key (genero) references generos(id),
    primary key(id)
);


create table compras(
	id int not null auto_increment,
    usuario int not null,
    tipo varchar(100) not null,
    produto varchar(100) not null,
    valor double not null default 0,
    
    foreign key (usuario) references usuarios(id),
    primary key(id)

);
