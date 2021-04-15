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
          <a href="/admin/index" class="simple-text logo-normal">
            BDMIN
          </a>
        </div>
        <ul class="nav">
          <li >
            <a href="/admin/mypage/modifyinfos">
              <i class="tim-icons icon-badge"></i>
              <p>Mypage</p>
            </a>
          </li>
          <li>
            <a href="/admin/vote/makevote">
              <i class="tim-icons icon-tap-02"></i>
              <p>Vote</p>
            </a>
          </li>
          <li>
            <a href="/admin/authority">
              <i class="tim-icons icon-single-02"></i>
              <p>Authority</p>
            </a>
          </li>
          <li>
            <a href="/admin/mgmtfee">
              <i class="tim-icons icon-chart-bar-32"></i>
              <p>Management Fee</p>
            </a>
          </li>
          <li>
            <a href="/admin/car">
              <i class="tim-icons icon-bus-front-12"></i>
              <p>Car</p>
            </a>
          </li>
          <li>
            <a href="/admin/schedule/addschedule">
              <i class="tim-icons icon-calendar-60"></i>
              <p>Schedule</p>
            </a>
          </li>
          <li>
            <a href="/admin/chat">
              <i class="tim-icons icon-chat-33"></i>
              <p>Chat</p>
            </a>
          </li>
          <li>
          <a href="/bdmin/notice/noticelist">
            <i class="tim-icons icon-volume-98"></i>
      		 <p>notice</p>
      		</a>
      	 </li>
      	<li  >
	       	<a href="/bdmin/management/apartment">
	         <i class="tim-icons icon-key-25"></i>
	  		 <p>BDIN - apartment</p>
	  	    </a>
	  	 </li>
	  	  <li  class="active ">
	       	<a href="/bdmin/management/adminauthority">
	         <i class="tim-icons icon-key-25"></i>
	  		 <p>BDIN - manager</p>
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
            <a class="navbar-brand" href="#pablo">BDMIN - admin application</a>
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
                    <a href="${context }/admin/mypage/modifyinfo" class="nav-item dropdown-item">Profile</a>
                  </li>
                  <li class="nav-link">
                    <a href="${context }/admin/chat" class="nav-item dropdown-item">1:1 chat</a>
                  </li>
                  <div class="dropdown-divider"></div>
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
                  	
	                    <div class="col-md-9 px-md-1">
	                      <div class="form-group">
	                        <label>신청 아이디 </label>
	                        <div class = "d-flex">
	                        	<input type="text" class="form-control col-md-8" id = "id" name="id" required="required" value="${adminApplication.id }">
		                		<button type="button" class="btn btn-primary col-md-2" onclick="idCheck()" style="display: flex; justify-content: center;align-items: center; margin-left: 10px">아이디 검사</button>
	                     	</div>
	                     	<div id = "id_check" class = " mt-2"></div>
	                      </div>
	                    </div>
	                    
	                    
	                    <div class="col-md-6 px-md-1">
			              <div class="form-group">
	                        <label>신청 비밀번호</label>			              
			                <input type="password" class="form-control password"  id = "password_1" readonly="readonly"  placeholder="비밀번호" value="${adminApplication.password }"> 
			              </div>
	                    </div>
	                    	                    
		                  <div class="col-md-6 px-md-1">
	                      <div class="form-group">
	                        <label>신청자 이름</label>
	                        <input type="text" class="form-control" name="name" required="required" readonly="readonly" value="${adminApplication.name }">
	                      </div>
	                    </div>
	                    <div class="col-md-6 px-md-1">
	                      <div class="form-group">
	                        <label>신청자 생년월일</label>
	                        <input type="Date" class="form-control" name="birth" required="required" readonly="readonly" value="${adminApplication.birth }">
	                      </div>
	                    </div>
                    </div>
                	<div class="card-footer" style="display: flex; justify-content: space-around;">
		                <button type="submit" class="btn btn-fill btn-success">승인하기</button>
		                <button type="button" class="btn btn-fill btn-primary">반려하기</button>
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

</body>
</html>