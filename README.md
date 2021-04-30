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






# 선영

## **1. generation**
기능 : 로그인, 아이디 찾기, 비밀번호 찾기, 회원 정보수정, 문자인증, 이메일인증, 세대원 보기, 세대원 추가, 세대원 수정, 세대원 삭제

접근 방법 : Q 해당 아파트의 세대를 어떻게 구별할 것이며 어떻게 아이디의 중복을 막을 것인가.

<img width="531" alt="1" src="https://user-images.githubusercontent.com/74293481/116706165-46402000-aa08-11eb-9efb-046885303d2a.PNG">


##### -아파트의 기본 키를 세대 테이블 외래키로 두어 구별했다.

```
String Separator = adminRepository.selectApartmentBySeparator(apartmentIdx);
String id = Separator + generation.getBuilding() + "d" + generation.getNum() + "h";

generation.setId(id);

```
##### -아파트 구분자를 받아서 세대의 같은 동, 호의 중복을 막았다.

## **2. manager**
기능 : 로그인, 아이디 찾기, 비밀번호 찾기, 회원 정보수정, 문자인증, 이메일인증, 세대 보기, 세대 검색, 세대 동으로 검색, 세대 추가, 세대 초기화, 세대 삭제

접근 방법 : Q 아파트와 관리인을 어떻게 연결해줄 것인가

<img width="560" alt="2" src="https://user-images.githubusercontent.com/74293481/116706812-ff9ef580-aa08-11eb-939e-424d8bd844b5.PNG">

##### -아파트의 기본 키를 관리인 테이블 외래키로 두어 구별했다.


## **3. mymgmtfee**
기능 : 관리비 결제

사용 라이브러리 : 아임포트

```
$("#check_module").click(function () {
        var IMP = window.IMP; 
        IMP.init('');
        IMP.request_pay({
            pg: 'html5_inicis',
          
            merchant_uid: 'merchant_' + new Date().getTime(),
           
            name: '주문명:관리비 고지서',
           
            amount: periodPayment,
           
            buyer_email: 'aboo@siot.do',
            buyer_name: 'ABOO',
            buyer_tel: '010-1234-5678',
            buyer_addr: '경기도 성남시',
            buyer_postcode: '123-456',
            m_redirect_url: 'https://www.yourdomain.com/payments/complete'

        }, function (rsp) {
            console.log(rsp);
            if (rsp.success) {
           	    var msg = '결제가 완료 되었습니다;';
                          
                payment(rsp.pay_method,rsp.paid_amount);


            } else {
                var msg = '결제에 실패 하였습니다;';
                msg += '에러 내용 : ' + rsp.error_msg;

            }
            alert(msg);
        });
    });

let mgmtfeeIdx = $("#mgmtfeeIdx")[0].defaultValue;
  	console.dir(mgmtfeeIdx)
  	
	let payment = (method,amount) => {
		
	    const url = '/mypage/mymgmtfee/payment';
	    let paramObj = new Object(); 
	    paramObj.mgmtfeeIdx = mgmtfeeIdx; 
	    paramObj.paymentMethod = method; 
	    paramObj.paymentAmount = amount; 


	    let headerObj = new Headers();
	    headerObj.append("content-type","application/json
	    fetch" >");
	    fetch(url,{
	       method:"post",
	       headers:headerObj,
	       body:JSON.stringify(paramObj)
	    }).then(response => {
	       if(response.ok){
	          return response.text();   
	       }
	       throw new AsyncPageError(response.text());
	    }).then((text) => {
	       if(text == 'success'){
	     		location.href = "/mypage/mymgmtfee";
	       }else{
	       }
	    }).catch(error => {
	       error.alertMessage();
	    });	
    
	}


```
##### -결제 성공 시 payment()로 미납->완료 UPDATE, 결제 테이블의 INSERT 프로시저를 돌린다. 


## **4. used**
기능 : 게시판 보기, 게시글 상세보기, 거래 중/거래 완료로 검색, 키워드 검색, 게시글 작성, 게시글 수정, 게시글 삭제, 게시글 비공개, 댓글 작성, 댓글 수정, 댓글 삭제, 댓글 비공개

접근 방법 : Q 아파트 단위로 게시판이 존재해야 한다.

<img width="608" alt="3" src="https://user-images.githubusercontent.com/74293481/116709317-86ed6880-aa0b-11eb-91a9-6ca3cdcffb5b.PNG">

##### -게시판에 글을 작성할 때 로그인된 세대의 SESSION에서 APARTMENT_IDX를 받아 INSERT.
##### -게시판을 보여줄 때 로그인된 세대의 SESSION에서 APARTMENT_IDX를 받아 해당 아파트의 게시글만 보여준다.


## **5. echo**
기능 : 접속 종료, 세대 종료 시 관리자에게 안내

사용 클래스 : Spring Framework의 TextWebSocketHandler Class
사용한 이유 : 스프링 프레임워크 내 기능을 사용하고자 했으며 관리자와 세대가 계속 통신을 주고받아야 하므로 양방향을 지원하는 Web Socket을 사용했다.

```
@Override
public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

	int idx = sessionList.indexOf(session);
	String id = (String) nameList.get(idx);
	sessionList.remove(session);
	nameList.remove(idx);


	Generation generation = (Generation) session.getAttributes().get("generation");
	String apartmentIdx = generation.getApartmentIdx();

	disconnectList(id, apartmentIdx);

}
	
public void disconnectList(String id, String apartmentIdx) throws IOException {

	for (int i = 0; i < nameList.size(); i++) {
		if (nameList.get(i).equals(apartmentIdx)) {
			sessionList.get(i).sendMessage(new TextMessage("[안내] " + id + " 세대가 퇴장하셨습니다."));
		}
	}
}


```
##### -퇴장 시 sessionList, nameList에서 지운다.

