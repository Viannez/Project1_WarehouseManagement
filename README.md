# Mystery Box Warehouses (Project 1 & Project 2)
Warehouse web-app that fascilitates administrator to view, add, remove, and alter warehouse entities. Theming is mystery boxes, where miscellaneous bundles can be sold depending on size. User can:
- View and sort list of all warehouses by ID and capacity.
- View and sort list of all products by ID and price.
- Create and delete warehouse from warehouse list, cannot have two warehouses with the same name.
- Create and delete product from product list, includes drop down for size category selection.
- View individual warehouse details magnifying glass button, routed '/warehouse/{id}'
  - Update warehouse details with pen button. Empty inputs will just keep the fields' previous values. Capacity cannot be lower than total products stocked in the warehouse.
  - Add new product inventory to warehouse with drop down of all products, cannot add product already in warehouse.
  - View what products exist in each warehouse and the stock of the product.
  - Edit stock of each product listed in warehouse with pen button.
  - Delete product inventory from that specific warehouse..
  - Go to view individual products' details with door button
- View individual product details with magnifying glass button, routed '/product/{id}'
  - Update product details with pen button, includes drop down for size category selection
  - View what warehouses the product is in and the stock in each warehouse.
  - Delete product inventory from that specific warehouse.
  - Go to view individual warehouses' details with door button

# Links for Project 2:
## AWS
[Link to frontend deployed on AWS](http://mystery-box-warehouses-frontend.s3-website-us-east-1.amazonaws.com/warehouse) 

[Link to RDS database deployed on AWS](http://mystery-box-warehouses-env.eba-mmmmraim.us-east-1.elasticbeanstalk.com/warehouse)
#### S3 buckets
- mystery-box-warehouses-backend
- mystery-box-warehouses-frontend

#### EC2
- Instance: mystery-box-warehouses-jenkins

Elastic Beanstalk
- Application: mystery-box-warehouses
- Environment: Mystery-box-warehouses-env

#### RDS
- Mysteryboxwarehouses-db instance
## Jenkins
[Jenkins](http://3.93.172.113:8080/job/mysterybox-warehouse-pipeline/) pipeline using AWS EC2 instance to deploy code changes to AWS automatically, perform tests, and generate reports. Performance test trends based on [JMeter test plan](testing/src/test/jmeter/Mysterybox-warehouse-JMeter.jmx)
![image](https://github.com/user-attachments/assets/b8d2c5f2-0eec-4c28-94f9-9e45ada6dd9b)
## SonarCloud
[SonarCloud](https://sonarcloud.io/organizations/viannez/projects) link that provides information on backend & frontend coverage, quality gates, and more
![image](https://github.com/user-attachments/assets/833535cd-cb6e-4a25-833b-8945454eedfb)
## Cucumber Report
[Cucumber Report](https://reports.cucumber.io/report-collections/d7789cc2-cacf-4683-b7a6-cc5ebea41dfe) provides information on BDD tests using Cucumber + Selenium.
Every Jenkins build has three cucumber reports
![image](https://github.com/user-attachments/assets/4ca59393-8888-4d5e-934a-a4e1d08edba4)

## Testing Structure
### Testing directory
- Cucumber and Selenium [Step Defintions and Page Object Models](testing/src/test/java/com/skillstorm)
- Cucumber [Feature Files](testing/src/test/resources/com/skillstorm/active)
- Uses [Jmeter Maven Plugin](https://github.com/jmeter-maven-plugin/jmeter-maven-plugin) with [JMeter test plan](testing/src/test/jmeter/Mysterybox-warehouse-JMeter.jmx)
### Warehouse-frontend
- Jest testing [Coverage](warehouse-frontend/coverage)
### Warehouse-management
- Mockito [Unit Tests](https://github.com/Viannez/Project1_WarehouseManagement/tree/main/warehouse-management/src/test/java/com/skillstorm/warehouse_management)
- TestNG [Suite Report](warehouse-management/test-output/html/suite0_test0_results.html)
- Used [Jacoco-Maven Plugin](https://www.eclemma.org/jacoco/trunk/doc/maven.html) to report coverage of Mockito unit tests

### Comprehensive list of tools used for testing:
- Selenium
- Cucumber
- TestNG
- Jest
- Mockito
- AWS
- Jenkins
- JaCoCo
- Burp Suite
- SonarCloud
- Jmeter

# Instructions for Project 1
## How to Run:
- Clone repository
- Run /Notes/create_entities.sql in pg Admin 4
- Go into /warehouse-backend:
```terminal
mvn install
```
- Run spring boot maven app warehouse-management from apps
- Go into /warehouse-frontend:
```terminal
npm install
```
- Run warehouse-frontend:
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
- Cannot make a warehouse capacity smaller than its current total product stock inventory
- Cannot overfill warehouse by editing a product's stock
- Cannot add duplicate product records in warehouse
- Cannot name two warehouses the same name
- Price of product cannot be below 0




