create table restaurante (
    id bigint auto_increment,
    nome varchar(50) not null,
    cep varchar(8) not null,
    endereco varchar(200) not null,
    cnpj varchar(14) not null,
    descricao text,
    tipo_de_cozinha_id bigint not null,

    primary key (id),
    unique key uk_restaurante_nome (nome),
    key fk_tipo_de_cozinha_id (tipo_de_cozinha_id),
    constraint fk_tipo_de_cozinha_id foreign key (tipo_de_cozinha_id) references tipo_de_cozinha (id)
);