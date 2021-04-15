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
	          <li class="nav-item active"><a class="nav-link" href="/board/info/infolist">Board</a></li>
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
              <p class="breadcrumbs" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }"><span class="mr-2"><a href="/board/interior/intlist">INTERIOR</a></span> <span><a href="/board/used/usedlist">Bullentin</a></span></p>
	            <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">Info</h1>
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
          		<h2 class="mb-3">${infoBoard.bTitle}</h2>
          		<div class="d-flex align-self-center">
          			<span class="mr-4">${infoBoard.bWriter}</span>
          			<i class="fas fa-clock align-self-center mr-2"></i>
          			<span>${infoBoard.bWdate}</span>
          			<c:choose>
          			 	<c:when test="${sessionScope.admin != null}">
		          			<div class="d-flex justify-content-end ml-4">
		          				<a onclick="privateInfo()" class="mr-4" style="cursor: pointer;"><i class="fas fa-ban" style="color: #666666;"></i></a>
				            	<a href="/board/info/listinfo" class="mr-4"><i class="fas fa-list-ul" style="color: #666666;"></i></a>
				            </div>
		            	</c:when>
          				<c:when test="${sessionScope.generation.generationIdx == infoBoard.generationIdx}">
		          			<div class="d-flex justify-content-end ml-4">
				            	<a href="${context}/board/info/editinfo?bIdx=${infoBoard.bIdx}" class="mr-4"><i class="fas fa-pen" style="color: #666666;"></i></a>
				            	<a onclick="deleteInfo();" class="mr-4"><i class="fas fa-trash" style="color: #666666;"></i></a>
				            	<a href="/board/info/listinfo" class="mr-4"><i class="fas fa-list-ul" style="color: #666666;"></i></a>
				            </div>
		            	</c:when>

		            	<c:otherwise>
		            		<div class="d-flex justify-content-end ml-4">
		          				
				            	<a href="/board/info/listinfo" class="mr-4"><i class="fas fa-list-ul" style="color: #666666;"></i></a>
				            </div>
		            	</c:otherwise>
		            </c:choose>
          		</div>
          	</div>
          	<hr>
			<c:choose>
          		<c:when test="${infoBoard.bIsPrivate == 0}">
           			<p>${infoBoard.bContent}</p>
            	</c:when>
            	<c:otherwise>
            		<p>비공개 처리된 게시물입니다.</p>
            	</c:otherwise>
            	
            </c:choose>

            <div class="pt-5 mt-5">
              <h3 class="mb-5">${infoCmtCnt} Comments</h3>
              <ul class="comment-list">
              <c:choose>
                	<c:when test="${infoCmtCnt == 0}">
                		<li class="comment">작성된 댓글이 없습니다.</li>
                	</c:when>
              		<c:otherwise>
              <c:forEach items="${infoCmtList}" var="infoCmtList">
                <li class="comment" id="infoCmt">
                  <div class="vcard bio">
                   <img src="../../../../resources/abooimg/user.jpg" alt="Image placeholder">
                  </div>
                  <c:choose>
                 	<c:when test="${infoCmtList.cIsPrivate == 0}">
		                 <div class="comment-body">
		                   <h3>${infoCmtList.cWriter}</h3>
		                   <div class="meta">${infoCmtList.cWdate}</div>
		                    <p>${infoCmtList.cContent}</p>
		                    <c:choose>
		                    	<c:when test="${sessionScope.admin != null}">
				                    <p>
				                    	<a onclick="infoCmtPrivate(${infoCmtList.cIdx});" class="mr-4"><i class="fas fa-ban" style="color: #666666;"></i></a>
						            	
				                    </p>
		                    	</c:when>
		                    	<c:when test="${generation.generationIdx == infoCmtList.generationIdx}">
		                    		<p>
		                    			<a onclick="infoCmtEdit(${infoCmtList.cIdx});" class="mr-4"><i class="fas fa-pen" style="color: #666666;"></i></a>
				            			<a onclick="infoCmtdel(${infoCmtList.cIdx});" class="mr-4"><i class="fas fa-trash" style="color: #666666;"></i></a>
		                    		</p>
		                    	</c:when>
		                    </c:choose>
		                 </div>
                  	</c:when>
                  	<c:otherwise>
                  		비공개 처리된 댓글입니다.
                  	</c:otherwise>
                  </c:choose>
                </li>
                
                <li class="comment" id="infoCmtEdit${infoCmtList.cIdx}" style="display:none">
                  <div class="vcard bio">
                   <img src="../../../../resources/abooimg/user.jpg" alt="Image placeholder">
                  </div>
                  <div class="comment-body">
                    <h3>${infoCmtList.cWriter}</h3>
                    <div class="meta">${infoCmtList.cWdate}</div>
                    
                    <form action="${context}/board/info/infocmtedit" method="post">


							<input type="hidden" name="bIdx" value="${infoBoard.bIdx}">
							<input type="hidden" name="cIdx" value="${infoCmtList.cIdx}">
		                    <textarea name="cContent" id="message" cols="30" rows="5" class="form-control">${infoCmtList.cContent}</textarea>

		                  <p>
		                    <input type="submit" value="수정하기" class="btn mt-3 py-3 px-4 btn-primary">
		                  </p>
	
	                </form>
                  </div>
                </li>
			 </c:forEach>
			 </c:otherwise>
			 </c:choose>

              </ul>
              <!-- END comment-list -->
              
              <div class="comment-form-wrap pt-5">
                <h3 class="mb-5">Leave a comment</h3>
                <form action="${context}/board/info/uploadinfocmt" method="post" class="p-5 bg-light">

                  <div class="form-group">
					<input type="hidden" name="bIdx" value="${infoBoard.bIdx}">
					<input type="hidden" name="generationIdx" value="${generation.generationIdx}">
                    <textarea name="cContent" id="message" cols="30" rows="5" class="form-control"></textarea>
                  </div>
                  <div class="form-group">
                    <input type="submit" value="등록하기" class="btn py-3 px-4 btn-primary">
                  </div>

                </form>
              </div>
            </div>

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
  
  let deleteInfo = () => {
		let bIdx = ${infoBoard.bIdx};
		if(confirm("게시물을 삭제하시겠습니까?")){
			fetch("/board/info/deleteinfo?bIdx=" + bIdx,{
	  			method:"GET"
	  		})
	  		.then(response => response.text())
	  		.then(text => {
	  			if(text == 'success'){
	  				alert("게시물이 삭제되었습니다.");
					location.href = "/board/info/listinfo";
	  			}else{
	  				alert("게시물 삭제 중 에러가 발생했습니다.");
	  				location.href = "/board/info/detail?bIdx=" + bIdx;
	  			}
	  		})
		}else{
			alert("취소되었습니다.");
		}
	}
  
  let privateInfo = () => {
	  
	  let bIdx = ${infoBoard.bIdx};
		if(confirm("게시물을 비공개 처리하시겠습니까?")){
			fetch("/board/info/privateinfo?bIdx=" + bIdx,{
	  			method:"GET"
	  		})
	  		.then(response => response.text())
	  		.then(text => {
	  			if(text == 'success'){
	  				alert("게시물이 비공개 처리되었습니다.");
					location.href = "/board/info/listinfo";
	  			}else{
	  				alert("게시물 비공개 처리 중 에러가 발생했습니다.");
	  				location.href = "/board/info/detail?bIdx=" + bIdx;
	  			}
	  		})
		}else{
			alert("취소되었습니다.");
		}
	  
  }
  
  let infoCmtPrivate = (cIdx) => {
	  
	  let bIdx = ${infoBoard.bIdx};
		if(confirm("댓글을 비공개 처리 하시겠습니까?")){
			fetch("/board/info/infocmtprivate?cIdx=" + cIdx,{
	  			method:"GET"
	  		})
	  		.then(response => response.text())
	  		.then(text => {
	  			if(text == 'success'){
	  				alert("댓글이 비공개 처리되었습니다.");
					location.href = "/board/info/detail?bIdx=" + bIdx;
	  			}else{
	  				alert("댓글 비공개 처리 중 에러가 발생했습니다.");
	  				location.href = "/board/info/detail?bIdx=" + bIdx;
	  			}
	  		})
		}else{
			alert("취소되었습니다.");
		}
	  
  }
  
  let infoCmtEdit = (cIdx) => {
	  
	  document.querySelector('#infoCmtEdit' + cIdx).style.display = 'block';
	  document.querySelector('#infoCmt' + cIdx).style.display = 'none';
	  
  }
  
  let infoCmtdel = (cIdx) => {
		let bIdx = ${infoBoard.bIdx};
		if(confirm("댓글을 삭제하시겠습니까?")){
			fetch("/board/info/infocmtdel?cIdx=" + cIdx,{
	  			method:"GET"
	  		})
	  		.then(response => response.text())
	  		.then(text => {
	  			if(text == 'success'){
	  				alert("댓글이 삭제되었습니다.");
					location.href = "/board/info/detail?bIdx=" + bIdx;
	  			}else{
	  				alert("댓글 삭제 중 에러가 발생했습니다.");
	  				location.href = "/board/info/detail?bIdx=" + bIdx;
	  			}
	  		})
		}else{
			alert("취소되었습니다.");
		}
	  
  }
  
  
  

  
  
  
  
  </script>
    
</body>
</html>