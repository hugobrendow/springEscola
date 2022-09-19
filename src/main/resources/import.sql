INSERT INTO Curso VALUES('1648719c-e211-4be7-b6af-a7ce2041aa3f', 'Java 1', 'Java basico 1');
INSERT INTO Curso VALUES(random_uuid(), 'Java 2', 'Java basico 2');
INSERT INTO Curso VALUES(random_uuid(), 'PHP 1', 'PHP basico 1');
INSERT INTO Curso VALUES(random_uuid(), 'PHP 2', 'PHP basico 2');
INSERT INTO Curso VALUES(random_uuid(), 'Java JSP 1', 'JSP basico 1');
INSERT INTO Curso VALUES(random_uuid(), 'Java JSP 2', 'JSP basico 2');
INSERT INTO Curso VALUES(random_uuid(), 'Spring Boot 1', 'Spring Boot basico 1');
INSERT INTO Curso VALUES(random_uuid(), 'Spring Boot 2', 'Spring Boot basico 2');
INSERT INTO Curso VALUES(random_uuid(), 'SQL 1', 'SQL Básico 1');
INSERT INTO Curso VALUES(random_uuid(), 'COBOL 1', 'COBOL Básico 1');

INSERT INTO Aluno VALUES ('3211f571-fbd7-4700-a804-b4910def0660', '00011122233', '1995-05-05', 'joao.p@g.com', 'João Paulo', '22332233', null);
INSERT INTO Aluno VALUES ('41b4fceb-2a64-4d8a-8c18-ac6f7caeedf9', '11122233344', '1998-08-08', 'maria.c@g.com', 'Maria Clara', '24242424', null);

INSERT INTO Turma VALUES ('49cafb13-9e74-45d6-976f-f50d5da2c6d6', 'T414', '2096-01-01', '1996-01-01', '5', 'Tarde', '1648719c-e211-4be7-b6af-a7ce2041aa3f');

INSERT INTO Matricula VALUES ('0be5868c-4b59-474e-a185-a1290b19b23e', '2096-01-01', '123', true, '3211f571-fbd7-4700-a804-b4910def0660', '49cafb13-9e74-45d6-976f-f50d5da2c6d6');
INSERT INTO Matricula VALUES ('ca92f2a3-4d1b-40cb-a913-641bb0cb81f1', '2022-12-12', '321', true, '41b4fceb-2a64-4d8a-8c18-ac6f7caeedf9', '49cafb13-9e74-45d6-976f-f50d5da2c6d6');

INSERT INTO Disciplina VALUES ('c8b5ed87-5cb1-4904-9ac6-3224478893d3', 15, 'Collections');
INSERT INTO Disciplina VALUES ('4f518854-0b59-4260-bed2-eddecdb2fa79', 20, 'JPA com Hibernate');

INSERT INTO Curso_Disciplina VALUES ('d9632725-f147-43f8-bc01-679fbc780297', '1648719c-e211-4be7-b6af-a7ce2041aa3f', 'c8b5ed87-5cb1-4904-9ac6-3224478893d3');
INSERT INTO Curso_Disciplina VALUES ('68581d68-fa46-47f6-aa85-38f7844ca0ae', '1648719c-e211-4be7-b6af-a7ce2041aa3f', '4f518854-0b59-4260-bed2-eddecdb2fa79');

INSERT INTO Nota VALUES ('49ca45b4-9666-482f-ba50-f50782de6888', 7.5, 'd9632725-f147-43f8-bc01-679fbc780297', '0be5868c-4b59-474e-a185-a1290b19b23e')
INSERT INTO Nota VALUES (random_uuid(), 8.3, '68581d68-fa46-47f6-aa85-38f7844ca0ae', '0be5868c-4b59-474e-a185-a1290b19b23e')
INSERT INTO Nota VALUES (random_uuid(), 8, 'd9632725-f147-43f8-bc01-679fbc780297', 'ca92f2a3-4d1b-40cb-a913-641bb0cb81f1')
