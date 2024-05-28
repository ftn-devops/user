FROM openjdk:17-oracle

EXPOSE 8080

COPY ./build/libs/user-0.0.1-SNAPSHOT.jar /app/user.jar
WORKDIR /app

CMD ["java", "-Xmx4g", "-jar", "user.jar"]