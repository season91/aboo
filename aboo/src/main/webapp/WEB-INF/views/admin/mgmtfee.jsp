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
            <img src="../../../resources/images/logo_w.png">
          </a>
          <a href="/admin/index" class="simple-text logo-normal">
            ADMIN
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
          <li class="active ">
            <a href="/admin/mgmtfee">
              <i class="tim-icons icon-chart-bar-32"></i>
              <p>Management Fee</p>
            </a>
          </li>
          <li>
            <a href="/admin/vehicle">
              <i class="tim-icons icon-bus-front-12"></i>
              <p>Vehicle</p>
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
            <a class="navbar-brand" href="#pablo">Vehicle</a>
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
                    <img src="../../resources/img/anime3.png">
                  </div>
                  <b class="caret d-none d-lg-block d-xl-block"></b>
                  <p class="d-lg-none">
                    Log out / Login
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
      
      
      <div class="content">
      	 
           <div class="row">
          <div class="col-md-12">
            <div class="card ">
              <div class="card-header">
                <h3 class="title">세대별 관리비 고지 내역</h3>
              </div>
              
              <div class="col-md-12">
                <div class="places-buttons">
                  <div class="row">
                    <div class="col-md-6 ml-auto mr-auto text-center">
                      <h4 class="card-title">
                       	고지서 상세 검색
                        <p class="category">조회 조건을 선택하세요.</p>
                      </h4>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-16 ml-auto mr-auto">
                      <div class="row">
                        <div class="col-md-3">
                          <button class="btn btn-primary btn-block">미납상태만 보기</button>
                        </div>
                        <div class="col-md-3 dropdown">
                          <button class="btn btn-primary btn-block" data-toggle="dropdown">고지월별 보기</button>
                          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
		                    <input type="month">
		                  </div>
                        </div>
                        <div class="col-md-3">
                          <button class="btn btn-primary btn-block" id="search-button" data-toggle="modal" data-target="#mgmtfeeNumberModal">관리비번호로 검색</button>
                        </div>
                        <div class="col-md-3">
                          <button class="btn btn-primary btn-block" id="search-button" data-toggle="modal" data-target="#searchModal">세대정보로 검색</button>
                        </div>
                         <div class="col-md-3">
                          <button class="btn btn-primary btn-block" id="mgmtfee-upload" data-toggle="modal" data-target="#mgmtfeeUploadrModal">관리비 업로드</button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
          </div>
              
              <div class="card-body">
                <div class="table-responsive">
                  <table class="table tablesorter " id="">
                    <thead class=" text-primary">
                      <th>
                        관리비번호
                      </th>
                      <th>
                        세대정보
                      </th>
                      <th>
                        관리비 고지월
                      </th>
                      <th>
                        산출금액
                      </th>
                      <th>
                        납부상태
                      </th>
                    </thead>
                    <tbody>
                      <tr>
                        <td>
                          F100000
                        </td>
                        <td>
                          103동 906호
                        </td>
                        <td>
                          21년 3월
                        </td>
                        <td>
                          260,000
                        </td>
                        <td>
                        	미납
                        </td>
                      </tr>
                      <tr>
                        <td>
                          F100001
                        </td>
                        <td>
                          103동 905호
                        </td>
                        <td>
                          21년 3월
                        </td>
                        <td>
                          123,789
                        </td>
                        <td>
                        미납
                        </td>
                      </tr>
                      <tr>
                         <td>
                          F100002
                        </td>
                        <td>
                          103동 904호
                        </td>
                        <td>
                          21년 3월
                        </td>
                        <td>
                          213,789
                        </td>
                        <td>
                        완료
                        </td>
                      </tr>
                      <tr>
                         <td>
                          F100003
                        </td>
                        <td>
                          103동 905호
                        </td>
                        <td>
                          21년 3월
                        </td>
                        <td>
                          123,789
                        </td>
                        <td>
                        미납
                        </td>
                      </tr>
                      <tr>
                         <td>
                          F100004
                        </td>
                        <td>
                          103동 903호
                        </td>
                        <td>
                          21년 3월
                        </td>
                        <td>
                          223,000
                        </td>
                        <td>
                        미납
                        </td>
                      </tr>
                      <tr>
                         <td>
                          F100005
                        </td>
                        <td>
                          103동 902호
                        </td>
                        <td>
                          21년 3월
                        </td>
                        <td>
                          172,000
                        </td>
                        <td>
                        미납
                        </td>
                      </tr>
                      <tr>
                         <td>
                          F100006
                        </td>
                        <td>
                          103동 901호
                        </td>
                        <td>
                          21년 3월
                        </td>
                        <td>
                          100,000
                        </td>
                        <td>
                        완료
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
          <div class="row mt-5 d-flex card-body ">
	          <div class="col text-center">
	            <div class="block-27">
	              <ul>
	                <li><a href="#">&lt;</a></li>
	                <li class="active"><span>1</span></li>
	                <li><a href="#">2</a></li>
	                <li><a href="#">3</a></li>
	                <li><a href="#">4</a></li>
	                <li><a href="#">5</a></li>
	                <li><a href="#">&gt;</a></li>
	              </ul>
	            </div>
	          </div>
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
    
    <div class="modal fade" id="mgmtfeeNumberModal" tabindex="-1" role="dialog" aria-labelledby="mgmtfeeNumberModalLabel" aria-hidden="true">
       <div class="modal-dialog" role="document">
         <div class="modal-content" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675); color:white;">
           <div class="modal-header">
           	<h4 class="modal-title" id="mgmtfeeNumberModalLabel">관리비번호로 검색</h4>
            	 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <i class="tim-icons icon-simple-remove"></i>
              </button>
           </div>
           <div class="modal-body">
        <form>
          <div class="form-group">
           <input type="text" class="form-control mgmtfee-keyword" id="inlineFormInputGroup" placeholder="관라비번호로 검색하세요.">
          </div>
        </form>
      </div>
           <div class="modal-footer">
           <button type="button" class="btn btn-primary btn-search-vehicleNumber" data-dismiss="modal">검색</button>
           </div>
         </div>
       </div>
     </div>
    
    
     <div class="modal fade" id="mgmtfeeUploadrModal" tabindex="-1" role="dialog" aria-labelledby="mgmtfeeUploadModalLabel" aria-hidden="true">
       <div class="modal-dialog" role="document">
         <div class="modal-content" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675); color:white;">
           <div class="modal-header">
           	<h4 class="modal-title" id="mgmtfeeUploadModalLabel">관리비 파일 업로드</h4>
            	 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <i class="tim-icons icon-simple-remove"></i>
              </button>
           </div>
           <div class="modal-body">
        <form action="/work/mgmtfeeuploadimpl" id="form-mgmtfee" method="post" enctype="multipart/form-data">
          <div class="">
           <input type="file" name="file" class="form-control col-xs-2 " id="inlineFormInputGroup" value="파일을 업로드 하세요">
          </div>
    	  </div>
           <div class="modal-footer">
           <button type="submit" class="btn btn-primary " data-dismiss="modal" onclick="send()">업로드하기</button>
           </form>
           <button type="button" class="btn btn-primary btn_down-file" data-dismiss="modal" onclick="downloadFile()">관리비 양식 다운로드</button>
           </div>
         </div>
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
     
     function send() {
 		let form = new FormData(document.getElementById('form-mgmtfee'));
 		
 		$.ajax({
 			url:"${ContextPath}/admin/mgmtfee/uploadimpl",
 			dataType:"json",
 			data:form,
 			processData:false,
 			contentType:false,
 			type:"POST"
 		})
 	}
 	
 	function downloadFile(){
 	      location.href = '${context}' + "/admin/mgmtfeeformdownload";
 	   }
    </script>
    <script>
      $(document).ready(function() {
        // Javascript method's body can be found in assets/js/demos.js
        demo.initDashboardPageCharts();

      });
    </script>
</body>
</html>