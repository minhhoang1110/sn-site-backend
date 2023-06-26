# Deploy Sprint Boot And Postegres on Render with Web Services and Docker

### Create Dockerfile at the root folder

    #
    # Build stage
    #
    FROM maven:3.8.2-jdk-11 AS build
    COPY . .
    RUN mvn clean package -Pprod -DskipTests

    #
    # Package stage
    #
    FROM openjdk:11-jdk-slim
    COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
    # ENV PORT=8080
    EXPOSE 8080
    ENTRYPOINT ["java","-jar","demo.jar"]

### Building a Dockerfile from the root folder

    By default docker uses the Dockerfile of the current folder if you run a single command like
    Run: docker build -t sn-site-backend .

### Run image with docker on localhost

    Run docker run -p 8081:8081 sn-site-backend

## Create Postegres database from Render.com

    https://dashboard.render.com/

### Creat new Web Service app
