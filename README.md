# Backend de Reservas de Salas

API REST para gerenciar **salas**, **usuários** e **reservas**. O projeto usa Java 21, Spring Boot, Spring Data JPA e PostgreSQL. As reservas têm validações de horário e podem ser canceladas.

## Requisitos

- Java 21
- PostgreSQL

## Configuração e execução

1. Crie no PostgreSQL um banco chamado `reserva_de_salas`.
2. Confira a URL, o usuário e a senha do banco em `src/main/resources/application.properties` e ajuste-os para o seu ambiente.
3. Na raiz do projeto, execute:

```bash
./mvnw spring-boot:run
```
## Principais rotas
Recurso
Rotas
Salas
GET/POST /api/v1/salas, GET/PUT/DELETE /api/v1/salas/{id}, PUT /api/v1/salas/{id}/desativar
Usuários
GET/POST /api/v1/usuarios, GET/PUT/DELETE /api/v1/usuarios/{id}
Reservas
GET/POST /api/v1/reservas, GET/PUT/DELETE /api/v1/reservas/{id}, PUT /api/v1/reservas/{id}/cancelar

