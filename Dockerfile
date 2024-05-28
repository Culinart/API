# Etapa de build
FROM amazoncorretto:17 AS build
WORKDIR /app

# Install Maven
RUN yum install -y wget tar && \
    wget https://archive.apache.org/dist/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz && \
    tar xzf apache-maven-3.8.6-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.8.6/bin/mvn /usr/bin/mvn

# Copy project files
COPY pom.xml .
COPY src ./src

# Run Maven package
RUN mvn clean package

# Etapa de produção
FROM openjdk:17-jdk-slim AS production-stage
WORKDIR /app
COPY --from=build /app/target/culinart-v1.0.jar .
EXPOSE 8080
CMD ["java", "-jar", "culinart-v1.0.jar"]
