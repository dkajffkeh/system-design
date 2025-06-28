FROM openjdk:17-slim

WORKDIR /app

COPY ./build/libs/id-generator.jar /app/id-generator.jar

CMD ["java", "-Xms256m", "-Xmx512m", "-XX:+UseG1GC", "-jar", "id-generator.jar"]
