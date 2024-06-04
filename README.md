# ing-store-api
API that acts as a store management tool

#### Stack: Java 17, Spring Boot, PostgreSQL, JUnit, Mockito, JWT

ing-store-api acts as a store management tool, having as a main purpose handling Product entities.

A Product entity consists of: name, price and currency. 
ID of every product is handled automatically by the PostgreSQL database by autoincrement.

The implemented functionalities are replication the CRUD operations: adding a product (Create),
searching a product by id or name (Read), updating a product price (Update) and removing
an existing product from the Database, based on its id (Delete).

#### User authentication and role based access mechanism consists of two entities: USER and ADMIN.
As stated by the security policy, the USER can access the GET endpoints, hence is able to READ product
related information from the database. Every registered user is able to receive a list of all the existing 
products and information about a specific product based on its id or name.

On the other hand, in addition to the USER privileges, the ADMIN can add, update and delete products.

#### Login procedure
The AuthenticationController exposes two endpoints:
##### POST /v1/auth/register
Offers the possibility to register a new user. Passwords are encrypted.
##### POST /v1/auth/login
Checks credentials of the existing user and generates in response a JWT Token.

After introducing your credentials, the generated JWT Token, which is available fo 1 Hour, will be introduced
in the Authorization Header of the Product endpoints, in order to operate on the database. 

##### Authorization type: Bearer

##### Error handling
The API provides a customized error handling mechanism, some custom errors depending on the scenario are thrown
and mapped to a ErrorDetail model which is exposed as a response. 

Covered error codes: Bad Request, Unauthorized, Not Found, Internal Server Error.

#### Product endpoints
##### POST /v1/products - adding a new product
##### GET /v1/products - retrieving a list of existing products
##### GET /v1/products/{id} - retrieve product details based on id
##### GET /v1/products/{name} - retrieve product details based on its name
##### PUT /v1/products/{id}/price - updating a product price
##### DELETE /v1/products/delete/{id} - removing a product from the database

#### Start the application
##### 1. Clone repository: https://github.com/mihaimiloiu01/ing-store-api.git
##### 2. Make sure you have all the dependencies installed and able to run: Java 17, PosgreSQL, Postman for endpoint testing

