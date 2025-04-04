# syntax=docker/dockerfile:1

##############################
# Builder Stage
##############################
FROM gradle:8.11.1-jdk17 AS builder

# 필요한 패키지 설치
RUN apt-get update && \
    apt-get install -y --no-install-recommends bash curl zip && \
    rm -rf /var/lib/apt/lists/*

# timezone 설정
ENV TZ=Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# 프로젝트 루트 전체를 빌드 컨텍스트로 사용
WORKDIR /app
COPY . .

# Gradle Wrapper 실행 권한 추가
RUN chmod +x ./gradlew

# 종속성 다운로드 (실패해도 계속 진행)
RUN ./gradlew --no-daemon dependencies || true

# lifelog-service 모듈만 빌드 (다른 모듈은 제외)
RUN ./gradlew :lifelog-service:clean :lifelog-service:bootJar --no-daemon

##############################
# Runtime Stage
##############################
FROM arm64v8/openjdk:17-jdk-slim

WORKDIR /app

ARG ENVIRONMENT
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}

# wait-for-it 스크립트 복사 및 실행 권한 부여 (프로젝트 루트에 위치)
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

# 빌드 단계에서 생성된 lifelog-service JAR 파일 복사
COPY --from=builder /app/lifelog-service/build/libs/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "app.jar"]
