FROM maven:3.6-openjdk-17 as build
WORKDIR /builder

COPY . /builder/
RUN mvn clean package -DskipTests
FROM amazoncorretto:17-alpine3.16

WORKDIR /app
COPY --from=build /builder/target/*.jar /app/culinart-v1.0.jar
EXPOSE 8080
CMD ["java", "-jar", "culinart-v1.0.jar"]