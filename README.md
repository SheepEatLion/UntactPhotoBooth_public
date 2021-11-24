# :camera: UntactPhotoBooth :camera:

### :bookmark_tabs: 개요
- 프로젝트 목적
- 프로젝트 아이디어 및 배경 설명
- 사용된 기술 스택
- 구현 방식
- 흐름도
- 접속 url
- - -
### :pushpin: 프로젝트 목적 
- 졸업작품 제출을 위한 목적으로 시작한 프로젝트입니다.
- 개인 사정상 갑작스레 시작하게되어 약 2주의 시간동안 직접 기획하고 개발한 뒤, 배포까지 마쳤습니다.

- - -

### :speech_balloon: 프로젝트 아이디어 및 배경
- 코로나의 등장에도 불구하고 취업 준비는 계속 되어야하기에 증명사진 또한 필수입니다.
- 하지만 코로나가 심할 때에 대면으로 사진관에 가서 사진을 찍기 난감한 사람들을 위해 제작했습니다.
- 아이러니하게도 최근에는 온라인으로 이루어지는 활동들이 늘어나면서 노트북 또는 휴대폰에도 고성능의 캠이 장착되어 출시되고 있습니다.
- 비대면으로 증명사진을 촬영하고 이를 분석할 수 있도록 도와주는 웹사이트를 기획 및 개발하게 되었습니다.

- - -

### :shopping_cart: 기술 스택
![image](https://user-images.githubusercontent.com/16449498/143180253-ed12be1c-64f0-4433-aadd-1f04dd567057.png)

- - -

### ✔️ 테이블 설계
- 데이터베이스는 AWS RDS의 MariaDB 사용
- 테이블은 2개로 하고 User 테이블과 History 테이블이 있다.
- User 테이블은 회원정보를 관리하고 History 테이블에서는 회원이 업로드한 기록을 관리한다. 


![image](https://user-images.githubusercontent.com/16449498/143179557-c9ef7070-b831-4888-8dfb-f495faf71202.png)

- - -

### ✔️ ORG Chart
- 일반적인 ORG Chart에서 일부 변형
- 빨간색은 웹페이지를 의미하며 회색은 기능 또는 권한을 의미한다.


![image](https://user-images.githubusercontent.com/16449498/143179427-898f98a0-f966-4cef-9965-64070c3589ce.png)

- - -

### ✔️ Flow Chart
- 시스템의 전체적인 흐름도
- 소셜 로그인을 통해 서버리스 BaaS 형태로 로그인 구현
- 사진촬영은 WebRTC 기술 사용
- 얼굴 분석은 네이버 클로바 API 사용
- SSL 인증서를 사용한 배포는 AWS Route 53, ELB, ACM 사용

![image](https://user-images.githubusercontent.com/16449498/143179468-1ab1fe5a-8815-458f-8234-42244eb6086c.png)


- - -

### ✔️ issue 사항

배포 과정에서 WebRTC의 보안 이슈 발생 -> SSL 인증서 발급 후, Https 로 배포하여 해결
