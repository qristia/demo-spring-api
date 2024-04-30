# Rest API de Exemplo usando Spring Boot e Postgres
<br>

# Como rodar a API

Pré-requisitos

1. Java Development Kit (JDK)
2. Maven

Passos

1. Clone o repositório

2. Navegue até a pasta do projeto

3. Build o projeto: `mvn clean install`

4. Rode a aplicação: `mvn spring-boot:run`

# Documentação da API

### Base URL
- Base URL (development) para todos os endpoint: `http://localhost:8080/`

### Pegar todas as tarefas com filtros opcionais

- **URL:** `/tasks`
- **Method:** `GET`
- **Parameters:**
    - `createdDate`**:** Filtra tarefas por data criada (format: yyyy-MM-dd)
    - `dueDate`**:** Filtra tarefas por data de vencimento (format: yyyy-MM-dd)
    - `status`**:** Filtra tarefas por status (PENDING, PROGRESS, COMPLETED)

- **Response:**
    - **Status:** 200 OK
    - **Body:** Array de tarefas

- **Exemplo Request:**

    ```
    GET /tasks?createdDate=2024-04-01&status=PENDING
    ```

- **Exemplo Response:**

    ```
    [
        {
            "id": 1,
            "title": "titulo 1",
            "description": "description",
            "status": "PENDING",
            "createdDate": "2024-04-01",
            "dueDate": "2024-06-02"
        },
        {
            "id": 2,
            "title": "titulo 2",
            "description": "description",
            "status": "PENDING",
            "createdDate": "2024-04-01",
            "dueDate": "2024-08-02"
        }
    ]
    ```

---

### Pegar uma única tarefa por ID

- **URL:** `/tasks/{id}`
- **Method:** `GET`
- **Response:**
    - **Status:** 200 OK
    - **Body:** Objeto JSON da tarefa

- **Exemplo Request:**

    ```
    GET /tasks/1
    ```

- **Exemplo Response:**

    ```
    {
        "id": 1,
        "title": "titulo 1",
        "description": "description",
        "status": "PENDING",
        "createdDate": "2024-04-01",
        "dueDate": "2024-06-02"
    }
    ```

---

### Criar nova tarefa

- **URL:** `/tasks`
- **Method:** `POST`
- **Body:**
    - Objeto JSON representando a nova tarefa:
        - `title` (string): Título da tarefa
        - `description` (string): Descrição da tarefa.
        - `dueDate` (string): Data de vencimento da tarefa (formato: yyyy-MM-dd)
- **Response:**
    - **Status:** 201 Created
    - **Body:** Objeto JSON da tarefa criada

- **Exemplo Request:**

    ```
    POST /task

    {
        "title": "Tarefa de exemplo",
        "description": "Completar a tarefa...",
        "dueDate": "2024-05-15"
    }
    ```

---

### Editar tarefa existente

- **URL:** `/tasks`
- **Method:** `PATCH`
- **Body:**
    - Objeto JSON representando as propriedades que devem ser atualizadas:
        - `title` (string): Novo título da tarefa
        - `description` (string): Nova descrição da tarefa.
        - `dueDate` (string): Nova data de vencimento da tarefa (formato: yyyy-MM-dd)
- **Response:**
    - **Status:** 200 Created
    - **Body:** Objeto JSON da tarefa atualizada

- **Exemplo Request:**

    ```
    PATCH /task

    {
        "title": "Tarefa de exemplo 1",
        "dueDate": "2024-05-30"
    }
    ```

---

### Apagar tarefa

- **URL:** `/tasks`
- **Method:** `DELETE`
- **Parameters:**
    - `id`: ID da tarefa a ser deletada
- **Response:**
    - **Status:** 204 No Content

- **Exemplo Request:**

    ```
    DELETE /task/1
    ```
