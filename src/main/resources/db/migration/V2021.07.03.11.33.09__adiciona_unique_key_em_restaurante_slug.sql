update restaurante set slug='lahmajun-delicias-turcas' where id=1;
update restaurante set slug='almanara' where id=2;
update restaurante set slug='saj' where id=3;
update restaurante set slug='salvo' where id=4;
update restaurante set slug='acaraje-da-rosy' where id=5;
update restaurante set slug='china-in-box-aclimacao' where id=6;
update restaurante set slug='asia-express' where id=7;
update restaurante set slug='chinakim' where id=8;
update restaurante set slug='jardim-de-napoli' where id=9;
update restaurante set slug='osteria-generale-bela-vista' where id=10;
update restaurante set slug='lellis-trattoria' where id=11;
update restaurante set slug='famiglia-mancini' where id=12;

alter table restaurante add unique key uk_restaurante_slug (slug);