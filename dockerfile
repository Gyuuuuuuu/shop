# Java 17이 설치된 베이스 이미지로 변경
FROM openjdk:17-jdk-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일 복사
COPY target/HelloSpringBoot-0.0.1-SNAPSHOT.jar app.jar

# 포트 오픈
EXPOSE 8080

# 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]
