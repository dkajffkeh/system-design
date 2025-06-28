FROM openjdk:17-slim

WORKDIR /app

COPY ./build/libs/id-generator-0.0.1.jar /app/id-generator.jar

CMD ["java", "-Xms256m", "-Xmx512m", "-XX:+UseG1GC", "-jar", "id-generator.jar"]
