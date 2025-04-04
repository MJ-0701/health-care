# syntax=docker/dockerfile:1

##############################
# Builder Stage
##############################
FROM gradle:8.11.1-jdk17 AS builder

RUN apt-get update && \
    apt-get install -y --no-install-recommends bash curl zip && \
    rm -rf /var/lib/apt/lists/*

ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew --no-daemon dependencies || true

# api-gateway 모듈만 빌드
RUN ./gradlew :api-gateway:clean :api-gateway:bootJar --no-daemon

##############################
# Runtime Stage
##############################
FROM arm64v8/openjdk:17-jdk-slim

WORKDIR /app

ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}

COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# 빌드 단계에서 생성한 JAR 파일 복사 (api-gateway 모듈 기준)
COPY --from=builder /app/api-gateway/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "app.jar"]

