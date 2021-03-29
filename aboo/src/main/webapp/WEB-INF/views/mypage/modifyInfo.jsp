<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/generationhead.jsp" %>
<!DOCTYPE html>
<html>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<body>
    
	  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="/index">ABOO</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>

	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item active"><a href="/index" class="nav-link">Home</a></li>
	          <li class="nav-item"><a href="/about" class="nav-link">About</a></li>
	          <li class="nav-item "><a href="/myapt/schedule" class="nav-link">MyApt</a></li>
	          <li class="nav-item"><a class="nav-link" href="/board/info/infolist">Board</a></li>
	          <li class="nav-item "><a href="/mypage/modifyinfo" class="nav-link">MyPage</a></li>
	          <c:choose>
		          <c:when test="${sessionScope.generation == null}">
		          	<li class="nav-item cta"><a href="/login" class="nav-link"><span>Login</span></a></li>	          
		          </c:when>
		          <c:when test="${sessionScope.generation != null}">
		          	<li class="nav-item cta"><a href="/logout" class="nav-link"><span>Logout</span></a></li>	          
		          </c:when>
		          <c:when test="${sessionScope.admin == null}">
		          	<li class="nav-item cta"><a href="/login" class="nav-link"><span>Admin Login</span></a></li>	          
		          </c:when>
		          <c:when test="${sessionScope.admin != null}">
		          	<li class="nav-item cta"><a href="/login" class="nav-link"><span>Admin Logout</span></a></li>	          
		          </c:when>
	          </c:choose>
	        </ul>
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->

    <section class="home-slider owl-carousel">
      <div class="slider-item bread-item" style="background-image: url(images/bg_1.jpg);" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container-fluid">
          <div class="row slider-text align-items-center justify-content-center" data-scrollax-parent="true">
            <div class="col-md-8 mt-5 text-center col-sm-12 ftco-animate" data-scrollax=" properties: { translateY: '70%' }">
              <p class="breadcrumbs" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }"><span class="mr-2"><a href="index.html">Home</a></span> <span>Contact</span></p>
	            <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">Contact</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
  
    <section class="ftco-section contact-section ftco-degree-bg">
     	<h4 class = "text-center mb-4">개인정보 수정</h4>
      <div class="container">
        <div class="row block-9 justify-content-center">
          <div class="col-md-6">
            <form:form id = "modifyForm" action="/mypage/modifyupdate" method="post" modelAttribute ="generation">
              <div class="form-group">
                <input type="text" readonly="readonly" class="form-control" name = "id" value = "${selectGeneration.id}" >
              </div>
              <div class="form-group">
                <input type="text" class="form-control password"  id = "password_1"  placeholder="비밀번호"> 
              </div>
              <div class="form-group">
                <input type="text" class="form-control password" id = "password_2"  name = "password" placeholder="비밀번호 확인">
			    <div id = "pass" style="font-size: 1vw;"></div> <!--비밀번호 일치-->
			    <div id ="passwordConfirm" class = "validator"></div> <!--프론트 패스워드 유효성-->
              	<div><form:errors path="password" cssClass="validator"/></div><!--백 패스워드 유효성  -->
              </div>
              <div class="form-group">
                <input type="text" readonly="readonly" name ="building" value = "${selectGeneration.building}" class="form-control" >
              </div>
              <div class="form-group">
                <input type="text" readonly="readonly" name = "num" value = "${selectGeneration.num}" class="form-control" >
              </div>
              <div class="form-group d-flex justify-content-between">
                <input type="text" class="form-control col-md-10" name = "tell" readonly="readonly" value = "${selectGeneration.tell}"><button type="button" class="btn btn-primary px-xl-3 py-xl-1" data-toggle="modal" data-target="#tellModal">인증</button>              
              </div>
              <div class="form-group d-flex justify-content-between">
                <input type="text" class="form-control col-md-10"  name = "email" readonly="readonly" value = "${selectGeneration.email}"><button type="button" class="btn btn-primary px-xl-3 py-xl-1" data-toggle="modal" data-target="#emailModal">인증</button>
              </div>
              <div class="form-group">
                <input type="text" readonly="readonly" class="form-control" name = "regDate" value = "${selectGeneration.regDate}" >
              </div>
              <div class="form-group">
                <input type="submit" value="수정완료" class="btn btn-primary py-3 px-5 col-sm-12" >
              </div>
            </form:form>                              
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
    
       <!-- 번호 인증 Modal -->
      <div class="modal fade" id="tellModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel">인증번호</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
       	      <div class="form-group">
                <input type="text" class="form-control"> 
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">확인</button>
            </div>
          </div>
        </div>
      </div>
      
       <!-- 이메일 인증 Modal -->
      <div class="modal fade" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel">이메일 인증</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
            <div class="form-group d-flex justify-content-between">
                <input type="text" class="form-control col-md-10" id = "email"><button type="button" class="btn btn-primary px-xl-3 py-xl-1" onclick="emailSend()">발송</button>
              </div>
       	      <div class="form-group">
                <input type="text" class="form-control" id = "certifiedNum"> 
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary" onclick="certifiedNum()">확인</button>
            </div>
          </div>
        </div>
      </div>
  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>



    <script>
     let flg = false;
    
    $('.password').focusout(function () {
        var pwd1 = $("#password_1").val();
        var pwd2 = $("#password_2").val();

        if (pwd1 != '' && pwd2 == '') {
            null;
        } else if (pwd1 != "" || pwd2 != "") {
            if (pwd1 == pwd2) {
                document.querySelector("#pass").style.color='blue'
                pass.innerHTML = '비밀번호가 일치합니다';
                flg = true;
            } else {
                document.querySelector("#pass").style.color='red'
                pass.innerHTML = '비밀번호가 일치하지않습니다';
				flg = false;
            }
        }
    });
    

     document.querySelector('#modifyForm').addEventListener('submit',(e)=>{
	   let password_1 = document.querySelector("#password_1").value;
	   let password_2 = document.querySelector("#password_2").value;
		
 	   let regExp = /^(?!.*[ㄱ-힣])(?=.*\W)(?=.*\d)(?=.*[a-zA-Z])(?=.{8,})/;
 	   
 	   if(!(regExp.test(password_1) || regExp.test(password_2))){
 		   //form의 데이터 전송을 막음
 		   e.preventDefault();
 		   passwordConfirm.innerHTML = '비밀번호는 숫자,영문자,특수문자 조합의 8글자 이상인 문자열입니다2.';
 		   password_1.value='';
 		   password_1.value='';

 	   }
 	   
 	   	if (!flg) {
           	document.querySelector("#pass").innerHTML = '비밀번호가 일치 하지않습니다'
  		   	e.preventDefault();


		}
 	   
    });  
    
   	</script>
   	
	<script type="text/javascript">
      let emailSend = () => {
    	  let email = document.querySelector("#email").value;
    	  console.dir(email);
    	  
          const url = '/mypage/modifyemailimpl';
           
          let paramObj = new Object();
          paramObj.email = document.querySelector("#email").value;
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
             if(text == 'fail'){ 
                alert('실패')
             }else{ 
                 alert('메일이 발송되었습니다.');

             }
          }).catch(error => {
             error.alertMessage();
          }); 
       }

   </script>

	<script type="text/javascript">
      let certifiedNum = () => {
    	  
          const url = '/mypage/authenticationemail';
           
          let paramObj = new Object();
          paramObj.certifiedNum = document.querySelector("#certifiedNum").value;
          paramObj.email = document.querySelector("#email").value;
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
             if(text == 'fail'){ 
                alert('정확한 인증번호를 입력해주세요')
             }else{ 
                 alert('이메일 인증에 성공하였습니다.');
                 location.href = "/mypage/modifyinfo"
             }
          }).catch(error => {
             error.alertMessage();
          }); 
       }

   </script>

   <script type="text/javascript">
      $('#tellModal').modal(options)
   </script>
   
   <script type="text/javascript">
      $('#emailModal').modal(options)
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