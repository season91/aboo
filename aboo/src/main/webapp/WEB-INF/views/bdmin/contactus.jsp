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
           ABOO
          </a>
        </div>
        <ul class="nav">
          <li >
            <a href="/admin/mypage/modifyinfos">
              <i class="tim-icons icon-badge"></i>
              <p> contact us</p>
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
            <div class="card">
              <div class="card-header">
                <h3 class="title">Contact Us, 서비스 이용 신청서</h3>
                <p>안녕하세요. 아파트를 부탁해! 서비스에 관심주심에 감사드립니다.</p>
                <p>서비스 이용을 위해 기본 항목들을 입력해 제출해주시면 4~5일안으로 처리해드리도록 하겠습니다.</p>
                <p>자세한 사항은 담당자에게 메일로 문의해주시기 바랍니다.</p>
              </div>
                	  
              <div class="card-body">
                  <form id="form-apart" action="${context}/bdmin/contactusimpl" method="post">
                  <h4 class="title"> 1. 아파트 정보 작성</h4>
                  	<div class="row">
	                    <div class="col-md-2 pr-md-1">
	                      <div class="form-group">
	                        <label>아파트 이름(지역명과 같이 작성)</label>
	                        <input type="text" class="form-control"name="aApartmentName" placeholder="예 : 반포자이, 산이리대주파크빌">
	                      </div>
	                    </div>
	                    <div class="col-md-2 px-md-1">
	                      <div class="form-group">
	                        <label>아파트 구분자(영문 3자)</label>
	                        <input type="text" class="form-control" name="aSeparator" placeholder="예 : 반포자이 bxi " maxlength="3">
	                      </div>
	                    </div>
	                    <div class="col-md-2 pl-md-1">
	                      <div class="form-group">
	                        <label for="exampleInputEmail1">아파트 주소</label>
	                        <input type="button" class="form-control" value="주소 검색" onclick="searchAdress()">
	                        <input type="tel" class="form-control" readonly="readonly" id="address" name="aApartmentAddress">
	                      </div>
	                    </div>
		                  <div class="col-md-2 px-md-1">
	                      <div class="form-group">
	                        <label>아파트 총 세대수</label>
	                        <input type="text" class="form-control" name="aGenerationCnt" placeholder="아파트 총 세대수">
	                      </div>
	                    </div>
	                    <div class="col-md-2 px-md-1">
	                      <div class="form-group">
	                        <label>주차 가능 대수</label>
	                        <input type="text" class="form-control" name="aParking" placeholder="아파트 주차가능 대수">
	                      </div>
	                    </div>
                    </div>
                    
                     <h4 class="title"> 2. 신청자 정보 작성</h4>
	                 <div class="row">
                  	<div class="col-md-3 pr-md-1">
                      <div class="form-group">
                        <label>신청 담당자 이름</label>
                        <input type="text" class="form-control" name="aName" placeholder="김아부" >
                      </div>
                    </div>                 	
                     <div class="col-md-3 pr-md-1">
                      <div class="form-group">
                        <label>신청 담당자 연락처</label>
                        <input type="tel" class="form-control" name="aTell" placeholder="-제외하고 입력하세요">
                      </div>
                    </div>                 	
                     <div class="col-md-3 pr-md-1">
                      <div class="form-group">
                        <label>신청 담당자 이메일</label>
                        <input type="email" class="form-control" name="aEmail" placeholder="aboo@aboo.com" >
                      </div>
                    </div>                 	
                	</div>
                	<div class="card-footer" style="display: flex; justify-content: space-around;">
		                <button type="submit" class="btn btn-fill btn-primary">제출하기</button>
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
   
    </script>
    <script>
      $(document).ready(function() {
        // Javascript method's body can be found in assets/js/demos.js
        demo.initDashboardPageCharts();

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