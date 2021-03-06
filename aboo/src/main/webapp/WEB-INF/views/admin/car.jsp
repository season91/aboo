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
          <li class="active ">
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
            <a class="navbar-brand" href="#pablo">Car</a>
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
      
   
      <!-- End Navbar -->
      
		
      <div class="content">
      	 
           <div class="row">
          <div class="col-md-12">
            <div class="card ">
              <div class="card-header">
                <h3 class="title">?????? ?????? ??????</h3>
              </div>
              
              <div class="col-md-12">
                <div class="places-buttons">
                  <div class="row col-md-12">
                    <div class="col-md-6 ml-auto mr-auto text-center">
                      <h4 class="card-title">
                       	???????????? ??????
                        <p class="category">?????? ????????? ???????????????.</p>
                      </h4>
                    </div>
                  </div>
                 <div class="row ">
                    <div class="col-lg-12 ml-auto mr-auto">
                      <div class="row">
                        <div class="col-md-4">
                          <button type="button" class="btn btn-primary btn-block carAdd" data-toggle="modal" data-target="#addcarModal" >?????? ??????</button>
                        </div>
                        <div class="col-md-4">
                         <button type="button" class="btn btn-primary btn-block carModify" data-toggle="modal" data-target="#modifycarModal">?????? ??????</button>
                        </div>
                         <div class="col-md-4">
                         <button type="button" class="btn btn-primary btn-block carDelete" data-toggle="modal" data-target="#deletecarModal">?????? ??????</button>
                        </div>
                         <div class="col-md-4">
                         <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#carNumberModal">??????????????? ??????</button>
                        </div>
                        <div class="col-md-4">
                          <button type="button" class="btn btn-primary btn-block" data-toggle="modal" data-target="#geneIdxModal" >??????????????? ??????</button>
                        </div>
                         <div class="col-md-4">
                         <form action="${context }/admin/car/application">
                          <button type="submit" class="btn btn-primary btn-block">?????? ?????? ??????</button>
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
                      <th>
                        <div class="form-check checkAll" id="checkAll">
                            <label class="form-check-label">
                              <input class="form-check-input allcar" type="checkbox" value="">
                              <span class="form-check-sign">
                                <span class="check"></span>
                              </span>
                            </label>
                          </div>
                      </th>
                       <th>????????????</th>
                      <th>????????????</th>
                      <th>????????????</th>
                      <th>????????????</th>
                    </thead>
                    <tbody>
                    
                    <c:choose>
                    	<c:when test="${carList.size() > 0 }">
                    		<c:forEach items="${carList}" var="car" varStatus="status">
		                     <tr>
		                       <td>
		                          <div class="form-check">
		                            <label class="form-check-label">
		                              <input class="form-check-input car" type="checkbox" value="">
		                              <span class="form-check-sign">
		                                <span class="check"></span>
		                              </span>
		                            </label>
		                          </div>
		                        </td>
		                        <td>${car.carIdx}</td>
		                        <td> ${generationList[status.index].building }-${generationList[status.index].num}</td>
		                        <td> ${car.carNumber } </td>
		                        <c:choose>
		                        	<c:when test="${car.isDel eq 0}">
		                        		<td>?????? ???</td>
		                        	</c:when>
		                        	<c:otherwise>
		                        		<td>?????? ??????</td>
		                        	</c:otherwise>
		                        </c:choose>
		                        
		                       </tr>
		                    </c:forEach>
                    	</c:when>
                    	<c:otherwise>
                    		<script>
		               		alert('??????????????? ????????????.');
		               		location.href='/admin/car';
		               		</script>
                    	</c:otherwise>
                    </c:choose>
                      
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
          
          <c:choose>
          	<c:when test="${searchType eq 'apartmentIdx' }">
          	<div class="row mt-5 d-flex card-body ">
	          <div class="col text-center">
	            <div class="block-27">
	              <ul>
	                <li><a href="/admin/${paging.type }">&lt;&lt;</a></li>
	                <li><a href="/admin/${paging.type }?page=${paging.prev}">&lt;</a></li>
                	 <c:choose>
	                	<c:when test="${paging.lastPage eq 0 }">
	                		<li><a href="/admin/${paging.type }"><span>1</span></a></li>
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
          	
          	<c:otherwise>
          	<div class="row mt-5 d-flex card-body ">
	          <div class="col text-center">
	            <div class="block-27">
	              <ul>
	                <li><a href="/admin/${paging.type }?standard=${searchType }&keyword=${keyword}">&lt;&lt;</a></li>
	                <li><a href="/admin/${paging.type }?page=${paging.prev}&standard=${searchType }&keyword=${keyword}">&lt;</a></li>
              	  <c:choose>
                	<c:when test="${paging.lastPage eq 0 }">
                		<li><a href="/admin/${paging.type }?standard=${searchType }&keyword=${keyword}">1</a></li>
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
    
    <!-- ?????? ?????? -->
   <div class="modal fade" id="addcarModal" tabindex="-1" role="dialog" aria-labelledby="addcarModalModalLabel" aria-hidden="true">
       <div class="modal-dialog" role="document">
         <div class="modal-content" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675); color:white;">
           <div class="modal-header">
           	<h4 class="modal-title" id="addcarModalLabel">?????? ??????</h4>
            	 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <i class="tim-icons icon-simple-remove"></i>
              </button>
           </div>
           <div class="modal-body">
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">?????? ??????(???)</label>
	            <input type="text" class="form-control" name="building" id="building">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">?????? ??????(??????)</label>
	            <input type="text" class="form-control" name="num" id="num">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">?????? ??????</label>
	            <input type="text" class="form-control" name="carNumber" id="carNumber">
	          </div>
	          <div class="modal-footer">
	           <button type="submit" class="btn btn-primary" onclick="send()">????????????</button>
	           <button type="button" class="btn btn-default" data-dismiss="modal">????????????</button>
	           </div>
      		</div>
         </div>
       </div>
     </div>
	
	<!-- ?????? ?????? ?????? -->
	<div class="modal fade" id="modifycarModal" tabindex="-1" role="dialog" aria-labelledby="modifycarModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675); color:white;">
	      <div class="modal-header">
	       	<h4 class="modal-title" id="modifycarModalLabel">?????? ??????</h4>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	      </div>
	      <div class="modal-body">
	        <form action="${context }/admin/carmodify">
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">?????? ?????? ??????</label>
	            <input type="text" class="form-control" readonly="readonly" id="modify-car-idx"  name="carIdx">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">?????? ??????</label>
	            <input type="text" class="form-control" readonly="readonly" id="modify-car-info" name="generationInfo">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">?????? ??????</label>
	            <input type="text" class="form-control" id="modify-car-number" placeholder="??????????????? ???????????????.(???: 123???4567)" name="carNumber">
	          </div>
	            <div class="modal-footer">
		      	<button type="submit" class="btn btn-primary">????????????</button>
		        <button type="button" class="btn btn-default" data-dismiss="modal">????????????</button>
		      </div>
	        </form>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- ?????? ?????? ?????? -->
	<div class="modal fade" id="deletecarModal" tabindex="-1" role="dialog" aria-labelledby="deletecarModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675); color:white;">
	      <div class="modal-header">
	       	<h4 class="modal-title" id="deletecarModalLabel">?????? ??????</h4>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">??? <span id="result"></span> ?????? ?????? ?????????????????????????</label>
	          </div>
	          <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">????????????</button>
		        <button type="button" class="btn btn-primary btn-delete-car" >????????????</button>
		      </div>
	        </form>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<div class="modal fade" id="carNumberModal" tabindex="-1" role="dialog" aria-labelledby="carNumberModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675); color:white;">
            <div class="modal-header">
            	<h4 class="modal-title" id="carNumberModalLabel">??????????????? ??????</h4>
             	 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                <i class="tim-icons icon-simple-remove"></i>
	              </button>
            </div>
            <div class="modal-body">
	        <form action="${context }/admin/car">
	          <div class="form-group">
	           <input type="hidden" name="standard" value="carNumber">
	           <input type="text" class="form-control" name="keyword" id="inlineFormInputGroup" placeholder="??????????????? ??????">
	          </div>
	          <div class="modal-footer">
             <button type="submit" class="btn btn-primary">??????</button>
            </div>
	        </form>
	      </div>
          </div>
        </div>
      </div>
      
      
      <div class="modal fade" id="geneIdxModal" tabindex="-1" role="dialog" aria-labelledby="geneIdxModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
          <div class="modal-content" style="background-image: linear-gradient(to bottom left, #344675, #263148, #344675); color:white;">
            <div class="modal-header">
            	<h4 class="modal-title" id="geneIdxModalLabel">??????????????? ??????</h4>
             	 <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                <i class="tim-icons icon-simple-remove"></i>
	              </button>
            </div>
            <div class="modal-body">
	        <form action="${context }/admin/car">
	          <div class="form-group">
	          	<input type="hidden" name="standard" value="generationInfo">
	           <input type="text" class="form-control"  name="keyword" id="inlineFormInputGroup" placeholder="??????????????? ?????? (101-101)" pattern="[0-9]{3,4}-[0-9]{3,4}">
	          </div>
	           <div class="modal-footer">
	            <button type="submit" class="btn btn-primary">??????</button>
	            </div>
	        </form>
	      </div>
          </div>
        </div>
      </div>
      
    <!--   Core JS Files   -->
    <script src="../../../resources/js/admin/car.js"></script>
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
    <script type="text/javascript">
	  let send = () =>{ 
			let carNumber =  document.querySelector('#carNumber').value;
			let building =  document.querySelector('#building').value;
			let num =  document.querySelector('#num').value;
			if (carNumber) {
				let regExp = /^\d{2,3}[???-???]\d{4}$/;
				if(regExp.test(carNumber)){
					fetch("/admin/caradd?building="+building+"&num="+num+"&carNumber="+carNumber,{
						method:"GET"
					})
					.then(response => response.text())
					.then(text => {
						console.dir(text);
						if(text == 'success'){
							alert("??????????????? ?????????????????????.");
							location.href="/admin/car";
						} else if(text == 'null') {
							alert("???????????? ?????? ?????? ?????? ?????????.");
							location.href="/admin/car";
						} else {
							alert("????????? ???????????? ???????????????. ??????????????? ???????????????.");
						}
					})
				} else {
					alert("???????????? ????????? ?????? ?????????????????????.");
				}
			} else{
				alert("???????????? ??????????????? ??????????????????.");
			}
		}
	  </script>
</body>
</html>