<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/generationhead.jsp"%>
<!DOCTYPE html>
<html>

<body>

	<nav
		class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light"
		id="ftco-navbar">
		<div class="container">
			<a class="navbar-brand" href="/index">ABOO</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#ftco-nav" aria-controls="ftco-nav"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="oi oi-menu"></span> Menu
			</button>

			<div class="collapse navbar-collapse" id="ftco-nav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active"><a href="/index" class="nav-link">Home</a></li>
					<li class="nav-item"><a href="/about" class="nav-link">About</a></li>
					<li class="nav-item "><a href="/myapt/schedule"
						class="nav-link">MyApt</a></li>
					<li class="nav-item"><a class="nav-link"
						href="/board/info/listinfo">Board</a></li>
					<li class="nav-item"><a href="/mypage/modifyinfo"
						class="nav-link">MyPage</a></li>
					<c:choose>
						<c:when
							test="${sessionScope.generation == null and sessionScope.admin == null}">
							<li class="nav-item cta"><a href="/login" class="nav-link"><span>Login</span></a></li>
						</c:when>
						<c:when test="${sessionScope.generation != null}">
							<li class="nav-item cta"><a href="/logout" class="nav-link"><span>Logout</span></a></li>
						</c:when>
						<c:when test="${sessionScope.admin != null}">
							<li class="nav-item cta"><a href="/admin/logout"
								class="nav-link"><span>Logout</span></a></li>
						</c:when>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
	<!-- END nav -->

	<section class="home-slider owl-carousel">
		<div class="slider-item bread-item"
			style="background-image: url(images/bg_1.jpg);"
			data-stellar-background-ratio="0.5">
			<div class="overlay"></div>
			<div class="container-fluid">
				<div
					class="row slider-text align-items-center justify-content-center"
					data-scrollax-parent="true">
					<div class="col-md-8 mt-5 text-center col-sm-12 ftco-animate" data-scrollax=" properties: { translateY: '70%' }">
						<h1 class="mb-3 bread"data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">Home</h1>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="ftco-section contact-section ftco-degree-bg">
	<div style="height: 40vh;background-color: #f7f7f7; display: flex; flex-direction: column; justify-content: center;">
	
		<h4 class="text-center" style="">회원님의 아이디는</h4>
		<div class="container">
			<div class="row block-9 justify-content-center">
				<b id = "id" style="display: none;">${findGeneration.id}</b>입니다. 감사합니다. 
				<div id = "idOpenBnt" style="border: none;padding-left:5px ;cursor: pointer;"  onclick="idOpen()" ><i class="fas fa-lock"></i></div>
				<div id = "idCloseBnt" style="display : none;  border: none; padding-left:5px ;pacursor: pointer;"  onclick="idClose()" ><i class="fas fa-lock-open"></i></div>
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
	                <li class="ftco-animate"><a href="https://github.com/season91/aboo"><span><i class="fab fa-github"></i></span></a></li>
	                <li class="ftco-animate"><a href="https://app.asana.com/0/1200089682403032/list"><span><i class="fas fa-calendar-check"></i></span></a></li>
	                <li class="ftco-animate"><a href="https://www.erdcloud.com/d/d3HajP7xvXbTnSxtE"><span><i class="fas fa-cloud"></i></span></a></li>
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
	                <li><a href="/board/info/listinfo" class="py-2 d-block">Board</a></li>
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
	<div id="ftco-loader" class="show fullscreen">
		<svg class="circular" width="48px" height="48px">
			<circle class="path-bg" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke="#eeeeee" />
			<circle class="path" cx="24" cy="24" r="22" fill="none"
				stroke-width="4" stroke-miterlimit="10" stroke="#F96D00" /></svg>
	</div>

	<script type="text/javascript">
	 let idOpen = () =>{
		 document.querySelector("#idOpenBnt").style.display = 'none';
		 document.querySelector("#id").style.display = '';
		 document.querySelector("#idCloseBnt").style.display = '';
		 
	 }
	
	 let idClose = () =>{
		 document.querySelector("#idOpenBnt").style.display = '';
		 document.querySelector("#id").style.display = 'none';
		 document.querySelector("#idCloseBnt").style.display = 'none';
		 
	 }
	
	
	
	</script>

   
	<script src="../../../resources/js/generation/jquery.min.js"></script>
	<script
		src="../../../resources/js/generation/jquery-migrate-3.0.1.min.js"></script>
	<script src="../../../resources/js/generation/popper.min.js"></script>
	<script src="../../../resources/js/generation/bootstrap.min.js"></script>
	<script src="../../../resources/js/generation/jquery.easing.1.3.js"></script>
	<script src="../../../resources/js/generation/jquery.waypoints.min.js"></script>
	<script src="../../../resources/js/generation/jquery.stellar.min.js"></script>
	<script src="../../../resources/js/generation/owl.carousel.min.js"></script>
	<script
		src="../../../resources/js/generation/jquery.magnific-popup.min.js"></script>
	<script src="../../../resources/js/generation/aos.js"></script>
	<script
		src="../../../resources/js/generation/jquery.animateNumber.min.js"></script>
	<script src="../../../resources/js/generation/bootstrap-datepicker.js"></script>
	<script src="../../../resources/js/generation/jquery.timepicker.min.js"></script>
	<script src="../../../resources/js/generation/scrollax.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
	<script src="../../../resources/js/generation/google-map.js"></script>
	<script src="../../../resources/js/generation/main.js"></script>


</body>
</html>