# 패스트레인 사전 테스트 문제

### 사용 DB: H2 database
#### 다운로드 URL: http://www.h2database.com/html/main.html

### H2 database 다운로드 후, h2.sh 실행
### H2 콘솔 브라우저가 열리면, 최초 파일 DB 생성 필요 (아래와 같이 입력하여 1회 Connect 수행해야 함)
### JDBC URL: jdbc:h2:~/assignment
### 생성 후, jdbc:h2:tcp://localhost/~/assignment 로 Connect 가능

### 회원 테이블 create query
create table member
(
	id varchar(255),
	password varchar(255),
	primary key (id)
);

### 웹 서버 API는 아래의 URL을 이용하여 확인 및 테스트 가능
#### http://localhost:8080/swagger-ui.html
