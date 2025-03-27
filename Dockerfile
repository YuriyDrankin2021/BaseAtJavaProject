FROM gradle:jdk21-corretto
COPY ./ /app/
WORKDIR /app
RUN chmod +x ./gradlew && ./gradlew test
RUN chmod +x ./gradlew && ./gradlew allureReport
