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

# 빌드 컨텍스트가 프로젝트 루트이므로, eureka-server 모듈만 빌드하도록 명령어 수정
RUN ./gradlew :eureka-server:clean :eureka-server:bootJar --no-daemon

##############################
# Runtime Stage
##############################
FROM arm64v8/openjdk:17-jdk-slim

WORKDIR /app

ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}

COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# 빌드 단계에서 생성한 eureka-server JAR 파일 복사
COPY --from=builder /app/eureka-server/build/libs/*.jar app.jar

ARG PORT=8761
EXPOSE ${PORT}

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "app.jar"]
