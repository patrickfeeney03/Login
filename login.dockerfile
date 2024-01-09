FROM openjdk:21

WORKDIR /app

COPY target/Login-0.0.1-SNAPSHOT.jar /app

EXPOSE 8082

CMD ["java", "-jar", "Login-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]