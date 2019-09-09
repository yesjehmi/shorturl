# shorturl
> 2019 카카오페이 경력 서버 개발자 과제

url을 입력받아 간략화된 url (short url)로 변환하여 주고, 번환 된 url이 들어왔을 때 변환 이전의 원 url로 redirect 해 주는 서비스

## Getting Started

### Requirements
*webapp으로 개발하고 URL 입력폼 제공 및 결과 출력
* URL Shortening Key 는 8 Character 이내로 생성되어야 합니다.
* 동일한 URL 에 대한 요청은 동일한 Shortening Key 로 응답해야 합니다.
* Shortening 된 URL 을 요청받으면 원래 URL 로 리다이렉트 합니다.
* Shortening Key 생성 알고리즘은 직접 구현해야 합니다. (라이브러리 사용 불가) 
* Unit Test 코드 작성
* Database 사용은 필수 아님 (선택)

### Prerequisites
* Gradle 
* JDK 1.8

### Build
``` bash
# terminal
./gradlew bootRun

# IntelliJ
1. Sync gradle
2. Run Application

```

### Start

http://localhost/<br>
입력시 최초 입력 화면  

## API Specifications
| Action | API | Parameter | Body | Response |
|--------|-----|-----------|------|------------------|
| create short URL| POST /api/v1/shortUrl  | N/A | {"originUrl":"http://daum.net"} | {"shortUrl": "http://localhost/d"} |
| Redirect page using shortUrl  | GET<br> /{shortUrl}  | shortUrl=[String]|N/A|  N/A |

## Dependencies

Dependence         |Version
-------------------|-------
spring-boot       |2.1.8.RELEASE
spring-boot-starter-data-jpa |
spring-boot-starter-web |
spring-boot-starter-test |
spring-boot-starter-thymeleaf |
com.h2database:h2 |
org.projectlombok:lombok |
