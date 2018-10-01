# Adidas City Itinerary Management Service

This project is created to expose itineraries data to be consumed by 
a different service in order to serve itineraries. This project also has
basic features such as save and search in order to ease the tests.

The project also has a built in feature to load a list of cities in order to 
load data for testing.

### EndPoints ###

| Service       | EndPoint                      | Method | Description                                      |
| ------------- | ----------------------------- | :-----:| ------------------------------------------------ |
| City          | /api/city/add                 | POST   | Add a single city to the database                |
| City          | /api/city/{id}                | GET    | Return city details                              |
| Itinerary     | /api/itinerary/city/{id}      | GET    | Return all itineraries given an origin city      |
| Itinerary     | /api/itinerary/join           | POST   | Return created itinerary between cities          |

## Technologies:

- **Consul** Used for discovery service.
- **Neo4J** Used for graphHelp data persistence
- **Swagger** Used to document and expose the endpoints 

## Build 

- *>mvn clean package* : to build
- *>docker build . -t adidas-city-itinerary-management:latest* : to build
