FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-alpine-slim
MAINTAINER Jose Fresnadillo <jose.fresna@masmovil.com>

FROM adoptopenjdk/openjdk11:jdk-11.0.2.9-alpine-slim

WORKDIR /app

COPY target/vertx-ddd-template-11.0.0-fat.jar app.jar

EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -jar app.jar"]