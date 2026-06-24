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
## My personal challenges
At the beginning of this academy, I struggled a lot with programming. My first capstone was very challenging, but I was able to complete it and pass.

For my second capstone, I started asking more questions, studying more, and spending more time practicing my programming skills. That helped strengthen my knowledge and made me feel more prepared.

Now, in this final capstone, one of my biggest challenges was learning how to work with an existing Spring Boot project instead of building everything from scratch. I had to understand how the controllers, services, repositories, models, and security classes worked together before adding new features.

Another challenge was debugging API status codes like `400 Bad Request`, `401 Unauthorized`, `403 Forbidden`, `404 Not Found`, and `500 Internal Server Error`. This helped me understand authentication, authorization, request bodies, and how to return the correct response from the backend.

For this capstone, I was able to work with more confidence. I learned how to ask better questions, use the right support, and keep debugging until the application worked. This project showed me how much you can learn when you fully commit yourself to the process.
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

## Next Time
Next time, I would like to add more features, improve the efficiency of my code, and continue building projects that can become real world applications published and used by others.

