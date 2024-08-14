# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from your local machine to the working directory inside the container
COPY target/MarineRecord-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your application runs on
EXPOSE 8086

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]