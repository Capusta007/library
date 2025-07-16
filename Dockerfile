FROM openjdk:21-jdk-slim
LABEL authors="charnenka"

WORKDIR /app
COPY target/library-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]