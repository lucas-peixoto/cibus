alter table restaurante
    add column slug varchar(50) not null,
    add column taxa_de_entrega double default 0,
    add column tempo_minimo_entrega int default 0,
    add column tempo_maximo_entrega int default 0;
