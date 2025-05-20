##Build Stage
#FROM maven:3.8.7-openjdk-18 AS build
#WORKDIR /build
#COPY pom.xml .
#RUN mvn dependency:go-offline
#COPY src ./src
#RUN mvn clean package -DskipTests
#
##Runtime Stage
#FROM amazoncorretto:17
#
##Define few thing
#
#WORKDIR /app
#COPY --from=build /build/target/demo-*.jar /app/
#EXPOSE 8080
#demo-0.0.1-SNAPSHOT
#
#
