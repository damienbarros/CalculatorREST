FROM openjdk
#LABEL authors="Damien"
WORKDIR /app
COPY target/calculator-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]