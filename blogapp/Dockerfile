FROM eclipse-temurin:17

LABEL mentainer="user@gmail.com"

WORKDIR /app

COPY target/blogapp-0.0.1-SNAPSHOT.jar /app/blogapp.jar

ENTRYPOINT ["java", "-jar", "blogapp.jar"]