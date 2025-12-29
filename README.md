# 🚀 Oriontek Customers API

API REST desarrollada en **Spring Boot** siguiendo **Arquitectura Limpia + DDD + CQRS**, con autenticación **JWT vía Keycloak**, manejo consistente de errores con el objeto `Result`, y soporte para **Soft Delete**.

---

## 📦 Tecnologías

* Java 21
* Spring Security (OAuth2 Resource Server)
* Keycloak (JWT)
* Spring Data JPA
* PostgreSQL
* Docker & Docker Compose
* Swagger / OpenAPI

---

## 🧱 Arquitectura

* **Domain**: Entidades y reglas de negocio
* **Application**: Commands, Queries, Handlers, Validators, Result
* **Infrastructure**: JPA, Security, Keycloak
* **Presentation**: Controllers (REST API)

Patrones utilizados:

* CQRS
* Mediator
* Result Pattern
* Soft Delete
* Repository Pattern

---

## ⚙️ Requisitos Previos

* Java 21
* Maven
* Docker y Docker Compose
* Keycloak

---

## 🧬 Clonar el Proyecto

```bash
git clone https://github.com/tu-org/oriontek.customers.app.git
cd oriontek.customers.app
```

---

## 🐳 Levantar Infraestructura con Docker

```bash
docker-compose up -d
```

Servicios:

* PostgreSQL
* Keycloak

---
## Ejecutar el proyecto

mvn spring-boot:run


## 🔐 Seguridad

* Autenticación por **JWT**
* Manejo uniforme de errores:

```json
{
  "success": false,
  "error": {
    "code": "UNAUTHORIZED",
    "message": "Invalid or expired token"
  }
}
```

---

## 📚 Swagger

Acceso:

```
http://localhost:8080/swagger-ui/index.html
```

---

## 📌 Endpoints

### ➕ Crear Cliente

**POST** `/api/v1/customers`

Request:

```json
{
    "name": " Felix R",
    "lastName": "Sanchez G",
    "email": "Felix@felix.com",
    "identificationNumber": "224-0079987-9",
    "identificationType": 1,
    "addresses": [
        {
            "street": "Calle la jabilla",
            "city": "Saltadilla",
            "country": "RD",
            "principal": true
        }
    ]
}
```

Response 201 Created

---

### 📄 Obtener Clientes (Paginado)

**GET** `/api/v1/customers?page=0&size=10`

Response:

```json
{
    "success": true,
    "value": {
        "content": [
            {
                "id": "30a02167-8167-4366-8391-befab0792ca0",
                "name": "Jahizel Omega",
                "lastName": "Otaño Peña",
                "email": "jahziel0929@gmail.com",
                "identificationNumber": "402-3380715-0",
                "identificationType": 1,
                "addresses": [
                    {
                        "id": "18aa4f1d-d239-42aa-a1a8-303ce163f273",
                        "street": "",
                        "city": "string",
                        "country": "string",
                        "principal": true
                    }
                ]
            },
            {
                "id": "2e492329-973b-4109-9fa8-0b0430a5204c",
                "name": "string",
                "lastName": "string",
                "email": "string@gmail.com",
                "identificationNumber": "string",
                "identificationType": 1,
                "addresses": [
                    {
                        "id": "69b44e85-5580-4c19-9170-30f4456b7816",
                        "street": "string",
                        "city": "string",
                        "country": "string",
                        "principal": true
                    }
                ]
            },
            {
                "id": "0e5f35be-8f0e-4efd-ab65-6fe168f96767",
                "name": "Jahizel Omega",
                "lastName": "Otaño Peña",
                "email": "jahziel20929@gmail.com",
                "identificationNumber": "402-3380715-1",
                "identificationType": 1,
                "addresses": [
                    {
                        "id": "6392db8d-fa1f-4ec2-8e96-bd35645346ee",
                        "street": "string",
                        "city": "string",
                        "country": "string",
                        "principal": false
                    },
                    {
                        "id": "beaa1e9f-13c3-43f5-9800-c54dd6860167",
                        "street": "Segunda Direccion",
                        "city": "PEPE",
                        "country": "RD",
                        "principal": false
                    },
                    {
                        "id": "84364c49-4757-479c-9372-8c989234aa51",
                        "street": "Segunda Direccion",
                        "city": "PEPE",
                        "country": "RD",
                        "principal": true
                    }
                ]
            },
            {
                "id": "bf4098b9-c96a-48cf-be08-fe2661bc2816",
                "name": "Jahizel Omega",
                "lastName": "Otaño Peña",
                "email": "jahziel2092912@gmail.com",
                "identificationNumber": "402-3387715-9",
                "identificationType": 1,
                "addresses": [
                    {
                        "id": "f46fee97-57c8-4124-b43b-c18b8968256c",
                        "street": "string",
                        "city": "string",
                        "country": "string",
                        "principal": true
                    }
                ]
            },
            {
                "id": "7abe2e83-67b7-44ef-887c-9052728d07e1",
                "name": " Felix R",
                "lastName": "Sanchez G",
                "email": "Felix@felix.com",
                "identificationNumber": "224-0079987-9",
                "identificationType": 1,
                "addresses": [
                    {
                        "id": "2d1746d9-5774-4bef-8a67-0f422df01010",
                        "street": "Calle la jabilla",
                        "city": "Saltadilla",
                        "country": "RD",
                        "principal": true
                    }
                ]
            },
            {
                "id": "5cfd9f24-91c9-4a14-b5f4-ac8f7083a750",
                "name": "Ramon",
                "lastName": "German",
                "email": "Felix1@felix.com",
                "identificationNumber": "221-0079987-9",
                "identificationType": 1,
                "addresses": [
                    {
                        "id": "9200fec2-c587-4054-b7ca-8cbb3037d41d",
                        "street": "Segunda Direccion",
                        "city": "PEPE",
                        "country": "RD",
                        "principal": true
                    },
                    {
                        "id": "03d64107-c591-4e01-896e-fafcb4f07eec",
                        "street": "Calle la buya",
                        "city": "Santiago",
                        "country": "Republik D",
                        "principal": false
                    },
                    {
                        "id": "d1c20cd6-a186-4785-8fcc-594a9cd98689",
                        "street": "Calle la puñalá",
                        "city": "Cuero Landia",
                        "country": "Dominike Ripablik",
                        "principal": true
                    }
                ]
            }
        ],
        "pageable": {
            "pageNumber": 0,
            "pageSize": 10,
            "sort": {
                "empty": true,
                "sorted": false,
                "unsorted": true
            },
            "offset": 0,
            "paged": true,
            "unpaged": false
        },
        "last": true,
        "totalElements": 6,
        "totalPages": 1,
        "first": true,
        "size": 10,
        "number": 0,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "numberOfElements": 6,
        "empty": false
    },
    "error": null,
    "failure": false
}
```

---

### 🔍 Obtener Cliente por ID

**GET** `/api/v1/customers/{id}`

Response:

```json
{
    "success": true,
    "value": {
        "id": "5cfd9f24-91c9-4a14-b5f4-ac8f7083a750",
        "name": "Ramon",
        "lastName": "German",
        "email": "Felix1@felix.com",
        "identificationNumber": "221-0079987-9",
        "identificationType": 1,
        "addresses": [
            {
                "id": "9200fec2-c587-4054-b7ca-8cbb3037d41d",
                "street": "Segunda Direccion",
                "city": "PEPE",
                "country": "RD",
                "principal": true
            },
            {
                "id": "03d64107-c591-4e01-896e-fafcb4f07eec",
                "street": "Calle la buya",
                "city": "Santiago",
                "country": "Republik D",
                "principal": false
            },
            {
                "id": "d1c20cd6-a186-4785-8fcc-594a9cd98689",
                "street": "Calle la puñalá",
                "city": "Cuero Landia",
                "country": "Dominike Ripablik",
                "principal": true
            }
        ]
    },
    "error": null,
    "failure": false
}
```

---

### ✏️ Actualizar Cliente

**PUT** `/api/v1/customers/{id}`

Request:

```json
{
  "name": "Juan Updated",
  "lastName": "Perez",
  "email": "juan@email.com",
  "identificationNumber": "123456",
  "identificationType": 1
}
```

---

### ➕ Agregar Dirección

**POST** `/api/v1/customers/{customerId}/addresses`

Request:

```json
{
  "street": "Nueva Calle",
  "city": "SD",
  "country": "RD",
  "principal": true
}
```

---

### ✏️ Actualizar Dirección

**PUT** `/api/v1/customers/{customerId}/addresses/{addressId}`

Request:

```json
{
  "street": "Calle Actualizada",
  "city": "SD",
  "country": "RD",
  "principal": false
}
```

Response:

```json
{
    "success": true,
    "value": null,
    "error": null,
    "failure": false
}
```


---

### 🗑️ Eliminar Cliente (Soft Delete)

**DELETE** `/api/v1/customers/{customerId}`

Response:

```json
{
    "success": true,
    "value": null,
    "error": null,
    "failure": false
}
```

---

### 🗑️ Eliminar Dirección (Soft Delete)

**DELETE** `/api/v1/customers/{customerId}/addresses/{addressId}`

---


Response:

```json
{
    "success": true,
    "value": null,
    "error": null,
    "failure": false
}
```

## 👨‍💻 Autor

**Gerald Antonio Silverio Serrata**

