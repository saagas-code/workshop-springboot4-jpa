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

### Autenticacão (As rotas são protegidas por token JWT)
| Method | Url        | Descricão                      | Retorno            | Body |
| ------ | ---------- | ------------------------------ | ------------------ | ------------------ |
| POST    | /users/auth     | Retorna um token JWT     | [JSON](#token)  | [Exemplo](#authLogin) |
| POST   | /users     | Cria um usuário                | [JSON](#users)     | [Exemplo](#authRegister) |

### Usuários
| Method | Url        | Descricão                      | Retorno            | Body |
| ------ | ---------- | ------------------------------ | ------------------ | ------------------ |
| GET    | /users     | Lista todos os usuários        | [JSON](#users)     |
| GET    | /users/:id | Lista um usuário pelo id       | [JSON](#users)     |
| DELETE | /users/:id | Deleta um usuário pelo id      | 204 | 
| PUT    | /users/:id | Edita dados do usuário pelo id | [JSON](#categoryP) | [Exemplo](#authRegister)

### Produtos

| Method | Url           | Descricão                | Retorno           | Body |
| ------ | ------------- | ------------------------ | ----------------- | ------------------ |
| GET    | /products     | Lista todos os produtos  | [JSON](#products) |
| GET    | /products/:id | Lista um produto pelo ID | [JSON](#products) |
| POST    | /products | Cria um produto | [JSON](#products) | [Exemplo](#productBody)
| PUT    | /products | Edita um produto | [JSON](#products) | [Exemplo](#productBody)
| DELETE    | /products | Delete um produto pelo ID | 204 |

### Categorias

| Method | Url             | Descricão                   | Retorno             | Body |
| ------ | --------------- | --------------------------- | ------------------- | ------------------ |
| GET    | /categories     | Lista todas as categories   | [JSON](#categories) |
| GET    | /categories/:id | Lista uma categoria pelo id | [JSON](#categories) |
| POST    | /categories | Cria uma categoria | [JSON](#categories) | [Exemplo](#categoryBody)
| DELETE    | /categories/:id | Deleta uma categoria pelo ID | 204 |

### Pedidos

| Method | Url             | Descricão                   | Retorno             | Body |
| ------ | --------------- | --------------------------- | ------------------- | ------------------ |
| GET    | /orders     | Lista todas os pedidos   | [JSON](#orders) |
| GET    | /orders/:id | Lista um pedido pelo ID | [JSON](#orders) |
| POST    | /orders | Cria um pedido | [JSON](#orders) | [Exemplo](#orderBody)
| DELETE    | /orders/:id | Delete um pedido pelo ID | 204 |


<h1 align="center">Exemplos de retornos JSON's</h1>


#### <a id="users">Retorno dos usuários -> /users</a>

```json
{
	"id": 1,
	"first_name": "admin",
	"email": "admin@gmail.com",
	"phone": "admin",
	"role": "ADMIN"
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

#### <a id="orders">Retorno dos pedidos -> /orders</a>

```json
{
  "id": 1,
	"name": "Hamburger",
	"description": "Comidas",
	"price": 999.0,
	"imgUrl": "url.com",
	"categories": [
		{
			"id": 2,
			"name": "Teste"
		}
	]
}
```


#### <a id="token">Retorno do login -> /users/auth</a>

```json
{
  "email": teteu@gmail.com,
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBdXRoIiwic3ViIjoiYWRtaW5AZ21haWwuY29tIiwiaWQiOjEsImV4cCI6MTY4MDcyNzgxN30.CcBbzNj2caJkMRc05n42UG2BAVGjXrdJa8GVv1gZUEg"
}
```



<h1 align="center">Exemplos de body's das requisicoes POST</h1>


#### <a id="authRegister">Corpo da criacão de um usuário -> /users/register</a>

```json
{ 
 "name": "String", 
 "email": "String@gmail.com", 
 "phone": "Number", 
 "password": "String" 
} 
```

#### <a id="authLogin">Corpo da autenticacão do usuário -> /users/auth</a>

```json
{ 
 "email": "String@gmail.com", 
 "password": "String"
} 
```


#### <a id="productBody">Corpo para criacão de um produto -> /products</a>

```json
{ 
 "name": "String", 
 "description": "String", 
 "price": "Number", 
 "imgUrl": "String",
	"categoriaIds": ["categoryId"]
} 
```

#### <a id="categoryBody">Corpo para criacão de uma categoria -> /categories</a>

```json
{ 
 "name": "String"
}  
```

#### <a id="orderBody">Corpo para criacão de um pedido -> /orders</a>

```json
{ 
 "clientId": 1 ,
	"items": [
		{
		"productId": 1,
		"quantity": 2
		}
	]
}  
```
