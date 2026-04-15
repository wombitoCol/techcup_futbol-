# ─────────────────────────────────────────────
# STAGE 1: Build
# ─────────────────────────────────────────────
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# Copiar archivos de dependencias primero (cache de capas)
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Descargar dependencias (se cachea si pom.xml no cambia)
RUN ./mvnw dependency:go-offline -q

# Copiar código fuente y compilar
COPY src ./src
RUN ./mvnw package -DskipTests -q

# ─────────────────────────────────────────────
# STAGE 2: Runtime (imagen mínima)
# ─────────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine AS runtime

# Usuario no-root por seguridad
RUN addgroup -S techcup && adduser -S techcup -G techcup

WORKDIR /app

# Copiar solo el JAR desde el stage anterior
COPY --from=builder /app/target/*.jar app.jar

# Cambiar propietario
RUN chown techcup:techcup app.jar

USER techcup

EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
  CMD wget -q --spider http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
