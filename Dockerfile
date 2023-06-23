
# Stage 1, base image to build the application
FROM eclipse-temurin:17-jdk-alpine AS build

# Make a temporary directory in the container
RUN mkdir /tmp/mindex
# Set the current working directory
WORKDIR /tmp/mindex
# Copy everything over so we can build the application into a jar file
COPY . .
# Build the Jar File
RUN ./gradlew bootJar

# Based on Docker file from https://spring.io/guides/topicals/spring-boot-docker/
# Final Stage Package the application
FROM eclipse-temurin:17-jdk-alpine AS final
ARG JAR_FILE
VOLUME /tmp
# Copy the built Jar file to the new 'clean' stage
COPY --from=build /tmp/mindex/build/libs/${JAR_FILE} app.jar

# Entry Point
ENTRYPOINT ["java","-jar","/app.jar"]

# Expose Port
EXPOSE 8080