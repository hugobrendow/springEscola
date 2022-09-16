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
INSERT INTO Curso VALUES('1648719c-e211-4be7-b6af-a7ce2041aa3f', 'TESTE', 'TESTE BASICO');

INSERT INTO Aluno VALUES ('3211f571-fbd7-4700-a804-b4910def0660', '414', '1996-01-01', 'a@g.com', 'aluno', '14555', null);

INSERT INTO Turma VALUES ('49cafb13-9e74-45d6-976f-f50d5da2c6d6', 'T414', '2096-01-01', '1996-01-01', '5', 'Tarde', '1648719c-e211-4be7-b6af-a7ce2041aa3f');

INSERT INTO Matricula VALUES ('0be5868c-4b59-474e-a185-a1290b19b23e', '2096-01-01', '123', true, '3211f571-fbd7-4700-a804-b4910def0660', '49cafb13-9e74-45d6-976f-f50d5da2c6d6');
INSERT INTO Disciplina VALUES ('c8b5ed87-5cb1-4904-9ac6-3224478893d3', '4.5', 'JAVA');
INSERT INTO Curso_Disciplina VALUES ('d9632725-f147-43f8-bc01-679fbc780297', '1648719c-e211-4be7-b6af-a7ce2041aa3f', 'c8b5ed87-5cb1-4904-9ac6-3224478893d3');

