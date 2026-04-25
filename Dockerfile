FROM eclipse-temurin:21-jdk-jammy
RUN mkdir /opt/app
COPY /build/libs/user-service-0.0.1-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/user-service-0.0.1-SNAPSHOT.jar"]