# POC Integration Test with Rest Assured

## Tech

Techs used in this project:

- [Java8] - For the backend
- [Maven 3.6.3] - To build and management dependecies
- [Junit5] - Junit 5 to integration test and unit test
- [RestAssured] - RestAssured to integration test
- [Spring] - Framework used spring/spring-boot
- [Mongo] - Database NoSql
- [Docker] - Docker to use mongodb
- [Docker-compose] - Docker compose used to run mongodb from yml file

## sites to help 


| Site | README |
| ------ | ------ |
| Jsonschema | https://jsonschema.net/home |
| RestAssured | https://rest-assured.io |

## Executition

Docker compose to up mongo db in detached mode.

```sh
docker-compose -f docker/mongo.yml up -d
```

Running application

```sh
mvn spring-boot:run
```

## Tests

Executing unit tests

```sh
mvn clean test
```
Executing integration tests

```sh
mvn clean verify