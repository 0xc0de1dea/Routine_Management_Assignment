# 일정 관리 시스템

```
프로젝트 개요
 - 프로젝트 목적: 스프링 숙련에 익숙해지기 위한 일정 관리 시스템 구현
 - 주요 학습 목표: 세션 관리, CRUD 학습, 검증, 비밀번호 암호화, 페이징 기능을 다룸
 
기술 스택
 - Language: Java 21
 - IDE: IntelliJ IDEA
 
주요 기능
 - 유저 회원가입
 - 유저 로그인
 - 일정 생성
 - 일정 단건 조회
 - 일정 목록 조회
 - 일정 페이징 조회
 - 일정 수정
 - 일정 삭제
 - 댓글 생성
 - 댓글 조회
 - 댓글 수정
 - 댓글 삭제
 
패키지 구조
src/
├── comment/
│   ├── controller/
│   │　 └── CommentController.java
│   ├── dto/
│   │　 └── CommentDto.java
│   ├── entity/
│   │　 ├── BaseEntity.java
│   │　 └── Comment.java
│   ├── repository/
│   │　 └── CommentRepository.java
│   └── service/
│       ├── impl.java
│       │   └── CommentServiceImpl.java
│       └── CommentService.java
├── config/
│   ├── JpaAuditingConfig.java
│   └── PasswordEncoder.java
├── date/
│   ├── controller/
│   │　 └── DateController.java
│   ├── dto/
│   │　 ├── DateCommentDto.java
│   │　 ├── DateDto.java
│   │　 └── DatePageDto.java
│   ├── entity/
│   │　 ├── BaseEntity.java
│   │　 └── Date.java
│   ├── repository/
│   │　 └── DateRepository.java
│   └── service/
│       ├── impl.java
│       │   └── DateServiceImpl.java
│       └── DateService.java
├── exception/
│   ├── CustomException.java
│   ├── ErrorResponse.java
│   └── GlobalExceptionHandler.java
│── type/
│   └── Errorcode.java
└── user/
    ├── controller/
    │　 └── UserController.java
    ├── dto/
    │　 ├── LoginDto.java
    │　 ├── SignupDto.java
    │　 └── UserDto.java
    ├── entity/
    │　 ├── BaseEntity.java
    │　 └── User.java
    ├── repository/
    │　 └── UserRepository.java
    └── service/
        ├── impl.java
        │   └── UserServiceImpl.java
        └── UserService.java

```


# Date_Management API

유저 회원가입과 로그인, 일정 CRUD, 댓글 CRUD, 페이징 기능을 제공합니다.

https://documenter.getpostman.com/view/38015679/2sBXVfiBEQ

# ERD