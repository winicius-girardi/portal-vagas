# Portal de Vagas – Setup de Desenvolvimento

Este projeto é dividido em duas partes: **Back-end (Spring Boot + PostgreSQL)** e **Front-end (HTML + Tailwind + JS puro)**. Siga as instruções abaixo para configurar o ambiente e rodar a aplicação localmente.

---

## 🐘 Banco de Dados (PostgreSQL via Docker)

Antes de tudo, suba o banco de dados:

```bash
cd back
docker compose up -d
```

> Isso criará um container com o PostgreSQL escutando na porta **5432**. Certifique-se de que essa porta esteja livre.

---

## ⚙️ Back-End (Spring Boot)

### ✅ Requisitos

- **Java 21**
- **IntelliJ Community Edition** (recomendado)
- **Gradle** (a IDE resolve automaticamente)

### ▶️ Instruções

1. Abra a pasta `back` no IntelliJ.
2. A IDE detectará o projeto como um aplicativo Gradle.
3. Aguarde o carregamento das dependências.
4. Rode a aplicação diretamente pela classe principal `PortalVagasApplication.java` ou via terminal:

```bash
./gradlew bootRun
```

> O backend espera que o PostgreSQL esteja **rodando na porta 5432** com o esquema e permissões configurados via `docker-compose.yml`.

---

## 🌐 Front-End (HTML + Tailwind + JS)

### ✅ Requisitos

- **Node.js** instalado na máquina

### ▶️ Instruções

1. Acesse a pasta `front`.

```bash
cd front
```

2. Instale o servidor estático:

```bash
npm install -g serve
```

3. Rode o servidor na porta 5500 (necessário para evitar problemas de CORS com o backend):

```bash
serve -p 5500
```

4. Acesse os arquivos diretamente via navegador:

```
http://localhost:5500/home.html
```

> ⚠️ **Importante**: Abrir os arquivos diretamente com duplo clique no navegador **não funcionará corretamente** por causa de CORS. 

---

## ✅ Estrutura do Projeto

```
├── back/            # Código Java Spring Boot
│   ├── build.gradle
│   ├── docker-compose.yml
│   └── src/
└── front/           # HTML + Tailwind + JS
    ├── index.html
    ├── home.html
    ├── login.html
    ├── cadastro.html
    └── *.js
```

---
