FROM maven:3.9.8-amazoncorretto-21-debian as build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve

COPY src ./src

RUN mvn clean install -DskipTests




FROM amazoncorretto:21

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar


ARG DB_URL
ARG DB_USER
ARG DB_PWD
ARG SECRET_KEY

ENV DB_URL=${DB_URL}
ENV DB_USER=${DB_USER}
ENV DB_PWD=${DB_PWD}
ENV SECRET_KEY=${SECRET_KEY}

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]


