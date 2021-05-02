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

### **[전체 기능]** 아파트를 부탁해 액터별 사용할 수 있는 기능 <br>
 Actor 1. Bdmin : 아파트 관리, 어드민 계정 CRUD, 어드민 공지사항 CRUD<br>
 Actor 2. Admin : 로그인&로그아웃, 정보변경, Generation CRUD, 관리비 CRUD, 차량 CRUD, 차량 신청 승인/반려, 일정 CRUD, 투표 CRUD, 게시글&댓글 비공개처리, 1:1채팅<br>
 Actor 3. Generation : 로그인&로그아웃, 정보변경 , GenerationWon  CRUD, 게시글 및 댓글 CRUD, 관리비 결제, 차량 등록 신청, 주차현황 확인, 등록차량 상태 확인, 아파트 주변 공공기관 확인, 내가 쓴 게시글 목록 확인, 내 알람 확인<br>
 Actor 4. GenerationWon : 아파트 안건 투표하기<br>

### **[패키지별 구현 기능]**
 1. com/kh/aboo/bdmin : Bdmin 액터가 사용할 기능 패키지로 management, notice 패키지 존재<br>
 > management<br>
 - 서비스 이용중인 apartment 관리와 Admin 계정 CRUD 기능을 구현한 패키지<br>
 - ManagementController의 Method : apartment 목록 출력 및 수정, 서비스 신청서 출력 및 승인/반려, Admin 계정 CRUD, Admin 계정 신청서 승인/반려, Admin 계정 승인시 아이디 및 이메일 중복 확인<br>

 > notice<br>
 - Admin에게 보여줄 공지사항 CRUD 기능을 구현한 패키지<br>
 - NoticeController의 Method : 공지사항 목록 출력, 공지사항 CRUD<br>

 2. com/kh/aboo/admin : Admin 액터가 사용할 기능 패키지로 car, index, mgmtfee, schedule, vote 패키지가 존재<br>
 > car<br>
 - 차량 CRUD와 QR코드를 생성/읽는 기능 구현한 패키지<br>
 - CarContoller의 Method : 차량 및 차량신청 목록 출력, 차량 CRUD, 차량 QR 코드 생성, 세대정보/차량번호로 검색<br>

 > mgmtfee<br>
 - 관리비 Excel 양식 다운로드 및 업로드, CRUD 기능 구현한 패키지<br>
 - MgmtfeeController의 Method : 관리비 Excel 양식 다운로드, 작성한 관리비 Excel 업로드, 관리비 목록 출력, 관리비 CRUD, 미납/고지월별/관리비번호/세대정보로 검색<br>

 > vote<br>
 - 투표를 관리할 때 사용하는 기능을 구현한 패키지<br>
 - AdminVoteController의 Method : 투표 생성, 투표 수정, 투표 삭제, 투표 종료<br>

 3. com/kh/aboo/board : Generation 액터와 Admin 액터가 사용할 게시판 CRUD 패키지로 info, interior, used 패키지가 존재<br>
 > interior<br>
 - Generation 액터가 인테리어 게시판의 글 및 댓글을 CRUD할 기능을 구현한 패키지<br>
 - Admin 액터가 인테리어 게시판 글 및 댓글을 관리할 수 있는 기능을 구현한 패키지<br>
 - InteriorController의 Method : 인테리어 게시글 목록 출력, 인테리어 게시글 상세화면 출력, 인테리어 게시글 CRUD, 인테리어 댓글 CRUD, 인테리어 게시글 비공개 처리(Admin 권한), 인테리어 댓글 비공개 처리(Admin 권한), 인테리어 게시글 제목으로 검색<br>

 > used<br>
 - Generation 액터가 중고 게시판의 글 및 댓글을 CRUD할 기능을 구현한 패키지<br>
 - Admin 액터가 중고 게시판 글 및 댓글을 관리할 수 있는 기능을 구현한 패키지<br>
 - UsedController의 Method : 중고 게시글 목록 출력, 중고 게시글 상세화면 출력, 중고 게시글 CRUD, 중고 댓글 CRUD, 중고 게시글 비공개 처리(Admin 권한), 중고 댓글 비공개 처리(Admin 권한), 중고 게시글 제목으로 검색, 중고 거래 중/거래 완료 검색<br>

#### 4. com/kh/aboo/myapt : Generation 액터와 GenerationWon 액터가 사용할 기능 패키지로 aptSchedule, institutions, parking, vote 패키지가 존재
#### > institutions
 - Generation 액터가 본인 아파트 주변의 공공기관 위치 정보를 얻을 때 사용하는 기능을 구현한 패키지<br>
 - InstitutionsController의 Method : view에서 아파트 주변 공공기관의 위치를 출력하기 위해 아파트의 위치 정보를 view로 보내는 메서드가 존재<br>

#### > parking
 - Generation 액터가 본인 아파트 현재 주차가능 대수를 확인하고 차량 등록 신청하는 패키지<br>
 - ParkingController의 Method : 주차 가능 대수 출력, 차량 등록 신청<br>

#### > vote
 - Generation 액터가 생성된 투표를 확인할 수 있는 기능을 구현한 패키지<br>
 - GenerationWon 액터가 생성된 투표에 참여할 수 있는 기능을 구현한 패키지<br>
 - VoteController의 Method : 투표 목록 출력, 투표 상세화면 출력, 투표 참여 여부 확인, 전화번호 문자 인증, 세대원 인증, 표 행사, 투표 제목으로 검색<br>


 5. com/kh/aboo/mypage : Generation 액터가 사용할 기능 패키지로 myalarm, mycar, mymgmtfee, writelist 패키지가 존재<br>
 > mycar<br>
 - 아파트에 등록된 본인 차량의 QR 이미지와 주차 상태를 파악하는 패키지<br>
 - MyCarController의 Method : 내가 등록한 차량 출력, QR 코드 다운로드 기능<br>

 > mymgmtfee<br>
 - 본인 관리비 목록을 확인/결제 하는 패키지<br>
 - MyMgmtfeeController 의 Method : 고지된 관리비 목록 출력, 관리비 상세 출력, 관리비 결제<br>

 > writelist<br>
 - 본인이 정보&질문 게시판, 인테리어 게시판, 중고거래 게시판에 작성한 글을 확인할 수 있는 기능을 구현한 패키지<br>
 - WriteListController의 Method : 내가 작성한 정보&질문 게시글 목록 출력, 내가 작성한 인테리어 게시글 목록 출력, 내가 작성한 중고거래 게시글 목록 출력<br>

 6. com/kh/aboo/user : ABOO 사이트 사용자들을 관리하는 패키지로 apartment, generation, generationWon, manager 패키지가 존재<br>
 > apartment<br>
 - ABOO 사이트와 협력하는 아파트들의 정보를 관리<br>

 > generation<br>
 - Generation 액터의 정보를 관리하고 GenerationWon 액터를 관리하는 기능을 구현한 패키지<br>
 - GenerationController의 Method : 로그인, 로그아웃 ,아이디 찾기, 비밀번호 찾기, 회원 정보 수정, 문자 인증, 이메일 인증, 세대원 CRUD<br>

 > generationWon<br>
 - GenerationWon 액터의 정보를 관리<br>

 > manager<br>
 - Admin 액터의 정보를 관리하고 Generation 액터를 관리하는 기능을 구현한 패키지<br>
 - AdminController의 Method  : 로그인, 로그아웃, 아이디 찾기, 비밀번호 찾기, 회원 정보수정, 문자인증, 이메일인증, 세대 보기, 세대 검색, 세대 동으로 검색, 세대 추가, 세대 초기화, 세대 삭제<br>

 7-1. com/kh/aboo/common : [공통모듈1] 공통주소코드, 공통에러코드, 권한관리, 채팅, 메일, util 기능이 있는 패키지로 code, echo, exception, interceptor, mail, util 패키지가 존재<br>

 7-2. com/kh/aboo/common/util : [공통모듈2] 인코딩, 파일업로드 및 다운로드, 페이징처리, QR코드 생성, 랜덤 기능이 있는 패키지로 encoding, file, paging, qrcode, random 패키지가 존재<br>




