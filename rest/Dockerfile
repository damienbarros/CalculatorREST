FROM openjdk
#LABEL authors="Damien"

# Set the working directory
WORKDIR /app

# Copy the build artifacts
COPY target/rest-*.jar app.jar

# Expose port 8080 for the REST API
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
