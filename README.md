# Favicon Application - Submission
### Author: David Purdy (resume@davidpurdy.net)

## Online Instance

Base Url:
http://favicon.purdylabs.com

Example Query: http://favicon.purdylabs.com/?url=wordpress.com

JSON API Endpoint: http://favicon.purdylabs.com/favicon?url=soso.com

Top N Refresh Trigger: http://favicon.purdylabs.com/refresh/1000


## Quick Start - Locally

* To compile `mvn clean compile`

* To run unit tests `mvn clean test`
 
* To run locally:
  1. `mvn clean package`
  2. `docker build -t favicon-app:latest .`
  3. `docker run -P -d --name mongo mongo`
  4. `docker run -p 8080:8080 --link=mongo --name favicon-app favicon-app:latest`
  
  This will make set up a mongo docker image running locally and then a local favicon application.  It can be accessed at `localhost:8080`

## Useful Endpoints

- [/](http://favicon.purdylabs.com) - Root endpoint webpage
- [/?url=http%3A%2F%2Fwww.facebook.com](http://favicon.purdylabs.com/?url=http://www.facebook.com) - Example UI query
- [/favicon?url=wikipedia.org](http://favicon.purdylabs.com/favicon?url=wikipedia.org) - JSON API GET
- [/refresh/XXX](http://favicon.purdylabs.com/refresh/1000) - Refresh the top 1000 Alexa entries asyncronously

## Features

1. Uses standardized framebook to remove boilerplate as much as possible for microservice using [Spring Boot](https://projects.spring.io/spring-boot/)
1. Validation of irl structure with standard library logic, extensible at the service layer.  
1. Full separation of layers from Controllers, Services, and DAOs
1. Implements standard [MongoDB](http://www.mongodb.com) object document stores for long term persistence
1. Example unit tests to show service logic exercise
1. Use of the [Akka](https://akka.io/) library to parallelize the getting/parsing without expensive concurrecy overhead code.  Actor model implemeted in Akka is a VERY powerful tool for such operations and the actor model could be used for massive parallel actions throughout with robust error handling and back-pressure design
1. Use of standardized caching library [EhCache](http://www.ehcache.org/) to minimize outside hits, leveraging the stable character of webpages.
1. Templating engine [Freemarker](https://freemarker.apache.org/index.html) for webpages to separate concerns of logic and presentation
1. Containerization of all executables with [Docker](http://www.docker.com) for ultimate portabliity and scalable deployment