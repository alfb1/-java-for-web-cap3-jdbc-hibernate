# java-for-web-cap3-jdbc-hibernate
third chapter of the book "Programa��o Java para web" Luckow and Melo, 2nd edtion

Fazendo a conexao com o mysql utilizando o mysql-connector-java-8.0.18.jar Plataform Independent doo site http://dev.mysql.com/downloads/connector/j/

Banco de dados agenda :
usando o MySql Command Line Client 

CREATE DATABASE 'agenda';

USE 'agenda';

01-07-2020
Create table contato :

create table contato(
  codigo   int not null auto_increment,
  name     varchar(50) null,
  telefone varchar(50) null,
  email    varchar(50) null,
  dt_cad   date null,
  obs      text,
  primary key(codigo)
);


Fazendo a conexao com a classe ConectaMySQL.
Cria��o da classe contato.
Cria��o das opera��es CRUD para com a classe ContatoCrud.
