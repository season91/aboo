<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/adminhead.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body class=" ">
  <div class="wrapper ">
    <div class="sidebar">
      <!--
        Tip 1: You can change the color of the sidebar using: data-color="blue | green | orange | red | yellow"
    -->
       <div class="sidebar-wrapper">
        <div class="logo">
          <a href="/admin/index" class="simple-text logo-mini">
            <img src="../../../resources/abooimg/logo_w.png">
          </a>
          <a href="/index" class="simple-text logo-normal">
           ABOO
          </a>
        </div>
        <ul class="nav">
          <li >
            <a href="/bdmin/contactus">
              <i class="tim-icons icon-badge"></i>
              <p>Admin Application</p>
            </a>
          </li>
        </ul>
      </div>
    </div>
    <div class="main-panel">
      <!-- Navbar -->
      <nav class="navbar navbar-expand-lg navbar-absolute navbar-transparent   ">
        <div class="container-fluid">
          <div class="navbar-wrapper">
            <div class="navbar-toggle d-inline">
              <button type="button" class="navbar-toggler">
                <span class="navbar-toggler-bar bar1"></span>
                <span class="navbar-toggler-bar bar2"></span>
                <span class="navbar-toggler-bar bar3"></span>
              </button>
            </div>
            <a class="navbar-brand" href="#pablo">아파트를 부탁해</a>
          </div>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-bar navbar-kebab"></span>
            <span class="navbar-toggler-bar navbar-kebab"></span>
            <span class="navbar-toggler-bar navbar-kebab"></span>
          </button>
         <div class="collapse navbar-collapse" id="navigation">
            <ul class="navbar-nav ml-auto ">
              <li class="dropdown nav-item">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
                  <div class="photo">
                    <img src="../../../../resources/img/anime3.png">
                  </div>
                  <b class="caret d-none d-lg-block d-xl-block"></b>
                  <p class="d-lg-none">
                    Log out
                  </p>
                </a>
                <ul class="dropdown-menu dropdown-navbar">
                  <li class="nav-link">
                   <c:choose>
                  	<c:when test="${sessionScope.bdmin == null}">
                    	<a href="/bdmin/login" class="nav-item dropdown-item">Log in</a>
                  	</c:when>
                  	<c:when test="${sessionScope.bdmin != null}">
                    	<a href="/bdmin/logout" class="nav-item dropdown-item">Log out</a>
                  	</c:when>
                  </c:choose>
                  </li>
                </ul>
              </li>
              <li class="separator d-lg-none"></li>
            </ul>
          </div>
        </div>
      </nav>
      
      <div class="content">
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header">
                <h3 class="title">어드민 계정 신청서</h3>
              </div>
                	  
                <div class="card-body">
                  <form id="form_admin_join" action="/bdmin/adminapplicationadd" method="post">
                  <h4 class="title">신청서 작성</h4>
                  	<div class="" style="display: block; margin-left: 20px">             	
 	                    
 	                    <div class="col-md-6 px-md-1">
			              <div class="form-group">
	                        <label>신청 아파트명</label>			              
			                <input type="text" class="form-control"  id = "" name ="apartmentName" placeholder="ex) 반포자이"> 
			              </div>
	                    </div>       
	                    
	                    <div class="col-md-6 px-md-1">
			             <div class="form-group">
	                        <label for="exampleInputEmail1">신청 아파트주소</label>
	                        <input type="text" class="form-control"  id="address" name="apartmentAddress" onclick="searchAdress()" required="required">
	                      </div>
	                    </div>           	
                  	
                  	
	                    <div class="col-md-9 px-md-1">
	                      <div class="form-group">
	                        <label>신청 아이디 </label>
	                        <div class = "d-flex">
	                        	<input type="text" class="form-control col-md-8" id = "id" name="id" required="required">
		                		<button type="button" class="btn btn-primary col-md-2" onclick="idCheck()" style="display: flex; justify-content: center;align-items: center; margin-left: 10px">아이디 검사</button>
	                     	</div>
	                     	<div id = "id_check" class = " mt-2"></div>
	                      </div>
	                    </div>
	                    
	                    
	                    <div class="col-md-6 px-md-1">
			              <div class="form-group">
	                        <label>신청 비밀번호</label>			              
			                <input type="text" class="form-control password"  id = "password_1"  placeholder="비밀번호"> 
						    <div id ="passwordConfirm" class = "validator"></div> <!--프론트 패스워드 유효성-->
			              </div>
	                    </div>
	                    

	                    <div class="col-md-6 px-md-1">
			              <div class="form-group">
	                        <label>신청 비밀번호확인</label>			              
			                <input type="text" class="form-control password" id = "password_2"  name = "password" placeholder="비밀번호 확인">
						    <div id = "passwordConfirm2" class = "validator"></div> <!--비밀번호 일치-->
			              </div>
	                    </div>
	                    
	                    	                    
		                  <div class="col-md-6 px-md-1">
	                      <div class="form-group">
	                        <label>신청자 이름</label>
	                        <input type="text" class="form-control" name="name" required="required">
	                      </div>
	                    </div>
	                    
	                    <div class="col-md-6 px-md-1">
	                      <div class="form-group">
	                        <label>신청자 이메일</label>
	                        <input type="email" class="form-control" name="email" required="required">
	                      </div>
	                    </div>      	                    
	                    
	                    <div class="col-md-6 px-md-1">
	                      <div class="form-group">
	                        <label>신청자 생년월일</label>
	                        <input type="Date" class="form-control" name="birth" required="required">
	                      </div>
	                    </div>                    
                	<div class="card-footer" style="display: flex;">
		                <button type="submit" class="btn btn-fill btn-primary m">신청서 작성</button>
                	</div>
                    </div>
                </form>
			</div>
            </div>
          </div>
      </div>
      </div>


     <footer class="footer">
        <div class="container-fluid">
          <nav>
            <ul>
              <li>
                <a href="/about">
                  About
                </a>
              </li>
              <li>
                <a href="/index">
                  to generation
                </a>
              </li>
            </ul>
          </nav>
          <div class="copyright float-right">
            ©
            <script>
              document.write(new Date().getFullYear())
            </script> made with <i class="tim-icons icon-heart-2"></i> by
            <a href="https://www.creative-tim.com" target="_blank">aboo</a> for a better apartment.
          </div>
      </footer>
      </div>
    </div>
    
   
     
   
    <!--   Core JS Files   -->
    <script src="../../../resources/js/admin/mgmtfee.js"></script>
    <script src="../../../resources/js/admin/core/jquery.min.js"></script>
    <script src="../../../resources/js/admin/core/popper.min.js"></script>
    <script src="../../../resources/js/admin/core/bootstrap.min.js"></script>
    <script src="../../../resources/js/admin/plugins/perfect-scrollbar.jquery.min.js"></script>
    <!--  Google Maps Plugin    -->
    <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
    <!-- Chart JS -->
    <script src="../../../resources/js/admin/plugins/chartjs.min.js"></script>
    <!--  Notifications Plugin    -->
    <script src="../../../resources/js/admin/plugins/bootstrap-notify.js"></script>
    <!-- Control Center for Black Dashboard: parallax effects, scripts for the example pages etc -->
    <script src="../../../resources/js/admin/black-dashboard.min.js?v=1.0.0" type="text/javascript"></script>
    <!-- Black Dashboard DEMO methods, don't include it in your project! -->
    <script src="../../../resources/demo/demo.js"></script>
    <script>
      $(document).ready(function() {
        $().ready(function() {
          $sidebar = $('.sidebar');
          $navbar = $('.navbar');

          $full_page = $('.full-page');

          $sidebar_responsive = $('body > .navbar-collapse');
          sidebar_mini_active = true;
          white_color = false;

          window_width = $(window).width();

          fixed_plugin_open = $('.sidebar .sidebar-wrapper .nav li.active a p').html();



          $('.fixed-plugin a').click(function(event) {
            // Alex if we click on switch, stop propagation of the event, so the dropdown will not be hide, otherwise we set the  section active
            if ($(this).hasClass('switch-trigger')) {
              if (event.stopPropagation) {
                event.stopPropagation();
              } else if (window.event) {
                window.event.cancelBubble = true;
              }
            }
          });

          $('.fixed-plugin .background-color span').click(function() {
            $(this).siblings().removeClass('active');
            $(this).addClass('active');

            var new_color = $(this).data('color');

            if ($sidebar.length != 0) {
              $sidebar.attr('data-color', new_color);
            }

            if ($navbar.length != 0) {
              $navbar.attr('data-color', new_color);
            }

            if ($full_page.length != 0) {
              $full_page.attr('filter-color', new_color);
            }

            if ($sidebar_responsive.length != 0) {
              $sidebar_responsive.attr('data-color', new_color);
            }
          });

          $('.switch-sidebar-mini input').on("switchChange.bootstrapSwitch", function() {
            var $btn = $(this);

            if (sidebar_mini_active == true) {
              $('body').removeClass('sidebar-mini');
              sidebar_mini_active = false;
              blackDashboard.showSidebarMessage('Sidebar mini deactivated...');
            } else {
              $('body').addClass('sidebar-mini');
              sidebar_mini_active = true;
              blackDashboard.showSidebarMessage('Sidebar mini activated...');
            }

            // we simulate the window Resize so the charts will get updated in realtime.
            var simulateWindowResize = setInterval(function() {
              window.dispatchEvent(new Event('resize'));
            }, 180);

            // we stop the simulation of Window Resize after the animations are completed
            setTimeout(function() {
              clearInterval(simulateWindowResize);
            }, 1000);
          });

          $('.switch-change-color input').on("switchChange.bootstrapSwitch", function() {
            var $btn = $(this);

            if (white_color == true) {

              $('body').addClass('change-background');
              setTimeout(function() {
                $('body').removeClass('change-background');
                $('body').removeClass('white-content');
              }, 900);
              white_color = false;
            } else {

              $('body').addClass('change-background');
              setTimeout(function() {
                $('body').removeClass('change-background');
                $('body').addClass('white-content');
              }, 900);

              white_color = true;
            }


          });

          $('.light-badge').click(function() {
            $('body').addClass('white-content');
          });

          $('.dark-badge').click(function() {
            $('body').removeClass('white-content');
          });
        });
      });
      
     $('.modal').on('hidden.bs.modal', function (e) {
 	    $(this).find('form')[0].reset()
		});
    
    </script>
    <script>
      $(document).ready(function() {
        // Javascript method's body can be found in assets/js/demos.js
        demo.initDashboardPageCharts();

      });
    </script>
    
    
    
       <script type="text/javascript">
	   let idCheckFlg = false;
	   let idCheck = () => {
	      //사용자가 입력한 아이디
	      //요소의 아이디속성이 있을 경우 해당 엘리먼트를 가져다가 사용할 수 있다.
	      let id = document.querySelector("#id").value;
	      console.dir('id는 ??'+ id)
	      if(id){
	         fetch("/bdmin/adminapplicationidcheck?id=" + id,{
	            method:"GET"
	         })	
	         .then(response => response.text())
	         .then(text =>{
	            if(text == 'success'){
	               idCheckFlg = true;
	               document.querySelector("#id_check").innerHTML = '사용 가능한 아이디 입니다.';
	               document.querySelector("#id_check").style.color = 'skyblue';

	            }else{
	               idCheckFlg = false;
	               document.querySelector("#id_check").innerHTML = '사용 불 가능한 아이디 입니다.';
	               document.querySelector("#id_check").style.color = 'red';
	               document.querySelector("#id").value="";
	            }
	         })
	         
	      }else{
	         alert("아이디를 입력하지 않으셨습니다.");
	      }
	   };

	   let flg = false;
	    
	    $('.password').focusout(function () {
	        var pwd1 = $("#password_1").val();
	        var pwd2 = $("#password_2").val();

	        if (pwd1 != '' && pwd2 == '') {
	            null;
	        } else if (pwd1 != "" || pwd2 != "") {
	            if (pwd1 == pwd2) {
	                document.querySelector("#passwordConfirm2").style.color='blue'
	                passwordConfirm2.innerHTML = '비밀번호가 일치합니다';
	                flg = true;
	            } else {
	                document.querySelector("#passwordConfirm2").style.color='red'
	                passwordConfirm2.innerHTML = '비밀번호가 일치 하지않습니다';
					flg = false;
	            }
	        }
	    });
	    

	     document.querySelector('#form_admin_join').addEventListener('submit',(e)=>{
		   let password_1 = document.querySelector("#password_1").value;
		   let password_2 = document.querySelector("#password_2").value;
			
	 	   let regExp = /^(?!.*[ㄱ-힣])(?=.*\W)(?=.*\d)(?=.*[a-zA-Z])(?=.{8,})/;
	 	   
	 	   if(!(regExp.test(password_1) || regExp.test(password_2))){
	 		   //form의 데이터 전송을 막음
	 		   e.preventDefault();
	 		   passwordConfirm.innerHTML = '비밀번호는 숫자,영문자,특수문자 조합의 8글자 이상인 문자열입니다2.';
	 		   passwordConfirm2.innerHTML='';
	 		   password_1.value='';
	 		   password_1.value='';

	 	   }
	 	   
	 	   	if (!flg) {
	           	document.querySelector("#passwordConfirm2").innerHTML = '비밀번호가 일치 하지않습니다'
	           	e.preventDefault();

			}
	 	   	
	 	   if(!idCheckFlg){
			   e.preventDefault();
			   alert("아이디 중복검사를 하지 않으셨습니다.");
			   id.focus()
		   }
	 	   
	    });  

   
   </script>
   
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
	function searchAdress() {
		 new daum.Postcode({
		        oncomplete: function(data) {
		        	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var addr = ''; // 주소 변수
	                var extraAddr = ''; // 참고항목 변수

	                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                    addr = data.roadname;
	                } else { // 사용자가 지번 주소를 선택했을 경우(J)
	                    addr = data.bname;
	                }

	                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	                if(data.userSelectedType === 'R'){
	                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                        extraAddr += data.bname;
	                    }
	                    // 건물명이 있고, 공동주택일 경우 추가한다.
	                    if(data.buildingName !== '' && data.apartment === 'Y'){
	                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                    }
	                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                    if(extraAddr !== ''){
	                        extraAddr = ' (' + extraAddr + ')';
	                    }
	                    // 조합된 참고항목을 해당 필드에 넣는다.
	                    document.getElementById("address").value = extraAddr;
	                    console.dir("1"+extraAddr);
	                
	                } else {
	                    document.getElementById("address").value = '';
	                }

	                
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('address').value = data.zonecode;
	                document.getElementById("address").value = addr;
	                
	                console.dir("2"+data.zonecode);
	                console.dir("3"+addr);
	                // 커서를 상세주소 필드로 이동한다.
	                document.getElementById("address").focus();
		        }
		 }).open();
		
	}
	   
	</script>

</body>
</html>