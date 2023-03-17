FROM openjdk:11-jre

WORKDIR /appdelivery

ARG JAR_FILE

COPY target/${JAR_FILE} /appdelivery/api.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]