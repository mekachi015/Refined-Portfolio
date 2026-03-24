# Use official OpenJDK base image
FROM eclipse-temurin:21-jdk-alpine AS builder

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

# Runtime stage
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/Portfolio-Backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8085

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]