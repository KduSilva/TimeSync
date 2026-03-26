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



## Exemplo de dados no H2 Console

Após rodar os testes e inserir registros, o banco H2 em memória fica assim:

### Tabela CARGO
| ID | NOME  | DESCRICAO            |
|----|-------|----------------------|
| 1  | ADM   | Administrador geral  |
| 2  | LIDER | Líder de equipe      |
| 3  | MEMBRO| Membro da equipe     |

### Tabela USUARIO
| ID | NOME    | EMAIL                | CARGO_ID |
|----|---------|----------------------|----------|
| 1  | Eduardo | eduardo@empresa.com  | 1        |
| 2  | Maria   | maria@empresa.com    | 2        |
| 3  | João    | joao@empresa.com     | 3        |

### Tabela EQUIPE
| ID | NOME             |
|----|------------------|
| 1  | Equipe de Vendas |

### Tabela AVISO
| ID | TITULO                 | DESCRICAO               | DATA_HORA           | CRIADOR_ID |
|----|------------------------|-------------------------|---------------------|------------|
| 1  | Reunião de Planejamento| Definir metas da semana | 2026-03-28 10:00:00 | 1          |

### Tabela RESPOSTA
| ID | STATUS   | JUSTIFICATIVA             | AVISO_ID | USUARIO_ID |
|----|----------|---------------------------|----------|------------|
| 1  | RECUSADO | Tenho outro compromisso   | 1        | 3          |

---

Esses dados foram inseridos manualmente no H2 Console para demonstração.  
Você pode acessar o console em:  

http://localhost:8080/h2-console

Com as credenciais:
- **JDBC URL**: `jdbc:h2:mem:timesyncdb`  
- **User**: `sa`  
- **Password**: *(em branco)*



## Roadmap de Implementação atualizado em 26/03/2026

### Autenticação e Usuários
- [X] Adicionar campo **senha** ao `Usuario` com criptografia (BCrypt).  
- [X] Criar **AuthController** com endpoints:
  - `/auth/register` → cadastro de conta.  
  - `/auth/login` → login com JWT.  
- [X] Configurar **Spring Security + JWT** para proteger rotas.
- [X] Incluir role no JWT e utilitários para extrair email e cargo.  
- [X] Regras de acesso por cargo (ADM, LIDER, MEMBRO).

---

## Roteiro de Testes para JWT no Postman
seguindo ROADMAP
### 1. Criar uma Collection
- Abra o **Postman**.  
- Clique em **New → Collection**.  
- Nomeie como: `TimeSync API Tests`.  
- Dentro dessa collection, vamos criar as requisições.

---

### 2. Registro de Usuário
- **Método:** `POST`  
- **URL:** `http://localhost:8080/auth/register`  
- **Body → Raw → JSON**  
  ```json
  {
    "email": "teste@exemplo.com",
    "senha": "123456",
    "cargo": { "nome": "ADM" }
  }
  ```
- **Teste esperado:**  
  - Retorna `200 OK` com o objeto do usuário salvo.  
  - Senha deve estar criptografada no banco (não aparece em texto puro).  

---

### 3. Login de Usuário
- **Método:** `POST`  
- **URL:** `http://localhost:8080/auth/login`  
- **Body → Raw → JSON**  
  ```json
  {
    "email": "teste@exemplo.com",
    "senha": "123456"
  }
  ```
- **Teste esperado:**  
  - Retorna `200 OK` com um **token JWT** (string longa).  
  - Esse token será usado nas próximas requisições.  

 **Dica:** No Postman, copie o token retornado e salve em uma variável da Collection:  
- Vá em **Authorization** da Collection.  
- Tipo: **Bearer Token**.  
- Cole o token no campo.  
Assim todas as requisições vão usar automaticamente esse token.

---

### 4. Acesso a rota protegida (ADM)
- **Método:** `GET`  
- **URL:** `http://localhost:8080/admin/teste`  
- **Headers:**  
  ```
  Authorization: Bearer <TOKEN>
  ```
- **Teste esperado:**  
  - Se o usuário tiver cargo `ADM`, retorna `200 OK`.  
  - Se não tiver, retorna `403 Forbidden`.  

---

### 5. Acesso a rota protegida (LIDER)
- **Método:** `GET`  
- **URL:** `http://localhost:8080/lider/teste`  
- **Headers:**  
  ```
  Authorization: Bearer <TOKEN>
  ```
- **Teste esperado:**  
  - Se o usuário for `ADM` ou `LIDER`, acesso liberado.  
  - Caso contrário, `403 Forbidden`.  

---

### 6. Acesso a rota protegida (MEMBRO)
- **Método:** `GET`  
- **URL:** `http://localhost:8080/membro/teste`  
- **Headers:**  
  ```
  Authorization: Bearer <TOKEN>
  ```
- **Teste esperado:**  
  - Se o usuário for `ADM`, `LIDER` ou `MEMBRO`, acesso liberado.  
  - Caso contrário, `403 Forbidden`.  

---

## Onde colocar os scripts JSON
- No Postman, vá em cada requisição → **Body → Raw → JSON**.  
- Cole os exemplos que te passei (registro e login).  
- Para rotas protegidas, use **Headers → Authorization** com o token JWT.  

---

### Avisos
- [ ] CRUD completo de avisos:
  - Criar aviso (somente ADM).  
  - Listar avisos (todos ou por equipe).  
  - Buscar aviso por ID.  
  - Editar aviso (somente ADM).  
  - Excluir aviso (somente ADM).  
- [ ] Validação de regras (ADM cria, justificativa obrigatória ao recusar).

---

### Notificações
- [ ] Criar entidade `Notificacao` com:
  - Mensagem, data/hora, status (NOVA/VISUALIZADA).  
  - Relacionamento com `Usuario` e `Aviso`.  
- [ ] Implementar `NotificacaoService`:
  - Gerar notificação ao criar aviso.  
  - Gerar notificação ao responder aviso.  
- [ ] Endpoints `/notificacoes`:
  - Listar notificações do usuário.  
  - Marcar como visualizada.

---

###  Funcionalidades Extras
- [ ] **Histórico de atividades** (quem criou, respondeu, visualizou).  
- [ ] **Dashboard** com estatísticas (avisos ativos, respostas aceitas/recusadas).  
- [ ] **Agendamento de avisos** (avisos programados para aparecer em determinada data/hora).  
- [ ] **Integração futura** com e-mail ou push notifications.


- Projeto desenvolvido por Carlos Eduardo Ferreira da Silva (apelido Kdu Silva) como estudo e portfólio em Java Spring Boot.
---
