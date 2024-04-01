# 👋 MEFI 👋
### 팀 단위 화상회의와 다자간의 문서편집이 가능하도록 도와주는 협업툴 서비스

<br>

### 💚 MEFI 소개 및 시연 영상

<br>

### 💚 MEFI 서비스 화면

<br>

### 💚 주요 기능
- 서비스 설명 : 팀 단위 화상 통화와 다자간의 문서편집이 가능하도록 도와주는 협업툴 서비스입니다.
- 주요 기능
  - webRTC를 통해 실시간 화상 회의
  - 화상 회의 도중, 다중간의 문서 작업과 채팅으로 업무 효율을 높일 수 있음
  - 회의가 끝나면 자동으로, 작성한 문서가 저장됨. 관련 문서를 다운로드 할 수 있음.
  - 회의 예약 시, 해당 회의와 관련된 문서를 회의 상세 페이지에 업로드 및 다운로드 가능
  - 회의 예약은 팀장만 가능하며, 팀 생성 시, 팀을 생성한 사람이 팀장 권한을 가짐.

<br>

### 💚 개발 환경 
🌱 백앤드
- intellij
- spring boot 3.2.1
- spring-boot-jpa
- spring security 6.1.3
- java 17
- MySQL 8.0.36

<br>

🌱 프론트
- Visual Studio Code
- Vue3
- Node.js 20.10.0
- Vuetify
- figma

<br>

🌱 라이브러리
- openvidu 2.29.0
- Yjs 13.6.10
- Quill 1.3.7

<br>

🌱 Infra
- Docker
- Jenkins 2.426.2
- AWS EC2
- AWS S3 
- Server 20.04.6 LTS (GNU/Linux 5.15.0-1051-aws x86_64)
- Nginx 1.24.0

<br>

### 💫서비스 아키텍처 
![img](./readme/MEFI%20아키텍처.PNG.png)

<br>

### 💫CICD

<br>

### 💥기술 특이점
참고 자료 : https://github.com/yesfordev/homedong

💥 WebRTC(Openvidu)

> Openvidu로 할 수 있는 실시간 채팅, 화면 공유 기능 뿐만이 아니라 회의가 종료되어 세션이 끝나면 구성원들이 함께 작성한 문서를 PDF로 저장하는 등 여러 기능을 구현하였습니다. 추가적으로 여러 레이아웃 배치 구현하여 사용자가 화면을 편하게 이용할 수 있게 노력하였으며, 팀장 권한에 따라 세션을 생성하고 회의를 종료하는 등 서버에서 Openvidu api 메서드를 통해 여러 기능을 구현하였습니다.

💥 Yjs & Quill

> 한 화면에서 문서 작업을 동시에 하기 위해 CRDT 알고리즘이 필요하였으며, 이를 구현해놓은 Yjs 라이브러리와 웹 에디터인 Quill을 연동하여 동시 문서 편집 기능을 구현하였습니다. Quill에 내장된 toolbar 외에도 markdown 형식을 통해 문서를 작성할 수 있게 하였으며, Openvidu를 통한 회의가 종료되었을 경우 Quill에 저장된 내용을 PDF 파일 형식으로 변환하여 S3 storage에 업로드할 수 있게 하였습니다. 또한 문서를 작성하는 사용자의 정보를 커서에 삽입하여 구성원들이 어디에서 문서를 편집하고 있는지 확인할 수 있습니다.

💥 S3
(부광)

💥 실행
- 프로젝트 클론 
  ```
  // 원격 저장소 로컬 저장
  git clone {Github 주소}

  // 폴더 이동
  cd S10P12D204
  ```
- 프론트 로컬 실행
  ```
  // 폴더 이동
  cd front-end

  // node.js 설치
  npm install

  // 로컬 프론트 실행
  npm run dev 
  ```
- 백엔드 로컬 실행
  ```
  // 폴더 이동
  cd backend
  
  // 프로젝트 빌드
  ./gradlew clean build

  // 프로젝트 실행
  java -jar build/libs/backend-0.0.1.jar
  ```

<br>

### 👨‍👩‍👧 협업툴
- Git
- Jira
- Gerrit
- Notion
- Mattermost

<br>

### 📝 요구사항 정의서
...

<br>

### 🎨 화면 설계서
...

<br>

### ⚡️ Git 컨벤션
⚡️제목
- Feat : 새로운 기능을 추가하는 경우
- Fix : 버그를 고치는 경우
- Docs : 문서를 수정한 경우
- Style : 코드 포맷 변경, 세미콜론 누락, 코드 수정이 없는 경우
- Refactor : 코드 리펙토링
- Test : 테스트 코드. 리팩토링 테스트 코드를 추가했을 때
- Chore : 빌드 업무 수정, 패키지 매니저 수정
- Design : CSS 등 사용자가 UI 디자인을 변경했을 때
- Rename : 파일명(or 폴더명)을 수정한 경우
- Remove : 코드의 삭제가 있을때

<br>

⚡️commit message 예시
```
Feat : "추가 로그인 함수"
```

<br>


### ⚡️ Git Flow
- Git flow 사용한 브랜치
  - master
  - develop be/ad : 배포
  - sprint{N} : 스프린트 주차별 브랜치
  - feature : 세부 작업 브랜치

<br>

- git flow 기획한 이유
  - 개발 시, 맡은 기능 별로 develop 하위에 feature 브랜치 생성
  - 개발 완료 시, 해당 feature 브랜치를 develop에 merge한다.
  - 개발 테스트 시, develop에 파이프라인 연결하여 배포 및 테스트 작업 진행
  - 개발 완료 및 테스트 완료 시, master 브랜치로 배포 진행

<br>

- Git 브랜치 이름 컨벤션
  ```
  // Back-end
  feature/도메인/세부기능

  // Front-end
  ....
  ```

<br>

### 🐛 코드 컨벤션
🐛 프론트 코드 컨밴션
...

<br>

🐛 백앤드 코드 컨밴션
- JPQL vs **QueryDSL**
- File 구조
```
Domain
    Entity
    Repository
    RepositoryCustom
    RepositoryImpl
    Controller
    Service
    ServiceImpl
    RequestDto
    ResponseDto
```
- 

<br>

### 👨‍👩‍👧 Jira
협업 및 일정, 업무 관리를 위해 Jira를 이용하였습니다. 매주 월요일 오전 회의를 통해 한 주 동안 진행할 스프린트를 계획하고, 진행할 스토리와 테스트를 해당 스프린트에 생성하여 등록하였습니다. 또한 매주 금요일 오후에 회의를 진행하여 해당 스프린트에 대한 회고를 진행하였습니다.
- Epic : 큰 도메인으로 분류
- Stroy : 사용자 관점에서 기능을 사용하는 상황을 기술
- subtask : 사용자가 사용할 기능을 개발할때 필요한 목록으로 디테일하게 기술, 소요된 시간 및 스토리포인트 할당

<br>

### 👨‍👩‍👧 Notion
개발 환경 구축에 필요한 정보, 라이브러리 및 기술 관련 링크, 회의록 작성 및 프로젝트 진행에 관련된 산출물을 기록하고 공유하는 용도로 사용하였습니다. 컨벤션 및 브랜치 전략 등 또한 노션에 기록함으로써, 모두가 항시 확인할 수 있도록 관리하였습니다.

<br>

### 👨‍👩‍👧 Scrum
매일 아침 9시에 스크럼 회의를 10분 동안 진행하며, 어제 했던 일과 오늘 진행할 업무, 발생한 이슈를 공유하는 시간을 가졌습니다. 스크럼을 통해 팀원들의 현재 상황을 파악할 수 있고, 프로젝트에 대해 꾸준히 모니터링할 수 있었습니다.

<br>

### 🎨 ERD
![alt text](./readmefile/ERD.png)

<br>

### 🎨 EC2 포트 정리
| 이름 | 내부 포트 | 외부 포트 |
| :-----: | :-----: | :-----: |
| Vue | 5173 | 5173 |
| SpringBoot | 8080 | 8080 |
| Jenkins | 9000 | 9000 |
| MySQL | 3306 | 3306 |
| http | 80 | ----- |
| https | 443 | ----- |
| openvidu-coturn-1 | 3478 | 3478 |

<br>

### 👨‍👩‍👧 팀원 역할
😃정철주

😊김건우

😘박정환

😝이지연

😛김인호

😀조수현

<br>

### 👨‍👩‍👧 프로젝트 소감
😃정철주

😊김건우

😘박정환

😝이지연

😛김인호

😀조수현