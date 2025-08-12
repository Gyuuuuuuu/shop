# Docker Compose 사용법

## 개요
이 프로젝트는 Docker Compose를 사용하여 다음 서비스들을 실행합니다:
- MySQL 8.0 데이터베이스
- Redis 7 캐시 서버
- Spring Boot 애플리케이션

## 사전 요구사항
- Docker
- Docker Compose

## 빠른 시작

### 1. 환경변수 설정 (선택사항)
`.env` 파일을 생성하여 환경변수를 설정할 수 있습니다:
```bash
MYSQL_ROOT_PASSWORD=your_root_password
MYSQL_DATABASE=content_platform
MYSQL_USER=contentuser
MYSQL_PASSWORD=your_password
SPRING_PROFILES_ACTIVE=docker
```

### 2. 서비스 실행
```bash
# 모든 서비스 시작
docker-compose up -d

# 로그 확인
docker-compose logs -f

# 특정 서비스 로그 확인
docker-compose logs -f content-platform-app
```

### 3. 서비스 중지
```bash
# 모든 서비스 중지
docker-compose down

# 볼륨까지 삭제
docker-compose down -v
```

## 서비스 정보

### MySQL 데이터베이스
- **포트**: 3306
- **데이터베이스**: content_platform
- **사용자**: contentuser
- **비밀번호**: contentpass (기본값)

### Redis
- **포트**: 6379
- **용도**: 캐싱, 세션 관리

### Spring Boot 애플리케이션
- **포트**: 8080
- **프로필**: docker

## 데이터베이스 연결
Spring Boot 애플리케이션은 자동으로 MySQL과 Redis에 연결됩니다.

## 볼륨
- `mysql-data`: MySQL 데이터 영속성
- `redis-data`: Redis 데이터 영속성
- `./uploads`: 파일 업로드 디렉토리
- `./logs`: 애플리케이션 로그

## 문제 해결

### 포트 충돌
기본 포트가 이미 사용 중인 경우 docker-compose.yml에서 포트를 변경하세요.

### 데이터베이스 연결 실패
```bash
# MySQL 컨테이너 상태 확인
docker-compose ps

# MySQL 로그 확인
docker-compose logs content-platform-db
```

### 애플리케이션 재시작
```bash
docker-compose restart content-platform-app
```
