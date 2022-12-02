#Build Stage
FROM maven:3.6.3-jdk-11-slim AS build
COPY src /Kaiburr-Assessment/src
COPY pom.xml /Kaiburr-Assessment
RUN mvn -f /Kaiburr-Assessment/pom.xml clean package


FROM openjdk:11-jre-slim
ADD target/kaiburr-assessment-0.0.1.jar kaiburr-assessment-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kaiburr-assessment-0.0.1.jar"]
