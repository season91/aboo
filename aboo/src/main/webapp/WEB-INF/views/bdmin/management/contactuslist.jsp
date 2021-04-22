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
            <a href="/admin/vehicle">
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
	     <li  class="active ">
	       	<a href="/bdmin/management/apartment">
	         <i class="tim-icons icon-key-25"></i>
	  		 <p>BDIN - apartment</p>
	  	    </a>
	  	 </li>
	  	  <li  class=" ">
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
            <a class="navbar-brand" href="#pablo">BDMIN - contact us list</a>
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
            <div class="card ">
              <div class="card-header">
                <h3 class="title">Apart 신청 현황</h3>
              </div>
              <div class="col-md-12">
                <div class="places-buttons">
                  <div class="row">
                    <div class="col-md-6 ml-auto mr-auto text-center">
                      <h4 class="card-title">
                       	Apart 신청 검색
                        <p class="category">조회 조건을 선택하세요.
                      </h4>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-12 ml-auto mr-auto">
                      <div class="row " style="display: flex; justify-content: center">
                        <div class="col-md-3">
                          <button type="button" class="btn btn-success btn-block" id="search-button" data-toggle="modal" data-target="#nameModal">아파트 명으로 검색</button>
                        </div>
                        <div class="col-md-3">
                        <form action="${context }/bdmin/management/apartment">
                          <button type="submit" class="btn btn-success btn-block" id="search-button" data-toggle="modal" data-target="#apartmentModal">아파트 현황 보기</button>
                        </form>
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
                   <th>신청번호</th>
                   <th>아파트명</th>
                   <th>아파트 주소</th>
                   <th>세대 수</th>
                   <th>신청자 명</th>
                   <th>상세보기</th>
                 </thead>
                 <tbody>
                  
            	<c:forEach items="${apartApplicationList}" var="application" varStatus="status">
                   <tr>
                       <td> ${application.applicationIdx}</td>
                       <td> ${application.aApartmentName } </td>
                       <td> ${application.aGenerationCnt }</td>
                       <td> ${application.aParking} </td>
                       <td> ${application.aName} </td>
                       <td> <a href="${context }/bdmin/management/contactusdetail?applicationIdx=${application.applicationIdx}"><i class="tim-icons icon-single-copy-04"></i></a></td>
                      </tr>
                   </c:forEach>
              		
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
          
          <c:choose>
          	<c:when test="${searchType eq 'basic' }">
          	<div class="row d-flex card-body ">
	          <div class="col text-center">
	            <div class="block-27">
	              <ul>
	                <li><a href="/bdmin/management/contactuslist">&lt;&lt;</a></li>
	                <li><a href="/bdmin/management/contactuslist?page=${paging.prev}">&lt;</a></li>
	                <c:choose>
	                	<c:when test="${paging.lastPage eq 0 }">
	                		<li><a href="/bdmin/management/contactuslist">1</a></li>
	                	</c:when>
	                	<c:otherwise>
	                		<c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
		                      <c:choose>
		                         <c:when test="${paging.currentPage eq page}">
		                            <li class="active"><a href="/bdmin/management/contactuslist?page=${page}">${page}</a></li>
		                         </c:when>
		                         <c:otherwise>
		                            <li><a href="/bdmin/management/contactuslist?page=${page}">${page}</a></li>
		                         </c:otherwise>
		                      </c:choose>
		                 	 </c:forEach> 
	                	</c:otherwise>
	                </c:choose>
	                 
	                <li><a href="/bdmin/management/contactuslist?page=${paging.next}">&gt;</a></li>
	                <li><a href="/bdmin/management/contactuslist?page=${paging.lastPage }">&gt;&gt;</a></li>
	              </ul>
	            </div>
	          </div>
	        </div>
          	</c:when>
          	
          	<c:otherwise>
          	
          	 <div class="row d-flex card-body ">
	          <div class="col text-center">
	            <div class="block-27">
	              <ul>
	                <li><a href="/bdmin/management/contactuslist?standard=${searchType }&keyword=${keyword}">&lt;&lt;</a></li>
	                <li><a href="/bdmin/management/contactuslist?page=${paging.prev}&standard=${searchType }&keyword=${keyword}">&lt;</a></li>
	                <c:choose>
	                	<c:when test="${paging.lastPage eq 0 }">
	                		<li><a href="/bdmin/management/contactuslist?standard=${searchType }&keyword=${keyword}">1</a></li>
	                	</c:when>
	                	<c:otherwise>
	                		<c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
		                      <c:choose>
		                         <c:when test="${paging.currentPage eq page}">
		                            <li class="active"><a href="/bdmin/management/contactuslist?page=${page}&standard=${searchType }&keyword=${keyword}">${page}</a></li>
		                         </c:when>
		                         <c:otherwise>
		                            <li><a href="/bdmin/management/contactuslist?page=${page}&standard=${searchType }&keyword=${keyword}">${page}</a></li>
		                         </c:otherwise>
		                      </c:choose>
		                 	 </c:forEach> 
	                	</c:otherwise>
	                </c:choose>
	                <li><a href="/bdmin/management/contactuslist?page=${paging.next}&standard=${searchType }&keyword=${keyword}">&gt;</a></li>
	                <li><a href="/bdmin/management/contactuslist?page=${paging.lastPage }&standard=${searchType }&keyword=${keyword}">&gt;&gt;</a></li>
	              </ul>
	            </div>
	          </div>
	        </div>
          	
          	</c:otherwise>
          	
          </c:choose>
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
    
    
     
    <div class="modal fade" id="nameModal" tabindex="-1" role="dialog" aria-labelledby="nameModalLabel" aria-hidden="true">
       <div class="modal-dialog" role="document">
         <div class="modal-content" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675); color:white;">
           <div class="modal-header">
           	<h4 class="modal-title" id="nameModalLabel">아파트 명으로 검색 검색</h4>
            	 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <i class="tim-icons icon-simple-remove"></i>
              </button>
           </div>
           <div class="modal-body">
        <form action="${context }/bdmin/management/contactuslist">
          <div class="form-group">
          <input type="hidden" name="standard" value="apartmentName">
           <input type="text" class="form-control mgmtfee-keyword" id="inlineFormInputGroup" name="keyword" placeholder="아파트 이름으로 검색하세요.">
          </div>
          <div class="modal-footer">
           <button type="submit" class="btn btn-primary">검색</button>
           </div>
        </form>
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
</body>
</html>