# 🌿 Leafyer 
- 잎이 많은, 잎을 닮은 라이프스타일을 가진 사람들이 모이는 식물 거래 사이트
- 식물에 관심이 있는 사용자들이 반려식물 사진과 정보를 올리며 커뮤니티를 형성
- 채팅을 통해 반려식물을 거래할 수 있는 기능을 제공 
- 식물에 대한 기본적인 정보를 제공하여 사용자들이 식물 관련 지식을 습득

<br/><br/>

## ⏳ 작업기간 
2023.6.4 ~ 진행중

<br/>

## 👨‍💻 팀원
- 정철희, 박세희, 조준휘, 전혜진

<br/>

## 🛠️ 기술스택
- ![Java](https://img.shields.io/badge/Java-007396?style=flat&logo=Java&logoColor=white)
 ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=Spring%20Boot&logoColor=white)
 ![JPA](https://img.shields.io/badge/JPA-6600ff?style=flat&logo=Java&logoColor=white)
 ![MyBatis](https://img.shields.io/badge/MyBatis-F58020?style=flat&logo=Apache%20Tomcat&logoColor=white)
 ![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white)
 ![GitHub Actions](https://img.shields.io/badge/GitHub%20Actions-2088FF?style=flat&logo=GitHub%20Actions&logoColor=white)
 ![IntelliJ](https://img.shields.io/badge/IntelliJ-000000?style=flat&logo=IntelliJ%20IDEA&logoColor=white)
 ![EC2](https://img.shields.io/badge/EC2-232F3E?style=flat&logo=Amazon%20AWS&logoColor=white)
 ![RDS](https://img.shields.io/badge/RDS-FF9900?style=flat&logo=Amazon%20RDS&logoColor=white)
 ![S3](https://img.shields.io/badge/S3-569A31?style=flat&logo=Amazon%20S3&logoColor=white)

<br/>

## 🤝 협업툴
- ![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat&logo=GitHub&logoColor=white)
  ![Discord](https://img.shields.io/badge/Discord-7289DA?style=flat&logo=Discord&logoColor=white)
  ![Notion](https://img.shields.io/badge/Notion-000000?style=flat&logo=Notion&logoColor=white)


<br/>

## 🏢 DB 명세서
![DB_ERD](https://github.com/LEAFY-7/leafy-back/assets/96738163/f8b2976c-05b7-4df3-bfbc-8fa7ab8e95f4)

<br/>

## 🎯 기능

### 로그인, 회원가입
- 스프링 시큐리티와 JWT 토큰을 이용한 로그인 및 회원가입 기능
  
### 피드(게시물)
- 사용자는 인스타그램처럼 식물 관련 피드 업로드
- 좋아요, 공유, 댓글, 구독 기능 포함

### 팔로우
- 사용자는 다른 사용자를 팔로우하여 피드를 구독

### 댓글
- 사용자는 피드와 qna에 댓글 및 대댓글을 작성 가능

### 신고
- 사용자는 다른 사용자나 피드를 신고하여 관리자에게 악성 사용자를 알림

### 차단
- 사용자는 다른 피드나 사용자를 차단 하여 악성 사용자로부터 피드를 조회하지 않음
  
### QNA, 공지
- 사용자들에게 공지나 궁금한 사항에 대한 질문을 받음
  
### 알림
- 사용자들에게 다양한 이벤트로 게시물에 대한 좋아요 알림, 나를 팔로우하는 회원의 대한 알림 등등 여러 알림으로 사용자에게 활동감을 넣어줌
  
### 식물 정보 검색
- 공공 데이터 API를 이용하여 식물의 경매가, 품종명 등의 정확한 정보를 제공

### 메인 페이지 및 회원 상세 페이지
- 팔로워 정보와 게시글 활동 내역 및 개인 정보 수정
  
### 실시간 채팅
- 사용자간 실시간 채팅 기능을 제공

<br/>

## 🚩 이슈 및 트러블 슈팅

### 게시글 좋아요 동시성 이슈
- update 쿼리를 실행하기 전 조회 쿼리를 Beta Lock(Pessimistic Lock)으로 로직을 변경 하였습니다. ( 데드락을 대비하기 위해 좋아요 집계 테이블을 정규화 )

### 테스트하기 어려운 영역을 구분하고 분리하기
- 인증 번호를 받고 나서 3분 동안 인증을 완료를 해야하는 로직을 작성중 DB의 Date Format을 사용하게 되면 예외 케이스 작성이 거의 불가능했습니다.
 테스트 하기 어려운 부분을 메서드 밖 매개변수로 넘겨서 해결을 했습니다.

- 예시 : between 되는 시간을 받아 예외적인 상황에서 정상적으로 메서드가 실행되는지 테스트 작성 -> CertificationNumberRepositoryTest

### GitHub Action과 AWS를 통한 백엔드, 프론트엔드 자동 배포
- 경로 충돌 이슈: 자동 빌드 전 수동 배포된 파일이 경로 충돌을 일으켜 배포에 실패하는 문제가 발생했습니다. 수동 배포 파일을 삭제하여 문제를 해결했습니다.
- 메모리 부족 이슈: 하나의 EC2 서버로 스프링과 리액트 서버를 사용하는 도중 메모리 부족으로 인해 서버가 중단되는 문제가 발생했습니다. 메모리 용량을 늘리고 스왑 메모리를 할당하여 문제를 해결했습니다.

### 코드 디플로이 아카이브 파일 이슈
- 아카이브 파일 삭제로 인한 배포 실패: 아카이브 파일 삭제 후 배포 시 아카이브 파일을 찾지 못해 배포가 실패하는 문제가 발생했습니다. 아카이브 파일을 복구하여 문제를 해결했습니다.

### 포트 이슈
- 80 포트 사용: 도메인의 80 포트 사용을 위해 Nginx를 사용하여 프록시 서버를 설정하고 포트 포워딩을 통해 문제를 해결했습니다.

### 백그라운드 실행 이슈
- SSH 종료 시 서버 중단: nohup 명령어로 백그라운드에서 프론트 서버를 실행했지만 SSH 종료 시 서버도 함께 종료되는 문제가 발생했습니다. screen 명령어를 사용하여 SSH와 서버를 분리하여 문제를 해결했습니다.
