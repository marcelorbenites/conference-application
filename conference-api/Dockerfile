FROM openjdk:12-alpine as JDK
WORKDIR /app
COPY . ./
RUN ./gradlew clean bootJar

FROM openjdk:12-alpine as JAR
WORKDIR /app
COPY  --from=JDK /app/build/libs/conference-api-0.0.1.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "conference-api-0.0.1.jar"]