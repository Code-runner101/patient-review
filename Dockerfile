# Используйте образ Maven как базовый образ
FROM maven:3.8.1-openjdk-11 as build

# Копируем исходный код в контейнер
COPY src /usr/src/myapp/src
COPY pom.xml /usr/src/myapp

# Устанавливаем рабочую директорию
WORKDIR /usr/src/myapp

# Собираем проект
RUN mvn package

# Используем образ Java для запуска
FROM openjdk:20

# Копируем собранный jar-файл из предыдущего образа
COPY --from=build /usr/src/myapp/target/ThymeleafProject-0.0.1-SNAPSHOT.jar /usr/app/ThymeleafProject-0.0.1-SNAPSHOT.jar

# Устанавливаем рабочую директорию
WORKDIR /usr/app

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "-Dspring.config.name=$SPRING_CONFIG_NAME", "-Dspring.profiles.active=$SPRING_PROFILES_ACTIVE", "ThymeleafProject-0.0.1-SNAPSHOT.jar"]
