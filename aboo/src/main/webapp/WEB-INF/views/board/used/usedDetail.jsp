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
                <c:when test="${sessionScope.admin != null && sessionScope.generation == null}">
                   <li class="nav-item cta"><a href="/admin/logout" class="nav-link"><span>Admin Logout</span></a></li>             
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
  
    <section class="ftco-section">
      <div class="container">
        <div class="row">
          <div class="col-md-8 ftco-animate" style="flex: 0 0 100% !important; max-width: 100% !important;">
          	<div class="d-flex justify-content-between">
          		<c:choose>
          		<c:when test="${UsedBrd.isPrivate == 0}">
          		<h4>
	          		<c:choose>
		          		<c:when test="${UsedBrd.isTrnsc == 0}">[?????? ???]</c:when>
		          		<c:otherwise>[?????? ??????]</c:otherwise>
	          		</c:choose>
          		${UsedBrd.usedTitle}
          		</h4>
          		</c:when>
          		<c:otherwise>
          			<div class="mt-5 mb-5 text-center">
		            	<p>????????? ?????? ??? ??????????????????.</p>
		            </div>
          		</c:otherwise>
          		</c:choose>          	
          		<div class="d-flex align-self-center">
          			<span class="mr-4">${UsedBrd.usedWriter}</span>
          			<i class="fas fa-clock align-self-center mr-2"></i>
          			<span>${UsedBrd.usedRegDate}</span>
          			<c:choose>
          				<c:when test="${sessionScope.generation.generationIdx == UsedBrd.generationIdx}">
          					<div class="d-flex justify-content-end ml-4">
	          					<a href="/board/used/usedmodify?usedIdx=${UsedBrd.usedIdx}" class="mr-4" style="cursor: pointer;"><i class="fas fa-pen" style="color: #666666;"></i></a>
						        <a onclick="usedDelete()" class="mr-4" style="cursor: pointer;"><i class="fas fa-trash" style="color: #666666;"></i></a>
				            	<a href="/board/used/usedlist" class="mr-4" style="cursor: pointer;"><i class="fas fa-list-ul" style="color: #666666;"></i></a>
			            	</div>
          				</c:when>
          				<c:when test="${sessionScope.admin != null}">
          					<div class="d-flex justify-content-end ml-4">
	          					<a onclick="usedPrivate()" class="mr-4" style="cursor: pointer;"><i class="fas fa-ban" style="color: #666666;"></i></a>
	          					<a href="/board/used/usedlist" class="mr-4" style="cursor: pointer;"><i class="fas fa-list-ul" style="color: #666666;"></i></a>
          					</div>
          				</c:when>
          				<c:otherwise>
          					<div class="d-flex justify-content-end ml-4">
          						<a href="/board/used/usedlist" class="mr-4" style="cursor: pointer;"><i class="fas fa-list-ul" style="color: #666666;"></i></a>
          					</div>
          				</c:otherwise>
          			</c:choose>
          		</div>
          	</div>
          	<hr>

          	<c:choose>
          		<c:when test="${UsedBrd.isPrivate == 0}">
		          	<div>
		          		<div class = "d-flex justify-content-center" >
			          		<img class = "imgCenter detailImg" src="/file/${files.savePath}${files.renameFileName}">
						</div>
		          	</div>          		
          			<div class ="mt-5 mb-5">
          				${UsedBrd.usedContent}
          			</div>
          		</c:when>
          		<c:otherwise>
          			<div class="mt-5 mb-5 text-center">
		            	<p>????????? ?????? ??? ??????????????????.</p>
		            </div>
          		</c:otherwise>
          	</c:choose>
            <div class="pt-5 mt-5">
              <h3 class="mb-5">${usedBrdCmtCnt} Comments</h3>
              <ul class="comment-list">
                <c:choose>
                	<c:when test="${usedBrdCmtCnt == 0}">
                		<li class="comment">????????? ????????? ????????????.</li>
                	</c:when>
                	<c:otherwise>
              			<c:forEach items="${usedBrdCmtList}" var="usedBrdCmt">
              			
			                <li class="comment" id="usedBrdCmt${usedBrdCmt.usedCmtIdx}">
			                  	<div class="vcard bio">
			                      <img src="../../../../resources/abooimg/user.jpg" alt="Image placeholder">
			                    </div>
			                    <div class="comment-body">
			                      <h3>${usedBrdCmt.usedCmtWriter}</h3>
			                      <div class="meta">${usedBrdCmt.usedCmtRegDate}</div>
			                      <c:choose>
			                      	<c:when test="${usedBrdCmt.isPrivate == 0}">
			                      		<p>${usedBrdCmt.usedCmtContent}</p>
			                      		<c:choose>
				                      	    <c:when test="${sessionScope.generation.generationIdx == usedBrdCmt.generationIdx}">
				                      	  	    <p>
				                        	      <a onclick="usedBrdCmtModify(${usedBrdCmt.usedCmtIdx})" class="mr-4" style="cursor: pointer;"><i class="fas fa-pen" style="color: #666666;"></i></a>
						                  	      <a onclick="usedCmtDelete(${usedBrdCmt.usedCmtIdx})" class="mr-4" style="cursor: pointer;"><i class="fas fa-trash" style="color: #666666;"></i></a>
				                          	    </p>
				                        	</c:when>
				                        	<c:when test="${sessionScope.admin != null}">
				                        		<p>
				                        		  <a onclick="usedCmtPrivate(${usedBrdCmt.usedCmtIdx})" class="mr-4" style="cursor: pointer;"><i class="fas fa-ban" style="color: #666666;"></i></a>
				                        		</p>
				                        	</c:when>
				                      </c:choose>
			                      	</c:when>
			                      	<c:otherwise>
			                      		<p>????????? ?????? ??? ???????????????.</p>
			                      	</c:otherwise>
			                      </c:choose>
			                    </div>
			                </li>
			                
			                <li class="comment" id="usedBrdCmtModify${usedBrdCmt.usedCmtIdx}" style="display: none;">
			                  	<div class="vcard bio">
			                      <img src="../../../../resources/abooimg/user.jpg" alt="Image placeholder">
			                    </div>
			                    <div class="comment-body">
			                      <h3>${usedBrdCmt.usedCmtWriter}</h3>
			                      <div class="meta">${usedBrdCmt.usedCmtRegDate}</div>
			                      <form action="/board/used/usedbrdcmtmodify" method="post">
			                      	<input style="display: none;" name="usedCmtIdx" value="${usedBrdCmt.usedCmtIdx}">
			                      	<input style="display: none;" name="usedIdx" value="${usedBrdCmt.usedIdx}">
			                      	<textarea name="usedCmtContent" class="w-100" rows="5" style="resize: none;">${usedBrdCmt.usedCmtContent}</textarea>
			                      	<p>
			                      	  <input type="submit" value="????????????" class="btn py-2 px-3 btn-primary">
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
                <form action="/board/used/usedcmtupload" method="post" class="p-5 bg-light">
                  <div class="form-group">
                    <label for="message">Message</label>
                    <input style="display: none;" name="generationIdx" value="${sessionScope.generation.generationIdx}">
                    <input style="display: none;" name="usedIdx" value="${UsedBrd.usedIdx}">
                    <textarea name="usedCmtContent" id="usedCmtContent" cols="30" rows="10" class="form-control"></textarea>
                  </div>
                  <div class="form-group">
                    <input type="submit" value="Post Comment" class="btn py-3 px-4 btn-primary">
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
              <p>???????????? ?????????!<br>
              ????????? ?????? ?????????????????? ??????, ???????????????, ????????????, ???????????? ?????? ??? ???????????? ?????? ????????? ???????????????.
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
  	let usedDelete = () => {

  		let usedIdx = '${UsedBrd.usedIdx}';
  		
  		if(confirm("?????? ?????? ???????????????????")){
  			fetch("/board/used/useddelete?usedIdx=" + usedIdx,{
  	  			method:"GET"
  	  		})
  	  		.then(response => response.text())
  	  		.then(text => {
  	  			if(text == 'success'){
  	  				alert("???????????? ?????? ???????????????.");
					location.href = "/board/used/usedlist";
  	  			}else{
  	  				alert("????????? ?????? ??? ????????? ??????????????????.");
  	  				location.href = "/board/used/usedlist";
  	  			}
  	  		})
  		}else{
  			
  		}

  	}
  </script>
  
  
  
  <script type="text/javascript"> 
  	let usedPrivate = () => {
  		
  		let usedIdx = '${UsedBrd.usedIdx}';

  		if(confirm("?????? ????????? ?????????????????????????")){
  			fetch("/board/used/usedprivate?usedIdx=" + usedIdx,{
  	  			method:"GET"
  	  		})
  	  		.then(response => response.text())
  	  		.then(text => {
  	  			if(text == 'success'){
  	  				alert("???????????? ????????? ???????????????.");
					location.href = "/board/used/usedlist";
  	  			}else{
  	  				alert("????????? ????????? ??? ????????? ??????????????????.");
  	  				location.href = "/board/used/usedlist";
  	  			}
  	  		})
  		}else{
  			
  		}

  	}
  </script>
  
    
    <script>
    	let usedBrdCmtModify = (usedCmtIdx) => {
      		document.querySelector('#usedBrdCmt' + usedCmtIdx).style.display = "none";
      		document.querySelector('#usedBrdCmtModify' + usedCmtIdx).style.display = "block";
      	}
  	</script>


    
    <script type="text/javascript"> 
  	let usedCmtDelete = (usedCmtIdx) => {
  		
  		let usedIdx = '${UsedBrd.usedIdx}';

  		if(confirm("?????? ?????? ???????????????????")){
  			fetch("/board/used/usedbrdcmtdelete?usedCmtIdx=" + usedCmtIdx,{
  	  			method:"GET"
  	  		})
  	  		.then(response => response.text())
  	  		.then(text => {
  	  			if(text == 'success'){
  	  				alert("?????? ?????? ???????????????.");
					location.href = "/board/used/useddetail?usedIdx="+usedIdx;
  	  			}else{
  	  				alert("?????? ?????? ??? ????????? ??????????????????.");
  	  				location.href = "/board/used/useddetail?usedIdx="+usedIdx;
  	  			}
  	  		})
  		}else{
  			
  		}

  	}
  </script>
    
    
        <script type="text/javascript"> 
  	let usedCmtPrivate = (usedCmtIdx) => {
  		
  		let usedIdx = '${UsedBrd.usedIdx}';

  		if(confirm("?????? ????????? ???????????????????")){
  			fetch("/board/used/usedbrdcmtprivate?usedCmtIdx=" + usedCmtIdx,{
  	  			method:"GET"
  	  		})
  	  		.then(response => response.text())
  	  		.then(text => {
  	  			if(text == 'success'){
  	  				alert("?????? ????????? ???????????????.");
  	  				location.href = "/board/used/useddetail?usedIdx="+usedIdx;
  	  			}else{
  	  				alert("?????? ????????? ??? ????????? ??????????????????.");
  	  				location.href = "/board/used/useddetail?usedIdx="+usedIdx;
  	  			}
  	  		})
  		}else{
  			
  		}

  	}
  </script>
    
    
    
    
    
    
    
    
    
    
    
    
    
  </body>
</html>