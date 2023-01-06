# Api Restful, User-Demo. Creation and Authenticate
This project demonstrates the implementation of a RESTful API for user creation and authentication with h2 database, as well as integrated security using Spring Boot 3.0.1 and JSON Web Tokens (JWT)

![Diagrama User-Demo](Diagram-user-demo.png)
## Features
* User creation and login (authentication) 
* JWT authorization
* Authorization with Spring Security
* Swagger

## Technologies:
```
• Spring Boot 3.0.1
• Java 17
• Spring Security 6
• Open API 3
• H2 Database
• Gradle - Kotlin
• JSON Web Tokens (JWT)
``` 

## Getting Started
You will need to have the following installed on your local machine:
* JDK 17+
* Maven
* Gardle

To build and run the project, follow these steps:

* Clone the repository: `https://github.com/omar-napoleon/api-demo-user`
* Navigate to the project directory: cd api-demo-user
```
* Build the project: gradle build
* Option 1:
  • Run the project: gradle bootRun
* Option2 (Dockerfile):
  • Build: docker build -t user-demo .
  • Run: docker run -p8080:8080 user-demo
``` 
-> The application will be available at http://localhost:8080.

## Swagger
```
• http://localhost:8080/swagger-ui/index.html#/
• cd api-demo-user/openapi.yaml
```
### POST User creation 
#### localhost:8080/api/v1/user/register
```csharp
curl --location --request POST 'localhost:8080/api/v1/user/register' \
--header 'Content-Type: application/json' \
--data-raw '{
 "name": "Juan Rodriguez",
 "email": "juanr@odriguez.org",
 "password": "Bci00001",
 "phones": [
{

 "number": "1234567",
 "citycode": "1",
 "countrycode": "57"
 } ]
}
'
``` 
### POST User Authenticate
#### localhost:8080/api/v1/user/authenticate
```csharp
curl --location --request POST 'localhost:8080/api/v1/user/authenticate' \
--header 'Content-Type: application/json' \
--data-raw '{
 "email": "juanr@odriguez.org",
 "password": "Bci00001"
}'
``` 
### GET User Demo message
#### localhost:8080/api/v1/demo
```csharp
curl --location --request GET 'http://localhost:8080/api/v1/demo' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuckBvZHJpZ3Vlei5vcmciLCJpYXQiOjE2NzI5NzY2OTMsImV4cCI6MTY3Mjk4MDI5M30.rSt8y9sz-e7kchIBevHIuPOj0Qyx8ustq1BSvJRNY5Y'
``` 
### GET User
#### localhost:8080/api/v1/demo/:id
```csharp
curl --location --request GET 'http://localhost:8080/api/v1/demo/a75354e1-4153-42a7-b669-a0236046f1d1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqdWFuckBvZHJpZ3Vlei5vcmciLCJpYXQiOjE2NzI5NzY2OTMsImV4cCI6MTY3Mjk4MDI5M30.rSt8y9sz-e7kchIBevHIuPOj0Qyx8ustq1BSvJRNY5Y'
``` 
## Postman Collection
```
• cd api-demo-user/demo-user-jwt.postman_collection.json
```