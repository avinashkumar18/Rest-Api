#Build Stage
FROM maven:3.6.3-jdk-11-slim AS build
COPY src Rest-API/src
COPY pom.xml Rest-API/pom.xml
RUN mvn -f Rest-API/pom.xml clean package

#Deploy Stage
#FROM openjdk:11-jre-slim
#ADD Rest-API/target/Rest-API-0.1.1.jar Rest-API-0.1.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Rest-API/target/rest-api-0.1.1.jar"]
