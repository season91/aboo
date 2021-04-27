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
	          <li class="nav-item "><a href="/myapt/schedule" class="nav-link">MyApt</a></li>
	          <li class="nav-item"><a class="nav-link" href="/board/info/listinfo">Board</a></li>
	          <li class="nav-item active"><a href="/mypage/modifyinfo" class="nav-link">MyPage</a></li>
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
      <div class="slider-item bread-item" style="background-image: url(images/bg_1.jpg);" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container-fluid">
          <div class="row slider-text align-items-center justify-content-center" data-scrollax-parent="true">

            <div class="col-md-8 mt-5 text-center col-sm-12 ftco-animate" data-scrollax=" properties: { translateY: '70%' }">
 				<p class="breadcrumbs" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">		
	 				<span class="mr-2"><a href="/mypage/myalarm">My alarm</a></span>
	 				<span class="mr-2"><a href="/mypage/mycar">My Car</a></span>
	 				<span class="mr-2"><a href="/mypage/mymgmtfee">Management Fee</a></span>
	 				<span class="mr-2"><a href="/mypage/modifyinfo">My Information</a></span>
	 				<span class="mr-2"><a href="/mypage/writelist/myinfolist">My write list</a></span>
 				</p>
	            <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">Generation won</h1>
            </div>
          </div>
        </div>
      </div>
    </section>

  
    <section class="ftco-section">
    	<div class="container">
    		<div class="row justify-content-center mb-5">
          <div class="col-md-7 text-center heading-section ftco-animate">
     	  <h4 class = "text-center mb-2">세대원 관리</h4>
     	  <h6>세대원은 최대 5명까지 추가 가능합니다.</h6>
          </div>
        </div>
        	<div class = "d-flex justify-content-end">
				<button class = "btn px-xl-5 py-xl-2" style="color: #fff !important; background: linear-gradient(45deg, #6c9fdf 0%, #82c5f4 100%); margin-bottom: 1%;"  data-toggle="modal" data-target="#addModal">추가</button>
        	</div>
    		<div class="row">
    			<div class="col-md-12 ftco-animate">
    				<div class="table-responsive">
	    				<table class="table">
						    <thead class="thead-primary">
						      <tr>
						      	<th>No.</th>
						        <th>이름</th>
						        <th>전화번호</th>
						      </tr>
						    </thead>
						    <tbody>
								<c:forEach items="${generationWonList}" var="generationWon">
									<tr onclick="openModal(this)" data-toggle="modal" data-target="#modifyModal">
										<td class = "generationWonIdx" >${generationWon.generationWonIdx}</td>
									    <td class = "name" >${generationWon.name}</td>
									    <td class = "tell">${generationWon.tell}</td>
									 </tr>
							    </c:forEach>						    				
						    </tbody>						    
						  </table>
				          <div class="col text-center">
				            <div class="block-27">
				            <ul>
						        <li><a href="/mypage/generationwon" class="nav first">&lt;&lt;</a></li>
						        <li><a href="/mypage/generationwon?page=${paging.prev}">&lt;</a></li>
						         <c:choose>
							         <c:when test="${paging.lastPage eq 0 }">
							         	<li class = "active"><a href="/mypage/generationwon?page=${page}">1</a></li>
							         </c:when>
							         <c:otherwise>
								         <c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">					         
									         <c:choose>
									         	<c:when test="${paging.currentPage eq page}">
							         		  		<li class = "active"><a href="/mypage/generationwon?page=${page}">${page}</a></li>
									         	</c:when>
									         	<c:otherwise>
							         		  		<li><a href="/mypage/generationwon?page=${page}">${page}</a></li>
									         	</c:otherwise>
									         </c:choose>
						         		 </c:forEach>
					         		 </c:otherwise>
				         		</c:choose>
						        <li><a href="/mypage/generationwon?page=${paging.next}">&gt;</a></li>
						        <li>
		 	   	 				 <a href="/mypage/generationwon?page=${paging.lastPage}">&gt;&gt;</a>
						         </li>
					        </ul>
				            </div>
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
    
  	  <!-- 세대원 추가 Modal -->
      <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel">세대원 추가</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
       	      <div class="form-group">
                <label for="name">이름</label>
    			<input type="text" class="form-control addName" id="addName" placeholder="이름을 입력하세요">
              </div>             
              <div class="form-group">
                <label for="name">전화번호</label>
    			<input type="text" class="form-control addTell" id="addTell"  onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="11" placeholder="전화번호를 입력하세요">
              </div>      
            </div>
            <div class="modal-footer">
              <button type="button" id = "closeAdd" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary" onclick="add()">확인</button>
            </div>
          </div>
        </div>
      </div>
      
      
      <!-- 세대원 수정 Modal -->
      <div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel">세대원 수정</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
       	      <div class="form-group">
                <label for="name">이름</label>
    			<input type="text" class="form-control" id="modifyName" class= "modifyName" >
              </div>             
              <div class="form-group">
                <label for="name">전화번호</label>
    			<input type="text" class="form-control" id="modityTell" class= "modityTell" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="11" >
    			<input type="hidden" id = "modifyGenerationWonIdx" class = "modifyGenerationWonIdx"  name = "generationWonIdx">
              </div>     
            </div>
            <div class="modal-footer">
              <button type="button" id = "closeModity" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-info" onclick="modity() ">수정</button>
              <button type="button" class="btn btn-danger" onclick="del() ">삭제</button>
            </div>
          </div>
        </div>
      </div>

      
  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>
	
	
	<script type="text/javascript">
	
	
 		let openModal = (info) => {
		   $('#modifyModal').show();
		   let generationWonIdx = info.cells[0].innerText
		   let name = info.cells[1].innerText
		   let tell = info.cells[2].innerText
		   
		   document.querySelector("#modifyName").value = name;
		   document.querySelector("#modityTell").value = tell;
		   document.querySelector("#modifyGenerationWonIdx").value = generationWonIdx;
		   
		   console.dir(generationWonIdx)
		}
 		
	</script>
	
	<script type="text/javascript">
		let modity = () => {
			
	  	let tell = document.querySelector('#modityTell').value;
		let regExp = /^\d{11}$/;
		
 		if(regExp.test(tell)){	
			
			
		let modifyName = document.querySelector("#modifyName").value
		let modifyTell = document.querySelector("#modityTell").value
		let modifyGenerationWonIdx = document.querySelector("#modifyGenerationWonIdx").value
		
		let	result = confirm('정말 수정하시겠습니까?')
		 
		if (result) {

         const url = '/mypage/generationwonmodify';
         let paramObj = new Object();
         paramObj.name = modifyName;
         paramObj.tell = modifyTell;
         paramObj.generationWonIdx = modifyGenerationWonIdx;
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
          		alert("수정에 성공하였습니다.")
          		location.href = "/mypage/generationwon"
            }else{
          		alert("수정에 실패하였습니다.")
            }
         }).catch(error => {
            error.alertMessage();
         });	
         
		 }
 		}else{
      		alert("전화번호는 '-'를 제외한 11자리 숫자를 입력해주세요.")

 		}
		
		}
 		
		
		
		
	</script>
	
	
	<script type="text/javascript">
	let del = () => {
		
		let modifyName = document.querySelector("#modifyName").value
		let modifyTell = document.querySelector("#modityTell").value
		let modifyGenerationWonIdx = document.querySelector("#modifyGenerationWonIdx").value
		
		let	result = confirm('정말 삭제하시겠습니까?')
		 
		if (result) {

         const url = '/mypage/generationwondelete';
         let paramObj = new Object();
         paramObj.name = modifyName;
         paramObj.tell = modifyTell;
         paramObj.generationWonIdx = modifyGenerationWonIdx;
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
          		alert("삭제에 성공하였습니다.")
          		location.href = "/mypage/generationwon"
            }else{
          		alert("삭제에 실패하였습니다.")
            }
         }).catch(error => {
            error.alertMessage();
         });	
         
		 }
		}
 		
	</script>
	
	<script type="text/javascript">
	let add = () => {
	
	let tell = document.querySelector('#addTell').value;
	let regExp = /^\d{11}$/;
	
	if(regExp.test(tell)){		
		
	let addName = document.querySelector("#addName").value;
	let addTell = document.querySelector("#addTell").value;
	
	console.dir(addName)
	
	let	result = confirm('정말 추가하시겠습니까?') 
	if (result) {

        const url = '/mypage/generationwonadd';
        let paramObj = new Object();
        paramObj.name = addName;
        paramObj.tell = addTell;

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
         		alert("추가에 성공하였습니다.")
         		location.href = "/mypage/generationwon"
           }else{
         		alert("추가에 실패하였습니다.")
           }
        }).catch(error => {
           error.alertMessage();
        });	
        
	 }
	
	}else{
  		alert("전화번호는 '-'를 제외한 11자리 숫자를 입력해주세요.")
	}
	
	}/* END */
	
 		
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