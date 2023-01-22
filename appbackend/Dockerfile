# Use openjdk as the base image
FROM openjdk:8-jdk-alpine

# Copy the backend files
COPY target/pubtalkapp-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Start the server
CMD ["java", "-jar", "/app.jar"]


