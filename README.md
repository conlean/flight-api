
This is an example Flight API built on top of Spring Boot + HSQLDB as part of a challenge in the recruiting process.

## Requirements

1. Java 11

2. Maven 

3. Spring Boot


## Steps to Setup

**1. Download the zip file **

**2. Run the app using maven**

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET localhost:8080 (Welcome, App running)

    GET localhost:8080/api/flight/   (All items)
    
    POST localhost:8080/api/flight/ (create new todo)
    
    
    GET localhost:8080/api/todos/search? (Get flight by Origin, Destination and Weight)

You can test them using swagger or any other rest client.

    http://localhost:8080/swagger-ui.html#/

## Key points to see as an example

+ API implementation 
+ Testing with mockito

## Challenge nota

A company receives for each airline a standardized list containing:

Airline Code
Flight Number
Origin Airport
Destination Airport
Flight Time
Price by Kg

When a company has booked more than 10000Kg in the current year gets a
5% discount in the fares.
Please build a Web Service to get a flight for a given Origin,
Destination and Weight in Kg having the ability to choose the cheapest
one or the fastest.
Notes:
* You DO NOT need to implement any UI for this exercise.
* You do not need to write code to load data.
* You have to deliver the data and test cases to run it.
* Source code and any documentation should be written in English.


