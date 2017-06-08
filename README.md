# spring-rest
Rest API built with [Spring Boot](https://spring.io/) and [MongoDB](https://www.mongodb.com/).

## Requirements
- Java8
- Maven
- Docker

## How to Run with Docker
- Create a new Docker network bridge: `docker network create spring_demo_net`
- Create a docker image with Dockerfile in docker folder: `docker build --tag=spring-demo-1.0 .`
- Start a MongoDB container: `docker run --name spring-demo-mongo --network=spring_demo_net mongo:3.4`. You must give this container exactly this name in order to work.
- Start Spring Boot container: `docker run --name spring-demo --network=spring_demo_net -p 8080:8080  spring-demo-1.0`

## How to use
- To list all users in database, send a GET request to `localhost:8080/users`
- To get one user, send a GET request to `localhost:8080/users/user-id` with user id
- To insert a user, send a json object as POST request to the `localhost:8080/users`. Sent json must be constructed like this: `{"first_name":"FIRST_NAME","last_name":"LAST_NAME","salary":SALARY}`. Database only supports this format.
- To update a user, send a json object as PUT request with user's id to `localhost:8080/users/user-id`. Sent json can contain only one field as well as all three fields. For example if one wants to update a users salary, `{"salary":NEW_AMOUNT}` can be sent.
- To delete a user, send a DELETE request to the `localhost:8080/users/user-id` with user's id.
- To delete all users, send a DELETE request to the `localhost:8080/users`.
