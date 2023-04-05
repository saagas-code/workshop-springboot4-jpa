<h1 align="center">Delivery API - Spring Boot (Em desenvolvimento)</h1>

Projeto realizado durante o curso da Udemy do professor
[Nélio Alves](https://www.udemy.com/user/nelio-alves/). O projeto construído é uma aplicação back-end em Java com o framework Spring Boot, que fornece rotas de usuário, ordem e pedidos para um sistema de gerenciamento de pedidos online.

O projeto contém três entidades principais: Cliente, Pedido e Produto e Categorias que estão relacionadas entre si em um modelo de banco de dados simples. A aplicação oferece um conjunto de endpoints RESTful para manipular essas entidades, permitindo que os usuários da API façam operações CRUD básicas (create, read, update e delete).

<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=java,maven,hibernate" />
  </a>
</p>

<h1 align="center">🚀 Tecnologias</h1>

<div align="center">
	<table border="1">
	<tr>
    <td>Java</td>
    <td>Spring-Boot</td>
    <td>Spring-Data</td>
    <td>JPA</td>
    <td>Hibernate</td>
    
    
  </tr>
  <tr>
    <td>Maven</td>
    <td>Insomnia</td>
    <td>Spring-Security</td>
    <td>Token JWT</td>
    <td>Cript de Senha</td>

  </tr>
  </table>
</div>

<h1 align="center">Instalacão</h1>

**1 - Clonar a aplicacão**

```bash
git clone https://github.com/saagas-code/workshop-springboot4-jpa.git
```

**2 - Execute o projeto pelo Eclipse IDE**

```bash
Abra o eclipse -> File -> Import -> Existing Maven Projects -> Import maven projects
Selecione a pasta raiz do projeto clonado
```

**3 - Configurar arquivos do banco de dados**

```bash
Vá até src/main/resources -> application-dev.properties
Modifique os dados abaixo com as configuracoes do seu banco de dados POSTGRESQL
spring.datasource.url=jdbc:postgresql://localhost:5432/springboot #nome do db
spring.datasource.username=postgres #usuario
spring.datasource.password=8819 #senha
```

<h1 align="center">Rotas (em Desenvolvimento)</h1>

### Usuários

| Method | Url        | Descricão                      | Retorno            |
| ------ | ---------- | ------------------------------ | ------------------ |
| GET    | /users     | Lista todos os usuários        | [JSON](#users)     |
| GET    | /users/:id | Lista um usuário pelo id       | [JSON](#users)     |
| POST   | /users     | Cria um usuário                | [JSON](#users)     |
| DELETE | /users/:id | Deleta um usuário pelo id      | [JSON](#categoryD) |
| PUT    | /users/:id | Edita dados do usuário pelo id | [JSON](#categoryP) |

### Produtos

| Method | Url           | Descricão                | Retorno           |
| ------ | ------------- | ------------------------ | ----------------- |
| GET    | /products     | Lista todos os produtos  | [JSON](#products) |
| GET    | /products/:id | Lista um produto pelo id | [JSON](#products) |

### Categorias

| Method | Url             | Descricão                   | Retorno             |
| ------ | --------------- | --------------------------- | ------------------- |
| GET    | /categories     | Lista todas as categories   | [JSON](#categories) |
| GET    | /categories/:id | Lista uma categoria pelo id | [JSON](#categories) |


<h1 align="center">Exemplos de retornos JSON's</h1>

#### <a id="users">Retorno dos usuários -> /categories</a>

```json
{
  "id": 7,
  "name": "Bob Brown",
  "email": "bob@gmail.com",
  "phone": "977557755",
  "password": "123456"
}
```

#### <a id="products">Retorno dos produtos -> /products</a>

```json
{
  "id": 1,
  "name": "The Lord of the Rings",
  "description": "Lorem ipsum dolor sit amet, consectetur.",
  "price": 90.5,
  "imgUrl": "",
  "categories": [
    {
      "id": 2,
      "name": "Books"
    }
  ]
}
```

#### <a id="products">Retorno das categorias -> /categories</a>

```json
{
  "id": 1,
  "name": "Carros"
}
```
