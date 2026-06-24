# Marbin's Video Game Online Store 🛒

## Description

This project is a Spring Boot backend API for an e-commerce website. The API supports product browsing, category management, shopping cart features, user profiles, and checkout/order creation.

The project uses a layered architecture with controllers, services, repositories, and models. The backend connects to a MySQL database and uses JWT authentication for protected endpoints.

## Features

- User registration and login
- JWT authentication
- View and search products
- View categories and products by category
- Admin create, update, and delete categories
- Admin create, update, and delete products
- Shopping cart for logged-in users
- User profile view and update
- Checkout and order creation
## Technologies Used

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- JWT
- Insomnia
- IntelliJ IDEA
- 
## My personal challenges
One of my biggest challenges was learning how to work with an existing Spring Boot project instead of building everything from scratch. I had to understand how the controllers, services, repositories, models, and security classes worked together before adding new features.

Another challenge was debugging API status codes like `401 Unauthorized`, `403 Forbidden`, `404 Not Found`, and `500 Internal Server Error`. This helped me understand authentication, authorization, and how to return the correct response from the backend.

I also had to practice tracing bugs through different layers of the application. For example, fixing the product stock update bug required checking the controller, service, model, and database fields.



