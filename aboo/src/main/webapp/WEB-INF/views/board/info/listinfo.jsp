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
	          <c:when test="${sessionScope.generation == null and sessionScope.admin == null}">
	          <li class="nav-item cta"><a href="/login" class="nav-link"><span>Login</span></a></li>	          
	          </c:when>
	          <c:when test="${sessionScope.generation != null or sessionScope.admin != null}">
	          <li class="nav-item cta"><a href="/logout" class="nav-link"><span>Logout</span></a></li>	          
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
 	<div class="slider-item bread-item" style="background-image: url(../../../resources/abooimg/logo_w.png);" data-stellar-background-ratio="0.5">        <div class="overlay"></div>
        <div class="container-fluid">
          <div class="row slider-text align-items-center justify-content-center" data-scrollax-parent="true">

            <div class="col-md-8 mt-5 text-center col-sm-12 ftco-animate" data-scrollax=" properties: { translateY: '70%' }">
              <p class="breadcrumbs" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }"><span class="mr-2"><a href="/board/interior/intlist">INTERIOR</a></span> <span><a href="/board/used/usedlist">Used</a></span></p>
	            <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">Info</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
  
    <section class="ftco-section bg-light">
      <div class="container">
        <div class=" pt-5 pb-5 w-99">
         <div class=" mb-4 ">
            <div class="card-body ">
            <div>
             <p class="h4 pb-3">?????? & ?????? ?????????</p>
              <p>???????????? ??? ???????????? ????????? ????????? ??? ?????? ????????? ?????????.</p>
              </div>
             <div class=" d-flex justify-content-end">
             
              <form action="${context}/board/info/search" class="search-form col-md-5">
                <div class="form-group">
                  <div class="icon">
                  	<button type="submit" class="icon-search bg-white border-0"></button>
                  </div>

                  <input type="text" class="form-control info-keyword" name="keyword" placeholder="????????? ???????????????.">
                </div>
              </form>
            </div>
  		<div class="row">
		   <div class="col-md-12 ftco-animate">
		      <div class="table-responsive">
                 <table class="table tablesorter " style="width:99% !important;">

                    <thead class=" text-primary">
                    
                      <th>
                        ?????????
                      </th>
                      <th>
                        ????????????
                      </th>
                      <th>
                      	??????
                      </th>
                      <th>
                        ?????????
                      </th>
                      <th class="text-center">
                        ????????????
                      </th>
                    </thead>
                   
                    <tbody>
 					<c:forEach items="${infoBoard}" var="infoBoard" varStatus="status">
 					
		                      <tr>
		                        <td>
		                         ${infoBoard.bIdx}
		                        </td>
		                        <td>
		                         ${infoBoard.bCategory}
		                        </td>
		                        <c:choose>
        							<c:when test="${infoBoard.bIsPrivate == 0}">
				                        <td class="ml-2">
				                        <div class="d-flex justify-center ml-3">
				                         <a href="${context}/board/info/detail?bIdx=${infoBoard.bIdx}" class="text-dark">
				                          ${infoBoard.bTitle}
				                          </a>
				                          <div class="ml-3">
				                          <a href="${context}/board/info/detail?bIdx=${infoBoard.bIdx}" class="meta-chat text-dark"><span class="icon-chat" style="font-size:0.9vw;"></span>${infoCmtCntList[status.index]}</a></div>
										</div>
				                         
				                         
				                        </td>
				                     </c:when>
				                     <c:otherwise>
				                     	<td>
				                     	 <div class="d-flex justify-center ml-3">
				                     		<a href="${context}/board/info/detail?bIdx=${infoBoard.bIdx}" class="text-dark">????????? ????????? ??????????????????.</a>
				                     		<div class="ml-3">
				                         	 <a href="${context}/board/info/detail?bIdx=${infoBoard.bIdx}" class="meta-chat text-dark"><span class="icon-chat" style="font-size:0.9vw;"></span>${infoCmtCntList[status.index]}</a>
				                         	 </div>
				                         </div>
				                         
				                     	</td>
				                     </c:otherwise>
				                     
				                     
				                 </c:choose>
		                        
		                        <td>
		                           ${infoBoard.bWriter}
		                        </td>
		                        <td class="text-center">
		                          ${infoBoard.bWdate}
		                        </td>
		                      </tr>
    					</c:forEach>
                    </tbody>
                   
                  </table>
				<div class="nav ftco-animate nav-pills d-flex justify-content-start" aria-orientation="vertical">	
                  <a class="nav-link pl-5 pr-5" href="/board/info/addinfo">?????????</a>
                </div>
                </div>
              </div>
          </div>
          <div class="mt-3 col-md-12 d-flex justify-content-center">
          <div class="text-center">
            <div class="block-27">
            
                  <ul>
                <li><a href="${context}/board/${paging.type}/listinfo">&lt;&lt;</a></li>
                <li><a href="${context}/board/${paging.type}/listinfo?page=${paging.prev}">&lt;</a></li>
	                <c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
	                   <c:choose>
	                      <c:when test="${paging.currentPage eq page}">
	                         <li class="active"><a href="${context}/board/${paging.type}/listinfo?page=${page}">${page}</a></li>
	                      </c:when>
	                      <c:otherwise>
	                         <li><a href="${context}/board/${paging.type}/listinfo?page=${page}">${page}</a></li>
	                      </c:otherwise>
	                   </c:choose>
	              	 </c:forEach>
                <li><a href="${context}/board/${paging.type}/listinfo?page=${paging.next}">&gt;</a></li>
                <li><a href="${context}/board/${paging.type}/listinfo?page=${paging.lastPage}">&gt;&gt;</a></li>
              </ul>
            </div>
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