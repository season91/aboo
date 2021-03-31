<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/generationhead.jsp" %>
<!DOCTYPE html>
<html>
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<body>
	  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="/index">ABOO</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>
	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item"><a href="/index" class="nav-link">Home</a></li>
	          <li class="nav-item"><a href="/about" class="nav-link">About</a></li>
	          <li class="nav-item"><a href="/myapt/schedule" class="nav-link">MyApt</a></li>
	          <li class="nav-item"><a class="nav-link" href="/board/info/listinfo">Board</a></li>
	          <li class="nav-item active"><a href="/mypage/modifyinfo" class="nav-link">MyPage</a></li>
	          <li class="nav-item cta"><a href="/login" class="nav-link"><span>Login</span></a></li>
	        </ul>
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->

    <section class="home-slider owl-carousel">
      <div class="slider-item bread-item" style="background-image:url(../../../resources/abooimg/logo_w.png);" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container-fluid">
          <div class="row slider-text align-items-center justify-content-center" data-scrollax-parent="true">

            <div class="col-md-8 mt-5 text-center col-sm-12 ftco-animate" data-scrollax=" properties: { translateY: '70%' }">
              <p class="breadcrumbs" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }"><span class="mr-2"><a href="/mypage/myalarm">My alarm</a></span><span class="mr-2"><a href="/mypage/myvehicle">My vehicle</a></span><span class="mr-2"><a href="/mypage/modifyinfo">My Information</a></span><span class="mr-2"><a href="/mypage/writelist">My write list</a></span></p>
	            <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">Management Fee detail</h1>
            </div>
          </div>
        </div>
      </div>
    </section>

 <section class="ftco-section bg-light">
    	<div class="container">
    		<div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2 class="mb-4"> ${mgmtdate.YEAR}년 ${mgmtdate.MONTH }월 관리비 상세 내역</h2>
            <p>관리비 관련 문의사항은 관리자에게 문의해주시기 바랍니다.</p>
          </div>
        </div>
    		<div class="row d-flex justify-content-center">
	        <div class="col-lg-6 col-md-10 ftco-animate ">
	          <div class="block-7">
	            <div class="text-center">
	            <c:choose>
	            	<c:when test="${mgmtfee != null }">
	            		<span class="price"><span class="number">관리비 고지서</span></span>
			            <c:choose>
	            			<c:when test="${mgmtfeeOverdue != null }">
	            				<span class="price"><span class="number period-payment"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.periodPayment + mgmtfeeOverdue.overdueFee}"/> </span></span>
	            			</c:when>
	            			<c:otherwise>
	            				<span class="price"><span class="number period-payment"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.periodPayment}"/></span></span>
	            			</c:otherwise>
	            		</c:choose>
	            		<input type="hidden" id ="mgmtfeeIdx" value="${mgmtfee.mgmtfeeIdx}">
			            <span class="excerpt d-block"><strong>관리비 작성일 </strong> ${mgmtfee.mgmtWriteDate }</span>
			            <span class="excerpt d-block"><strong>관리기간 </strong>${mgmtfee.mgmtStartDate } ~ ${mgmtfee.mgmtEndDate}</span>
			            <ul class="pricing-text mb-4">
			               <li><strong>일반관리비 </strong><span id = "gnrlMgmtFee" ><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.gnrlMgmtFee}"/></span></li>
			              <li><strong>청소비 </strong><span id = "cleanFee"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.cleanFee}"/></span></li>
			              <li><strong>승강기유지비 </strong><span id = "elvtrMnfee" ><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.elvtrMnfee}"/></span></li>
			              <li><strong>세대전기료 </strong><span id = "genElctr"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.genElctr}"/></span></li>
			              <li><strong>공동전기료 </strong><span id = "comonElctr"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.comonElctr}"/></span> </li>
			              <li><strong>세대수도료 </strong><span id = "genWater"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.genWater}"/></span> </li>
			              <li><strong>하수도료 </strong><span id = "sewer"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.sewer}"/></span> </li>
			              <li><strong>경비비 </strong><span id = "expenses"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.expenses}"/></span></li>
			              <li><strong>세대감면액 </strong><span id = "genReduction"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.genReduction}"/></span></li>
			              <c:if test="${mgmtfeeOverdue != null }">
			              	<li class="overdue-fee"><strong>연체료 </strong><span id = "overdueFee" ><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfeeOverdue.overdueFee}"/></span></li>
			              </c:if>
			              <li><strong>납기내 금액 </strong><span id= "periodPayment"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.periodPayment}"/></span></li>
			              <li><strong>납기일 </strong> ${mgmtfee.dueDate }</li>
			              <li>납부기한을 넘겨 납부하면 연체료가 일할계산되어 다음달 관리비에 포함됩니다.</li>
			            </ul>
				          <a class="btn btn-primary d-block px-3 py-3 mb-4 btn-payment" id = "check_module">결제하기</a>
	            	</c:when>
	            	<c:otherwise>
	            		<span class="price"><span class="number">조회내역이 없습니다.</span></span>
	            	</c:otherwise>
	            </c:choose>
		            
	            </div>
	          </div>
	          <div class="row d-flex justify-content-center">
	          	 <a href="/mypage/mymgmtfee" class="btn btn-primary btn-primary-2 p-3 px-xl-5 py-xl-3" style="background: linear-gradient(45deg, #56c8fb 0%, #627bed 100%); border: none;">목록으로</a>
	          </div>
	        </div>
	      </div>
    	</div>
    </section>

    <footer class="ftco-footer ftco-bg-dark ftco-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">ABOO</h2>
              <p>아파트를 부탁해!<br>
              아파트 주변 공공기관부터 투표, 관리비납부, 차량등록, 층간소음 문의 등 관리하기 편한 기능을 제공합니다.
              </p>
              <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4 ml-md-5">
              <h2 class="ftco-heading-2">Useful Links</h2>
              <ul class="list-unstyled">
                <li><a href="/myapt/parking" class="py-2 d-block">Parking</a></li>
                <li><a href="/board/info/listinfo" class="py-2 d-block">Info Board</a></li>
                <li><a href="/board/interior/intlist" class="py-2 d-block">Interior Board</a></li>
                <li><a href="/board/used/usedlist" class="py-2 d-block">Used Board</a></li>
                <li><a href="/myapt/schedule" class="py-2 d-block">Apt Schedule</a></li>
                <li><a href="/myapt/institutions" class="py-2 d-block">Institutions</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
             <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">Navigational</h2>
              <ul class="list-unstyled">
                <li><a href="/index" class="py-2 d-block">Home</a></li>
                <li><a href="/about" class="py-2 d-block">About</a></li>
                <li><a href="/myapt/schedule" class="py-2 d-block">MyApt</a></li>
                <li><a href="/baord/info/listinfo" class="py-2 d-block">Board</a></li>
                <li><a href="/mypage/modifyinfo" class="py-2 d-block">MyPage</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
            	<h2 class="ftco-heading-2">Office</h2>
            	<div class="block-23 mb-3">
	              <ul>
	                <li><span class="icon icon-map-marker"></span><span class="text">6, Teheran-ro 14-gil, Gangnam-gu, Seoul, Republic of Korea</span></li>
	                <li><a href="#"><span class="icon icon-phone"></span><span class="text">+82 123 4567 8910</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">choayoung91@naver.com</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">gmldnjs74@gmail.com</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">minh0380@naver.com</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">psuny1031@naver.com</span></a></li>
	              </ul>
	            </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">

            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> <i class="icon-heart" aria-hidden="true"></i> by aboo for a better apartment.
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
          </div>
        </div>
      </div>
    </footer>
  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <script src="../../../resources/js/generation/jquery.min.js"></script>
  <script src="../../../resources/js/generation/jquery-migrate-3.0.1.min.js"></script>
  <script src="../../../resources/js/generation/popper.min.js"></script>
  <script src="../../../resources/js/generation/bootstrap.min.js"></script>
  <script src="../../../resources/js/generation/jquery.easing.1.3.js"></script>
  <script src="../../../resources/js/generation/jquery.waypoints.min.js"></script>
  <script src="../../../resources/js/generation/jquery.stellar.min.js"></script>
  <script src="../../../resources/js/generation/owl.carousel.min.js"></script>
  <script src="../../../resources/js/generation/jquery.magnific-popup.min.js"></script>
  <script src="../../../resources/js/generation/aos.js"></script>
  <script src="../../../resources/js/generation/jquery.animateNumber.min.js"></script>
  <script src="../../../resources/js/generation/bootstrap-datepicker.js"></script>
  <script src="../../../resources/js/generation/jquery.timepicker.min.js"></script>
  <script src="../../../resources/js/generation/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="../../../resources/js/generation/google-map.js"></script>
  <script src="../../../resources/js/generation/main.js"></script>
  
  <script type="text/javascript">
  	/* 선영아 여기야! */
    
    /* 성공하면 DB에 넣을 변수정리 */
  	let gnrlMgmtFee = $("#gnrlMgmtFee")[0].childNodes[0].nodeValue;
  	let cleanFee = $("#cleanFee")[0].childNodes[0].nodeValue;
  	let elvtrMnfee = $("#elvtrMnfee")[0].childNodes[0].nodeValue;
  	let genElctr = $("#genElctr")[0].childNodes[0].nodeValue;
  	let comonElctr = $("#comonElctr")[0].childNodes[0].nodeValue;
  	let genWater = $("#genWater")[0].childNodes[0].nodeValue;
  	let sewer = $("#sewer")[0].childNodes[0].nodeValue;
  	let expenses = $("#expenses")[0].childNodes[0].nodeValue;
  	let genReduction = $("#genReduction")[0].childNodes[0].nodeValue;

  	console.dir(gnrlMgmtFee)
    console.dir(cleanFee)
  	console.dir(elvtrMnfee)
  	console.dir(genElctr)
  	console.dir(comonElctr)
  	console.dir(genWater)
  	console.dir(sewer)
  	console.dir(expenses)
  	console.dir(genReduction)
	/* DB에 안넣네 괜히 만들었다  */
  
	

  	
  	
	$("#check_module").click(function () {
        var IMP = window.IMP; // 생략가능
        IMP.init('');
        // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        // i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
        IMP.request_pay({
            pg: 'html5_inicis', // version 1.1.0부터 지원.
            /*
            'kakao':카카오페이,
            html5_inicis':이니시스(웹표준결제)
            'nice':나이스페이
            'jtnet':제이티넷
            'uplus':LG유플러스
            'danal':다날
            'payco':페이코
            'syrup':시럽페이
            'paypal':페이팔
            */
            pay_method: 'card',
            /*
            'samsung':삼성페이,
            'card':신용카드,
            'trans':실시간계좌이체,
            'vbank':가상계좌,
            'phone':휴대폰소액결제
            */
            merchant_uid: 'merchant_' + new Date().getTime(),
            /*
            merchant_uid에 경우
            https://docs.iamport.kr/implementation/payment
            위에 url에 따라가시면 넣을 수 있는 방법이 있습니다.
            참고하세요.
            나중에 포스팅 해볼게요.
            */
            name: '주문명:관리비고지서',
            //결제창에서 보여질 이름
            amount: 100,
            //가격
            buyer_email: 'aboo@siot.do',
            buyer_name: 'ABOO',
            buyer_tel: '010-1234-5678',
            buyer_addr: '경기도 성남시',
            buyer_postcode: '123-456',
            m_redirect_url: 'https://www.yourdomain.com/payments/complete'
            /*
		            모바일 결제시,
		            결제가 끝나고 랜딩되는 URL을 지정
            (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐)
            */
        }, function (rsp) {
            console.log(rsp);
            if (rsp.success) {
           	    var msg = '결제가 완료되었습니다.';
             /*
           	    msg += '고유ID : ' + rsp.imp_uid;
                msg += '상점 거래ID : ' + rsp.merchant_uid;
                msg += '결제 금액 : ' + rsp.paid_amount;
                msg += '카드 승인번호 : ' + rsp.apply_num;
                msg += '결제수단' + rsp.pay_method;
                msg += '결제금액' + rsp.paid_amount;  */
                
                payment(rsp.pay_method,rsp.paid_amount);


            } else {
                var msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;

            }
            alert(msg);
        });
    });
  	
  	/* 이것은 관리비 고지서 번호 */
	let mgmtfeeIdx = $("#mgmtfeeIdx")[0].defaultValue;
  	console.dir(mgmtfeeIdx)
  	
	let payment = (method,amount) => {
		
	    const url = '/mypage/mymgmtfee/payment';
	    let paramObj = new Object(); 
	    paramObj.mgmtfeeIdx = mgmtfeeIdx; /* 고지서 번호 */
	    paramObj.paymentMethod = method; /* 결제수단 */
	    paramObj.paymentAmount = amount; /* 금액 (100원) */


	    let headerObj = new Headers();
	    headerObj.append("content-type","application/json");
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
	     		alert("결제 성공하였습니다.")
	     		location.href = "/mypage/mymgmtfee";
	       }else{
	     		alert("결제 실패하였습니다.")
	       }
	    }).catch(error => {
	       error.alertMessage();
	    });	
    
	}
  
  </script>
    
</body>
</html>