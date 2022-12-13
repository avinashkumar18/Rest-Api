#Build Stage
FROM maven:3.6.3-jdk-11-slim AS build
COPY src Kaiburr-Assessment/src
COPY pom.xml Kaiburr-Assessment/pom.xml
RUN mvn -f Kaiburr-Assessment/pom.xml clean package

#Deploy Stage
#FROM openjdk:11-jre-slim
#ADD Kaiburr-Assessment/target/kaiburr-assessment-0.0.1.jar kaiburr-assessment-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Kaiburr-Assessment/target/kaiburr-assessment-0.0.1.jar"]
