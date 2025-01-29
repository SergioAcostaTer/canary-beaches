FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY . .

RUN chmod +x gradlew && ./gradlew clean build -x check -x test

EXPOSE 8080

CMD ["java", "-jar", "-Dserver.port=$PORT", "build/libs/your-app.jar"]
