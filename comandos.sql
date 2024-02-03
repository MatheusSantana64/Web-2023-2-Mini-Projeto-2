CREATE TABLE ponto (
	id int AUTO_INCREMENT PRIMARY KEY,
	nome varchar(100) NOT NULL
);

CREATE TABLE onibus (
	id int AUTO_INCREMENT PRIMARY KEY,
	nome varchar(50) NOT NULL,
	numero int NOT NULL,
	partida int NOT NULL,
	destino int NOT NULL,
	manutencao boolean DEFAULT false,
	lotacao int DEFAULT NULL,
	FOREIGN KEY (partida) REFERENCES ponto(id),
	FOREIGN KEY (destino) REFERENCES ponto(id),
	UNIQUE (nome, numero)
);

CREATE TABLE parada (
	onibus int NOT NULL,
	ponto int NOT NULL,
	horario time NOT NULL,
	atrasado boolean DEFAULT false,
    PRIMARY KEY (onibus, ponto, horario),
    FOREIGN KEY (onibus) REFERENCES onibus(id),
    FOREIGN KEY (ponto) REFERENCES ponto(id)
);

INSERT INTO ponto (nome) VALUES
	('Terminal Vitoria Regia'), -- 1
	('R. Antonio Silva Saladino'), -- 2
	('Av. Itavuvu'), -- 3
	('R. Antonieta da Silva Gomes'), -- 4
	('Av. Chico Xavier'), -- 5
	('Terminal Santo Antonio'), -- 6
	('Av. Luiz Ferraz de Sampaio Junior'), -- 7
	('R. Aracoiaba'), -- 8
	('R. Dom Antonio Alvarenga'), -- 9
	('Av. Alcideivez Ap. Miranda de Oliveira'), -- 10
	('AV. Luiz Ferraz de Sampaio Junior'), -- 11
	('R. Araçoiaba'), -- 12
	('R. Antonio Alarenga'), -- 13
	('R. Aparecida'), -- 14
	('R. Vidal de Araujo'); -- 15

INSERT INTO onibus (nome, numero, partida, destino, manutencao, lotacao) VALUES
	('Aldeia dos laranjais', 39, 1, 1, false, 40), -- 1
	('Itavuvu', 25, 1, 3, false, 40), -- 2
	('Altos do Ipanema', 181, 6, 10, true, 40), -- 3
	('Jd. do Paco', 791, 6, 15, false, 40); -- 4

INSERT INTO parada VALUES
	(1, 1, '10:00:00', false),	-- Aldeia dos laranjais  Terminal Vitoria Regia
	(1, 2, '10:05:00', false),	-- Aldeia dos laranjais  R. Antonio Silva Saladino
	(1, 3, '10:15:00', false),	-- Aldeia dos laranjais  Av. Itavuvu
	(1, 4, '10:20:00', false),	-- Aldeia dos laranjais  R. Antonieta da Silva Gomes
	(1, 1, '10:30:00', false),	-- Aldeia dos laranjais  Terminal Vitoria Regia
	(2, 1, '09:45:00', false),	-- Itavuvu  Terminal Vitoria Regia
	(2, 2, '09:55:00', false),	-- Itavuvu  R. Antonio Silva Saladino
	(2, 3, '10:9:00', false),	-- Itavuvu  Av. Itavuvu
	(2, 5, '10:25:00', true),	-- Itavuvu  Av. Chico Xavier
	(2, 3, '10:30:00', true),	-- Itavuvu  Av. Itavuvu
	(3, 6, '08:55:00', false),  -- Altos do Ipanema  Terminal Santo Antonio
	(3, 7, '09:20:00', true),   -- Altos do Ipanema  Av. Luiz Ferraz de Sampaio Junior
	(3, 8, '09:24:00', false),	-- Altos do Ipanema  R. Aracoiaba
	(3, 9, '09:28:00', false),	-- Altos do Ipanema  R. Dom Antonio Alvarenga
	(3, 10, '09:40:00', false),	-- Altos do Ipanema  Av. Alcideivez Ap. Miranda de Oliveira
	(4, 6, '15:00:00', true),	-- Jd. do Paco  Terminal Santo Antonio
	(4, 11, '15:05:00', false),	-- Jd. do Paco  AV. Luiz Ferraz de Sampaio Junior
	(4, 12, '15:15:00', false),	-- Jd. do Paco  R. Araçoiaba
	(4, 13, '15:30:00', false),	-- Jd. do Paco  R. Antonio Alarenga
	(4, 14, '15:40:00', false),	-- Jd. do Paco  R. Aparecida
	(4, 15, '15:46:00', false);	-- Jd. do Paco  R. Vidal de Araujo
