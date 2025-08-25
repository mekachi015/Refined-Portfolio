# Use official OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy the Maven wrapper and pom.xml first (for better layer caching)
COPY mvnw .
COPY .mvn/ .mvn/
COPY pom.xml .

# Copy the source code
COPY src/ src/

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the port your app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "target/Portfolio-Backend-0.0.1-SNAPSHOT.jar"]