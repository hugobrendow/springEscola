INSERT INTO Curso VALUES('4d933b83-b79a-492e-93ba-ef1b22eeb6f6', 'Java 1', 'Java basico 1');
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



INSERT INTO DISCIPLINA(ID, NOME, CARGA_HORARIA) VALUES('0a063e4e-f200-46ee-8cef-d91b624595eb', 'POO 1', 20);
INSERT INTO DISCIPLINA(ID, NOME, CARGA_HORARIA) VALUES('f995f890-c412-4159-bd7c-5bb8c1995d2a', 'POO 2', 20);
INSERT INTO DISCIPLINA(ID, NOME, CARGA_HORARIA) VALUES(random_uuid(), 'Logica 1', 30);
INSERT INTO DISCIPLINA(ID, NOME, CARGA_HORARIA) VALUES(random_uuid(), 'Logica 2', 40);
INSERT INTO DISCIPLINA(ID, NOME, CARGA_HORARIA) VALUES(random_uuid(), 'Logica 3', 60);
