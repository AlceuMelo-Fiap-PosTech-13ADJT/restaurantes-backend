# Stage 1: Build - Multi-stage com cache otimizado
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app

# Cache de dependências (Dockerfile1)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia estrutura Maven completa
COPY mvnw .
COPY .mvn .mvn
COPY mvnw.cmd .
COPY src ./src

# Build do JAR
RUN ./mvnw clean package -DskipTests -B || \
    mvn clean package -DskipTests -B

# Stage 2: Runtime - Segurando e monitorado
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Segurança: user não-root (Dockerfile1)
RUN adduser -D -u 1000 appuser && \
    chown -R appuser:appuser /app

USER appuser

# MySQL client para connector nativo (Dockerfile1)
RUN apk add --no-cache mysql-connector-java && \
    rm -rf /var/cache/apk/*

# Copia JAR do builder
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

# Health check robusto (Dockerfile1)
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
