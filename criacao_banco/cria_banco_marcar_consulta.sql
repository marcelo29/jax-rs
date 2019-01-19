DROP DATABASE marcar_consulta;

CREATE DATABASE marcar_consulta;

USE marcar_consulta;

CREATE TABLE TB_MEDICO (
id INTEGER PRIMARY KEY, 
nome TEXT,
crm INTEGER,
especialidade INTEGER);

CREATE TABLE TB_USUARIO (
id INTEGER PRIMARY KEY,
login TEXT,
senha TEXT,
perfil TEXT,
email TEXT);

CREATE TABLE TB_LOCAL_ATENDIMENTO(
id INTEGER PRIMARY KEY,
nome TEXT,
endereco TEXT);

CREATE TABLE TB_ESPECIALIDADE(
id INTEGER PRIMARY KEY,
nome TEXT);

CREATE TABLE TB_CONSULTA_MARCADA(
id INTEGER PRIMARY KEY auto_increment,
id_agenda_medico INTEGER,
usuario INTEGER,
data_marcacao_consulta TEXT,
situacao TEXT,
data_cancelamento TEXT);

CREATE TABLE TB_AGENDA_MEDICO (
id INTEGER PRIMARY KEY,
medico INTEGER,
data DATE,
hora TIME,
local_atendimento INTEGER,
situacao TEXT);

INSERT INTO TB_MEDICO VALUES(1,'Jose Carlos', 12345, 1);
INSERT INTO TB_MEDICO VALUES(2,'Maria Agripina', 54321, 2);
INSERT INTO TB_MEDICO VALUES(3,'Gustavo Mendes', 12345, 3);
INSERT INTO TB_MEDICO VALUES(4,'Antonio Francisco', 12345, 1);

INSERT INTO TB_USUARIO VALUES(1,'admin','admin', 'A', 'marcelo_.aguiar@hotmail.com.br');
INSERT INTO TB_USUARIO VALUES(2,'user','user', 'U', 'masasp29@gmail.com');

INSERT INTO TB_LOCAL_ATENDIMENTO VALUES(1, 'Hospital Sao Carlos', 'Avenida Pontes Vieira 2531 Fortaleza');
INSERT INTO TB_LOCAL_ATENDIMENTO VALUES(2, 'Hospital de Messejana', 'Avenida Frei Cirilo 3480 Fortaleza');
INSERT INTO TB_LOCAL_ATENDIMENTO VALUES(3, 'Hospital Sao Mateus', 'Avenida Santos Dumont 5633 Fortaleza');

INSERT INTO TB_ESPECIALIDADE VALUES(1, 'Cirurgiao');
INSERT INTO TB_ESPECIALIDADE VALUES(2, 'Pediatra');
INSERT INTO TB_ESPECIALIDADE VALUES(3, 'Clinica Geral');

INSERT INTO TB_AGENDA_MEDICO VALUES(1, 1, '2015-10-15', '08:00:00', 1, 'D');
INSERT INTO TB_AGENDA_MEDICO VALUES(2, 2, '2015-10-05', '13:00:00', 1, 'D');
INSERT INTO TB_AGENDA_MEDICO VALUES(3, 3, '2015-10-05', '08:00:00', 1, 'D');
INSERT INTO TB_AGENDA_MEDICO VALUES(4, 1, '2015-10-10', '13:00:00', 2, 'D');
INSERT INTO TB_AGENDA_MEDICO VALUES(5, 2, '2015-10-05', '08:00:00', 2, 'D');
INSERT INTO TB_AGENDA_MEDICO VALUES(6, 3, '2015-10-05', '13:00:00', 2, 'D');
INSERT INTO TB_AGENDA_MEDICO VALUES(7, 1, '2015-10-07', '10:00:00', 3, 'D');
INSERT INTO TB_AGENDA_MEDICO VALUES(8, 2, '2015-10-06', '10:00:00', 3, 'D');
INSERT INTO TB_AGENDA_MEDICO VALUES(9, 3, '2015-10-05', '10:00:00', 3, 'D');

SELECT * FROM TB_CONSULTA_MARCADA;
SELECT * FROM TB_AGENDA_MEDICO;