# spring-rest
A simple REST API using [Spring Boot](https://spring.io/) and [MongoDB](https://www.mongodb.com/).

## Requirements
- Java8
- Maven
- Docker

## How to Build
- Run the following command in the project folder: `mvn clean package dockerfile:build`. This will build the project and create a new docker image with name `fthbrmnby/spring-rest-demo`.
- Create a new Docker network bridge: `docker network create {your-network-name}`
- Start a MongoDB container. Be sure to give your mongo container a name, you'll need it: `docker run --name {your-db-name}  --network {your-network-name} mongo`.
- Start the Rest API container: `docker run --network {your-network-name} -p 8080:8080 -e SPRING_DATA_MONGODB_HOST={your-db-name}  fthbrmnby/spring-rest-demo`

## Endpoints
- Create a new entry in database

`[POST] 8080/users/create`

Sample Request
```javascript
{
    "firstName": "Walter",
    "lastName": "Curtz",
    "salary": 5000
}
```


- Get the user entry with the given id

`[GET] 8080/users?userId={user-id}`


- List all the entries in database

`[GET] 8080/users/all` 

- Delete the user entry with the given id

`[DELETE] 8080/users?userId={user-id}`