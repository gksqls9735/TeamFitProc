# TeamFitProc

## 설명
오라클 데이터 베이스를 활용한 자바 기반 프로젝트입니다.
프로시저를 활용하였습니다.
TeamFitdb는 사용자가 피트니스 루틴을 관리할 수 있도록 도와주는 자바 기반 프로젝트입니다. 
사용자는 운동을 검색하고 선택한 운동을 장바구니에 담아 관리할 수 있습니다.
사용자는 운동 강사와 1대1 매칭을 할 수 있습니다.

## 목차

- [기능](#기능)
- [프로젝트 구조](#프로젝트구조)
- [시작](#시작)
- [사용법](#사용법)
- [스크린샷](#스크린샷)
- [연락처](#연락처)

  ## 기능
- 사용자 관리
- 강의 검색
- 강의 신청 기능

## 프로젝트구조
```
TeamFitdb_project_structure/
├── .classpath
├── .project
├── LICENSE
├── README.md
├── bin/
└── src/
    ├── controller/
    │   ├── CartDAO.java
    │   ├── CartRegisterManager.java
    │   ├── DBUtil.java
    │   ├── ExerciseDAO.java
    │   ├── ExerciseRegisterManager.java
    │   ├── MatchsDAO.java
    │   ├── MatchsRegisterManager.java
    │   ├── UserDAO.java
    │   ├── UserRegisterManager.java
    ├── main/
    │   └── TeamfitMain.java
    ├── model/
    │   ├── CartVO.java
    │   ├── ExerciseVO.java
    │   ├── MatchsVO.java
    │   ├── UserVO.java
    └── view/
        ├── LOGIN_CHOICE.java
        ├── LoginViewer.java
        ├── MENU_CHOICE.java
        ├── MenuViewer.java
        ├── MYPAGE_CHOICE.java
```
- `src/`: 애플리케이션의 소스 코드가 포함되어 있습니다.
- `bin/`: 컴파일된 바이트코드가 포함되어 있습니다.
- `.classpath` 및 `.project`: Eclipse 특정 파일로 프로젝트 구성을 관리합니다.
- `.git/`: Git 구성 및 리포지토리 데이터가 포함되어 있습니다.

  ## 시작

### 필수 조건
- Java Development Kit (JDK) 8 이상
- Eclipse IDE (또는 다른 Java IDE)
- Git

### 단계

1. **리포지토리 클론:**

    ```sh
    git clone https://github.com/gksqls9735/teamfitproc.git
    ```

2. **프로젝트 디렉토리로 이동:**

    ```sh
    cd teamfitproc
    ```

3. **IDE에 프로젝트 가져오기:**

    - Eclipse IDE를 엽니다.
    - `File` > `Import`로 이동합니다.
    - `Existing Projects into Workspace`를 선택합니다.
    - 프로젝트를 클론한 디렉토리로 이동하여 선택합니다.
    - `Finish`를 클릭합니다.

4. **프로젝트 빌드:**

    Maven 또는 Gradle 빌드 구성을 IDE가 인식하는지 확인합니다.

## 사용법

1. **애플리케이션 실행:**

    - `src` 디렉토리로 이동합니다.
    - 메인 클래스를 찾습니다 (보통 `public static void main(String[] args)` 메서드를 포함).
    - 메인 클래스를 Java 애플리케이션으로 실행합니다.

2. **애플리케이션 사용:**

    - 화면에 표시되는 지침에 따라 팀 운동 강의 기록, 강사 매칭을 추가, 업데이트, 삭제 및 조회합니다.
  
  ## 스크린샷

### 회원 등록 및 로그인
![login_signup](https://github.com/gksqls9735/TeamFitdb/assets/166088728/5f3195c6-4454-415b-aa95-d016a95cc88f)

### 강의 등록
![lecture_registration](https://github.com/gksqls9735/TeamFitdb/assets/166088728/3e4e0cc6-1452-42bc-ab26-a840ba5a5bda)

### 강사 매칭
![instructor_matching](https://github.com/gksqls9735/TeamFitdb/assets/166088728/aaf89f0e-e5ba-4dc9-9b48-70b07510c5a3)

### 정보 확인
![myinfo1](https://github.com/gksqls9735/TeamFitdb/assets/166088728/d0728753-3b69-46a7-b801-759a91aa799a)
![myinfo2](https://github.com/gksqls9735/TeamFitdb/assets/166088728/abc59293-ce6f-4573-ac14-55b84652f1a3)


## 연락처
이메일 - [gksqls9735@gmail.com](mailto:gksqls9735@gmail.com)

프로젝트 링크: [https://github.com/gksqls9735/teamfitProc](https://github.com/gksqls9735/teamfitProc)
