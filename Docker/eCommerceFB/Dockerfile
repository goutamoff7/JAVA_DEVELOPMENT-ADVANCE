FROM openjdk:21-jdk

WORKDIR /app

COPY /target/e-commerce-app.jar e-commerce-app.jar

ENTRYPOINT ["java", "-jar", "e-commerce-app.jar"]