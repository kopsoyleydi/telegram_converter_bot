# syntax=docker/dockerfile:1

# Используем базовый образ Java 11
FROM openjdk:11

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем сначала только файл с зависимостями (это помогает использовать кэширование слоев Docker)
COPY build.gradle/*.jar ./

# Копируем само приложение
COPY target/*.jar app.jar

# Запускаем приложение при старте контейнера
ENTRYPOINT ["java", "-jar", "app.jar"]
