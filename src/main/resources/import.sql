INSERT INTO Curso VALUES(random_uuid(), 'Java 1', 'Java basico 1');
INSERT INTO Curso VALUES(random_uuid(), 'Java 2', 'Java basico 2');
INSERT INTO Curso VALUES(random_uuid(), 'PHP 1', 'PHP basico 1');
INSERT INTO Curso VALUES(random_uuid(), 'PHP 2', 'PHP basico 2');
INSERT INTO Curso VALUES(random_uuid(), 'Java JSP 1', 'JSP basico 1');
INSERT INTO Curso VALUES(random_uuid(), 'Java JSP 2', 'JSP basico 2');
INSERT INTO Curso VALUES(random_uuid(), 'Spring Boot 1', 'Spring Boot basico 1');
INSERT INTO Curso VALUES(random_uuid(), 'Spring Boot 2', 'Spring Boot basico 2');
INSERT INTO Curso VALUES(random_uuid(), 'SQL 1', 'SQL Básico 1');
INSERT INTO Curso VALUES(random_uuid(), 'COBOL 1', 'COBOL Básico 1');
insert into Perfil values (random_uuid(), 'ROLE_PROFESSOR');
insert into Perfil values (random_uuid(), 'ROLE_COORDENADOR');
insert into Usuario (id, account_non_expired, account_non_locked, credentials_non_expired, enabled, password, username) values (random_uuid(), true, true, true, true, '$2a$10$fwQJT.ABmklx9fNU3nunuO5Jkc7aloQK0UZQwVS0stt.Zg6y3hGxi', 'cristiano');
insert into Usuario (id, account_non_expired, account_non_locked, credentials_non_expired, enabled, password, username) values (random_uuid(), true, true, true, true, '$2a$10$fwQJT.ABmklx9fNU3nunuO5Jkc7aloQK0UZQwVS0stt.Zg6y3hGxi', 'jack');
insert into Usuario_Perfil (id, usuario_id, perfil_id) values (random_uuid(), SELECT id FROM Usuario WHERE USERNAME = 'jack', SELECT ID FROM Perfil WHERE permissao = 'ROLE_PROFESSOR');
insert into Usuario_Perfil (id, usuario_id, perfil_id) values (random_uuid(), SELECT id FROM Usuario WHERE USERNAME = 'cristiano', SELECT ID FROM Perfil WHERE permissao = 'ROLE_PROFESSOR');
insert into Usuario_Perfil (id, usuario_id, perfil_id) values (random_uuid(), SELECT id FROM Usuario WHERE USERNAME = 'cristiano', SELECT ID FROM Perfil WHERE permissao = 'ROLE_COORDENADOR');
INSERT INTO Curso VALUES('d014f344-382f-11ed-a261-0242ac120013', 'Spring Boot 1', 'Spring Boot basico 1');
INSERT INTO Curso VALUES('d014f344-382f-11ed-a261-0242ac120012', 'Spring Boot 2', 'Spring Boot basico 2');
INSERT INTO Curso VALUES('d014f344-382f-11ed-a261-0242ac120010', 'SQL 1', 'SQL Básico 1');
INSERT INTO Curso VALUES('d014f344-382f-11ed-a261-0242ac120002', 'COBOL 1', 'COBOL Básico 1');

INSERT INTO Turma VALUES('d014f344-382f-11ed-a261-0242ac120003','CCXP001',current_date(),current_date(),10,'MANHA','d014f344-382f-11ed-a261-0242ac120002');
INSERT INTO Turma VALUES('d014f344-382f-11ed-a261-0242ac120030','CCXP002',current_date(),current_date(),20,'TARDE','d014f344-382f-11ed-a261-0242ac120010');
INSERT INTO Turma VALUES('d014f344-382f-11ed-a261-0242ac120031','CCXP003',current_date(),current_date(),30,'NOITE','d014f344-382f-11ed-a261-0242ac120012');
INSERT INTO Turma VALUES('d014f344-382f-11ed-a261-0242ac120032','CCXP004',current_date(),current_date(),1,'EAD','d014f344-382f-11ed-a261-0242ac120013');

INSERT INTO Endereco VALUES('d014f344-382f-11ed-a261-0242ac120005','05856330','São Paulo','','SP','Rua Joaquim Pedroza',512);
INSERT INTO Endereco VALUES('d014f344-382f-11ed-a261-0242ac120006','02356330','Minas Gerais','','MG','Rua Cond Pedroza',100);
INSERT INTO Endereco VALUES('d014f344-382f-11ed-a261-0242ac120007','05156312','Pernanbuco','','PE','Rua Jardim Pedroza',205);
INSERT INTO Endereco VALUES('d014f344-382f-11ed-a261-0242ac120008','04812934','Alagoas','','AL','Rua São Pedroza',708);
INSERT INTO Endereco VALUES('d014f344-382f-11ed-a261-0242ac120043','0481214','Roraima','','RO','Rua São Pedroza',423);
INSERT INTO Endereco VALUES('d014f344-382f-11ed-a261-0242ac120044','04812974','Santa Catarina','','SC','Rua São Pedroza',842);

INSERT INTO Aluno VALUES('d014f344-382f-11ed-a261-0242ac120006','00000000111',current_date(),'rodrigo@gmail.com','Rodrigo','11987654321','d014f344-382f-11ed-a261-0242ac120005');
INSERT INTO Aluno VALUES('0dca7292-3919-11ed-a261-0242ac120002','00000000222',current_date(),'vitoria@gmail.com','Vitoria','11982354249','d014f344-382f-11ed-a261-0242ac120006');
INSERT INTO Aluno VALUES('0dca7292-3919-11ed-a261-0242ac120003','00000000333',current_date(),'davi@gmail.com','Davi','11987654e10','d014f344-382f-11ed-a261-0242ac120007');
INSERT INTO Aluno VALUES('0dca7292-3919-11ed-a261-0242ac120004','00000000444',current_date(),'ary@gmail.com','Ary','11987654241','d014f344-382f-11ed-a261-0242ac120008');
INSERT INTO Aluno VALUES('0dca7292-3919-11ed-a261-0242ac120050','00000000555',current_date(),'marlon@gmail.com','Marlon','11987654241','d014f344-382f-11ed-a261-0242ac120043');
INSERT INTO Aluno VALUES('0dca7292-3919-11ed-a261-0242ac120051','00000000666',current_date(),'paulo@gmail.com','Paulo','11987654241','d014f344-382f-11ed-a261-0242ac120044');

INSERT INTO Matricula VALUES (random_uuid(), current_date(), 1, 'ATIVADA', 'd014f344-382f-11ed-a261-0242ac120006', 'd014f344-382f-11ed-a261-0242ac120003');
INSERT INTO Matricula VALUES (random_uuid(), current_date(), 2, 'ATIVADA', '0dca7292-3919-11ed-a261-0242ac120002', 'd014f344-382f-11ed-a261-0242ac120030');
INSERT INTO Matricula VALUES (random_uuid(), current_date(), 3, 'ATIVADA', '0dca7292-3919-11ed-a261-0242ac120003', 'd014f344-382f-11ed-a261-0242ac120031');
INSERT INTO Matricula VALUES (random_uuid(), current_date(), 4, 'ATIVADA', '0dca7292-3919-11ed-a261-0242ac120004', 'd014f344-382f-11ed-a261-0242ac120032');
INSERT INTO Matricula VALUES (random_uuid(), current_date(), 5, 'ATIVADA', '0dca7292-3919-11ed-a261-0242ac120050', 'd014f344-382f-11ed-a261-0242ac120032');
INSERT INTO Matricula VALUES (random_uuid(), current_date(), 6, 'ATIVADA', '0dca7292-3919-11ed-a261-0242ac120051', 'd014f344-382f-11ed-a261-0242ac120032');
INSERT INTO Matricula VALUES (random_uuid(), current_date(), 7, 'ATIVADA', '0dca7292-3919-11ed-a261-0242ac120051', 'd014f344-382f-11ed-a261-0242ac120003');