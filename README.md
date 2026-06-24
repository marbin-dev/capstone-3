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

## Code I Am Most Proud Of

The code I am most proud of is my `ShoppingCartController`. I wrote the `GET`, `POST`, `PUT`, and `DELETE` methods to manage a user's shopping cart.

I am proud of this code because it helped me understand how REST APIs work in Spring Boot. Each method uses the logged-in user's information, finds their user id, calls the service layer, and returns the updated shopping cart.

## What I Learned

In this capstone, I learned how to work with an existing Spring Boot project and add new backend features without starting from scratch.

I practiced:

- Creating REST controller methods with `GET`, `POST`, `PUT`, and `DELETE`
- Using services to handle business logic
- Using repositories to work with the database
- Working with MySQL tables and JPA models
- Debugging HTTP status codes like `400`, `401`, `403`, `404`, and `500`
- Testing API endpoints with Insomnia
- Building a shopping cart, profile update, and checkout feature

This project helped me understand how the different layers of a backend application work together: controllers receive requests, services handle the logic, repositories talk to the database, and models represent the data.

