# Use a Gradle image to build the project
FROM gradle:7.5.1-jdk17 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and other necessary files
COPY . ./

# Execute the Gradle build command
RUN gradle build -x test

# Use a new image to run the application
FROM openjdk:17-oracle

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the previous stage
COPY --from=build /app/build/libs/user-0.0.1-SNAPSHOT.jar ./user.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-Xmx4g", "-jar", "user.jar"]
