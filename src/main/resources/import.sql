insert into tb_modelo(nome) values('Etios XE 1.3 2017/2018');
insert into tb_modelo(nome) values('Ford Car 1.5');
insert into tb_carro(nome, modelo_id) values('Etios', (select modelo.id from tb_modelo modelo where modelo.nome = 'Etios XE 1.3 2017/2018' limit 1));
insert into tb_carro(nome, modelo_id) values('Ford Car', (select modelo.id from tb_modelo modelo where modelo.nome = 'Ford Car 1.5' limit 1));