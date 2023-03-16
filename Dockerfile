FROM openjdk:11-jre

WORKDIR /appdelivery

copy target/*.jar /appdelivery/api.jar

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]