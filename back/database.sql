CREATE DATABASE portal_vagas;
\c portal_vagas

CREATE SCHEMA IF NOT EXISTS SCH_PORTAL_VAGAS;
CREATE USER  usuario_app WITH PASSWORD 'usuario_app';
GRANT USAGE ON SCHEMA sch_portal_vagas TO usuario_app;
GRANT CONNECT ON DATABASE portal_vagas TO usuario_app;

CREATE TABLE IF NOT EXISTS SCH_PORTAL_VAGAS.JOB (

    ID INT GENERATED ALWAYS AS IDENTITY,
    TITLE TEXT NOT NULL,
    DESCRIPTION TEXT NOT NULL,
    COMPANY TEXT NOT NULL,
    PUBLISH_DATE DATE NOT NULL,
    EXPIRE_DATE DATE NOT NULL,
    EXPERTISE_LEVEL TEXT NOT NULL,
    TYPE_OF_JOB TEXT NOT NULL,
    TEMPORARY BOOLEAN NOT NULL,
    ACCEPT_PCD BOOLEAN NOT NULL,
    STATE TEXT NOT NULL,
    CITY TEXT NOT NULL,
    LOCATION TEXT NOT NULL,

    CONSTRAINT ID_JOB_PK PRIMARY KEY (ID)

);

CREATE TABLE IF NOT EXISTS SCH_PORTAL_VAGAS.APP_USER (

    ID INT GENERATED ALWAYS AS IDENTITY,
    NAME VARCHAR NOT NULL,
    EMAIL VARCHAR NOT NULL,
    PASSWORD VARCHAR NOT NULL,
    ROLE VARCHAR NOT NULL,

    CONSTRAINT ID_USER_PK PRIMARY KEY (ID)

);

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES
    IN SCHEMA SCH_PORTAL_VAGAS TO usuario_app;

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES
    IN SCHEMA SCH_PORTAL_VAGAS TO usuario_app;

INSERT INTO SCH_PORTAL_VAGAS.JOB
(TITLE, DESCRIPTION, COMPANY, PUBLISH_DATE, EXPIRE_DATE, EXPERTISE_LEVEL, TYPE_OF_JOB, TEMPORARY, ACCEPT_PCD, STATE, CITY, LOCATION)
VALUES
    ('Estágio em Desenvolvimento Java', 'Vaga para estágio focado em desenvolvimento Java e Spring Boot.', 'Tech Solutions', '2025-07-01', '2025-08-01', 'JUNIOR', 'ESTÁGIO', TRUE, TRUE, 'SAO_PAULO', 'São Paulo', 'Av. Paulista, 1000'),
    ('Analista de Sistemas', 'Responsável pela análise e desenvolvimento de sistemas internos.', 'Innovatech', '2025-06-25', '2025-07-25', 'PLENO', 'CLT', FALSE, TRUE, 'RIO_DE_JANEIRO', 'Rio de Janeiro', 'Rua das Laranjeiras, 234'),
    ('Desenvolvedor Frontend', 'Desenvolvimento de interfaces web modernas usando React.', 'WebCraft', '2025-07-05', '2025-08-05', 'SENIOR', 'CLT', FALSE, FALSE, 'MINAS_GERAIS', 'Belo Horizonte', 'Av. Afonso Pena, 500'),
    ('Assistente Administrativo', 'Auxílio nas rotinas administrativas da empresa.', 'Admin Corp', '2025-07-10', '2025-08-10', 'JUNIOR', 'CLT', FALSE, TRUE, 'SAO_PAULO', 'São Paulo', 'Rua Augusta, 150'),
    ('Engenheiro de Dados', 'Projetar e manter pipelines de dados.', 'DataWorks', '2025-07-12', '2025-09-12', 'SENIOR', 'CLT', FALSE, FALSE, 'RIO_DE_JANEIRO', 'Rio de Janeiro', 'Av. Rio Branco, 45'),
    ('Estágio em Marketing Digital', 'Auxílio nas campanhas de marketing e mídias sociais.', 'MarketPro', '2025-06-30', '2025-07-30', 'JUNIOR', 'ESTÁGIO', TRUE, TRUE, 'SAO_PAULO', 'São Paulo', 'Rua Oscar Freire, 200');


INSERT INTO SCH_PORTAL_VAGAS.APP_USER
(NAME, EMAIL, PASSWORD, ROLE)
VALUES
    ('Administrador', 'admin@portalvagas.com', '$2a$10$abcd...123', 'ADMIN'),
    ('Usuário Comum', 'usuario@portalvagas.com', '$2a$10$xyz...456', 'USER');


