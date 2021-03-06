<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/generationhead.jsp" %>
<!DOCTYPE html>
<html>
<!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<body>
	  <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	      <a class="navbar-brand" href="/index">ABOO</a>
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>
	      <div class="collapse navbar-collapse" id="ftco-nav">
	        <ul class="navbar-nav ml-auto">
	          <li class="nav-item"><a href="/index" class="nav-link">Home</a></li>
	          <li class="nav-item"><a href="/about" class="nav-link">About</a></li>
	          <li class="nav-item"><a href="/myapt/schedule" class="nav-link">MyApt</a></li>
	          <li class="nav-item"><a class="nav-link" href="/board/info/listinfo">Board</a></li>
	          <li class="nav-item active"><a href="/mypage/modifyinfo" class="nav-link">MyPage</a></li>
	          <c:choose>
		          <c:when test="${sessionScope.generation == null && sessionScope.admin == null}">
		          	<li class="nav-item cta"><a href="/login" class="nav-link"><span>Login</span></a></li>	          
		          </c:when>
		          <c:when test="${sessionScope.generation != null && sessionScope.admin == null}">
		          	<li class="nav-item cta"><a href="/logout" class="nav-link"><span>Logout</span></a></li>	          
		          </c:when>
		          <c:when test="${sessionScope.admin != null && sessionScope.generation == null}">
		          	<li class="nav-item cta"><a href="/admin/logout" class="nav-link"><span>Admin Logout</span></a></li>	          
		          </c:when>
	          </c:choose>
	        </ul>
	      </div>
	    </div>
	  </nav>
    <!-- END nav -->

    <section class="home-slider owl-carousel">
      <div class="slider-item bread-item" style="background-image:url(../../../resources/abooimg/logo_w.png);" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container-fluid">
          <div class="row slider-text align-items-center justify-content-center" data-scrollax-parent="true">

            <div class="col-md-8 mt-5 text-center col-sm-12 ftco-animate" data-scrollax=" properties: { translateY: '70%' }">
              <p class="breadcrumbs" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }"><span class="mr-2"><a href="/mypage/myalarm">My alarm</a></span><span class="mr-2"><a href="/mypage/mycar">My Car</a></span><span class="mr-2"><a href="/mypage/modifyinfo">My Information</a></span><span class="mr-2"><a href="/mypage/generationwon">Generation won</a></span><span class="mr-2"><a href="/mypage/writelist/myinfolist">My write list</a></span></p>
	            <h1 class="mb-3 bread" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">My Management Fee detail</h1>
            </div>
          </div>
        </div>
      </div>
    </section>

	 <section class="ftco-section bg-light">
    	<div class="container">
    		<div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2 class="mb-4"> ${mgmtdate.YEAR}??? ${mgmtdate.MONTH }??? ????????? ?????? ??????</h2>
            <p>????????? ?????? ??????????????? ??????????????? ?????????????????? ????????????.</p>
          </div>
        </div>
    		<div class="row d-flex justify-content-center">
	        <div class="col-lg-6 col-md-10 ftco-animate ">
	          <div class="block-7">
	            <div class="text-center">
	            <c:choose>
	            	<c:when test="${mgmtfee != null }">
	            		<span class="price"><span class="number">????????? ?????????</span></span>
			            <c:choose>
	            			<c:when test="${mgmtfeeOverdue != null }">
	            				<span class="price"><span class="number period-payment"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.periodPayment + mgmtfeeOverdue.overdueFee}"/> </span></span>
	            			</c:when>
	            			<c:otherwise>
	            				<span class="price"><span class="number period-payment"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.periodPayment}"/></span></span>
	            			</c:otherwise>
	            		</c:choose>
	            		<input type="hidden" id ="mgmtfeeIdx" value="${mgmtfee.mgmtfeeIdx}">
			            <span class="excerpt d-block"><strong>????????? ????????? </strong> ${mgmtfee.mgmtWriteDate }</span>
			            <span class="excerpt d-block"><strong>???????????? </strong>${mgmtfee.mgmtStartDate } ~ ${mgmtfee.mgmtEndDate}</span>
			            <ul class="pricing-text mb-4">
			               <li><strong>??????????????? </strong><span id = "gnrlMgmtFee" ><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.gnrlMgmtFee}"/></span></li>
			              <li><strong>????????? </strong><span id = "cleanFee"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.cleanFee}"/></span></li>
			              <li><strong>?????????????????? </strong><span id = "elvtrMnfee" ><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.elvtrMnfee}"/></span></li>
			              <li><strong>??????????????? </strong><span id = "genElctr"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.genElctr}"/></span></li>
			              <li><strong>??????????????? </strong><span id = "comonElctr"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.comonElctr}"/></span> </li>
			              <li><strong>??????????????? </strong><span id = "genWater"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.genWater}"/></span> </li>
			              <li><strong>???????????? </strong><span id = "sewer"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.sewer}"/></span> </li>
			              <li><strong>????????? </strong><span id = "expenses"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.expenses}"/></span></li>
			              <li><strong>??????????????? </strong><span id = "genReduction"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.genReduction}"/></span></li>
			              <c:if test="${mgmtfeeOverdue != null }">
			              	<li class="overdue-fee"><strong>????????? </strong><span id = "overdueFee" ><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfeeOverdue.overdueFee}"/></span></li>
			              </c:if>
			              <li><strong>????????? ?????? </strong><span id= "periodPayment"><fmt:formatNumber type="number" maxFractionDigits="3" value="${mgmtfee.periodPayment}"/></span></li>
			              <li><strong>????????? </strong> ${mgmtfee.dueDate }</li>
			              <li>??????????????? ?????? ???????????? ???????????? ?????????????????? ????????? ???????????? ???????????????.</li>
			            </ul>
			            <c:if test="${mgmtfee.isPayment eq 0 && sessionScope.admin == null}">
			            	<a class="btn btn-primary d-block px-3 py-3 mb-4 btn-payment" id = "check_module">????????????</a>
			            </c:if>
	            	</c:when>
	            	<c:otherwise>
	            		<span class="price"><span class="number">??????????????? ????????????.</span></span>
	            	</c:otherwise>
	            </c:choose>
	            </div>
	          </div>
	          <div class="row d-flex justify-content-center">
	          		<c:if test="${sessionScope.admin == null}">
		          	 <a href="/mypage/mymgmtfee" class="btn btn-primary btn-primary-2 p-3 px-xl-5 py-xl-3 mt-3" style="background: linear-gradient(45deg, #56c8fb 0%, #627bed 100%); border: none;">????????????</a>
		          	</c:if>
		          </div>
	        </div>
	      </div>
    	</div>
    	
    </section>

    <footer class="ftco-footer ftco-bg-dark ftco-section">
      <div class="container">
        <div class="row mb-5">
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">ABOO</h2>
              <p>???????????? ?????????!<br>
              ????????? ?????? ?????????????????? ??????, ???????????????, ????????????, ???????????? ?????? ??? ???????????? ?????? ????????? ???????????????.
              </p>
              <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                <li class="ftco-animate"><a href="https://github.com/season91/aboo"><span><i class="fab fa-github"></i></span></a></li>
                <li class="ftco-animate"><a href="https://app.asana.com/0/1200089682403032/list"><span><i class="fas fa-calendar-check"></i></span></a></li>
                <li class="ftco-animate"><a href="https://www.erdcloud.com/d/d3HajP7xvXbTnSxtE"><span><i class="fas fa-cloud"></i></span></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4 ml-md-5">
              <h2 class="ftco-heading-2">Useful Links</h2>
              <ul class="list-unstyled">
                <li><a href="/myapt/parking" class="py-2 d-block">Parking</a></li>
                <li><a href="/board/info/listinfo" class="py-2 d-block">Info Board</a></li>
                <li><a href="/board/interior/intlist" class="py-2 d-block">Interior Board</a></li>
                <li><a href="/board/used/usedlist" class="py-2 d-block">Used Board</a></li>
                <li><a href="/myapt/schedule" class="py-2 d-block">Apt Schedule</a></li>
                <li><a href="/myapt/institutions/institutions" class="py-2 d-block">Institutions</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
             <div class="ftco-footer-widget mb-4">
              <h2 class="ftco-heading-2">Navigational</h2>
              <ul class="list-unstyled">
                <li><a href="/index" class="py-2 d-block">Home</a></li>
                <li><a href="/about" class="py-2 d-block">About</a></li>
                <li><a href="/myapt/schedule" class="py-2 d-block">MyApt</a></li>
                <li><a href="/board/info/listinfo" class="py-2 d-block">Board</a></li>
                <li><a href="/mypage/modifyinfo" class="py-2 d-block">MyPage</a></li>
                <li><a href="/bdmin/contactus" class="py-2 d-block">Contact us</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md">
            <div class="ftco-footer-widget mb-4">
            	<h2 class="ftco-heading-2">Office</h2>
            	<div class="block-23 mb-3">
	              <ul>
	                <li><span class="icon icon-map-marker"></span><span class="text">6, Teheran-ro 14-gil, Gangnam-gu, Seoul, Republic of Korea</span></li>
	                <li><a href="#"><span class="icon icon-phone"></span><span class="text">+82 123 4567 8910</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">choayoung91@naver.com</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">gmldnjs74@gmail.com</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">minh0380@naver.com</span></a></li>
	                <li><a href="#"><span class="icon icon-envelope"></span><span class="text">psuny1031@naver.com</span></a></li>
	              </ul>
	            </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">

            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
  Copyright &copy;<script>document.write(new Date().getFullYear());</script> <i class="icon-heart" aria-hidden="true"></i> by aboo for a better apartment.
  <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
          </div>
        </div>
      </div>
    </footer>
    
  

  <!-- loader -->
  <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


  <script src="../../../resources/js/generation/jquery.min.js"></script>
  <script src="../../../resources/js/generation/jquery-migrate-3.0.1.min.js"></script>
  <script src="../../../resources/js/generation/popper.min.js"></script>
  <script src="../../../resources/js/generation/bootstrap.min.js"></script>
  <script src="../../../resources/js/generation/jquery.easing.1.3.js"></script>
  <script src="../../../resources/js/generation/jquery.waypoints.min.js"></script>
  <script src="../../../resources/js/generation/jquery.stellar.min.js"></script>
  <script src="../../../resources/js/generation/owl.carousel.min.js"></script>
  <script src="../../../resources/js/generation/jquery.magnific-popup.min.js"></script>
  <script src="../../../resources/js/generation/aos.js"></script>
  <script src="../../../resources/js/generation/jquery.animateNumber.min.js"></script>
  <script src="../../../resources/js/generation/bootstrap-datepicker.js"></script>
  <script src="../../../resources/js/generation/jquery.timepicker.min.js"></script>
  <script src="../../../resources/js/generation/scrollax.min.js"></script>
  <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
  <script src="../../../resources/js/generation/google-map.js"></script>
  <script src="../../../resources/js/generation/main.js"></script>
  
  <script type="text/javascript">
  	/* ????????? ?????????! */
    
    /* ???????????? DB??? ?????? ???????????? */
  	let gnrlMgmtFee = $("#gnrlMgmtFee")[0].childNodes[0].nodeValue;
  	let cleanFee = $("#cleanFee")[0].childNodes[0].nodeValue;
  	let elvtrMnfee = $("#elvtrMnfee")[0].childNodes[0].nodeValue;
  	let genElctr = $("#genElctr")[0].childNodes[0].nodeValue;
  	let comonElctr = $("#comonElctr")[0].childNodes[0].nodeValue;
  	let genWater = $("#genWater")[0].childNodes[0].nodeValue;
  	let sewer = $("#sewer")[0].childNodes[0].nodeValue;
  	let expenses = $("#expenses")[0].childNodes[0].nodeValue;
  	let genReduction = $("#genReduction")[0].childNodes[0].nodeValue;

  	console.dir(gnrlMgmtFee)
    console.dir(cleanFee)
  	console.dir(elvtrMnfee)
  	console.dir(genElctr)
  	console.dir(comonElctr)
  	console.dir(genWater)
  	console.dir(sewer)
  	console.dir(expenses)
  	console.dir(genReduction)
	/* DB??? ????????? ?????? ????????????  */
  
	

  	
  	
	$("#check_module").click(function () {
        var IMP = window.IMP; // ????????????
        IMP.init('');
        // 'iamport' ?????? ???????????? "????????? ????????????"??? ??????
        // i'mport ????????? ????????? -> ????????? -> ?????????????????????
        IMP.request_pay({
            pg: 'html5_inicis', // version 1.1.0?????? ??????.
            /*
            'kakao':???????????????,
            html5_inicis':????????????(???????????????)
            'nice':???????????????
            'jtnet':????????????
            'uplus':LG????????????
            'danal':??????
            'payco':?????????
            'syrup':????????????
            'paypal':?????????
            */
            pay_method: 'card',
            /*
            'samsung':????????????,
            'card':????????????,
            'trans':?????????????????????,
            'vbank':????????????,
            'phone':?????????????????????
            */
            merchant_uid: 'merchant_' + new Date().getTime(),
            /*
            merchant_uid??? ??????
            https://docs.iamport.kr/implementation/payment

            */
            name: '?????????:??????????????????',
            //??????????????? ????????? ??????
            amount: 100,
            //??????
            buyer_email: 'aboo@siot.do',
            buyer_name: 'ABOO',
            buyer_tel: '010-1234-5678',
            buyer_addr: '????????? ?????????',
            buyer_postcode: '123-456',
            m_redirect_url: 'https://www.yourdomain.com/payments/complete'
            /*
		            ????????? ?????????,
		            ????????? ????????? ???????????? URL??? ??????
            (???????????????, ?????????, ????????? ????????? ????????????. PC??? ??????????????? callback????????? ????????? ?????????)
            */
        }, function (rsp) {
            console.log(rsp);
            if (rsp.success) {
           	    var msg = '????????? ?????????????????????.';
             /*
           	    msg += '??????ID : ' + rsp.imp_uid;
                msg += '?????? ??????ID : ' + rsp.merchant_uid;
                msg += '?????? ?????? : ' + rsp.paid_amount;
                msg += '?????? ???????????? : ' + rsp.apply_num;
                msg += '????????????' + rsp.pay_method;
                msg += '????????????' + rsp.paid_amount;  */
                
                payment(rsp.pay_method,rsp.paid_amount);


            } else {
                var msg = '????????? ?????????????????????.';
                msg += '???????????? : ' + rsp.error_msg;

            }
            alert(msg);
        });
    });
  	
  	/* ????????? ????????? ????????? ?????? */
	let mgmtfeeIdx = $("#mgmtfeeIdx")[0].defaultValue;
  	console.dir(mgmtfeeIdx)
  	
	let payment = (method,amount) => {
		
	    const url = '/mypage/mymgmtfee/payment';
	    let paramObj = new Object(); 
	    paramObj.mgmtfeeIdx = mgmtfeeIdx; /* ????????? ?????? */
	    paramObj.paymentMethod = method; /* ???????????? */
	    paramObj.paymentAmount = amount; /* ?????? (100???) */


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
	     		location.href = "/mypage/mymgmtfee";
	       }else{
	       }
	    }).catch(error => {
	       error.alertMessage();
	    });	
    
	}
  
  </script>
    
</body>
</html>