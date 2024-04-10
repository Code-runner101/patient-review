# Dockerfile
FROM openjdk:20

RUN mkdir -p /usr/src/myapp
COPY target/ThymeleafProject-0.0.1-SNAPSHOT.jar /usr/src/myapp
WORKDIR /usr/src/myapp
ENTRYPOINT ["java -jar -Dspring.config.name=$SPRING_CONFIG_NAME -Dspring.profiles.active=$SPRING_PROFILES_ACTIVE ThymeleafProject-0.0.1-SNAPSHOT.jar"]