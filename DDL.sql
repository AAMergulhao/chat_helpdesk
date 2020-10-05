create schema anotacao;

use anotacao;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on anotacao.* to user@'localhost';

create table usr_usuario (
  usr_id bigint unsigned not null auto_increment,
  usr_nome varchar(20) not null,
  usr_senha varchar(50) not null,
  usr_avatar varchar(255) not null,
  primary key (usr_id),
  unique key uni_usuario_nome (usr_nome)
);

create table aut_autorizacao (
  aut_id bigint unsigned not null auto_increment,
  aut_nome varchar(20) not null,
  primary key (aut_id),
  unique key uni_aut_nome (aut_nome)
);

create table uau_usuario_autorizacao (
  usr_id bigint unsigned not null,
  aut_id bigint unsigned not null,
  primary key (usr_id, aut_id),
  foreign key aut_usuario_fk (usr_id) references usr_usuario (usr_id) on delete restrict on update cascade,
  foreign key aut_autorizacao_fk (aut_id) references aut_autorizacao (aut_id) on delete restrict on update cascade
);

create table usr_notificacoes (
  not_id bigint unsigned not null auto_increment,
  user_id bigint not null,
  not_titulo varchar(20) not null,
  not_conteudo varchar(255) not null,
  not_send_by bigint not null,
  primary key (not_id)
);

create table conversas (
  chat_id bigint unsigned not null auto_increment,
  origem varchar(20) not null,
  conteudo varchar(255) not null,
  status bigint not null,
  data date NOT NULL,
  inicio time NOT NULL,
  final time Not Null,
  participante1_id bigint not null,
  participante2_id bigint not null,
  primary key (chat_id)
);

insert into usr_usuario (usr_nome, usr_senha)
    values('Fabiola', '12345');
insert into aut_autorizacao (aut_nome)
    values('ROLE_ADMIN');
insert into uau_usuario_autorizacao values (1, 1);