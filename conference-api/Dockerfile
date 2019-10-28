FROM openjdk:12-alpine as JDK
WORKDIR /app
COPY . /app
RUN ./gradlew clean bootJar

FROM openjdk:12-alpine as JAR
WORKDIR /app
COPY  --from=JDK /app/build/libs/conference-0.0.1.jar .
COPY  --from=JDK /app/build/resources/ .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "conference-0.0.1.jar"]