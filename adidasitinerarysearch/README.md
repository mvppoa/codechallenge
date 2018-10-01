# Adidas City Itinerary Search Service

This project is created to consume itineraries and expose the their shortest path and distance 
a different service in order to serve itineraries. 

The project also has a built in feature to load a list of cities in order to 
load data for testing.

### EndPoints ###

| Service       | EndPoint                                                                 | Method | Description                                      |
| ------------- | ------------------------------------------------------------------------ | :-----:| ------------------------------------------------ |
| Itinerary     | /api/search/connections/shortest/path/{originCity}/{destinyCity}      | GET    | Return all itineraries given an origin city      |
| Itinerary     | /api/search/connections/shortest/distance/{originCity}/{destinyCity}  | GET    | Return created itinerary between cities          |

## Technologies:

- **Consul** is used for discovery service.
- **Feign** is used to access management service.
- **Swagger** Used to expose documentation

## Build 

- *>mvn clean package* : to build
- *>docker build . -t adidas-city-itinerary-search:latest* : to build

## TODO

- Add unit tests.
- Add CI using CircleCI. 
