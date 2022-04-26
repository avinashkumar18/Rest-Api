FROM openjdk:11
EXPOSE 8080
ADD target/kaiburr-assessment-0.0.1.jar kaiburr-assessment-0.0.1.jar
ENTRYPOINT ["java","-jar","kaiburr-assessment-0.0.1.jar"]
