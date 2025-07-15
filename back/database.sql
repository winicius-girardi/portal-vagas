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
    ('Analista de QA', 'Testes automatizados e garantia da qualidade de software.', 'QualitySoft', '2025-07-08', '2025-08-08', 'ASSOCIATE', 'CLT', FALSE, TRUE, 'PARANA', 'Curitiba', 'Av. Sete de Setembro, 1010'),
    ('Desenvolvedor Backend Node.js', 'Manutenção e desenvolvimento de APIs REST em Node.js.', 'Backend Pro', '2025-07-02', '2025-08-02', 'SENIOR', 'PJ', FALSE, FALSE, 'RIO_GRANDE_DO_SUL', 'Porto Alegre', 'Rua dos Andradas, 765'),
    ('Designer UX/UI', 'Criação de interfaces centradas no usuário para aplicações web.', 'Creative Minds', '2025-07-06', '2025-08-06', 'ASSOCIATE', 'CLT', FALSE, TRUE, 'BAHIA', 'Salvador', 'Av. Tancredo Neves, 300'),
    ('Suporte Técnico', 'Atendimento ao cliente e suporte de TI de primeiro nível.', 'HelpTech', '2025-07-01', '2025-07-31', 'JUNIOR', 'CLT', FALSE, TRUE, 'CEARA', 'Fortaleza', 'Rua dos Tabajaras, 90'),
    ('Arquiteto de Software', 'Desenho de arquitetura de sistemas escaláveis e distribuídos.', 'SoftArch', '2025-07-03', '2025-09-03', 'SENIOR', 'CLT', FALSE, FALSE, 'SAO_PAULO', 'Campinas', 'Av. Francisco Glicério, 400'),
    ('Estágio em Ciência de Dados', 'Análise de dados e apoio em projetos de machine learning.', 'DataLab', '2025-07-04', '2025-08-04', 'INTERNSHIP', 'INTERNSHIP', TRUE, TRUE, 'MINAS_GERAIS', 'Uberlândia', 'Av. João Naves de Ávila, 1500'),
    ('Product Owner', 'Gestão de backlog e priorização de funcionalidades.', 'AgilePro', '2025-07-07', '2025-08-07', 'ASSOCIATE', 'CLT', FALSE, FALSE, 'PERNAMBUCO', 'Recife', 'Rua da Aurora, 250'),
    ('Técnico em Redes', 'Instalação e manutenção de redes de computadores.', 'NetCorp', '2025-07-09', '2025-08-09', 'JUNIOR', 'CLT', FALSE, TRUE, 'GOIAS', 'Goiânia', 'Av. Goiás, 1234'),
    ('Coordenador de Projetos', 'Planejamento e execução de projetos de TI.', 'ProjectTech', '2025-07-11', '2025-08-11', 'SENIOR', 'CLT', FALSE, FALSE, 'RIO_DE_JANEIRO', 'Niterói', 'Rua Moreira César, 900'),
    ('Analista de BI', 'Criação de dashboards e análise de dados com Power BI.', 'Insight Data', '2025-07-13', '2025-08-13', 'ASSOCIATE', 'PJ', FALSE, FALSE, 'DISTRITO_FEDERAL', 'Brasília', 'SCS Quadra 5, Bloco A'),
    ('Estágio em Suporte', 'Atendimento e suporte a usuários internos.', 'SupportNow', '2025-07-14', '2025-08-14', 'INTERNSHIP', 'INTERNSHIP', TRUE, TRUE, 'TOCANTINS', 'Palmas', 'Av. JK, 800'),
    ('Engenheiro de Dados', 'Desenvolvimento de pipelines e engenharia de dados.', 'DataFlow', '2025-07-15', '2025-08-15', 'SENIOR', 'PJ', FALSE, FALSE, 'AMAZONAS', 'Manaus', 'Av. Djalma Batista, 1500'),
    ('Estágio em Marketing', 'Criação de conteúdo e apoio em redes sociais.', 'MarketUp', '2025-07-16', '2025-08-16', 'INTERNSHIP', 'INTERNSHIP', TRUE, TRUE, 'ALAGOAS', 'Maceió', 'Av. Fernandes Lima, 1234'),
    ('Analista DevOps', 'Automação de infraestrutura com Docker e Jenkins.', 'CloudOps', '2025-07-17', '2025-08-17', 'INTERMEDIATE', 'CLT', FALSE, FALSE, 'PARA', 'Belém', 'Av. Nazaré, 99'),
    ('Desenvolvedor Frontend', 'Implementação de componentes em React.', 'WebFront', '2025-07-18', '2025-08-18', 'ASSOCIATE', 'CLT', FALSE, TRUE, 'ESPIRITO_SANTO', 'Vitória', 'Rua do Comércio, 88'),
    ('Product Manager', 'Liderança de produto e roadmap.', 'PM Solutions', '2025-07-19', '2025-08-19', 'SENIOR', 'PJ', FALSE, FALSE, 'MATO_GROSSO_DO_SUL', 'Campo Grande', 'Rua Rui Barbosa, 300'),
    ('Estágio em QA', 'Execução de testes manuais.', 'BugHunter', '2025-07-20', '2025-08-20', 'INTERNSHIP', 'INTERNSHIP', TRUE, TRUE, 'PIAUI', 'Teresina', 'Rua Simplício Mendes, 450'),
    ('Analista de Segurança', 'Monitoramento de sistemas e resposta a incidentes.', 'SecureNet', '2025-07-21', '2025-08-21', 'INTERMEDIATE', 'PJ', FALSE, FALSE, 'BAHIA', 'Feira de Santana', 'Av. Getúlio Vargas, 2400'),
    ('Scrum Master', 'Facilitação de squads ágeis.', 'AgileNow', '2025-07-22', '2025-08-22', 'ASSOCIATE', 'CLT', FALSE, TRUE, 'RIO_GRANDE_DO_NORTE', 'Natal', 'Av. Engenheiro Roberto Freire, 500'),
    ('Desenvolvedor .NET', 'APIs REST com C# e .NET Core.', 'CodeBase', '2025-07-23', '2025-08-23', 'JUNIOR', 'CLT', FALSE, TRUE, 'PERNAMBUCO', 'Olinda', 'Rua do Sol, 120'),
    ('Analista Funcional', 'Especificação de requisitos de sistema.', 'FuncSys', '2025-07-24', '2025-08-24', 'INTERMEDIATE', 'CLT', FALSE, TRUE, 'MARANHAO', 'São Luís', 'Av. Litorânea, 321'),
    ('Estágio em Desenvolvimento', 'Apoio em desenvolvimento web.', 'DevStart', '2025-07-25', '2025-08-25', 'INTERNSHIP', 'INTERNSHIP', TRUE, TRUE, 'PARAIBA', 'João Pessoa', 'Av. Epitácio Pessoa, 600'),
    ('UX Designer', 'Criação de fluxos de navegação e wireframes.', 'UserFlow', '2025-07-26', '2025-08-26', 'ASSOCIATE', 'CLT', FALSE, TRUE, 'CEARA', 'Juazeiro do Norte', 'Rua São Pedro, 450'),
    ('Administrador de Banco de Dados', 'Gerenciamento de banco PostgreSQL.', 'DataSecure', '2025-07-27', '2025-08-27', 'SENIOR', 'PJ', FALSE, FALSE, 'RIO_DE_JANEIRO', 'Nova Iguaçu', 'Av. Nilo Peçanha, 999'),
    ('Consultor SAP', 'Implementação de módulos FI e MM.', 'SAP Solutions', '2025-07-28', '2025-08-28', 'SENIOR', 'PJ', FALSE, FALSE, 'SAO_PAULO', 'Santo André', 'Av. Portugal, 888'),
    ('Engenheiro de Software', 'Desenvolvimento orientado a testes e arquitetura.', 'CleanCode Inc.', '2025-07-29', '2025-08-29', 'SENIOR', 'CLT', FALSE, FALSE, 'MINAS_GERAIS', 'Contagem', 'Rua Tiradentes, 150'),
    ('Trainee de TI', 'Capacitação em desenvolvimento full stack.', 'FutureTech', '2025-07-30', '2025-08-30', 'TRAINEE', 'CLT', FALSE, TRUE, 'RIO_GRANDE_DO_SUL', 'Caxias do Sul', 'Av. Júlio de Castilhos, 310'),
    ('Desenvolvedor Android', 'Apps móveis nativos com Kotlin.', 'MobileDev', '2025-07-31', '2025-08-31', 'ASSOCIATE', 'CLT', FALSE, TRUE, 'MATO_GROSSO', 'Cuiabá', 'Av. Historiador Rubens de Mendonça, 500'),
    ('Analista de Redes', 'Gerenciamento de redes locais.', 'NetSecure', '2025-08-01', '2025-09-01', 'INTERMEDIATE', 'CLT', FALSE, TRUE, 'SERGIPE', 'Aracaju', 'Av. Beira Mar, 100'),
    ('Especialista em Cloud', 'Arquitetura de soluções AWS.', 'CloudMasters', '2025-08-02', '2025-09-02', 'SENIOR', 'PJ', FALSE, FALSE, 'DISTRITO_FEDERAL', 'Brasília', 'Setor Bancário Norte, Quadra 2'),
    ('Analista de Produto', 'Análise e levantamento de melhorias.', 'ProdTeam', '2025-08-03', '2025-09-03', 'ASSOCIATE', 'CLT', FALSE, TRUE, 'RORAIMA', 'Boa Vista', 'Rua das Mangueiras, 700'),
    ('Desenvolvedor PHP', 'Back-end com Laravel.', 'PHP Tech', '2025-08-04', '2025-09-04', 'JUNIOR', 'CLT', FALSE, TRUE, 'ACRE', 'Rio Branco', 'Rua Epaminondas Jácome, 80'),
    ('Estágio em UI', 'Apoio na criação de componentes visuais.', 'UI Lab', '2025-08-05', '2025-09-05', 'INTERNSHIP', 'INTERNSHIP', TRUE, TRUE, 'AMAPA', 'Macapá', 'Av. FAB, 210')
;

