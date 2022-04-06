# Spring-Security-Project
## Spring Boot(Gradle), OAuth, JPA(MySQL)
### Spring Security, Login(Google, Facebook, Naver)
- - -

## 프로젝트 구성

![1](/src/main/resources/static/202204-1.png)<br><br>

### Class
![2](/src/main/resources/static/202204-2.png)<br>

- - -

## Login Page

![3](/src/main/resources/static/202204-3.png)<br>

- - -

## Security 회원가입 & 로그인
### 스프링 시큐리티 + BCryptPasswordEncoder

![4](/src/main/resources/static/202204-4.gif)<br>


### DB(암호화 해시 함수)
![5](/src/main/resources/static/202204-5.png)<br>

- - -

## Google OAuth 로그인 & 조회
![6](/src/main/resources/static/202204-6.gif)<br>

### Oauth User Data
```json
{
  "id": 2,
  "email": "mok02198@gmail.com",
  "role": "ROLE_USER",
  "providerId": "google",
  "createDate": "2022-04-06T09:22:09.200+00:00"
}
```

- - -

## Facebook OAuth 로그인 & 조회
![7](/src/main/resources/static/201026-7.gif)<br>

### Oauth User Data

```json
{
  "id": 3,
  "email": "mok02198@gmail.com",
  "role": "ROLE_USER",
  "providerId": "facebook",
  "createDate": "2022-04-06T09:22:49.594+00:00"
}
```

- - -

## Naver 로그인 & 조회
![8](/src/main/resources/static/201026-8.gif)<br>

### Oauth User Data
```json
{
  "id": 4,
  "email": "mok02198@naver.com",
  "role": "ROLE_USER",
  "providerId": "naver",
  "createDate": "2022-04-06T09:23:20.710+00:00"
}
```

- - -