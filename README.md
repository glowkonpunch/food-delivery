# Food‑Delivery

A minimal full‑stack food ordering & delivery demo built with **Spring Boot (backend)**, **Angular (frontend)**, and **MySQL (database)**.

---

## ✨ Features

* **JWT authentication** with role‑based access (CUSTOMER, DELIVERY, ADMIN)
* View food menu (public)
* Customer cart & order placement
* Delivery dashboard for pending orders
* Admin management for foods & users

---

## 🗂 Project structure

```
food-delivery-project/
├── food-delivery-backend/   # Spring Boot API
└── food-delivery-frontend/  # Angular SPA
```

---

## 🚀 Quick start

### Prerequisites

| Tool        | Version |
| ----------- | ------- |
| JDK         | 17+     |
| Maven       | 3.6+    |
| Node        | 18+     |
| npm         | 9+      |
| Angular CLI | 17+     |
| MySQL       | 8.x     |

### 1. Database

Make sure MySQL is running on `localhost:3306` with a root user and empty password (or edit `application.properties`).

### 2. Backend

```bash
cd food-delivery-backend
mvn spring-boot:run
```

The first run creates the **food\_delivery** schema and seeds sample data from `src/main/resources/data.sql`.

### 3. Frontend

```bash
cd food-delivery-frontend
npm install
npm start
```

Angular dev server will run on [http://localhost:4200](http://localhost:4200)

### Sample Accounts

| Role     | Username | Password |
| -------- | -------- | -------- |
| Admin    | admin    | admin    |
| Customer | testuser | customer |
| Delivery | courier1 | delivery |

---

## 📖 REST API (brief)

* `POST /api/auth/register` – Register
* `POST /api/auth/login` – Login and receive JWT
* `GET /api/foods` – List all foods (public)
* `POST /api/foods` – Add food (ADMIN only)
* `POST /api/orders/create` – Place new order (CUSTOMER)
* `GET /api/orders/pending` – List new orders (DELIVERY)
* `POST /api/orders/{id}/status?status=DELIVERED` – Update order status (DELIVERY)

For full documentation, see the "Food‑Delivery – Full Documentation" file in `/docs` or the included PDF.

---

## 🏗 Build for production

```bash
# Backend
cd food-delivery-backend
mvn clean package

# Frontend
cd food-delivery-frontend
ng build --prod
```

You can serve the JAR with Spring Boot and place the Angular `dist/` contents inside the `src/main/resources/static/` folder for production.

---

## 🧭 Roadmap / Ideas

* Stripe / PayPal payment integration
* Food image upload (S3 or local folder)
* Real-time WebSocket status updates
* Docker Compose setup
* Unit and E2E tests (JUnit 5, Cypress)

---

## 👥 Contributors

* **Alexandar Tomov** — Backend, Database, Frontend
* **Petar Ivanov** — Documentation, Database, Backend

---

## 🤝 License

Demo code — MIT. Feel free to adapt and extend.
