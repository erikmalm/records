# Employee REST API
 
This is a REST API for managing a list of employees. It provides endpoints for adding, retrieving, and deleting employees.

## Getting started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
- Java 8 or higher
- Maven


### Installing
Clone the repository and navigate to the project directory:
```
git clone https://github.com/your-username/employee-rest-api.git
cd employee-rest-api
```
Build the project using Maven:
```
mvn clean install
```
Run the application:
```
mvn spring-boot:run
```
The API will be available at http://localhost:8080/api.

## API
The API has the following endpoints:

### GET /api
Retrieves a list of all employees.

Example
```
curl -X GET http://localhost:8080/api
```

```
[
  {
    "firstName": "John",
    "lastName": "Doe",
    "email": "johndoe@example.com"
  },
  {
    "firstName": "Jane",
    "lastName": "Doe",
    "email": "janedoe@example.com"
  }
]
```

### GET /api/{email}

Retrieves the employee with the specified email address.

Example
```
curl -X GET http://localhost:8080/api/johndoe@example.com
```
```
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "johndoe@example.com"
}
```
### POST /api

Creates a new employee. The request body should be a JSON object with the following properties:

- firstName: the first name of the employee (required)
- lastName: the last name of the employee (required)
- email: the email of the employee (required)

Example
```
curl -X POST http://localhost:8080/api -d '{"firstName": "Jim", "lastName": "Smith", "email": "jimsmith@example.com"}'
```
```
{
  "firstName": "Jim",
  "lastName": "Smith",
  "email": "jimsmith@example.com"
}
```
### DELETE /api/{email}

Deletes the employee with the specified email address.

Example
```
curl -X DELETE http://localhost:8080/api/jimsmith@example.com
```

## Frontend
The project also includes a React frontend for interacting with the API. To run the frontend, navigate to the frontend directory and run the following commands:
```
npm install
npm start
```
The frontend will be available at http://localhost:3000.

