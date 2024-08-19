# E-commerce Platform

## Project Overview
This e-commerce platform is a robust backend solution designed to manage products, orders, and user accounts. The application includes features for user authentication and authorization, product and category management, and order processing. It is built using Spring Boot and secured with Spring Security.

### Key Features
- **User Authentication and Authorization:**
  - **JWT Implementation:** Role-based access control with two roles: USER and ADMIN.
    - **USER:** Can view products and place orders.
    - **ADMIN:** Can manage products, view orders, and handle administrative tasks.

- **Product Management:**
  - CRUD operations for products including fields such as id, name, description, price, and category.

- **Order Processing:**
  - CRUD operations for orders with fields such as id, userId, productId, quantity, and orderDate.

- **Category Management:**
  - CRUD operations for product categories with fields such as id and name.

## Technology Stack
- **Language:** Java 17
- **Backend Framework:** Spring Boot
- **Database:** MySQL
- **Security:** Spring Security for authentication and authorization
- **Build Tool:** Maven
- **Testing Tools:** Postman for API testing

## Project Structure

- **Location:** `/backend`
- **Components:**
  - `config`: Configuration classes for Spring Security and other settings.
  - `controller`: REST controllers for handling API requests.
  - `dto`: Data Transfer Objects for API communication.
  - `model`: Entity classes representing database tables.
  - `repository`: JPA repositories for database interactions.
  - `security`: Security configuration classes.
  - `service`: Business logic and service classes.
  - `EcommerceApplication.java`: Main application class.

## Installation and Setup

### Backend Setup
1. **Navigate to the Backend Directory:**
   ```bash
   cd backend
