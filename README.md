# TimeSync

**TimeSync** é um sistema de gestão de reuniões e avisos corporativos desenvolvido em **Java Spring Boot**.  
O objetivo é permitir que empresas organizem equipes, criem avisos de reuniões e gerenciem confirmações de presença de forma simples e eficiente.

---

## Funcionalidades
- **Gestão de cargos e níveis de acesso**:
  - **ADM Geral**: dono ou gerente maior, com acesso total.
  - **Líderes de grupos**: gerenciam equipes e podem criar avisos para seus grupos.
  - **Membros**: recebem notificações e podem aceitar ou recusar (com justificativa obrigatória em caso de recusa).
- **Criação de equipes** e associação de membros.
- **Criação de avisos/reuniões** com data e descrição.
- **Respostas dos membros** (aceitar/recusar com justificativa).
- **Banco de dados em memória (H2)** para testes rápidos.

---

## Tecnologias utilizadas
- Java 21
- Spring Boot 3.2.4
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok

---

## Como rodar o projeto
1. Clone o repositório:
   ```bash
   git clone https://github.com/KduSilva/TimeSync

2. Entre na pasta do projeto
   cd TimeSync

3. Rode a aplicação:
   mvn spring-boot:run

 4. Acesse no navegador:
  http://localhost:8080


      Endpoints principais
- POST /cargos → criar cargos (ADM, LIDER, MEMBRO).
- POST /usuarios → criar usuários vinculados a cargos.
- POST /equipes → criar equipes e adicionar membros.
- POST /avisos → criar avisos (somente ADM ou Líder).
- POST /avisos/{id}/resposta → responder aviso (aceitar/recusar).


Testes com H2
- Console H2 disponível em:
http://localhost:8080/h2-console


- JDBC URL: jdbc:h2:mem:timesyncdb
- Usuário: sa
- Senha: (em branco)

Status do projeto
  Estrutura inicial pronta
  Em desenvolvimento (controllers, services e regras de negócio)
  Próximos passos: autenticação com Spring Security e interface web

Autor
Projeto desenvolvido por Carlos Eduardo Ferreira da Silva, apelido Kdu Silva, como estudo e portfólio em Java Spring Boot.

---
