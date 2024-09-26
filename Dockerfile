FROM maven:3.9.8-amazoncorretto-21-debian as build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve

COPY src ./src

RUN mvn clean install -DskipTests


FROM amazoncorretto:21

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


