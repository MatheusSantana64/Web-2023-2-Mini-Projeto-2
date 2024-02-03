DROP TABLE parada;
DROP TABLE onibus;
DROP TABLE ponto;

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
	('R. Antonio Alarenga'), -- 11
	('R. Aparecida'), -- 12
	('R. Vidal de Araujo'), -- 13
	('R. Francisco Scarpa'), -- 14
	('Av. General Carneiro'), -- 15
	('Av. Armando Pannunzio'), -- 16
	('R. Horacio Cenci'), -- 17
	('Av. Adolpho Massaglia (Resid. Parque Sicilia)'), -- 18
	('Terminal Sao Paulo'), -- 19
	('R. Leopoldo Machado'), -- 20
	('Av. Juscelino K. de Oliveira'), -- 21
	('Av. Moreira Cesar'), -- 22
	('Rod. Joao Leme dos Santos'), -- 23
	('UFSCar'), -- 24
	('Av. Afonso Vergueiro'), -- 25
	('Av. Dr. Eugenio Salerno'), -- 26
	('Av. Dom Aguirre'), -- 27
	('R. do Terco'), -- 28
	('R. Joao Machado'), -- 29
	('Rod. Sen. Jose Ermirio de Moraes'); -- 30


INSERT INTO onibus (nome, numero, partida, destino, manutencao, lotacao) VALUES
	('Aldeia dos laranjais', 39, 1, 1, false, 40), -- 1
	('Itavuvu', 25, 1, 3, false, 40), -- 2
	('Altos do Ipanema', 181, 6, 10, true, 40), -- 3
	('Jd. do Paco', 791, 6, 15, false, 40), -- 4
	('Campolim', 651, 6, 19, false, 65), -- 5
	('Ufscar', 80, 20, 25, false, 70), -- 6
	('Rodoviaria / Pça. Nove de Julho', 101, 6, 20, true, 40), -- 7
	('Expresso Aparecidinha', 48, 6, 20, false, 70); -- 8


INSERT INTO parada VALUES
	(1, 1, '10:00:00', false),	-- Aldeia dos laranjais  Terminal Vitoria Regia
	(1, 2, '10:05:00', false),	-- Aldeia dos laranjais  R. Antonio Silva Saladino
	(1, 3, '10:15:00', false),	-- Aldeia dos laranjais  Av. Itavuvu
	(1, 4, '10:20:00', false),	-- Aldeia dos laranjais  R. Antonieta da Silva Gomes
	(1, 1, '10:30:00', false),	-- Aldeia dos laranjais  Terminal Vitoria Regia
	(2, 1, '09:45:00', false),	-- Itavuvu  Terminal Vitoria Regia
	(2, 2, '09:55:00', false),	-- Itavuvu  R. Antonio Silva Saladino
	(2, 3, '10:09:00', false),	-- Itavuvu  Av. Itavuvu
	(2, 5, '10:25:00', true),	-- Itavuvu  Av. Chico Xavier
	(2, 3, '10:30:00', true),	-- Itavuvu  Av. Itavuvu
	(3, 6, '08:55:00', false),  -- Altos do Ipanema  Terminal Santo Antonio
	(3, 7, '09:20:00', true),   -- Altos do Ipanema  Av. Luiz Ferraz de Sampaio Junior
	(3, 8, '09:24:00', false),	-- Altos do Ipanema  R. Aracoiaba
	(3, 9, '09:28:00', false),	-- Altos do Ipanema  R. Dom Antonio Alvarenga
	(3, 10, '09:40:00', false),	-- Altos do Ipanema  Av. Alcideivez Ap. Miranda de Oliveira
	(4, 6, '15:00:00', true),	-- Jd. do Paco  Terminal Santo Antonio
	(4, 7, '15:05:00', false),	-- Jd. do Paco  AV. Luiz Ferraz de Sampaio Junior
	(4, 8, '15:15:00', false),	-- Jd. do Paco  R. Araçoiaba
	(4, 11, '15:30:00', false),	-- Jd. do Paco  R. Antonio Alarenga
	(4, 12, '15:40:00', false),	-- Jd. do Paco  R. Aparecida
	(4, 13, '15:46:00', false),	-- Jd. do Paco  R. Vidal de Araujo
	(5, 7, '14:00:00', false),	-- Campolim  Av. Luiz Ferraz de Sampaio Junior
	(5, 14, '14:10:00', false),	-- Campolim  R. Francisco Scarpa
	(5, 15, '14:15:00', false),	-- Campolim  Av. General Carneiro
	(5, 16, '14:22:00', false),	-- Campolim  Av. Armando Pannunzio
	(5, 17, '14:22:00', false),	-- Campolim  R. Horacio Cenci
	(5, 18, '14:30:00', false),	-- Campolim  Av. Adolpho Massaglia (Resid. Parque Sicilia)
	(6, 19, '07:45:00', false),	-- Ufscar  Terminal Sao Paulo
	(6, 20, '07:50:00', false),	-- Ufscar  R. Leopoldo Machado
	(6, 21, '07:55:00', false),	-- Ufscar  Juscelino K. de Oliveira
	(6, 22, '08:00:00', false),	-- Ufscar  Av. Moreira Cesar
	(6, 15, '08:08:00', false),	-- Ufscar  Av. General Carneiro
	(6, 16, '08:20:00', false),	-- Ufscar  Av. Armando Pannunzio
	(6, 23, '08:30:00', false),	-- Ufscar  Rod. Joao Leme dos Santos
	(6, 24, '08:40:00', false),	-- Ufscar  UFSCar
	(7, 6, '07:55:00', false),	-- Rodoviaria  Terminal Santo Antonio
	(7, 5, '08:02:00', false),	-- Rodoviaria  Av. Afonso Vergueiro
	(7, 26, '08:04:00', false),	-- Rodoviaria  Av. Dr. Eugenio Salerno
	(7, 22, '08:10:00', false),	-- Rodoviaria  Av. Moreira Cesar
	(7, 21, '08:16:00', false),	-- Rodoviaria  Av. Juscelino K. de Oliveira
	(7, 27, '08:25:00', false),	-- Rodoviaria  Av. Dom Aguirre
	(7, 19, '08:30:00', false),	-- Rodoviaria  Terminal Sao Paulo
	(8, 28, '09:00:00', false),	-- Expresso Aparecidinha  R. do Terço
	(8, 29, '09:02:00', false),	-- Expresso Aparecidinha  Joao Machado
	(8, 30, '09:10:00', false),	-- Expresso Aparecidinha  Rod. Sen. Jose Ermirio de Moraes
	(8, 27, '09:20:00', false),	-- Expresso Aparecidinha  Av. Dom Aguirre
	(8, 25, '09:25:00', true),	-- Expresso Aparecidinha  Av. Afonso Vergueiro
	(8, 14, '09:27:00', true),	-- Expresso Aparecidinha  R. Francisco Scarpa
	(8, 7, '09:30:00', true),	-- Expresso Aparecidinha  Av. Luiz Ferraz de Sampaio Junior
	(8, 6, '09:35:00', false);	-- Expresso Aparecidinha  Terminal Santo Antonio