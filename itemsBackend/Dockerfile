# Use lightweight OpenJDK image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/itemsBackend-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 for the app
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
