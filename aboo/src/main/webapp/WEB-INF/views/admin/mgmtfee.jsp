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
          <li class="active ">
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
            <a class="navbar-brand" href="#pablo">Management Fee</a>
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
      
      
     
      <div class="content">
           <div class="row">
          <div class="col-md-12">
            <div class="card ">
              <div class="card-header">
                <h3 class="title">????????? ????????? ?????? ??????</h3>
              </div>
              
              <div class="col-md-12">
                <div class="places-buttons">
                  <div class="row">
                    <div class="col-md-6 ml-auto mr-auto text-center">
                      <h4 class="card-title">
                       	????????? ?????? ??????
                        <p class="category">?????? ????????? ???????????????.
                        <br>??????????????? ????????? ????????? ???????????????.</p>
                      </h4>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-16 ml-auto mr-auto">
                      <div class="row">
                        <div class="col-md-3">
                        <form action="${context }/admin/mgmtfee">
                          <button type="submit" class="btn btn-primary btn-block btn-nopayment" id="isPayment">??????????????? ??????
                          <input type="hidden" name="standard" value="isPayment">
                          </button>
                          </form>
                        </div>
                        <div class="col-md-3 dropdown">
                          <button class="btn btn-primary btn-block" id="search-button" data-toggle="modal" data-target="#mgmtfeeDueDateModal">?????? ?????? ??????</button>
                        </div>
                        <div class="col-md-3">
                          <button class="btn btn-primary btn-block" id="search-button" data-toggle="modal" data-target="#mgmtfeeNumberModal">?????????????????? ??????</button>
                        </div>
                        <div class="col-md-3">
                          <button class="btn btn-primary btn-block" id="search-button" data-toggle="modal" data-target="#generationInfoModal">??????????????? ??????</button>
                        </div>
                         <div class="col-md-3">
                          <button class="btn btn-primary btn-block" id="mgmtfee-upload" data-toggle="modal" data-target="#mgmtfeeUploadrModal">????????? ?????????</button>
                        </div>
                         <div class="col-md-3">
                         <button class="btn btn-primary btn-block mgmtfeeDelete" data-toggle="modal" data-target="#mgmtfeeDeleteModal" >????????? ??????</button>
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
                     <div class="form-check checkAll" id="checkAll">
                         <label class="form-check-label">
                           <input class="form-check-input allmgmtfee" type="checkbox" value="">
                           <span class="form-check-sign">
                             <span class="check"></span>
                           </span>
                         </label>
                       </div>
                   </th>
                   <th>???????????????</th>
                   <th>????????????</th>
                   <th>????????? ????????? </th>
                   <th>????????????</th>
                   <th>????????????</th>
                   <th>????????????</th>
                 </thead>
                 <tbody>
                
               <c:choose>
               	<c:when test="${mgmtfeeList.size() > 0}">
               		<c:forEach items="${mgmtfeeList}" var="mgmtfee" varStatus="status">
                   <tr>
                   	 <td>
                         <div class="form-check">
                           <label class="form-check-label">
                             <input class="form-check-input mgmtfee" type="checkbox" value="" name="mgmtfee">
                             <span class="form-check-sign">
                               <span class="check"></span>
                             </span>
                           </label>
                         </div>
                       </td>
                       <td><a href="/mypage/mymgmtfee/detail?mgmtfeeidx=${mgmtfee.mgmtfeeIdx }"> ${mgmtfee.mgmtfeeIdx}</a> </td>
                       <td> ${generationList[status.index].building }??? ${generationList[status.index].num}???</td>
                       <td> ${mgmtfee.dueDate } </td>
                       <td><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.periodPayment}"/> </td>
                       <c:choose>
                       	<c:when test="${mgmtfee.isPayment eq 0}">
                       		<td>??????</td>
                       	</c:when>
                       	<c:otherwise>
                       		<td>??????</td>
                       	</c:otherwise>
                       </c:choose>
                       <td> <a href="/admin/mgmtfee/modify?mgmtfeeidx=${mgmtfee.mgmtfeeIdx}"><i class="tim-icons icon-paper"></i></a></td>
                      </tr>
                   </c:forEach>
               	</c:when>
              
               </c:choose>
            	
              		
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
          
          
          <c:choose>
          	<c:when test="${searchType eq 'apartmentIdx'}">
          	<div class="row d-flex card-body ">
	          <div class="col text-center">
	            <div class="block-27">
	              <ul>
	                <li><a href="/admin/${paging.type }">&lt;&lt;</a></li>
	                <li><a href="/admin/${paging.type }?page=${paging.prev}">&lt;</a></li>
	                <c:choose>
	                	<c:when test="${paging.lastPage eq 0 }">
	                		<li><a href="/admin/${paging.type }">1</a></li>
	                	</c:when>
	                	<c:otherwise>
	                		<c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
		                      <c:choose>
		                         <c:when test="${paging.currentPage eq page}">
		                            <li class="active"><a href="/admin/${paging.type }?page=${page}">${page}</a></li>
		                         </c:when>
		                         <c:otherwise>
		                            <li><a href="/admin/${paging.type }?page=${page}">${page}</a></li>
		                         </c:otherwise>
		                      </c:choose>
		                 	 </c:forEach> 
	                	</c:otherwise>
	                </c:choose>
	                 
	                <li><a href="/admin/${paging.type }?page=${paging.next}">&gt;</a></li>
	                <li><a href="/admin/${paging.type }?page=${paging.lastPage }">&gt;&gt;</a></li>
	              </ul>
	            </div>
	          </div>
	        </div>
          	</c:when>
          	

          	<c:when test="${searchType eq 'nopayment' }">
          	<div class="row d-flex card-body ">
	          <div class="col text-center">
	            <div class="block-27">
	               <ul>
	                <li><a href="/admin/${paging.type }?standard=${searchType}">&lt;&lt;</a></li>
	                <li><a href="/admin/${paging.type }?page=${paging.prev}&standard=${searchType}">&lt;</a></li>
	                 <c:choose>
	                	<c:when test="${paging.lastPage eq 0 }">
	                		<li><a href="/admin/${paging.type }&standard=${searchType}">1</a></li>
	                	</c:when>
	                	<c:otherwise>
		                 <c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
	                      <c:choose>
	                         <c:when test="${paging.currentPage eq page}">
	                            <li class="active"><a href="/admin/${paging.type }?page=${page}&standard=${searchType}">${page}</a></li>
	                         </c:when>
	                         <c:otherwise>
	                            <li><a href="/admin/${paging.type }?page=${page}&standard=${searchType}">${page}</a></li>
	                         </c:otherwise>
	                      </c:choose>
	                 	 </c:forEach>
                 	 </c:otherwise>
	                </c:choose> 
	                <li><a href="/admin/${paging.type }?page=${paging.next}&standard=${searchType}">&gt;</a></li>
	                <li><a href="/admin/${paging.type }?page=${paging.lastPage }&standard=${searchType}">&gt;&gt;</a></li>
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
	                <li><a href="/admin/${paging.type }?standard=${searchType }&keyword=${keyword}">&lt;&lt;</a></li>
	                <li><a href="/admin/${paging.type }?page=${paging.prev}&standard=${searchType }&keyword=${keyword}">&lt;</a></li>
              	  <c:choose>
                	<c:when test="${paging.lastPage eq 0 }">
                		<li><a href="/admin/${paging.type }">1</a></li>
                	</c:when>
                	<c:otherwise>
	                 <c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
                      <c:choose>
                         <c:when test="${paging.currentPage eq page}">
                            <li class="active"><a href="/admin/${paging.type }?page=${page}&standard=${searchType }&keyword=${keyword}">${page}</a></li>
                         </c:when>
                         <c:otherwise>
                            <li><a href="/admin/${paging.type }?page=${page}&standard=${searchType }&keyword=${keyword}">${page}</a></li>
                         </c:otherwise>
                      </c:choose>
                 	 </c:forEach> 
                 	 </c:otherwise>
	                </c:choose>
	                <li><a href="/admin/${paging.type }?page=${paging.next}&standard=${searchType }&keyword=${keyword}">&gt;</a></li>
	                <li><a href="/admin/${paging.type }?page=${paging.lastPage }&standard=${searchType }&keyword=${keyword}">&gt;&gt;</a></li>
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
            ??
            <script>
              document.write(new Date().getFullYear())
            </script> made with <i class="tim-icons icon-heart-2"></i> by
            <a href="https://www.creative-tim.com" target="_blank">aboo</a> for a better apartment.
          </div>
      </footer>
      </div>
    </div>
    
    
     <div class="modal fade" id="mgmtfeeDueDateModal" tabindex="-1" role="dialog" aria-labelledby="mgmtfeeDueDateModalLabel" aria-hidden="true">
       <div class="modal-dialog" role="document">
         <div class="modal-content" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675); color:white;">
           <div class="modal-header">
           	<h4 class="modal-title" id="mgmtfeeDueDateModalLabel">???????????? ??????</h4>
            	 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <i class="tim-icons icon-simple-remove"></i>
              </button>
           </div>
           <div class="modal-body">
        <form action="${context }/admin/mgmtfee">
          <div class="form-group">
          <input type="hidden" name="standard" value="dueDate">
           <input type="month" class="form-control mgmtfee-keyword" id="inlineFormInputGroup" name="keyword">
          </div>
          <div class="modal-footer">
           <button type="submit" class="btn btn-primary">??????</button>
           </div>
        </form>
      </div>
         </div>
       </div>
     </div>
     
    <div class="modal fade" id="mgmtfeeNumberModal" tabindex="-1" role="dialog" aria-labelledby="mgmtfeeNumberModalLabel" aria-hidden="true">
       <div class="modal-dialog" role="document">
         <div class="modal-content" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675); color:white;">
           <div class="modal-header">
           	<h4 class="modal-title" id="mgmtfeeNumberModalLabel">?????????????????? ??????</h4>
            	 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <i class="tim-icons icon-simple-remove"></i>
              </button>
           </div>
           <div class="modal-body">
        <form action="${context }/admin/mgmtfee">
          <div class="form-group">
          <input type="hidden" name="standard" value="mgmtfeeIdx">
           <input type="text" class="form-control mgmtfee-keyword" id="inlineFormInputGroup" name="keyword" placeholder="?????????????????? ???????????????.">
          </div>
          <div class="modal-footer">
           <button type="submit" class="btn btn-primary">??????</button>
           </div>
        </form>
      </div>
         </div>
       </div>
     </div>
     
      <div class="modal fade" id="generationInfoModal" tabindex="-1" role="dialog" aria-labelledby="generationInfoModalLabel" aria-hidden="true">
       <div class="modal-dialog" role="document">
         <div class="modal-content" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675); color:white;">
           <div class="modal-header">
           	<h4 class="modal-title" id="generationInfoModalModalLabel">??????????????? ??????</h4>
            	 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <i class="tim-icons icon-simple-remove"></i>
              </button>
           </div>
           <div class="modal-body">
        <form action="${context }/admin/mgmtfee">
          <div class="form-group">
           <input type="hidden" name="standard" value="generationInfo">
           <input type="text" class="form-control mgmtfee-keyword" id="inlineFormInputGroup" name="keyword" placeholder="??????????????? ???????????????.(??? : 101-101)" pattern="[0-9]{3,4}-[0-9]{3,4}">
          </div>
          <div class="modal-footer">
           <button type="submit" class="btn btn-primary">??????</button>
           </div>
        </form>
      </div>
         </div>
       </div>
     </div>
    
    
     <div class="modal fade" id="mgmtfeeUploadrModal" tabindex="-1" role="dialog" aria-labelledby="mgmtfeeUploadModalLabel" aria-hidden="true">
       <div class="modal-dialog" role="document">
         <div class="modal-content" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675); color:white;">
           <div class="modal-header">
           	<h4 class="modal-title" id="mgmtfeeUploadModalLabel">????????? ?????? ?????????</h4>
            	 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <i class="tim-icons icon-simple-remove"></i>
              </button>
           </div>
           <div class="modal-body">
        <form action="/work/mgmtfeeuploadimpl" id="form-mgmtfee" method="post" enctype="multipart/form-data">
          <div class="">
           <input type="file" name="file" class="form-control col-xs-2 " id="inlineFormInputGroup" value="????????? ????????? ?????????">
          </div>
    	  </div>
           <div class="modal-footer">
           <button type="submit" class="btn btn-primary " data-dismiss="modal" onclick="send()">???????????????</button>
           </form>
           <button type="button" class="btn btn-primary btn_down-file" data-dismiss="modal" onclick="downloadFile()">????????? ?????? ????????????</button>
           </div>
         </div>
       </div>
     </div>
     
     <!-- ?????? ?????? ?????? -->
	<div class="modal fade" id="mgmtfeeDeleteModal" tabindex="-1" role="dialog" aria-labelledby="mgmtfeeDeleteModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675); color:white;">
	      <div class="modal-header">
	       	<h4 class="modal-title" id="mgmtfeeDeleteModalLabel">????????? ??????</h4>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">??? <span id="result"></span> ?????? ?????? ?????????????????????????</label>
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">????????????</button>
	        <button type="button" class="btn btn-primary btn-delete-mgmtfee" data-dismiss="modal">????????????</button>
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
 		const url = "${ContextPath}/admin/mgmtfee/uploadimpl";
 		
        fetch(url,{
        	method:"post",
        	body:form
        }).then(response => {
        	if(response.ok){
        		return response.text();
        	}
        	throw new AsyncPageError(response.text()); 
        }).then((text)=>{
        	if(text == "success"){
        		alert('?????? ??????????????????.');
        		location.href='${context}' + "/admin/mgmtfee";
             } else {
            	alert('????????? ????????? ????????????.');
             }
        })
         .catch(error=>{
        	 error.alertMessage();
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