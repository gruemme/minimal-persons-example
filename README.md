# What is this?

This is a minimal Spring Boot example with an endpoint for the person resource and an embedded
in memory H2 database. It uses REST and Spring Data JPA.

## Compilation

To compile the project within its root directory running Windows use:

```cmd
> mvnw.cmd clean install
```

To compile the project within its root directory running Mac or Linux use:

```bash
$ ./mvnw clean install
```

## Running 

To run the project within its root directory running Windows use:

```cmd
> mvnw.cmd spring-boot:run
```

To compile the project within its root directory running Mac or Linux use:

```bash
$ ./mvnw spring-boot:run
```

# Usage

In order to interact with this endpoint a browser or postman can be used. However, here are some
examples with curl running Mac or Linux interacting with the person.

Querying empty list endpoint:
```bash
$ curl -X GET "http://localhost:8080/persons" 

[]
```

Creating on a person:
```bash
$ curl -X POST "http://localhost:8080/persons/" \
    -H "Content-Type: application/json" \
    -d '{"name":"Jane Doe", "height":"168", "sex":"FEMALE"}'
    
{"id":1,"name":"Jane Doe","weight":null,"height":168,"sex":"FEMALE","dateOfBirth":null,"picture":null}
```

Creating another person:
```bash
$ curl -X POST "http://localhost:8080/persons/" \
    -H "Content-Type: application/json" \
    -d '{"name":"John Doe", "height":"181", "weight":"90", "sex":"MALE"}'
    
{"id":2,"name":"John Doe","weight":90,"height":181,"sex":"MALE","dateOfBirth":null,"picture":null}
```

Querying the list endpoint again:
```bash
$ curl -X GET "http://localhost:8080/persons" 

[{"id":1,"name":"Jane Doe","weight":null,"height":168,"sex":"FEMALE","dateOfBirth":null,"picture":null},{"id":2,"name":"John Doe","weight":90,"height":181,"sex":"MALE","dateOfBirth":null,"picture":null}]
```

Querying a single person:
```bash
$ curl -X GET "http://localhost:8080/persons/1" 

{"id":1,"name":"Jane Doe","weight":null,"height":168,"sex":"FEMALE","dateOfBirth":null,"picture":null}
```

Updating a person:
```bash
$ curl -X PUT "http://localhost:8080/persons/1" \
    -H "Content-Type: application/json" \
    -d '{"name":"Jane Doe", "height":"168", "weight": "67", "sex":"FEMALE"}'

{"id":1,"name":"Jane Doe","weight":67,"height":168,"sex":"FEMALE","dateOfBirth":null,"picture":null}
```

Deleting a person:
```bash
$ curl -X DELETE "http://localhost:8080/persons/2"
```

Querying the list endpoint again:
```bash
$ curl -X GET "http://localhost:8080/persons" 

[{"id":1,"name":"Jane Doe","weight":67,"height":168,"sex":"FEMALE","dateOfBirth":null,"picture":null}]
```
