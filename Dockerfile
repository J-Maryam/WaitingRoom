FROM openjdk:17-jdk-slim as build
WORKDIR /app
COPY target/WaitingRoom-0.0.1-SNAPSHOT.jar /app/wrm-app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/wrm-app.jar"]