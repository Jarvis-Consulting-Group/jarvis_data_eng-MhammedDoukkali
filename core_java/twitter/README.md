# Introduction
This command line tool is a Twitter application that leverages Twitter's REST API for functionalities like posting, displaying, and deleting tweets. It utilizes the Apache HTTPComponents library to interact with the Twitter REST API using OAuth 1.0. Object dependency management is handled by Spring Boot, while JUnit is employed for unit and integration testing. The project lifecycle is maintained using Maven, and the application image is distributed through Docker Hub using Docker.

# Quick Start

```
# Package locally with Maven
mvn clean package

# Pull docker image
docker pull themhammed/twitter

# Run the image with Twitter secrets
docker run --rm \
-e consumerKey=YOUR_VALUE \
-e consumerSecret=YOUR_VALUE \
-e accessToken=YOUR_VALUE \
-e tokenSecret=YOUR_VALUE \
themhammed/twitter post|show|delete [options]

# Post (post "tweet_text", "longitude:latitude")
post "test tweet" "

# Show (show id["field1, field2, ..."])
show 123456789 ["id, text"]

# Delete (delete "id1, id2, ...")
delete "123456789"

```


# Design
## UML diagram
![TwitterProject UML.png](assets%2FTwitterProject%20UML.png)
## 
**TwitterCLISpringboot**: This class utilizes the TwitterCLIApp while leveraging Spring for dependency management. By utilizing autowiring, the necessary objects are instantiated and initialized. Subsequently, it forwards the command line arguments to the TwitterCLIApp for processing.

**TwitterCLIApp**: This class serves as the command line interface for the application. It processes the command line arguments and delegates them to the appropriate controller method for handling. It then displays the response on the command line.

**TwitterHttpHelper**: This class represents the foundational layer of the application. It carries out the HTTP communication between the user and the Twitter API. It transforms the provided URIs into HTTP requests and receives the corresponding responses.

**TwitterDao**: Responsible for the data access of Tweet objects, this class constructs URIs that align with specific methods and executes them using the TwitterHttpHelper. It subsequently builds the corresponding Plain Old Java Object (POJO) for the Tweet.

**TwitterService**: This class manages the business logic of the application. By utilizing the TwitterDao, it ensures that requested tweets adhere to appropriate standards, such as text length and ID requirements.

**TwitterController**: As a controller class, it parses the command line arguments received from the TwitterCLIApp and forwards them to the service layer for processing.
## Models
The Tweet model employed in this application is a simplified rendition of the official Tweet model. It serves as a Data Transfer Object (DTO) that facilitates the transfer of tweet information between various layers of the application (controller, service, dao). The model consists of multiple other objects (Coordinates, Entities, UserMention, Hashtag) as well as object wrappers for primitive fields. This design pattern and structure enable the appropriate serialization and deserialization of JSON objects.

## Spring
In this project, the management of class dependencies was handled through the utilization of the Spring Boot framework. The application incorporates four Beans, namely TwitterController, TwitterService, TwitterDao, and TwitterHttpHelper. To indicate the respective responsibilities of each layer, the annotations @Controller, @Service, @Repository, and @Component were employed. The TwitterCLISpringBoot application, designated with the @SpringBootApplication annotation, effectively manages dependencies by injecting them using the '@Autowired' annotation within the constructors of the dependent classes.


# Test
The application encompasses both unit tests and integration tests for every component, implemented using JUnit. In the unit tests, dependencies were mocked using Mockito, guaranteeing that only the specific component under scrutiny was being tested. On the other hand, the integration tests ensured that the combined system functionality operated as expected when interacting with the Twitter API. In combination, these two types of tests were employed to ensure that the application performed in accordance with the desired specifications.

## Deployment
The application and its dependencies are bundled into a JAR file using Maven. To create a Docker image, a Dockerfile is utilized with the base image openjdk:8-alpine, along with the inclusion of the project's Uber JAR file. The Docker image is specifically configured to execute the main class, which is designated as the entry point. Subsequently, the image is pushed to DockerHub, facilitating straightforward deployment of the application.

# Improvements
- To enhance the application, consider developing a user-friendly Graphical User Interface (GUI) instead of the Command Line Interface (CLI). This GUI will provide a more intuitive and accessible way for users to interact with the application.
- Enhance the search functionality to find tweets based on their text content instead of solely relying on the tweet ID.
- Introduce additional features to the Twitter application, such as the ability to like and retweet tweets.