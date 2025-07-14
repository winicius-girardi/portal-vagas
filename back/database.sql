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

    CONSTRAINT EMAIL_UNIQUE UNIQUE (EMAIL),
    CONSTRAINT ID_USER_PK PRIMARY KEY (ID)

);

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES
    IN SCHEMA SCH_PORTAL_VAGAS TO usuario_app;

GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES
    IN SCHEMA SCH_PORTAL_VAGAS TO usuario_app;

INSERT INTO SCH_PORTAL_VAGAS.JOB
(TITLE, DESCRIPTION, COMPANY, PUBLISH_DATE, EXPIRE_DATE, EXPERTISE_LEVEL, TYPE_OF_JOB, TEMPORARY, ACCEPT_PCD, STATE, CITY, LOCATION)
VALUES
    ('Analista de QA', 'Testes automatizados e garantia da qualidade de software.', 'QualitySoft', '2025-07-08', '2025-08-08', 'PLENO', 'CLT', FALSE, TRUE, 'PARANA', 'Curitiba', 'Av. Sete de Setembro, 1010'),
    ('Desenvolvedor Backend Node.js', 'Manutenção e desenvolvimento de APIs REST em Node.js.', 'Backend Pro', '2025-07-02', '2025-08-02', 'SENIOR', 'PJ', FALSE, FALSE, 'RIO_GRANDE_DO_SUL', 'Porto Alegre', 'Rua dos Andradas, 765'),
    ('Designer UX/UI', 'Criação de interfaces centradas no usuário para aplicações web.', 'Creative Minds', '2025-07-06', '2025-08-06', 'PLENO', 'CLT', FALSE, TRUE, 'BAHIA', 'Salvador', 'Av. Tancredo Neves, 300'),
    ('Suporte Técnico', 'Atendimento ao cliente e suporte de TI de primeiro nível.', 'HelpTech', '2025-07-01', '2025-07-31', 'JUNIOR', 'CLT', FALSE, TRUE, 'CEARA', 'Fortaleza', 'Rua dos Tabajaras, 90'),
    ('Arquiteto de Software', 'Desenho de arquitetura de sistemas escaláveis e distribuídos.', 'SoftArch', '2025-07-03', '2025-09-03', 'SENIOR', 'CLT', FALSE, FALSE, 'SAO_PAULO', 'Campinas', 'Av. Francisco Glicério, 400'),
    ('Estágio em Ciência de Dados', 'Análise de dados e apoio em projetos de machine learning.', 'DataLab', '2025-07-04', '2025-08-04', 'JUNIOR', 'ESTÁGIO', TRUE, TRUE, 'MINAS_GERAIS', 'Uberlândia', 'Av. João Naves de Ávila, 1500'),
    ('Product Owner', 'Gestão de backlog e priorização de funcionalidades.', 'AgilePro', '2025-07-07', '2025-08-07', 'PLENO', 'CLT', FALSE, FALSE, 'PERNAMBUCO', 'Recife', 'Rua da Aurora, 250'),
    ('Técnico em Redes', 'Instalação e manutenção de redes de computadores.', 'NetCorp', '2025-07-09', '2025-08-09', 'JUNIOR', 'CLT', FALSE, TRUE, 'GOIAS', 'Goiânia', 'Av. Goiás, 1234'),
    ('Coordenador de Projetos', 'Planejamento e execução de projetos de TI.', 'ProjectTech', '2025-07-11', '2025-08-11', 'SENIOR', 'CLT', FALSE, FALSE, 'RIO_DE_JANEIRO', 'Niterói', 'Rua Moreira César, 900'),
    ('Analista de BI', 'Criação de dashboards e análise de dados com Power BI.', 'Insight Data', '2025-07-13', '2025-08-13', 'PLENO', 'PJ', FALSE, FALSE, 'DISTRITO_FEDERAL', 'Brasília', 'SCS Quadra 5, Bloco A');


INSERT INTO SCH_PORTAL_VAGAS.APP_USER
(NAME, EMAIL, PASSWORD, ROLE)
VALUES
    ('Administrador', 'admin@portalvagas.com', '$2a$10$abcd...123', 'ADMIN'),
    ('Usuário Comum', 'usuario@portalvagas.com', '$2a$10$xyz...456', 'USER');


