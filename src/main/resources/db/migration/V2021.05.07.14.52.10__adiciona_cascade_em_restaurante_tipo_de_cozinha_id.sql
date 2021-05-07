alter table restaurante
    drop constraint fk_tipo_de_cozinha_id;

alter table restaurante
    add constraint fk_tipo_de_cozinha_id
        foreign key (tipo_de_cozinha_id)
        references tipo_de_cozinha (id)
        on delete cascade
        on update cascade;