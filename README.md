# 🛒 E-Commerce API

API REST de e-commerce desenvolvida com Spring Boot, integrada a uma loja externa via Feign Client, com gerenciamento de produtos e carrinho de compras.

---

## 📋 Sobre o Projeto

Este projeto é uma API de e-commerce que permite o gerenciamento de produtos e carrinhos de compras. A aplicação consome a [Platzi Fake Store API](https://fakeapi.platzi.com/) para obter dados de produtos externos, além de oferecer sua própria camada de persistência e regras de negócio.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **OpenFeign** — integração com API externa (Platzi Store)
- **Docker / Docker Compose**
- **Maven**

---

## 📁 Estrutura do Projeto

```
src/main/java/br/com/alan/e_comerce/
├── client/
│   ├── PlatziStoreClient.java         # Feign Client para API externa
│   └── response/
│       └── PlatziProductResponse.java # DTO de resposta da API externa
├── controller/
│   ├── BasketController.java          # Endpoints do carrinho
│   └── ProductController.java         # Endpoints de produtos
├── entity/
│   ├── Basket.java                    # Entidade carrinho
│   └── Product.java                   # Entidade produto
├── enums/
│   ├── PaymentMethod.java             # Métodos de pagamento
│   └── Status.java                    # Status do carrinho/pedido
├── exceptions/
│   ├── BusinessException.java
│   ├── ControllerAdvice.java          # Handler global de exceções
│   ├── CustomErrorDecoder.java        # Decoder de erros do Feign
│   └── DataNotFoundException.java
├── repository/
│   └── BasketRepository.java
├── request/
│   ├── BasketRequest.java
│   ├── PaymentRequest.java
│   └── ProductRequest.java
└── service/
    ├── BasketService.java
    └── ProductService.java
```

---

## ⚙️ Configuração e Execução

### Pré-requisitos

- Java 17+
- Maven 3.8+
- Docker e Docker Compose (para banco de dados)

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/e-comerce.git
cd e-comerce
```

### 2. Suba os serviços com Docker

```bash
docker-compose up -d
```

### 3. Execute a aplicação

```bash
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

---

## 🔗 Endpoints

### Produtos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/products` | Lista todos os produtos |
| `GET` | `/products/{id}` | Busca produto por ID |
| `POST` | `/products` | Cria um novo produto |

### Carrinho

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/basket` | Lista todos os carrinhos |
| `GET` | `/basket/{id}` | Busca carrinho por ID |
| `POST` | `/basket` | Cria um novo carrinho |
| `POST` | `/basket/{id}/payment` | Realiza o pagamento |
| `DELETE` | `/basket/{id}` | Remove um carrinho |

---

## 💳 Métodos de Pagamento

Os métodos de pagamento disponíveis são definidos pelo enum `PaymentMethod`:

- `CREDIT_CARD`
- `DEBIT_CARD`
- `PIX`
- `BOLETO`

---

## 🏗️ Arquitetura

O projeto segue a arquitetura em camadas tradicional do Spring Boot:

```
Controller → Service → Repository → Banco de Dados
                ↓
         Feign Client → API Externa (Platzi Store)
```

- **Controller**: recebe requisições HTTP e delega para os services
- **Service**: contém as regras de negócio
- **Repository**: acesso ao banco de dados via Spring Data JPA
- **Feign Client**: comunicação com a API externa da Platzi Store
- **Exceptions**: tratamento centralizado de erros via `@ControllerAdvice`

---

## 🐳 Docker

O arquivo `docker-compose.yml` na raiz do projeto provisiona os serviços necessários (banco de dados). Para iniciá-los:

```bash
docker-compose up -d
```

---

## 👨‍💻 Autor

Desenvolvido por **Alan**
