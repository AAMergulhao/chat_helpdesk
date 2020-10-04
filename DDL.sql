create schema chatbot_helpdesk;

use chatbot_helpdesk;

create user 'user'@'localhost' identified by 'pass123';

grant select, insert, delete, update on chatbot_helpdesk.* to user@'localhost';

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
  usr_id bigint unsigned not null,
  not_titulo varchar(20) not null,
  not_conteudo varchar(255) not null,
  not_send_by bigint not null,
  primary key (not_id),
  KEY `usr_id` (`usr_id`),
  CONSTRAINT `usr_id_fk` FOREIGN KEY (`usr_id`) REFERENCES `usr_usuario` (`usr_id`)
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

insert into usr_usuario (usr_nome, usr_senha, usr_avatar) values('Fabiola', '12345', 'base64 image');
insert into usr_usuario (usr_nome, usr_senha, usr_avatar) values('Arthur', '12342345', 'base64 image');
insert into aut_autorizacao (aut_nome)
    values('ROLE_ADMIN');
insert into uau_usuario_autorizacao values (1, 1);
insert into usr_notificacoes  values (1, 1, 'Teste 2', 'Teste teste teste tet',2);
INSERT INTO `conversas` (`chat_id`, `origem`, `conteudo`, `status`, `data`, `inicio`, `final`, `participante1_id`, `participante2_id`) VALUES
(1, 'cliente', 'string json', 1, '2020-09-20', '21:36:02', '21:36:02', 1, 2),
(3, 'cliente', 'string json', 0, '2020-09-20', '21:52:11', '21:52:11', 1, 2),
(4, 'cliente', 'string json', 0, '2020-09-20', '21:52:11', '21:52:11', 1, 2),
(5, 'painel', 'string json', 0, '2020-09-20', '21:52:11', '21:52:11', 1, 2);