FROM openjdk:11-jre

WORKDIR /appdelivery

ARG JAR_FILE

COPY target/${JAR_FILE} /appdelivery/api.jar

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]