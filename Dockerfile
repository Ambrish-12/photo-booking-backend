# 1. Build Stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy project files and build executable JAR
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Run Stage
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy compiled JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose default port
EXPOSE 8080

# Run with memory limits optimized for Render's 512MB free tier
ENTRYPOINT ["java", "-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=400m", "-jar", "app.jar"]