FROM openjdk:11-jre-slim
ADD target/kaiburr-assessment-0.0.1.jar kaiburr-assessment-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Kaiburr-Assessment/target/kaiburr-assessment-0.0.1.jar"]
