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
	          <li class="nav-item"><a href="/about" class="nav-link">About</a></li>
	          <li class="nav-item active"><a href="/myapt/schedule" class="nav-link">MyApt</a></li>
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
              <p class="breadcrumbs" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }"><span class="mr-2"><a href="index.html">Parking</a></span> <span class="mr-2">Schedule</span> <span>Institutions</span></p>
	            <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">Vote</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
  
    <section class="ftco-section">
      <div class="container">
        <div class="row">
          <div class="col-md-8 ftco-animate" style="flex: 0 0 100% !important; max-width: 100% !important;">
          	<div class="d-flex justify-content-between">
          		<h2 class="mb-3">${voteMng.voteTitle}</h2>
          		<div class="d-flex align-self-center">
          			<i class="fas fa-clock align-self-center mr-2"></i>
          			<span>${voteMng.voteBeginDate} ~ ${voteMng.voteEndDate}</span>
          		</div>
          	</div>
          	<hr>
            ${voteMng.voteContent}
            
            <c:choose>
            	<c:when test="${voteMng.voteIsFinish == 0}">
            		<div class="about-author d-flex p-5 bg-light mt-3">
		              <div class="desc align-self-md-center w-100">
		                <h3 class="text-center">${voteMng.voteTitle}</h3>
		                <c:forEach items="${itemList}" var="itemList" varStatus="status">
		                	<div class="text-center">${status.count}. ${itemList}</div>
		                </c:forEach>
		              </div>
		            </div>
		            
		            <c:choose>
		            	<c:when test="${sessionScope.generation != null}">
		            		<div class="container text-center">
				            	<a href="/myapt/vote/authvote?voteNo=${voteMng.voteNo}" class="center-block btn btn-primary p-3 px-xl-5 py-xl-3 mt-3" style="background: linear-gradient(45deg, #12e6ca 0%, #8be55d 100%); border: none; color: white !important;">투표하러가기</a>
				            </div>
		            	</c:when>
		            	<c:otherwise>
		            		<div class="container text-center">
				            	<a href="/admin/vote/votefinish?voteNo=${voteMng.voteNo}" class="center-block btn btn-primary p-3 px-xl-5 py-xl-3 mt-3" style="background: linear-gradient(45deg, #56c8fb 0%, #627bed 100%); border: none; color: white !important;">투표 종료</a>
				            </div>
		            	</c:otherwise>
		            </c:choose>
            	</c:when>
            	<c:otherwise>
            		<div class="about-author d-flex p-5 bg-light mt-3">
		              <div class="desc align-self-md-center w-100 d-flex flex-column align-items-center">
		                <h3 class="text-center">${voteMng.voteTitle}</h3>
		                <c:forEach items="${itemList}" var="itemList" varStatus="status">
		                	<div class="text-center col-md-4 d-flex justify-content-between"><span>${status.count}. ${itemList}</span><span>${turnoutList[status.index]}%</span></div>
		                </c:forEach>
		              </div>
		            </div>
		            
		            <div class="mt-5 mb-5 text-danger text-center">
		            	<p class="mb-0">투표가 종료되었습니다.</p>
		            	<p class="mb-0">투표결과 ${idxOfMax+1}번 선택지가 ${maxTurnout}%로 가장 높은 수의 표를 얻었습니다.</p>
		            	<p>투표에 참여해주신 입주민 여러분 감사드립니다.</p>
		            </div>
            	</c:otherwise>
            </c:choose>
            
            <hr>
            
            <c:choose>
            	<c:when test="${sessionScope.generation != null}">
            		<div class="d-flex justify-content-end">
		            	<a href="/myapt/vote/votelist" class="mr-4"><i class="fas fa-list-ul" style="color: #666666;"></i></a>
		            </div>
            	</c:when>
            	<c:otherwise>
            		<c:choose>
            			<c:when test="${voteMng.voteIsFinish == 0}">
            				<div class="d-flex justify-content-end">
				            	<a href="/admin/vote/votemodify?voteNo=${voteMng.voteNo}" class="mr-4"><i class="fas fa-pen" style="color: #666666;"></i></a>
				            	<a onclick="voteDelete()" style="cursor: pointer;" class="mr-4"><i class="fas fa-trash" style="color: #666666;"></i></a>
				            	<a href="/myapt/vote/votelist" class="mr-4"><i class="fas fa-list-ul" style="color: #666666;"></i></a>
				            </div>
            			</c:when>
            			<c:otherwise>
            				<div class="d-flex justify-content-end">
				            	<a onclick="voteDelete()" style="cursor: pointer;" class="mr-4"><i class="fas fa-trash" style="color: #666666;"></i></a>
				            	<a href="/myapt/vote/votelist" class="mr-4"><i class="fas fa-list-ul" style="color: #666666;"></i></a>
				            </div>
            			</c:otherwise>
            		</c:choose>
            	</c:otherwise>
            </c:choose>
            
          </div> <!-- .col-md-8 -->

        </div>
      </div>
    </section> <!-- .section -->

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
              <h2 class="ftco-heading-2">Unseful Links</h2>
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


  <script src="../../../../resources/js/generation/jquery.min.js"></script>
  <script src="../../../../resources/js/generation/jquery-migrate-3.0.1.min.js"></script>
  <script src="../../../../resources/js/generation/popper.min.js"></script>
  <script src="../../../../resources/js/generation/bootstrap.min.js"></script>
  <script src="../../../../resources/js/generation/jquery.easing.1.3.js"></script>
  <script src="../../../../resources/js/generation/jquery.waypoints.min.js"></script>
  <script src="../../../../resources/js/generation/jquery.stellar.min.js"></script>
  <script src="../../../../resources/js/generation/owl.carousel.min.js"></script>
  <script src="../../../../resources/js/generation/jquery.magnific-popup.min.js"></script>
  <script src="../../../../resources/js/generation/aos.js"></script>
  <script src="../../../../resources/js/generation/jquery.animateNumber.min.js"></script>
  <script src="../../../../resources/js/generation/bootstrap-datepicker.js"></script>
  <script src="../../../../resources/js/generation/jquery.timepicker.min.js"></script>
  <script src="../../../../resources/js/generation/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="../../../../resources/js/generation/google-map.js"></script>
  <script src="../../../../resources/js/generation/main.js"></script>
  
  <script type="text/javascript">
	  let voteDelete = () => {
			let voteNo = ${voteMng.voteNo};
			if(confirm("투표를 삭제하시겠습니까?")){
				fetch("/admin/vote/votedelete?voteNo=" + voteNo,{
					method:"GET"
				})
				.then(response => response.text())
		  		.then(text => {
		  			if(text == 'success'){
		  				alert("투표가 삭제되었습니다.");
						location.href = "/myapt/vote/votelist";
		  			}else{
		  				alert("투표 삭제 중 에러가 발생했습니다.");
		  				location.href = "/myapt/vote/votelist";
		  			}
		  		})
			}else{
				alert("취소되었습니다.");
			}
		}
  </script>
    
  </body>
</html>