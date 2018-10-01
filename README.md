# ItineraryChallenge

Solution for the itinerary challenge.

## Technologies Used

Bellow is the list of the used technologies for this project.

- Docker: Used to run applications in containers and ease their the deploy and scaling.
- Docker Compose: Used to build multiple docker applications using a single command.
- SpringBoot: Used to create the micro-services
- Consul: Used for service discovery.
- Feign Client: Used to fetch data and to handle the circuit break pattern (uses hystrix and ribbon).
- Swagger: Used to provide information to navigate the site's REST interfaces dynamically by including hypermedia links with the responses.
- Zuul: Used to expose the microservices with swagger ui ennabled.
- Neo4: Graph NoSQL database used to store the cities and itineraries. 
- Java8: The micro services were created to run using java8

## Project modules

- **adidas-city-itinerary-search**
- **adidas-city-itinerary-management**
- **gateway-server**

## Documentation (Swagger)

Run the project and open the following url:
- http://localhost:8080

The command will load swagger-ui.

You may navigate through the two micro services specs:
http://localhost:8080/swagger-ui.html?urls.primaryName=adidas-city-itinerary-search
http://localhost:8080/swagger-ui.html?urls.primaryName=adidas-city-itinerary-management

##### How to test

Execute docker-compose up -d in order to load the applications.

There is a routine that will load data into neo4j when you start the application.
Four cities will be loaded and itineraries will be created between them.

The search micro-service fetchs all the connections from a chosen city through an rest endpoint and it will display
the information based on number of connected nodes and travel time.

You may check the connections on neo4j:
http://localhost:7474/browser/

After that you can call search service to check the shortest route and time between the services.

```
curl -X GET "http://localhost:8080/adidas-city-itinerary-search/api/search/connections/shortest/distance/Barcelona/Zaragoza" -H "accept: application/json"
curl -X GET "http://localhost:8080/adidas-city-itinerary-search/api/search/connections/shortest/path/Barcelona/Zaragoza" -H "accept: application/json"
```

## TODO 
- Enable security by implementing a oauth2 service and enabling then in the ms projects (the ms' are already prepared to use security, I was just in doubt if which authentication I could add to the project (OAUTH2 / JWT), in the end I almost ran out of time in order to implement this so I left it for a future release). 
- Further enhance the validations in order to avoid unexpected exceptions.
- Add CircleCI for continuous integration.
- Add more unit tests in order to cover more code.
- Create a slide for the pipeline proposal. I was thinking of using a canary-release pipeline aproach. (https://saucelabs.com/blog/using-canary-release-pipelines-to-achieve-continuous-testing).
