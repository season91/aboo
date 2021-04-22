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
	          <li class="nav-item"><a href="/myapt/schedule" class="nav-link">MyApt</a></li>
	          <li class="nav-item active"><a class="nav-link" href="/board/info/listinfo">Board</a></li>
	          <li class="nav-item"><a href="/mypage/modifyinfo" class="nav-link">MyPage</a></li>
				
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
    
    <div id="msg" class= "btn1Wrap">
		<button class = "btn1" onclick="chatPage()" style="outline: none;">Chat</button>	  
	</div>

	<script type="text/javascript">
	function chatPage() {
		location.href ='/myapt/chat';
	}
	</script>

    <section class="home-slider owl-carousel">
      <div class="slider-item bread-item" style="background-image: url(../../../resources/abooimg/logo_w.png);" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container-fluid">
          <div class="row slider-text align-items-center justify-content-center" data-scrollax-parent="true">

            <div class="col-md-8 mt-5 text-center col-sm-12 ftco-animate" data-scrollax=" properties: { translateY: '70%' }">
              <p class="breadcrumbs" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }"><span class="mr-2"><a href="/board/info/listinfo">Info</a></span> <span><a href="/board/interior/intlist">Interior</a></span></span></p>
	            <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">Used</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
  
    <section class="ftco-section bg-light">
    <div class = "container">
      <div class = "d-flex" style="height: 10vh; background-color:#fafafa !important">
      <form action="/board/used/usedlist">  
            <input type="hidden" name="kind" value="trnsc">
			<label> 거래 중 : <input type="radio" name="keyword" value="0"></label>
			<label> 거래 완료 : <input type="radio" name="keyword" value="1"></label>
			<button style="border: none; background-color:#fafafa !important ;outline: none;"><i class="fas fa-search"></i></button>
		</form> 
      </div>
        <div class="row">
        	<c:forEach items="${usedBrdList}" var="usedBrd" varStatus="status">
        		<c:choose>
        			<c:when test="${usedBrd.isPrivate == 0}">
        				<div class="col-md-4 ftco-animate">
							<div class="blog-entry">
							  <a href="/board/used/useddetail?usedIdx=${usedBrd.usedIdx}" class="block-20"><img class= "w-100 h-100 imgCenter"src="/file/${fileList[status.index].savePath}${fileList[status.index].renameFileName}"></a>
							  <div class="text d-flex py-4">
							    <div class="meta mb-3">
							      <div><a href="/board/used/useddetail?usedIdx=${usedBrd.usedIdx}">${usedBrd.usedRegDate}</a></div>
							      <div><a href="/board/used/useddetail?usedIdx=${usedBrd.usedIdx}">${usedBrd.usedWriter}</a></div>
							      <div><a href="/board/used/useddetail?usedIdx=${usedBrd.usedIdx}" class="meta-chat"><span class="icon-chat"></span>${cmtList[status.index]}</a></div>
							    </div>
							    <div class="desc pl-3">
							    <div class= "heading w-100" style="word-break: break-all; text-overflow: ellipsis;">
							      <a  href="/board/used/useddetail?usedIdx=${usedBrd.usedIdx}">
						          		<c:choose>
							          		<c:when test="${usedBrd.isTrnsc == 0}"><span style="color: blue;font-size: 1.5vw;">[거래 중]</span></c:when>
							          		<c:otherwise><span style="color: red; font-size: 1.5vw;">[거래 완료]</span></c:otherwise>
						          		</c:choose>
							      ${usedBrd.usedTitle}
							      </a>	
							      </div> 	
							    </div>
							  </div>
							</div>
		            	</div>
        			</c:when>
        			<c:otherwise>
        				<div class="col-md-4 ftco-animate">
							<div class="blog-entry">
							  <a href="/board/used/useddetail?usedIdx=${usedBrd.usedIdx}" class="block-20"><img class= "w-100 h-100 imgCenter" src="/file/${fileList[status.index].savePath}${fileList[status.index].renameFileName}Nob" onerror="this.src='../../../resources/abooimg/nopreviewimg.jpg'"></a>
							  <div class="text d-flex py-4">
							    <div class="meta mb-3">
							      <div><a href="/board/used/useddetail?usedIdx=${usedBrd.usedIdx}">${usedBrd.usedRegDate}</a></div>
							      <div><a href="/board/used/useddetail?usedIdx=${usedBrd.usedIdx}">${usedBrd.usedWriter}</a></div>
							      <div><a href="/board/used/useddetail?usedIdx=${usedBrd.usedIdx}" class="meta-chat"><span class="icon-chat"></span>1</a></div>
							    </div>
							    <div class="desc pl-3">
							      <h3 class="heading"><a href="/board/used/useddetail?usedIdx=${usedBrd.usedIdx}">비공개 처리된 게시물 입니다</a></h3>
							    </div>
							  </div>
							</div>
		            	</div>
        			</c:otherwise>
        		</c:choose>
            </c:forEach>
        </div>
        
        <div class="container d-flex justify-content-end">
			<form action="/board/used/usedlist" class="search-form" style="width: 40%;">
	          <div class="form-group mb-0">
	            <div class="icon" style="cursor: pointer;">
	            	<button class = "bg-white" style="border: none; outline: none;"><a class="icon-search"></a></button>
	            </div>
                <input type="hidden" name="kind" value="search">
	            <input type="text" class="form-control" name ="keyword" placeholder="키워드를 입력하세요.">
	          </div>
         </form>
		</div>
        
        <div class="container text-center d-flex justify-content-end mt-0">
	      <a href="/board/used/usedupload" class="center-block btn btn-primary p-3 px-xl-4 py-xl-2 btn-sm" style="background: linear-gradient(45deg, #4174d0 0%, #5dbfe5 100%); border: none; color: white !important;">글쓰기</a>
	    </div>
        
		<c:choose>
        <c:when test="${searchType eq 'apartmentIdx'}">        
        <div class="row mt-5">
          <div class="col text-center">
            <div class="block-27">
              <ul>
                <li><a href="/board/used/usedlist">&lt;&lt;</a></li>
                <li><a href="/board/used/usedlist?page=${paging.prev}">&lt;</a></li>
	                
	                <c:choose>
		                <c:when test="${paging.lastPage eq 0 }">
			            	<li class="active"><a href="/board/used/usedlist?page=${page}">1</a></li>
		                </c:when>
		                <c:otherwise>	               
			                <c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
			                   <c:choose>
			                      <c:when test="${paging.currentPage eq page}">
			                         <li class="active"><a href="/board/used/usedlist?page=${page}">${page}</a></li>
			                      </c:when>
			                      <c:otherwise>
			                         <li><a href="/board/used/usedlist?page=${page}">${page}</a></li>
			                      </c:otherwise>
			                   </c:choose>
			              	 </c:forEach>
		              	 </c:otherwise>
	              </c:choose>	 
	              
                <li><a href="/board/used/usedlist?page=${paging.next}">&gt;</a></li>
                <li><a href="/board/used/usedlist?page=${paging.lastPage}">&gt;&gt;</a></li>
              </ul>
            </div>
          </div>
        </div>
        </c:when>
        
        <c:otherwise>
        <div class="row mt-5">
          <div class="col text-center">
            <div class="block-27">
              <ul>
                <li><a href="/board/used/usedlist?kind=${searchType}&keyword=${keyword}">&lt;&lt;</a></li>
                <li><a href="/board/used/usedlist?page=${paging.prev}&kind=${searchType}&keyword=${keyword}">&lt;</a></li>
	                
	                <c:choose>
		                <c:when test="${paging.lastPage eq 0 }">
			            	<li class="active"><a href="/board/used/usedlist?page=${page}&kind=${searchType}&keyword=${keyword}">1</a></li>
		                </c:when>
		                <c:otherwise>	               
			                <c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
			                   <c:choose>
			                      <c:when test="${paging.currentPage eq page}">
			                         <li class="active"><a href="/board/used/usedlist?page=${page}$kind=${searchType}&keyword=${keyword}">${page}</a></li>
			                      </c:when>
			                      <c:otherwise>
			                         <li><a href="/board/used/usedlist?page=${page}&kind=${searchType}&keyword=${keyword}">${page}</a></li>
			                      </c:otherwise>
			                   </c:choose>
			              	 </c:forEach>
		              	 </c:otherwise>
	              </c:choose>	 
	              
                <li><a href="/board/used/usedlist?page=${paging.next}&kind=${searchType}&keyword=${keyword}">&gt;</a></li>
                <li><a href="/board/used/usedlist?page=${paging.lastPage}&kind=${searchType}&keyword=${keyword}">&gt;&gt;</a></li>
              </ul>
            </div>
          </div>
        </div>
        </c:otherwise>
        </c:choose>

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
    
  </body>
</html>