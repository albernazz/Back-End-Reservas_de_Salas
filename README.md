# 🏢 Backend de Reservas de Salas

API REST desenvolvida para gerenciamento e agendamento de **salas**, **usuários** e **reservas**. O sistema conta com validações automatizadas de regras de negócio para garantir que não haja conflitos de capacidade ou status de salas.

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Jakarta Bean Validation**
- **PostgreSQL**
- **Maven**

---

## ⚙️ Regras de Negócio & Validações

- **Salas:**
  - Devem estar **ativas** para receber novas reservas.
- **Reservas:**
  - A data/hora de **início** não pode ser maior ou igual à data/hora de **fim**.
  - A quantidade de pessoas não pode exceder a **capacidade máxima** da sala.
  - A quantidade de pessoas deve ser maior que zero.

---

## 🚀 Configuração e Execução

### Pré-requisitos
- Java 21 instalado
- PostgreSQL em execução

### Passo a passo

1. **Crie o banco de dados:**
   No PostgreSQL, crie um banco com o nome `reserva_de_salas`:
   ```sql
   CREATE DATABASE reserva_de_salas;

  Configure as credenciais:
Ajuste as propriedades do banco de dados no arquivo src/main/resources/application.properties:

```sql
Properties
spring.datasource.url=jdbc:postgresql://localhost:5432/reserva_de_salas
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
Execute a aplicação:
Na raiz do projeto, execute o comando:
```

Bash
./mvnw spring-boot:run
A API estará acessível em: http://localhost:8080

📌 Rotas da API
🏢 Salas (/api/v1/salas)
Método	Rota	Descrição
GET	/api/v1/salas	Lista todas as salas
GET	/api/v1/salas/{id}	Busca sala por ID
POST	/api/v1/salas	Cadastra uma nova sala
PUT	/api/v1/salas/{id}	Atualiza dados de uma sala
PUT	/api/v1/salas/{id}/desativar	Desativa uma sala
DELETE	/api/v1/salas/{id}	Deleta uma sala

---

👤 Usuários (/api/v1/usuarios)
Método	Rota	Descrição
GET	/api/v1/usuarios	Lista todos os usuários
GET	/api/v1/usuarios/{id}	Busca usuário por ID
POST	/api/v1/usuarios	Cadastra um novo usuário
PUT	/api/v1/usuarios/{id}	Atualiza dados de um usuário
DELETE	/api/v1/usuarios/{id}	Remove um usuário

---

📅 Reservas (/api/v1/reservas)
Método	Rota	Descrição
GET	/api/v1/reservas	Lista todas as reservas
GET	/api/v1/reservas/{id}	Busca reserva por ID
POST	/api/v1/reservas	Cria uma nova reserva
PUT	/api/v1/reservas/{id}	Atualiza dados de uma reserva
PUT	/api/v1/reservas/{id}/cancelar	Cancela uma reserva
DELETE	/api/v1/reservas/{id}	Deleta uma reserva
---
📝 Exemplo de JSON (Criar Reserva)
POST /api/v1/reservas

```sql
JSON
{
  "sala": { "id": 1 },
  "usuario": { "id": 1 },
  "inicio": "2026-10-15T14:00:00",
  "fim": "2026-10-15T16:00:00",
  "quantidadeDePessoas": 5,
  "status": "ATIVA"
}

