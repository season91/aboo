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
          <li  class="active">
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
          <li >
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
            <a class="navbar-brand" href="#pablo">Authority</a>
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
                    <a href="/admin/mypage/modifyinfo" class="nav-item dropdown-item">Profile</a>
                  </li>
                  <li class="nav-link">
                    <a href="/admin/chat" class="nav-item dropdown-item">1:1 chat</a>
                  </li>
                  <div class="dropdown-divider"></div>
                  <li class="nav-link">
                    <a href="/admin/logout" class="nav-item dropdown-item">Log out</a>
                  </li>
                </ul>
              </li>
              <li class="separator d-lg-none"></li>
            </ul>
          </div>
        </div>
      </nav>

      <!-- End Navbar -->
			  <div class="content">
		    <div class="row">
		        <div class="col-md-12">
					
					<div class= "w-100 d-flex justify-content-end">				
						<button class="btn btn-primary px-xl-5 py-xl-2 mb-2" data-toggle="modal" data-target="#add">??????</button>
						<button class="btn btn-info px-xl-5 py-xl-2 ml-2 mb-2" data-toggle="modal" data-target="#search">??????</button>
					</div>
		            <div class="card ">
		                <div class="card-header d-flex justify-content-between">
		                    <h4 class="card-title">????????????</h4>
		                </div>
		                <div class="card-body">
		                    <div class="table-responsive">
		                        <table class="table tablesorter " id="">
		                            <thead class=" text-primary">
			                            <tr>
			                                <th>??????</th>
			                                <th>??? </th>
			                                <th>???</th>
			                                <th>?????????</th>
			                                <th>?????????</th>
			                            </tr>
		                            </thead>
		                            <tbody>
										<c:forEach items="${authorityList}" var="authority">
											<tr onclick="openModal(this)" data-toggle="modal" data-target="#modifyModal">
											    <td>${authority.generationIdx}</td>
											    <td>${authority.building}</td>
											    <td>${authority.num}</td>
											   	<td>${authority.id}</td>
											    <td>${authority.regDate}</td>
											    <td><input type="hidden" id = "tell" value="${authority.tell}"></td>
											    <td><input type="hidden" id = "email" value="${authority.email}"></td>
											 </tr>
									    </c:forEach>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>		
		            </div>
		            	<c:choose>
          				<c:when test="${searchType eq 'apartmentIdx'}">
							<div class="row d-flex">
						    <div class="col text-center">
						      <div class="block-27">
						        <ul>
						        <li><a href="/admin/authority" class="nav first">&lt;&lt;</a></li>
						        <li><a href="/admin/authority?page=${paging.prev}">&lt;</a></li>
						        
						       <c:choose>
						        <c:when test="${paging.lastPage eq 0 }">
						         	<li class = "active"><a href="/admin/authority?page=${page}">1</a></li>
						        </c:when>
						    	<c:otherwise>						    	
							         <c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">					         
								         <c:choose>
								         	<c:when test="${paging.currentPage eq page}">
						         		  		<li class = "active"><a href="/admin/authority?page=${page}">${page}</a></li>
								         	</c:when>
								         	<c:otherwise>
						         		  		<li><a href="/admin/authority?page=${page}">${page}</a></li>
								         	</c:otherwise>
								         </c:choose>
					         		 </c:forEach>
				         		 </c:otherwise>
				         	    </c:choose>	 
						        <li><a href="/admin/authority?page=${paging.next}">&gt;</a></li>
						        <li>
		 	   	 				 <a href="/admin/authority?page=${paging.lastPage}">&gt;&gt;</a>
						         </li>
						        </ul>
						      </div>
						    </div>
						</div>	
					</c:when>
					
					<c:otherwise>
						<div class="row d-flex">
						    <div class="col text-center">
						      <div class="block-27">
						        <ul>
						        <li><a href="/admin/authority?kind=${searchType}&keyword=${keyword}" class="nav first">&lt;&lt;</a></li>
						        <li><a href="/admin/authority?page=${paging.prev}&kind=${searchType}&keyword=${keyword}">&lt;</a></li>
						       
						        <c:choose>
						        	<c:when test="${paging.lastPage eq 0 }">				
					         		  	<li><a href="/admin/authority?page=${page}&kind=${searchType}&keyword=${keyword}">1</a></li>
						        	</c:when>
							        <c:otherwise>
								         <c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">					         
									         <c:choose>
									         	<c:when test="${paging.currentPage eq page}">
							         		  		<li class = "active"><a href="/admin/authority?page=${page}&kind=${searchType}&keyword=${keyword}">${page}</a></li>
									         	</c:when>
									         	<c:otherwise>
							         		  		<li><a href="/admin/authority?page=${page}&kind=${searchType}&keyword=${keyword}">${page}</a></li>
									         	</c:otherwise>
									         </c:choose>
						         		 </c:forEach>
					         		 </c:otherwise>
				         		</c:choose> 
				         		
						        <li><a href="/admin/authority?page=${paging.next}&kind=${searchType}&keyword=${keyword}">&gt;</a></li>
						        <li>
		 	   	 				 <a href="/admin/authority?page=${paging.lastPage}&kind=${searchType}&keyword=${keyword}">&gt;&gt;</a>
						         </li>
						        </ul>
						      </div>
						    </div>
						</div>	
					</c:otherwise>
					
					</c:choose>
					
						        
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
            ??
            <script>
              document.write(new Date().getFullYear())
            </script> made with <i class="tim-icons icon-heart-2"></i> by
            <a href="https://www.creative-tim.com" target="_blank">aboo</a> for a better apartment.
          </div>
      </footer>
      </div>
    </div>

    
      <!-- ????????? ??????  Modal -->
      <div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title text-dark" id="myModalLabel">?????? ??????</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
            <form action="/admin/authorityadd" method="post">
       	      <div class="form-group">
                <label for="name">???</label>
    			<input type="text" id = "addBuilding" name = "building" class="form-control text-dark" required="required" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="?????? ???????????????">
              </div>             
              <div class="form-group">
                <label for="name">???</label>
    			<input type="text" id = "addNum" name = "num" class="form-control text-dark" required="required" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" placeholder="?????? ???????????????">
              </div>  
            <div class="modal-footer">
              <button type="button" id = "closeAdd" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary" >??????</button>
            </div>
              </form>    
            </div>
          </div>
        </div>
      </div>
      
      <!-- ????????? ??????  Modal -->
      <div class="modal fade" id="search" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title text-dark" id="myModalLabel">?????? ??????</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
            <form id = "searchForm" action="/admin/authority" method="get">
       	      <div class="form-group">
       	      	<div id = "lableWrap" class = "d-flex flex-column">
	                <label style="font-size: 1vw; color: #8a8a8a;" for="name">????????? ??????????????? ?????? : [000-000]</label>
	                <label style="font-size: 1vw; color: #8a8a8a;" for="name">?????? ??????????????? ?????? : [000]</label>
	                <label style="font-weight: bold;"><input type="checkbox" id = "checkBuilding"> ?????? ??????</label>
                </div>
                <div class = "d-flex justify-content-between">
                	<input id = "building" type="hidden" name="none" value="building">
                	<input id = "generation"  type="hidden" name="kind" value="generation">
    				<input type="text" id = "keyword" name = "keyword" class="form-control text-dark">
              	</div>
              </div>             
            <div class="modal-footer">
              <button type="button" id = "closeSearch" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="submit" class="btn btn-primary" >??????</button>
            </div>
              </form>    
            </div>
          </div>
        </div>
      </div>
      
      
      
      <!-- ????????? ?????? Modal -->
      <div class="modal fade" id="modifyModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h4 class="modal-title text-dark" id="myModalLabel">?????? ??????</h4>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            </div>
            <div class="modal-body">
    			<input type="hidden" class="form-control " id="modifyGenerationIdx"><!--generationIdx ????????????-->
       	      <div class="form-group">
                <label for="name">???</label>
    			<input type="text" class="form-control text-dark bg-white"  readonly="readonly"   id="modifyBuilding">
              </div>             
              <div class="form-group">
                <label for="name">???</label>
    			<input type="text" class="form-control text-dark bg-white" readonly="readonly" id="modifyNum">
              </div>
              <div class="form-group">
                <label for="name">?????????</label>
    			<input type="text" class="form-control text-dark bg-white" readonly="readonly" id="modifyId">
              </div>             
              <div class="form-group">
                <label for="name">????????????</label>
    			<input type="text" class="form-control text-dark bg-white" readonly="readonly" id="modifyTell">
              </div>
              <div class="form-group">
                <label for="name">????????????</label>
    			<input type="text" class="form-control text-dark bg-white" readonly="readonly" id="modityEmail">
              </div> 
               <div class="form-group">
                <label for="name">?????????</label>
    			<b><input type="text" class="form-control text-dark bg-white" readonly="readonly" id="modifyRegDate"></b>
              </div>    
            </div>
            <div class="modal-footer">
              <button type="button" id = "closeModity" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-info" onclick="reset() ">?????????</button>
              <button type="button" class="btn btn-danger" onclick="del() ">??????</button>
              
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

	<script type="text/javascript">
    document.querySelector('#searchForm').addEventListener('submit',(e)=>{
    	
		$("#checkBuilding").change(function(){        
	        if($("#checkBuilding").is(":checked")){

	        }else{
					let keyword = document.querySelector("#keyword").value;
					if (!keyword.includes('-')) {
						alert("-??? ???????????????");
			           	e.preventDefault();

					}

	        }
 	    }); 
	
    });
    
	</script>
    
    <!-- ????????? ?????? ????????? input?????? ???????????? -->
	<script type="text/javascript">
	
	let flg = false;
	
		$("#checkBuilding").change(function(){        
	        if($("#checkBuilding").is(":checked")){
				console.dir("??????")
				 document.querySelector("#generation").name = 'none';
				 document.querySelector("#building").name = 'kind';
				 flg = true;
				 console.dir('??????'+flg)
	        }else{
				console.dir("?????? ??????")
				 document.querySelector("#building").name = 'none';
				 document.querySelector("#generation").name = 'kind';
				 flg = false;
				 console.dir('????????????'+flg)

	        }
 	    }); 

        document.querySelector('#searchForm').addEventListener('submit', (e) => {
            let keyword = document.querySelector("#keyword").value;

            if (!flg) {
              if (!keyword.includes('-')) {
                alert("-??? ???????????????");
                e.preventDefault();

              }
            }
          });
	
	</script>

    <script type="text/javascript"> /*????????? ??? ??????*/
	let openModal = (info) => {
		   $('#modifyModal').show();
			  let generationIdx = info.cells[0].innerHTML;
			  let building = info.cells[1].innerHTML;
			  let num = info.cells[2].innerHTML;
			  let id = info.cells[3].innerHTML;
			  let regDate = info.cells[4].innerHTML;
			  let tell = info.cells[5].children[0].defaultValue
			  let email = info.cells[6].children[0].defaultValue
		   	  console.dir(generationIdx)

			  document.querySelector("#modifyGenerationIdx").value = generationIdx;
			  document.querySelector("#modifyBuilding").value = building;
			  document.querySelector("#modifyNum").value = num;
			  document.querySelector("#modifyId").value = id;
			  document.querySelector("#modifyRegDate").value = regDate;
			  document.querySelector("#modifyTell").value = tell;
			  document.querySelector("#modityEmail").value = email; 
		}
   </script>
   
   	<script type="text/javascript"> /*?????????*/
	let reset = () => {
		
		let	result = confirm('?????? ????????????????????????????')
		if (result) {
         const url = '/admin/authorityreset';
         let paramObj = new Object();
         paramObj.generationIdx = document.querySelector("#modifyGenerationIdx").value;
         paramObj.building = document.querySelector("#modifyBuilding").value;
         paramObj.num = document.querySelector("#modifyNum").value;
         paramObj.id = document.querySelector("#modifyId").value;

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
          		alert("???????????? ?????????????????????.")
          		location.href = "/admin/authority"
            }else{
          		alert("???????????? ?????????????????????.")
            }
         }).catch(error => {
            error.alertMessage();
         });	
         
		 }
		}
 		
	</script>
	
	<script type="text/javascript"> /*??????*/
	let del = () => {
		
		let	result = confirm('?????? ?????? ???????????????????')
		if (result) {
         const url = '/admin/authoritydelete';
         let paramObj = new Object();
         paramObj.generationIdx = document.querySelector("#modifyGenerationIdx").value;

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
          		alert("????????? ?????????????????????.")
          		location.href = "/admin/authority"
            }else{
          		alert("????????? ?????????????????????.")
            }
         }).catch(error => {
            error.alertMessage();
         });	
         
		 }
		}
 		
	</script>
	
	<!-- ?????? Close ????????? -->
	<script type="text/javascript">
	
		$('#closeAdd').click(function(e) {
		   $('#addBuilding').val(" ");
		   $('#addNum').val(" ");
		});		
		
		$('#closeSearch').click(function(e) {
			   $('#keyword').val(" ");
			});
		
	</script>
	
	
	
	
	
	
</body>
</html>