# Etapa 1: Build
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app
COPY . .
RUN gradle clean build -x test

# Etapa 2: Runtime
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copiar el JAR generado desde la etapa de build
COPY --from=builder /app/build/libs/*.jar app.jar

# Exposer el puerto
EXPOSE 8099

# Variables de entorno
ENV SPRING_PROFILES_ACTIVE=prod
ENV DATABASE_URL=postgresql://saberpro_user:dnqGnIZolxBxPagxDxQacKpLxPf7MQti@dpg-d4ig90shg0os739tg0m0-a/saberprodb
ENV PORT=8099

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]