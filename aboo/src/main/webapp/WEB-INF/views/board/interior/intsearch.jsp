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
                <c:when test="${sessionScope.generation == null && sessionScope.admin == null}">
                   <li class="nav-item cta"><a href="/login" class="nav-link"><span>Login</span></a></li>             
                </c:when>
                <c:when test="${sessionScope.generation != null && sessionScope.admin == null}">
                   <li class="nav-item cta"><a href="/logout" class="nav-link"><span>Logout</span></a></li>             
                </c:when>
                <c:when test="${sessionScope.admin == null && sessionScope.generation == null}">
                   <li class="nav-item cta"><a href="/login" class="nav-link"><span>Admin Login</span></a></li>             
                </c:when>
                <c:when test="${sessionScope.admin != null && sessionScope.generation == null}">
                   <li class="nav-item cta"><a href="/login" class="nav-link"><span>Admin Logout</span></a></li>             
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
              <p class="breadcrumbs" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }"><span class="mr-2"><a href="/board/info/listinfo">Info</a></span> <span><a href="/board/used/usedlist">Used</a></span></p>
	            <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">Interior</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
  
    <section class="ftco-section bg-light">
      <c:choose>
      	<c:when test="${!empty interiorBrd}">
      		<div class="container">
		        <div class="row">
		        	<c:forEach items="${interiorBrd}" var="interiorBrd" varStatus="status">
		        		<c:choose>
		        			<c:when test="${interiorBrd.intIsPrivate == 0}">
		        				<div class="col-md-4 ftco-animate">
									<div class="blog-entry">
									  <a href="/board/interior/intdetail?intPostNo=${interiorBrd.intPostNo}" class="block-20" style="background-image: url('${interiorBrd.intThumbnail}');"></a>
									  <div class="text d-flex py-4">
									    <div class="meta mb-3">
									      <div><a href="/board/interior/intdetail?intPostNo=${interiorBrd.intPostNo}">${interiorBrd.intRegDate}</a></div>
									      <div><a href="/board/interior/intdetail?intPostNo=${interiorBrd.intPostNo}">${interiorBrd.intWriter}</a></div>
									      <div><a href="/board/interior/intdetail?intPostNo=${interiorBrd.intPostNo}" class="meta-chat"><span class="icon-chat"></span> ${intCmtCntList[status.index]}</a></div>
									    </div>
									    <div class="desc pl-3">
									      <h3 class="heading"><a href="/board/interior/intdetail?intPostNo=${interiorBrd.intPostNo}">${interiorBrd.intTitle}</a></h3>
									    </div>
									  </div>
									</div>
				            	</div>
		        			</c:when>
		        			<c:otherwise>
		        				<div class="col-md-4 ftco-animate">
									<div class="blog-entry">
									  <a href="/board/interior/intdetail?intPostNo=${interiorBrd.intPostNo}" class="block-20" style="background-image: url('../../../resources/abooimg/nopreviewimg.jpg');"></a>
									  <div class="text d-flex py-4">
									    <div class="meta mb-3">
									      <div><a href="/board/interior/intdetail?intPostNo=${interiorBrd.intPostNo}">${interiorBrd.intRegDate}</a></div>
									      <div><a href="/board/interior/intdetail?intPostNo=${interiorBrd.intPostNo}">${interiorBrd.intWriter}</a></div>
									      <div><a href="/board/interior/intdetail?intPostNo=${interiorBrd.intPostNo}" class="meta-chat"><span class="icon-chat"></span> ${intCmtCntList[status.index]}</a></div>
									    </div>
									    <div class="desc pl-3">
									      <h3 class="heading"><a href="/board/interior/intdetail?intPostNo=${interiorBrd.intPostNo}">????????? ?????? ??? ??????????????????.</a></h3>
									    </div>
									  </div>
									</div>
				            	</div>
		        			</c:otherwise>
		        		</c:choose>
		            </c:forEach>
		        </div>
		        
		        <div class="container d-flex justify-content-end">
			          <form class="search-form" style="width: 40%;">
			          <div class="form-group mb-0">
			            <div class="icon" style="cursor: pointer;">
			            	<a id="intSearchIcon" class="icon-search"></a>
			            </div>
			            <input type="text" id="intSearch" name="intSearch" class="form-control" placeholder="????????? ???????????????.">
			         </div>
			        </form>
				</div>
		        
		        <div class="container text-center d-flex justify-content-end mt-0">
			      <a href="${context}/board/interior/intupload" class="center-block btn btn-primary p-3 px-xl-4 py-xl-2 btn-sm" style="background: linear-gradient(45deg, #12e6ca 0%, #8be55d 100%); border: none; color: white !important;">?????????</a>
			    </div>
		        
		        <div class="row mt-5">
		          <div class="col text-center">
		            <div class="block-27">
		              <ul>
		                <li><a href="${context}/board/${paging.type}/intsearch?intSearch=${intSearch}">&lt;&lt;</a></li>
		                <li><a href="${context}/board/${paging.type}/intsearch?intSearch=${intSearch}&page=${paging.prev}">&lt;</a></li>
			                <c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
			                   <c:choose>
			                      <c:when test="${paging.currentPage eq page}">
			                         <li class="active"><a href="${context}/board/${paging.type}/intsearch?intSearch=${intSearch}&page=${page}">${page}</a></li>
			                      </c:when>
			                      <c:otherwise>
			                         <li><a href="${context}/board/${paging.type}/intsearch?intSearch=${intSearch}&page=${page}">${page}</a></li>
			                      </c:otherwise>
			                   </c:choose>
			              	 </c:forEach>
		                <li><a href="${context}/board/${paging.type}/intsearch?intSearch=${intSearch}&page=${paging.next}">&gt;</a></li>
		                <li><a href="${context}/board/${paging.type}/intsearch?intSearch=${intSearch}&page=${paging.lastPage}">&gt;&gt;</a></li>
		              </ul>
		            </div>
		          </div>
		        </div>
		      </div>
      	</c:when>
      	<c:otherwise>
      		<div class="container">
      			<div class="mt-5 mb-5 text-center">?????? ???????????? ????????? ????????? ?????? ???????????? ????????????.</div>
      			
      			<div class="container d-flex justify-content-end">
			          <form class="search-form" style="width: 40%;">
			          <div class="form-group mb-0">
			            <div class="icon" style="cursor: pointer;">
			            	<a id="intSearchIcon" class="icon-search"></a>
			            </div>
			            <input type="text" id="intSearch" name="intSearch" class="form-control" placeholder="????????? ???????????????.">
			         </div>
			        </form>
				</div>
      			
      			<div class="container text-center d-flex justify-content-end mt-0">
			      <a href="${context}/board/interior/intupload" class="center-block btn btn-primary p-3 px-xl-4 py-xl-2 btn-sm" style="background: linear-gradient(45deg, #12e6ca 0%, #8be55d 100%); border: none; color: white !important;">?????????</a>
			    </div>
      		</div>
      	</c:otherwise>
      </c:choose>
    </section>

    <footer class="ftco-footer ftco-bg-dark ftco-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">ABOO</h2>
              <p>???????????? ?????????!<br>
              ????????? ?????? ?????????????????? ??????, ???????????????, ????????????, ???????????? ?????? ??? ???????????? ?????? ????????? ???????????????.
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
                <li><a href="/myapt/institutions/institutions" class="py-2 d-block">Institutions</a></li>
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
  	document.querySelector('#intSearchIcon').addEventListener('click', (e)=>{
		let keyword = document.querySelector('#intSearch').value;
		
		if(keyword){
  			location.href = '/board/interior/intsearch?intSearch=' + keyword;
  		}else{
  			alert("????????? ????????? ????????? ??????????????????.");
  		}
	})
  </script>
    
  </body>
</html>