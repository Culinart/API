# Etapa de build
FROM amazoncorretto:17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN yum install -y maven && mvn clean package
# Etapa de produção
FROM openjdk:17-jdk-slim AS production-stage
WORKDIR /app
COPY --from=build /app/target/culinart-v1.0.jar .
EXPOSE 8080
CMD ["java", "-jar", "culinart-v1.0.jar"]