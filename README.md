# Sales Management Backend

A Spring Boot REST API for managing products, sales, and users with MySQL.

---

## Tech Stack

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA (Hibernate)
- Spring Security
- MySQL 8+
- Lombok
- Maven

---

## Setup Instructions

### 1. Create MySQL Database

```sql
CREATE DATABASE sales_db;
```

### 2. Configure Database Credentials

Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sales_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3. Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

The server starts at **http://localhost:8080**

Sample data (users, products, sales) is auto-loaded from `data.sql` on first run.

---

## Default Credentials (from data.sql)

| Username    | Password    | Role      |
|-------------|-------------|-----------|
| admin       | password123 | ADMIN     |
| john.doe    | password123 | SALES_REP |
| jane.smith  | password123 | SALES_REP |
| mike.mgr    | password123 | MANAGER   |
| sara.rep    | password123 | SALES_REP |

---

## REST API Reference

### Users — `/api/users`

| Method | Endpoint               | Description          |
|--------|------------------------|----------------------|
| GET    | `/api/users`           | Get all users        |
| GET    | `/api/users/{id}`      | Get user by ID       |
| GET    | `/api/users/role/{role}` | Get users by role  |
| POST   | `/api/users`           | Create user          |
| PUT    | `/api/users/{id}`      | Update user          |
| PATCH  | `/api/users/{id}/deactivate` | Deactivate user |
| DELETE | `/api/users/{id}`      | Delete user          |

**Create/Update User Request Body:**
```json
{
  "username": "john.doe",
  "password": "secret123",
  "email": "john@example.com",
  "role": "SALES_REP",
  "fullName": "John Doe"
}
```

---

### Products — `/api/products`

| Method | Endpoint                        | Description              |
|--------|---------------------------------|--------------------------|
| GET    | `/api/products`                 | Get all products         |
| GET    | `/api/products/active`          | Get active products      |
| GET    | `/api/products/{id}`            | Get product by ID        |
| GET    | `/api/products/search?name=X`   | Search by name           |
| GET    | `/api/products/category/{cat}`  | Filter by category       |
| GET    | `/api/products/categories`      | List all categories      |
| GET    | `/api/products/low-stock?threshold=10` | Low stock alert   |
| POST   | `/api/products`                 | Create product           |
| PUT    | `/api/products/{id}`            | Update product           |
| PATCH  | `/api/products/{id}/stock?quantity=5` | Adjust stock      |
| DELETE | `/api/products/{id}`            | Soft-delete product      |

**Create/Update Product Request Body:**
```json
{
  "name": "Laptop Pro 15",
  "description": "High-performance laptop",
  "price": 1299.99,
  "stockQuantity": 50,
  "category": "Electronics",
  "sku": "SKU-LAPTOP-001"
}
```

---

### Sales — `/api/sales`

| Method | Endpoint                                  | Description              |
|--------|-------------------------------------------|--------------------------|
| GET    | `/api/sales`                              | Get all sales            |
| GET    | `/api/sales/{id}`                         | Get sale by ID           |
| GET    | `/api/sales/user/{userId}`                | Sales by user            |
| GET    | `/api/sales/product/{productId}`          | Sales by product         |
| GET    | `/api/sales/date-range?start=&end=`       | Sales in date range      |
| GET    | `/api/sales/dashboard`                    | Dashboard statistics     |
| POST   | `/api/sales`                              | Create sale              |
| PATCH  | `/api/sales/{id}/status`                  | Update sale status       |
| DELETE | `/api/sales/{id}`                         | Delete sale              |

**Create Sale Request Body:**
```json
{
  "productId": 1,
  "userId": 2,
  "quantity": 3,
  "customerName": "Alice Brown",
  "customerEmail": "alice@example.com",
  "notes": "Bulk order"
}
```

**Update Status Request Body:**
```json
{ "status": "CANCELLED" }
```

**Valid Statuses:** `PENDING`, `COMPLETED`, `CANCELLED`, `REFUNDED`

---

### Dashboard Response Example

```json
{
  "totalRevenue": 15240.50,
  "totalSales": 42,
  "totalProducts": 10,
  "totalUsers": 5,
  "revenueThisMonth": 3820.75,
  "salesThisMonth": 9
}
```

---

## Project Structure

```
src/main/java/com/sales/
├── SalesApplication.java
├── controller/
│   ├── ProductController.java
│   ├── SalesController.java
│   └── UserController.java
├── service/
│   ├── ProductService.java
│   ├── SalesService.java
│   └── UserService.java
├── repository/
│   ├── ProductRepository.java
│   ├── SalesRepository.java
│   └── UserRepository.java
├── entity/
│   ├── Product.java
│   ├── Sale.java
│   └── User.java
├── dto/
│   └── SalesDtos.java
└── config/
    ├── SecurityConfig.java
    ├── GlobalExceptionHandler.java
    └── DataInitializer.java
```
