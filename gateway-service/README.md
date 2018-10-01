# Gateway Server

This is a gateway project using Zuul and swagger. It will expose the service endpoints in order
for them to be easily accessed. 

The project also has a built in feature to load a list of cities in order to 
load data for testing.

### EndPoints ###

Check **adidas-city-itinerary-management** and **adidas-city-itinerary-search** for detailed information 

## Technologies:

- **Consul** Used for discovery service.
- **Zuul** Used to expose and access the services.
- **Swagger** Used to expose documentation

## Build 

- *>mvn clean package* : to build
- *>docker build . -t gateway-server:latest* : to build
