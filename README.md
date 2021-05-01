# aboo
kh정보교육원 final project

## 아파트를 부탁해! 🏘
<img src="https://postfiles.pstatic.net/MjAyMTA0MDJfMjAz/MDAxNjE3MzM0MTY0NDI5.HUB1sHczF56YK32-pnbMlXXRAS-tiVgUtoTu5477HSog.VJ0eYygCvEDLXPxVdHBgoGDIpDWA3rC--ipMKwXXykog.PNG.psuny1031/logo_b.png?type=w773" width="250px" height="200px">

### **기획 목적**

불과 몇년 전까지는 유치원, 학교, 학원 등등 알람 관련된 정보 전달은 종이 혹은 우편으로 보내서 전달을 했었습니다.<br>
그리고 최근까지 교육 관련 기관들은 정보 전달 매체가 웹, 앱으로 많이 변경되어 세대가 바뀌고 있는 시대를 살아가고 있습니다.<br>
여기서 단 한 곳! 이곳만이 아직 종이로 정보전달을 하고 있는데요. 그곳은 바로 아파트입니다!<br>
건물 외벽 도색, 부녀회장 선출, 엘리베이터 점검, 층간소음 주의 등등 각 1층 알림판이나 엘리베이터에 종이를 부착해서 알려주거나 방송으로 정보전달을 해주고 있습니다.<br>
아파트는 사소한 정보부터 큰 투표까지 많은 정보를 서로 주고 받아야 하기에 관리자도 입주민도 불필요한 커뮤니케이션을 많이 하게 됩니다.<br>
특히나 비대면이 대세인 요즘! 아파트 비대면 관리 사이트를 구현해 편의 제공을 하고자 합니다 :)<br>

### **구현 기능**

선영 : 회원관리(문자-네이버클라우드플랫폼), 관리비결제시스템(아임포트), 중고게시판 CRUD(ckedior), 채팅<br>
희원 : 알림(문자-네이버클라우드플랫폼), 정보&질문게시판 CRUD, 달력(full calendar)<br>
민희 : 투표 CRUD(문자-네이버클라우드플랫폼), 인테리어게시판 CRUD, 주변 공공기관 정보(카카오 지도)<br>
아영 : 관리비 CRUD(POI 라이브러리), 차량 CRUD(zxing), 차량 QR, 주차현황, 채팅<br>

### **패키지별 기능**
1. com/kh/aboo/admin : Admin 액터가 사용할 관리비 업로드&다운로드, 차량QR등록, 투표생성, 일정생성, 세대 권한 관리 기능이 있는 패키지
2. com/kh/aboo/bdmin : Bdmin 액터가 사용할 아파트 관리, Admin 계정 관리 기능이 있는 패키지 
3. com/kh/aboo/board : 정보&질문 게시판, 인테리어 게시판, 중고거래 게시판 CRUD 패키지
4. com/kh/aboo/myapt : 내 아파트 일정, 주변공공기관, 주차현황 및 차량 등록 신청, 투표하기 기능이 있는 패키지
5. com/kh/aboo/mypage : 내 알람, 내 차량, 내 관리비 확인 및 결제, 내가 작성한 글 확인기능이 있는 패키지
6. com/kh/aboo/user : 아파트, 세대, 세대원 3개 액터의 CRUD 패키지
7. com/kh/aboo/common : [공통모듈1] 공통주소코드, 공통에러코드, 권한관리, 채팅, 메일 기능이 있는 패키지
8. com/kh/aboo/common/util : [공통모듈2] 인코딩, 파일업로드 및 다운로드, 페이징처리, QR코드 생성, 랜덤 기능이 있는 패키지



