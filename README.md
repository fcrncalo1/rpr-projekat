# E-Commerce Mobile Shop - README

Welcome to the E-Commerce Mobile Shop project! This application serves as an online platform for buying and selling mobile products. The project is built around four primary tables: Users, Products, Orders, and Order Items. The application supports two main user roles: Admin and Guest.

## Table of Contents
- [User Roles](#user-roles)
- [Tables](#tables)
- [Functionality](#functionality)
- [Getting Started](#getting-started)

## User Roles

### Admin
Administrators have privileged access to the system. They can perform the following actions:
- Add, update, or delete users
- Manage products (add, update, delete)
- Delete orders

### Guest
Guest users have limited functionality but can perform the following actions:
- Browse products
- Add products to their cart
- Create orders for products in the cart
- Make purchases

## Tables

### Users
The "Users" table contains information about registered users.

### Products
The "Products" table lists the available mobile products for purchase. Each product entry includes details such as the product name, description, price, and quantity.

### Orders
The "Orders" table records order information, such as the user who placed the order and the date the order was created.

### Order Items
The "Order Items" table connects specific products to a particular order, indicating which products were part of a given order and in what quantity.

## Functionality

- **User Login**: Users can log in with appropriate authentication.

- **Product Browsing**: Guests can browse through available mobile products.

- **Cart Management**: Guests can add products to their cart while browsing.

- **Order Placement**: Guests can place orders, confirming the products they want to purchase.

- **Admin Privileges**: Admin users have additional rights to manage users, products, and orders.

## Getting Started

1. Clone the repository.
2. Set up the necessary database tables using the provided SQL scripts.
3. Configure the database connection in the application settings.
4. Build and run the application on your computer.
