FROM openjdk:11
ADD target/kaiburr-assessment-0.0.1.jar kaiburr-assessment-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "kaiburr-assessment-0.0.1.jar"]
