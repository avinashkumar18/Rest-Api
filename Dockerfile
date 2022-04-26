FROM openjdk:11
COPY target/kaiburr-assessment-0.0.1.jar kaiburr-assessment-0.0.1.jar
EXPOSE 8080
CMD ["sh", "-c", "java -jar kaiburr-assessment-0.0.1.jar"]
