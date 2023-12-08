FROM eclipse-temurin:21-jdk-jammy as base
EXPOSE 8080
ADD targer/quote_api.jar quote_api.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "quote_api.jar"]