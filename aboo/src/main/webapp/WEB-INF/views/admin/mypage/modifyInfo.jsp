<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/adminhead.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
            ADMIN
          </a>
        </div>
        <ul class="nav">
          <li >
            <a href="/admin/mypage/modifyinfo">
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
            <a href="/admin/bdin">
              <i class="tim-icons icon-key-25"></i>
              <p>BDIN</p>
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
            <a class="navbar-brand" href="#pablo">Dashboard</a>
          </div>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-bar navbar-kebab"></span>
            <span class="navbar-toggler-bar navbar-kebab"></span>
            <span class="navbar-toggler-bar navbar-kebab"></span>
          </button>
          <div class="collapse navbar-collapse" id="navigation">
            <ul class="navbar-nav ml-auto ">
              <div class="search-bar input-group">
                <!-- <input type="text" class="form-control" placeholder="Search...">
      <div class="input-group-addon"><i class="tim-icons icon-zoom-split"></i></div> -->
                <button class="btn btn-link" id="search-button" data-toggle="modal" data-target="#searchModal"><i class="tim-icons icon-zoom-split"></i></button>
                <!-- You can choose types of search input -->
              </div>
              <!-- <div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="searchModal" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="SEARCH">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <i class="tim-icons icon-simple-remove"></i>
          </button>
        </div>

        <div class="modal-footer">
        </div>
      </div>
    </div>
  </div> -->
              <li class="dropdown nav-item">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
                  <div class="notification d-none d-lg-block d-xl-block"></div>
                  <i class="tim-icons icon-sound-wave"></i>
                  <p class="d-lg-none">
                    New Notifications
                  </p>
                </a>
                <ul class="dropdown-menu dropdown-menu-right dropdown-navbar">
                  <li class="nav-link">
                    <a href="#" class="nav-item dropdown-item">Mike John responded to your email</a>
                  </li>
                  <li class="nav-link">
                    <a href="#" class="nav-item dropdown-item">You have 5 more tasks</a>
                  </li>
                  <li class="nav-link">
                    <a href="#" class="nav-item dropdown-item">Your friend Michael is in town</a>
                  </li>
                  <li class="nav-link">
                    <a href="#" class="nav-item dropdown-item">Another notification</a>
                  </li>
                  <li class="nav-link">
                    <a href="#" class="nav-item dropdown-item">Another one</a>
                  </li>
                </ul>
              </li>
              <li class="dropdown nav-item">
                <a href="#" class="dropdown-toggle nav-link" data-toggle="dropdown">
                  <div class="photo">
                    <img src="../../resources/img/anime3.png">
                  </div>
                  <b class="caret d-none d-lg-block d-xl-block"></b>
                  <p class="d-lg-none">
                    Log out
                  </p>
                </a>
                <ul class="dropdown-menu dropdown-navbar">
                  <li class="nav-link">
                    <a href="#" class="nav-item dropdown-item">Profile</a>
                  </li>
                  <li class="nav-link">
                    <a href="#" class="nav-item dropdown-item">Settings</a>
                  </li>
                  <div class="dropdown-divider"></div>
                  <li class="nav-link">
                    <a href="#" class="nav-item dropdown-item">Log out</a>
                  </li>
                </ul>
              </li>
              <li class="separator d-lg-none"></li>
            </ul>
          </div>
        </div>
      </nav>
      <div class="modal fade" id="searchModal" tabindex="-1" role="dialog" aria-labelledby="searchModal" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="SEARCH">
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <i class="tim-icons icon-simple-remove"></i>
              </button>
            </div>
            <div class="modal-footer">
            </div>
          </div>
        </div>
      </div>
      <!-- End Navbar -->
      <div class="content">
		<section class="ftco-section contact-section ftco-degree-bg">
    	<div style="height: 10vh"></div>
     	<h4 class = "text-center mb-4">개인정보 수정</h4>
	      <div class="container">
	        <div class="row block-9 justify-content-center">
	          <div class="col-md-6">
	            <form:form id = "modifyForm" action="/admin/mypage/modifyupdate" method="post" modelAttribute ="admin">
	              <div class="form-group d-flex">
	              	<div class="col-md-3  text-white d-flex justify-content-center align-items-center bg-sun">아이디</div>
	                <input type="text" readonly="readonly" class="form-control" id = "id" value="${selectAdmin.id}" >
	              </div>
	              <div class="form-group">
	                <input type="text" class="form-control password" id = "password_1" placeholder="비밀번호"> 
	                <div id ="passwordConfirm" class = "validator"></div> <!--프론트 패스워드 유효성-->
	              	<div><form:errors path="password" cssClass="validator"/></div><!--백 패스워드 유효성  -->	 
	              </div>
	              <div class="form-group">
	                <input type="text" class="form-control password" id = "password_2" name = "password" placeholder="비밀번호 확인">
				    <div id = "pass" class = "validator"></div> <!--비밀번호 일치-->               
	              </div>
	              <div class="form-group d-flex justify-content-between">
	            	<div class="col-md-3  text-white d-flex justify-content-center align-items-center bg-sun">휴대폰</div>
	                <input type="text" class="form-control col-md-7" readonly="readonly" name = "tell" value = "${selectAdmin.tell}" ><button type="button" class="btn btn-primary px-xl-3 py-xl-1" data-toggle="modal" data-target="#tellModal">인증</button>              
	              </div>
	              <div class="form-group d-flex justify-content-between">
	              <div class="col-md-3  text-white d-flex justify-content-center align-items-center bg-sun">이메일</div>
	                <input type="text" class="form-control col-md-7"  readonly="readonly" name = "email" value = "${selectAdmin.email}"><button type="button" class="btn btn-primary px-xl-3 py-xl-1" data-toggle="modal" data-target="#emailModal">인증</button>
	              </div>
	              <div class="form-group">
	                <input type="submit" value="수정완료" class="btn btn-primary mt-2 py-3 px-5 col-sm-12" >
	              </div>
	            </form:form>                    
	          </div>
	        </div>
	      </div>
	    </section>
      </div>
      <footer class="footer">
        <div class="container-fluid">
          <nav>
            <ul>
              <li>
                <a href="https://www.creative-tim.com">
                  Creative Tim
                </a>
              </li>
              <li>
                <a href="http://presentation.creative-tim.com">
                  About Us
                </a>
              </li>
              <li>
                <a href="http://blog.creative-tim.com">
                  Blog
                </a>
              </li>
            </ul>
          </nav>
          <div class="copyright float-right">
            ©
            <script>
              document.write(new Date().getFullYear())
            </script> made with <i class="tim-icons icon-heart-2"></i> by
            <a href="https://www.creative-tim.com" target="_blank">Creative Tim</a> for a better web.
          </div>
      </footer>
      </div>
    </div>
    <div class="fixed-plugin">
      <div class="dropdown show-dropdown">
        <a href="#" data-toggle="dropdown">
          <i class="fa fa-cog fa-2x"> </i>
        </a>
        <ul class="dropdown-menu">
          <li class="header-title"> Sidebar Background</li>
          <li class="adjustments-line">
            <a href="javascript:void(0)" class="switch-trigger background-color">
              <div class="badge-colors text-center">
                <span class="badge filter badge-primary active" data-color="primary"></span>
                <span class="badge filter badge-blue" data-color="blue"></span>
                <span class="badge filter badge-green" data-color="green"></span>
              </div>
              <div class="clearfix"></div>
            </a>
          </li>
          <li class="adjustments-line text-center color-change">
            <span class="color-label">LIGHT MODE</span>
            <span class="badge light-badge mr-2"></span>
            <span class="badge dark-badge ml-2"></span>
            <span class="color-label">DARK MODE</span>
          </li>
          <li class="button-container">
            <a href="https://www.creative-tim.com/product/black-dashboard" target="_blank" class="btn btn-primary btn-block btn-round">Download Now</a>
            <a href="https://demos.creative-tim.com/black-dashboard/docs/1.0/getting-started/introduction.html" target="_blank" class="btn btn-default btn-block btn-round">
              Documentation
            </a>
          </li>
          <li class="header-title">Thank you for 95 shares!</li>
          <li class="button-container text-center">
            <button id="twitter" class="btn btn-round btn-info"><i class="fab fa-twitter"></i> &middot; 45</button>
            <button id="facebook" class="btn btn-round btn-info"><i class="fab fa-facebook-f"></i> &middot; 50</button>
            <br>
            <br>
            <a class="github-button" href="https://github.com/creativetimofficial/black-dashboard" data-icon="octicon-star" data-size="large" data-show-count="true" aria-label="Star ntkme/github-buttons on GitHub">Star</a>
          </li>
        </ul>
      </div>
    </div>
      
      <!-- 번호 인증 Modal -->
      <div class="modal fade" id="tellModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content modal-bg-sun">
            <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel">휴대폰 인증</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class= "text-white" aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
            <div class="form-group d-flex justify-content-between">
                <input type="text" class="form-control col-md-10 " id = "tell" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="11" placeholder="휴대폰 번호를 입력하세요."><button type="button" class="btn btn-primary px-xl-3 py-xl-1" onclick="tellSend()">발송</button>
              </div>
       	      <div class="form-group">
                <input type="text" class="form-control " id = "certifiedPNum" placeholder="인증번호를 입력하세요."> 
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary" onclick="certifiedPNum()">확인</button>
            </div>
          </div>
        </div>
      </div>
      
       <!-- 이메일 인증 Modal -->
      <div class="modal fade" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content modal-bg-sun" >
            <div class="modal-header">
              <h4 class="modal-title " id="myModalLabel">이메일 인증</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span class= "text-white" aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
            <div class="form-group d-flex justify-content-between">
                <input type="text" class="form-control col-md-10 " id = "email" placeholder="이메일을 입력하세요."><button type="button" class="btn btn-primary px-xl-3 py-xl-1" onclick="emailSend()">발송</button>
              </div>
       	      <div class="form-group">
                <input type="text" class="form-control " id = "certifiedNum" placeholder="인증번호를 입력하세요."> 
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary"  onclick="certifiedNum()">확인</button>
            </div>
          </div>
        </div>
      </div>
      
      
      
      
      
      
      
      
      
    
	
	
	
    <!--   Core JS Files   -->
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
    </script>

    <script>
      $(document).ready(function() {
        // Javascript method's body can be found in assets/js/demos.js
        demo.initDashboardPageCharts();

      });
    </script>
    
    
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
           	document.querySelector("#pass").innerHTML = '비밀번호가 일치하지않습니다'
           	e.preventDefault();


		}
 	   
    }); 
    
   	</script>
   	
   	<!-- 이메일 인증 메일 -->
    <script type="text/javascript">
      let emailSend = () => {
    	  let email = document.querySelector("#email").value;
    	  console.dir(email);
    	  
          const url = '/admin/mypage/modifyemailimpl';
           
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
   
   
   <!--휴대폰 인증 메일 -->
   <script type="text/javascript">
      let tellSend = () => {
    	  let tell = document.querySelector("#tell").value;
    	  console.dir(tell);
    	  
          const url = '/admin/mypage/modifytellimpl';
           
          let paramObj = new Object();
          paramObj.tell = document.querySelector("#tell").value;
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
                 alert('문자가 발송되었습니다.');

             }
          }).catch(error => {
             error.alertMessage();
          }); 
       }

   </script>
   

  <!-- 이메일 인증 --> 
  <script type="text/javascript">
      let certifiedNum = () => {
    	  
          const url = '/admin/mypage/authemail';
           
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
                 location.href = "/admin/mypage/modifyinfo"
             }
          }).catch(error => {
             error.alertMessage();
          }); 
       }

   </script>
   
   
  <!-- 휴대폰 인증 --> 
  <script type="text/javascript">
      let certifiedPNum = () => {
    	  
          const url = '/admin/mypage/authtell';
           
          let paramObj = new Object();
          paramObj.certifiedPNum = document.querySelector("#certifiedPNum").value;
          paramObj.tell = document.querySelector("#tell").value;
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
                 alert('휴대폰 인증에 성공하였습니다.');
                 location.href = "/admin/mypage/modifyinfo"
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
    
</body>
</html>