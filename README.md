# 과제 - 블로그 검색

다운로드 링크:
https://drive.google.com/file/d/1sn0JRFfPEK-_XcqLJ86OZZczKKbMk7k4/view?usp=share_link

실행 명령어:
java -jar blog-0.0.1-SNAPSHOT.jar

API 명세:
- API: GET /v1/blogs
- 설명: 특정 검색어가 포함된 블로그 조회

Parameter|Type|Description|Required
-------|---|---|---
keyword|String|검색을 원하는 질의어|O
sort|String|결과 문서 정렬 방식, ACCURACY(정확도순) 또는 RECENCY(최신순), 기본 값 ACCURACY|X
page|Integer|결과 페이지 번호, 1~50 사이의 값, 기본 값 1|X
size|Integer|한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본 값 10|X

- API: GET /v1/blogs/keyword-ranking
- 설명: 조회 수가 포함된 인기 검색어 목록 (최대 10개)

데이터베이스:
- 인메모리 H2 DB 사용
- jar 파일 실행 후 접속
  - http://localhost:8080/h2-console

테스트 케이스:
- DataJpaTest를 이용한 Repository 테스트 케이스 작성
- MockWebServer와 Mockito를 이용하여 검색 API 장애에 대한 failover 처리 작성
- Mockito를 이용하여 인기 검색어 조회 테스트 케이스 작성

우대사항:
- 동시성 이슈가 발생할 수 있는 부분에 대한 처리
  - 키워드에 대한 조회 수 업데이트 시 단일 연산 쿼리 처리
  - @Version 애노테이션 처리

- 카카오 블로그 검색 API 장애가 발생한 경우에 대한 처리
  - 검색이란 역할로 분리하여 카카오 API를 우선적으로 선택하고 내부 서버 에러 장애 발생 시, 동적으로 구현체 변경되도록 처리
  - ![image](https://user-images.githubusercontent.com/68180535/226933728-99b2e622-be80-4f14-b2ab-e2690d6011fa.png)

