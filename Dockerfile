# Stage 1: Build - Multi-stage com cache otimizado
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app

# Cache de dependências
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

# Instalação de dependências necessárias como root
RUN apk add --no-cache curl && \
    adduser -D -u 1000 appuser

# Segurança: Configura permissões e usuário
RUN chown appuser:appuser /app

# Copia JAR do builder com permissão de proprietário
COPY --from=builder --chown=appuser:appuser /app/target/*.jar app.jar

USER appuser

EXPOSE 8080

# Health check robusto
HEALTHCHECK --interval=30s --timeout=3s --start-period=60s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
