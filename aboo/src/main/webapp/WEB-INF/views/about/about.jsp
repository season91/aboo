<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/generationhead.jsp" %>
<!DOCTYPE html>
<html>
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
	          <li class="nav-item active"><a href="/about" class="nav-link">About</a></li>
	          <li class="nav-item"><a href="/myapt/schedule" class="nav-link">MyApt</a></li>
	          <li class="nav-item"><a class="nav-link" href="/board/info/listinfo">Board</a></li>
	          <li class="nav-item"><a href="/mypage/modifyinfo" class="nav-link">MyPage</a></li>
	          <c:choose>
	          <c:when test="${sessionScope.generation == null}">
	          <li class="nav-item cta"><a href="/login" class="nav-link"><span>Login</span></a></li>	          
	          </c:when>
	          <c:when test="${sessionScope.generation != null}">
	          <li class="nav-item cta"><a href="/logout" class="nav-link"><span>Logout</span></a></li>	          
	          </c:when>
	          </c:choose>
	        </ul>
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->

    <section class="home-slider owl-carousel">
      <div class="slider-item bread-item" style="background-image: url(../../../resources/abooimg/logo_w.png);" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container-fluid">
          <div class="row slider-text align-items-center justify-content-center" data-scrollax-parent="true">

            <div class="col-md-8 mt-5 text-center col-sm-12 ftco-animate" data-scrollax=" properties: { translateY: '70%' }">
              <p class="breadcrumbs" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }"></p>
	            <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">About</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
  
    <section class="ftco-section bg-light">
    	<div class="container">
    		<div class="row d-md-flex">
	    		<div class="col-md-6 ftco-animate img about-image" style="background-image: url(../../../resources/abooimg/logo_b.png);">
	    		</div>
	    		<div class="col-md-6 ftco-animate p-md-5">
		    		<div class="row">
		          <div class="col-md-12 nav-link-wrap mb-5">
		            <div class="nav ftco-animate nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
		              <a class="nav-link active" id="v-pills-whatwedo-tab" data-toggle="pill" href="#v-pills-whatwedo" role="tab" aria-controls="v-pills-whatwedo" aria-selected="true">프로젝트 소개</a>

		              <a class="nav-link" id="v-pills-mission-tab" data-toggle="pill" href="#v-pills-mission" role="tab" aria-controls="v-pills-mission" aria-selected="false">사용기술</a>

		              <a class="nav-link" id="v-pills-goal-tab" data-toggle="pill" href="#v-pills-goal" role="tab" aria-controls="v-pills-goal" aria-selected="false">형상관리</a>
		            </div>
		          </div>
		          <div class="col-md-12 d-flex align-items-center" style="height:300px">
		            
		            <div class="tab-content ftco-animate" id="v-pills-tabContent">

		              <div class="tab-pane fade show active" id="v-pills-whatwedo" role="tabpanel" aria-labelledby="v-pills-whatwedo-tab">
		              	<div>
			                <h2 class="mb-4">기획 목적</h2>
			              	<p>최근까지 교육관련 기관들은 정보 전달 매체가 웹, 앱으로 많이 변경되어 세대가 바뀌고 있는 시대를 살아가고 있습니다.</p>
			                <p>여기서 단 한 곳! 이곳만이 아직 종이로 정보전달을 하고 있는데요. 그곳은 바로 아파트입니다!</p>
                      		<p>특히나 비대면이 대세인 요즘! 아파트 비대면 관리 사이트를 구현해 편의 제공을 하고자 합니다.</p>
				            </div>
		              </div>

		              <div class="tab-pane fade" id="v-pills-mission" role="tabpanel" aria-labelledby="v-pills-mission-tab">
		                <div>
			                <h2 class="mb-4">사용 프로그램</h2>
			              	<p> Eclipse, Visual Studio Code, Oracle</p>
                     		 <h2 class="mb-4">사용 언어</h2>
			                <p> Java, Javascript, Spring, Mybatis, JQuery, sql</p>
		                      <h2 class="mb-4">사용 기술</h2>
		                      <p> POI 라이브러리, Zxing 라이브러리, 네이버 클라우드 플랫폼, Import, ckeditor, fullcalendar, 카카오 지도, 다음 우편 번호 </p>
				            </div>
		              </div>

		              <div class="tab-pane fade" id="v-pills-goal" role="tabpanel" aria-labelledby="v-pills-goal-tab">
		                <div>
			                <h2 class="mb-4">Asana</h2>
			              	<p onclick="location.href='https://app.asana.com/0/1200089682403032/list'">https://app.asana.com/0/1200089682403032/list</p>
                     		 <h2 class="mb-4">Git</h2>
			                <p onclick="location.href='https://github.com/season91/aboo'">https://github.com/season91/aboo</p>
		                      <h2 class="mb-4">ERD CLOUD</h2>
		                      <p onclick="location.href='https://www.erdcloud.com/d/d3HajP7xvXbTnSxtE'">https://www.erdcloud.com/d/d3HajP7xvXbTnSxtE</p>
				            </div>
		              </div>
		            </div>
		          </div>
		        </div>
		      </div>
		    </div>
    	</div>
    </section>
    
    
    <section class="ftco-section">
      <div class="container">
        <div class="row justify-content-center mb-5 pb-5">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2 class="mb-4">Team Specialist</h2>
            <p>아파트를 부탁해 사이트 제작에 참여한 프로젝트원을 소개합니다.</p>
          </div>
        </div>
        <div class="row">
          <div class="col-md-3 ftco-animate">
            <div class="block-10">
              <div class="person-info mb-1">
                <span class="name">박선영</span>
                <span class="position">회원관리, 관리비 결제, 중고게시판</span>
              </div>
              <img src="../../../resources/abooimg/선영.gif" alt="" class="img-fluid mb-3">
              <p>기획했던 프로젝트가 실제로 구현되기까지 팀원들과 함께 하지 않았으면 구현하기 어려웠을 것이고 같이 오류들을 해결해가며 즐거움을 느꼈다.</p>
            </div>
          </div>
          <div class="col-md-3 ftco-animate">
            <div class="block-10">
              <div class="person-info mb-1">
                <span class="name">임희원</span>
                <span class="position">알람, 일정, 정보&질문 게시판 </span>
              </div>
              <img src="../../../resources/abooimg/희원.gif" alt="" class="img-fluid mb-3">
              <p>혼자서는 구현하기 어려웠을 기능들을 프로젝트를 통해 구현할 수 있어서 뿌듯하고
                능력있는 팀원들과 의미있는 프로그램을 만들 수 있어 뜻 깊은 시간이었습니다.</p>
            </div>
          </div>
          <div class="col-md-3 ftco-animate">
            <div class="block-10">
              <div class="person-info mb-1">
                <span class="name">조민희</span>
                <span class="position">투표, 인테리어 게시판, 공공기관 정보</span>
              </div>
              <img src="../../../resources/abooimg/민희.gif" alt="" class="img-fluid mb-3">
              <p>좋은 팀원들과 함께 협력하여 계획한 기능들이 하나 둘씩 구현되는 것을 볼 수 있어 보람차고 뿌듯했습니다. '아파트를 부탁해'는 제가 더욱 발전할 수 있는 소중한 경험이었습니다.              </p>
            </div>
          </div>
          <div class="col-md-3 ftco-animate">
            <div class="block-10">
              <div class="person-info mb-1">
                <span class="name"><i class="fas fa-crown"></i> 조아영</span>
                <span class="position">관리비 CRUD, 차량 QR, 주차현황, 채팅</span>
              </div>
              <img src="../../../resources/abooimg/아영.gif" alt="" class="img-fluid mb-3">
              <p>너무 좋은 조원들과 함께 하면서 집단지성이 뭔지 알게 되었고, 여러 라이브러리를 사용해보며 개인적으로 개발역량을 많이 키우게 된 감사한 프로젝트였습니다. </p>
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
                <li><a href="/bdmin/contactus" class="py-2 d-block">Contact us</a></li>
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
	  
	<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
	<script type="text/javascript">
		$("#sendBtn").click(function() {
			sendMessage();
			$('#message').val('')
		});
	
		let sock = new SockJS("http://localhost:9393/echo/");
		sock.onmessage = onMessage;
		sock.onclose = onClose;
		
		// 메시지 전송
		function sendMessage() {
			let msg = {
					target : $("#target").val(),
					messeage : $("#message").val()
			}
			sock.send(JSON.stringify(msg));
		}
		// 서버로부터 메시지를 받았을 때
		function onMessage(msg) {
			let data = msg.data;
			if(data.includes("[안내]")){
				$("#infoArea").append(data + "<br/>");
			} else {
				$("#messageArea").append(data + "<br/>");
				  /* 자동스크롤 내리기 */
				 const top = $('#messageArea').prop('scrollHeight'); 
				  $('#messageArea').scrollTop(top);
			}
			
		}
		// 서버와 연결을 끊었을 때
		function onClose(evt) {
			$("#messageArea").append("연결 끊김");
		
		}
	
	</script>
	  

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

</body>
</html>