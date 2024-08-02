# Project1_Warehouse_Management
Warehouse web-app that fascilitates administrator to view, add, remove, and alter warehouse entities.

## How to Run:
- Clone repository
- First run spring boot maven app warehouse-management
- run warehouse-frontend:
```terminal
npm run dev
```

## Java with Spring Boot
Created Spring Boot with Maven project in Java to build backend.

### Dependencies: 
- Spring Boot JPA
- Spring Boot Web
- Spring Boot Validation
- Spring Boot Test
- Spring Boot DevTools
- PostgreSQL
### Spring Boot JPA setup
Includes links to where they are located in the repository
#### Controllers
[Controllers](warehouse-management/src/main/java/com/skillstorm/warehouse_management/controllers) help manage requests and route them to the correct services
#### Models
[Models](warehouse-management/src/main/java/com/skillstorm/warehouse_management/models) defines the entitities of the Database.
#### Repositories
[Repositories](warehouse-management/src/main/java/com/skillstorm/warehouse_management/repositories) stores entity data and can have basic API for CRUD operation.
#### Services
[Services](warehouse-management/src/main/java/com/skillstorm/warehouse_management/services) defines the business logic that works with the entity data.
## PostgreSQL
Created database with entities in PostgreSQL to manage server data.
- Port: 5432
- Database Name: warehouse_management
### Entities
- Warehouse
- Product
- ProductInventory
- Category

[SQL Script to create entities](Notes/create_entities.sql) 

## React
UI framework that helps build the user interface with components and functional programming. 
### Often used Node.js modules
```terminal
npm install react-router-dom
npm install react-boostrap
```
- react-router-dom: routes components by router path
- react-bootstrap: UI library with pre-built components
### Component setup
The warehouse management components are grouped by relations to one another.
#### Categories
[Categories](warehouse-frontend/src/categories) have been separated in the main component types built for the warehouse web-app interface
- [Navigation](warehouse-frontend/src/categories/Navigation): navigation bar
- [Product](warehouse-frontend/src/categories/Product): product specific components
- [Warehouse](warehouse-frontend/src/categories/Warehouse): warehouse specific components
- [styles](warehouse-frontend/src/categories/styles): broadly defines style of various components
- [util](warehouse-frontend/src/categories/util): components made for GET requests, can be by '/entity/id' or '/entity'
#### Pages
[Pages](warehouse-frontend/src/pages) have the important components are are routed by the react-router-dom

#### Important constraints
- Cannot make a warehouse capacity smaller than it's current total inventory
- Cannot overfill warehouse
- Cannot make duplicate product records in warehouse
- Cannot name two warehouses the same name
- Price of product cannot be below 0




