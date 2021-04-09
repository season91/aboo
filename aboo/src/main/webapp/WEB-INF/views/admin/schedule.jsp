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
          <li class="active">
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
          <li>
            <a href="/bdmin/login">
              <i class="tim-icons icon-key-25"></i>
              <p>BDMIN</p>
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
            <a class="navbar-brand" href="#pablo">SCHEDULE</a>
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
                  	<c:when test="${sessionScope.admin == null}">
                    	<a href="/admin/login" class="nav-item dropdown-item">Log in</a>
                  	</c:when>
                  	<c:when test="${sessionScope.admin != null}">
                    	<a href="/admin/logout" class="nav-item dropdown-item">Log out</a>
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
        <div class="row">
          <div class="col-md-12">
            <div class="card ">
              <div class="card-header d-flex justify-content-center mt-3">
                <h3 class="card-title">${aptName} 일정목록</h3>
              </div>
              <div class="card-body">
                <div class="table-responsive">
                  <table class="table tablesorter" id="">
                    <thead class=" text-primary">
                      <th >
                        시작 날짜
                      </th>
                      <th>
                        마감 날짜
                      </th>
                      <th>
                        일정내용
                      </th>
                      </thead>
                    <tbody>
                    <c:forEach items="${schedule}" var="schedule" varStatus="status">
                      <tr id="schedule${status.index}">
                      	<td id="startDate">${schedule.scheduleSdate}</td>
                        <td id="endDate">${schedule.scheduleEdate}</td>
                        <td id="schContent">
                        	<span class="d-flex justify-content-between">${schedule.scheduleCon}<span style="font-size:1.1vw">
                            <a onclick="modifyschedule(${schedule.scheduleIdx},this);"><i class="tim-icons icon-pencil mr-2"></i></a>
                            <a onclick="deleteschedule(${schedule.scheduleIdx});"><i class="tim-icons icon-trash-simple"></i></a>
                        	</span>
                        	</span>
                        </td>
                        
                      </tr>
					</c:forEach>
                    </tbody>
                  </table>
                  <div class="card-footer d-flex justify-content-end">
                	<button type="submit" class="btn btn-fill btn-primary" onclick="addSchedule();">일정추가</button>
              	  </div>

                </div>
              </div>
               
             
       </div>
       <div class="row mt-5 d-flex card-body ">
             <div class="col text-center">
               <div class="block-27">
                 <ul>
                   <li><a href="${context}/admin/${paging.type}/addschedule">&lt;&lt;</a></li>
               		 <li><a href="${context}/admin/${paging.type}/addschedule?page=${paging.prev}">&lt;</a></li>
		                <c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
		                   <c:choose>
		                      <c:when test="${paging.currentPage eq page}">
		                         <li class="active"><a href="${context}/admin/${paging.type}/addschedule?page=${page}">${page}</a></li>
		                      </c:when>
		                      <c:otherwise>
		                         <li><a href="${context}/admin/${paging.type}/addschedule?page=${page}">${page}</a></li>
		                      </c:otherwise>
		                   </c:choose>
		              	 </c:forEach>
               		  <li><a href="${context}/admin/${paging.type}/addschedule?page=${paging.next}">&gt;</a></li>
               		<li><a href="${context}/admin/${paging.type}/addschedule?page=${paging.lastPage}">&gt;&gt;</a></li>
                 </ul>
               </div>
             </div>
           </div>
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
                <a href="/aboutus">
                  About Us
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

    
           <!-- 일정추가 모달창 -->
            
         <div class="modal bg-lg" id="schedule_add_modal">
 		<div class="modal-dialog" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675);" role="document">
        	<div class="modal-content modal-content border-white" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675);">
            	<div class="modal-header" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675);">
                	<h5 class="modal-title text-light" >일정 추가</h5>
                    <button type="button" id="btn_add_close" class="close">
                    <span aria-hidden="true">&times;</span></button>
                </div>
                <form action="${context}/admin/schedule/addscheduleimpl" method="post">
                	<div class="modal-body">        	
                    		<div class="form-group row">
                            	<div class="col-sm-3 mb-3 mb-sm-0 d-flex align-items-center">
                                	<h6 class="font-weight-bold">일정 시작</h6>
                                </div>
                                <div class="col-sm-9">
                                	<input type="date" name="scheduleSdate" id="scheduleSdate"class="form-control form-control-user h6 rounded bg-white text-dark"></input>
                                </div>
                            </div>
                            <div class="form-group row">
                            	<div class="col-sm-3 mb-3 mb-sm-0 d-flex align-items-center">
                                	<h6 class="font-weight-bold">일정 마감</h6>
                                </div>
                                <div class="col-sm-9">
                                	<input type="date" name="scheduleEdate" id="scheduleEdate"class="form-control form-control-user h6 rounded bg-white text-dark"></input>
                                </div>
                            </div>
                            <div class="form-group row">
                            	<div class="col-sm-3 mb-3 mb-sm-0 d-flex align-items-center">
	                            
	                           	 <h6 class="font-weight-bold">일정 내용</h6>
	                            </div>
	                            <div class="col-sm-9">
	                    	 	 	<textarea class="form-control form-control-user h6 rounded bg-white text-dark"  id="scheduleCon" name="scheduleCon"></textarea>
	                   			</div>
	                   		</div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">추가하기</button>
                   </div>
                </form>
            </div>
        </div>
     </div>
     
     <!-- 일정수정 모달창 -->
       <div class="modal bg-lg" id="schedule_modify_modal">
 		<div class="modal-dialog" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675);" role="document">
        	<div class="modal-content modal-content border-white" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675);">
            	<div class="modal-header" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675);">
                	<h5 class="modal-title text-light" >일정 수정</h5>
                    <button type="button" id="btn_modify_close" class="close">
                    <span aria-hidden="true">&times;</span></button>
                </div>
 
                	<div class="modal-body">        	
                    		<div class="form-group row">
                            	<div class="col-sm-3 mb-3 mb-sm-0 d-flex align-items-center">
                                	<h6 class="font-weight-bold">일정 시작</h6>
                                </div>
                                <div class="col-sm-9">
                                	<input type="date" name="scheduleSdate" id="modifyScheduleSdate" class="form-control form-control-user h6 rounded bg-white text-dark"></input>
                                </div>
                            </div>
                            <div class="form-group row">
                            	<div class="col-sm-3 mb-3 mb-sm-0 d-flex align-items-center">
                                	<h6 class="font-weight-bold">일정 마감</h6>
                                </div>
                                <div class="col-sm-9">
                                	<input type="date" name="scheduleEdate" id="modifyScheduleEdate"class="form-control form-control-user h6 rounded bg-white text-dark"></input>
                                </div>
                            </div>
                            <div class="form-group row">
                            	<div class="col-sm-3 mb-3 mb-sm-0 d-flex align-items-center">
	                            
	                           	 <h6 class="font-weight-bold">일정 내용</h6>
	                            </div>
	                            <div class="col-sm-9">
	                    	 	 	<textarea class="form-control  form-control-user h6 rounded bg-white text-dark"  id="modifyScheduleCon" name="scheduleCon" ></textarea>
	                   			</div>
	                   			<input type="hidden" id="scheduleIdx">
	                   		</div>
                    </div>
                    <div class="modal-footer">
                        <button onclick="modify();" class="btn btn-primary">수정하기</button>
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
      

    //일정 추가 모달을 띄워주는 함수
    let addSchedule = () => {
    	$('#schedule_add_modal').show();
    }
    	

    // 일정 추가 모달을 닫는 함수
    $('#btn_add_close').click(function(e) {

    	$('#schedule_add_modal').hide();
    });
    
	//일정 수정
	
	let modifyschedule = (scheduleIdx,sch) => {
    	$('#schedule_modify_modal').show();

    	
    	let schedule = sch.parentElement.parentElement.parentElement.parentElement;
    	let scheduleSdate = schedule.cells[0].innerHTML;
    	let scheduleEdate = schedule.cells[1].innerHTML;
    	let scheduleCon = schedule.cells[2].innerText;

    	document.querySelector('#modifyScheduleSdate').value = scheduleSdate;
    	document.querySelector('#modifyScheduleEdate').value = scheduleEdate;
    	document.querySelector('#modifyScheduleCon').value = scheduleCon;
    	document.querySelector('#scheduleIdx').value = scheduleIdx;
    	
    		
    }
	
	let modify = () =>{
		
		let scheduleIdx = document.querySelector('#scheduleIdx').value;
		let scheduleSdate = document.querySelector('#modifyScheduleSdate').value;
		let scheduleEdate = document.querySelector('#modifyScheduleEdate').value;
		let scheduleCon = document.querySelector('#modifyScheduleCon').value;
		scheduleCon = scheduleCon.replace(/(?:\r\n|\r|\n)/g,'');
		
		
		if(confirm("일정을 수정하시겠습니까?")){
			let paramObj = new Object();
	        paramObj.scheduleIdx = scheduleIdx;
	        paramObj.scheduleSdate = scheduleSdate;
	        paramObj.scheduleEdate = scheduleEdate;
	        paramObj.scheduleCon = scheduleCon;
	        let headerObj = new Headers();
	        headerObj.append("content-type","application/json");
			fetch("/admin/schedule/modifyschedule" ,{
	  			method:"post",
	  			headers:headerObj,
	  	        body:JSON.stringify(paramObj)
	  		})
	  		.then(response => response.text())
	  		.then(text => {
	  			if(text == 'success'){
	  				alert("일정이 수정 되었습니다.");
					location.href = "/admin/schedule/addschedule";
	  			}else{
	  				alert("일정 수정 중 에러가 발생하였습니다.");
	  				location.href = "/admin/schedule/addschedule";
	  			}
	  		})
		}else{
			alert("취소되었습니다.");
		}
		
		
	}
	
	
	
	//일정 삭제
	
	let deleteschedule = (scheduleIdx) => {

		if(confirm("일정을 삭제하시겠습니까?")){
			fetch("/admin/schedule/deleteschedule?scheduleIdx=" + scheduleIdx,{
	  			method:"GET"
	  		})
	  		.then(response => response.text())
	  		.then(text => {
	  			if(text == 'success'){
	  				alert("일정이 삭제되었습니다.");
					location.href = "/admin/schedule/addschedule";
	  			}else{
	  				alert("일정 삭제 중 에러가 발생했습니다.");
	  				location.href = "/admin/schedule/addschedule";
	  			}
	  		})
		}else{
			alert("취소되었습니다.");
		}
	  
  }
	
	$('#btn_modify_close').click(function(e) {

	    $('#schedule_modify_modal').hide();
	});
    
    </script>
</body>
</html>