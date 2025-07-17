FROM openjdk:21-jdk-slim
LABEL authors="charnenka"

WORKDIR /app
COPY target/booking-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]