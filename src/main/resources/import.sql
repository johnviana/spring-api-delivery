insert into cozinha (nome) values ('Tailandesa');
insert into cozinha (nome) values ('Indiana');
insert into cozinha (nome) values ('Argentina');
insert into cozinha (nome) values ('Brasileira');


insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Ceará');

insert into cidade (nome, estado_id) values ('Uberlândia', 1);
insert into cidade (nome, estado_id) values ('Belo Horizonte', 1);
insert into cidade (nome, estado_id) values ('São Paulo', 2);
insert into cidade (nome, estado_id) values ('Campinas', 2);
insert into cidade (nome, estado_id) values ('Fortaleza', 3);


insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, end_cep, end_logradouro, end_numero, end_bairro, end_cidade_id) values ('Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp,'38400-999', 'Rua João Pinheiro', '1000', 'Centro', 1);
insert into restaurante (nome, taxa_frete, data_cadastro, data_atualizacao, cozinha_id) values ('Thai Gourmet', 10, utc_timestamp, utc_timestamp, 1);
insert into restaurante (nome, taxa_frete, data_cadastro, data_atualizacao, cozinha_id) values ('Thai Delivery', 9.50, utc_timestamp, utc_timestamp, 1);
insert into restaurante (nome, taxa_frete, data_cadastro, data_atualizacao, cozinha_id) values ('Tuk Tuk Comida Indiana', 15, utc_timestamp, utc_timestamp, 2);

insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Dinheiro');

insert into permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1); 