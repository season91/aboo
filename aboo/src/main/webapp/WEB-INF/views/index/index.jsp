<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/generationhead.jsp" %>
<!DOCTYPE html>
<html>
<head>
   <style>
     *,
*::before,
*::after {
  box-sizing: border-box;
}

/* 자동차 div 배경이미지, 건물  */
.cloudcontainer {
  z-index: 1;
  position: relative;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  min-height: 35rem;
  background: url(../../../resources//abooimg/인덱스_건물.png) no-repeat center
    center;
  background-blend-mode: soft-light;
  background-size: contain;
  background-position: center center;
  padding: 2rem;

  transform: scale(0) translateY(5vh);
  will-change: transform;

  animation-name: fly-right-one;
  animation-timing-function: linear;
  animation-iteration-count: infinite;

  animation-duration: 15s;
  animation-delay: 1s;
}


/* 띄울 div들의 배경 */

.cloud-container--two {
  background-image: url(../../../resources/abooimg/구름_오.png);
  background-size: 60px;
  width: 60px;
  height: 50px;
  will-change: background-position;

  animation-name: fly-cycle;
  animation-timing-function: steps(10);
  animation-iteration-count: infinite;

  animation-delay: 1s;
}

/* 띄운 div의 효과 */

.cloud-container--two{
  position: absolute;
  top: 20%;
  left: -10%;
  transform: scale(0.8) translateX(50vw) translateY(-10vh); 
  will-change: transform;

  animation-name: fly-right-two;
  animation-timing-function: linear;
  animation-iteration-count: infinite;

  animation-duration: 16s;
  animation-delay: 1s;
}

.rcloud--two {
  background-image: url(../../../resources/abooimg/구름_오.png);
  background-size: auto 100%;
  width: 500px;
  height: 500px;
  will-change: background-position;

  animation-name: fly-cycle;
  animation-timing-function: steps(10);
  animation-iteration-count: infinite;

  animation-delay: 1s;

}

.rcloud-container--two {
   position: absolute;
  top: 20%;
  left: -10%;
  transform: scale(0.3) translateY(-135vh) translateX(115vw);
  will-change: transform;

  animation-name: fly-right-three;
  animation-timing-function: linear;
  animation-iteration-count: infinite;

  animation-duration: 16s;
  animation-delay: 1s;

}



.cloud--ay {
  background: url(../../../resources/abooimg/구름_왼.png) no-repeat center
    center;
  background-size: contain;
  width: 350px;
  height: 250px;
  will-change: background-position;

  animation-name: fly-cycle;
  animation-timing-function: steps(10);
  animation-iteration-count: infinite;

  animation-delay: 1s;
}

.cloud-container--ay {
  position: absolute;
  top: 20%;
  left: -10%;
  transform: translateY(-10vh) translateX(-3vw) scale(0.5);
  will-change: transform;

  animation-name: fly-right-ay;
  animation-timing-function: linear;
  animation-iteration-count: infinite;

  animation-duration: 16s;
  animation-delay: 1s;
}



@keyframes fly-cycle {
  100% {
    background-position: -900px 0;
  }
}




/* 띄운 div의 움직일 경로  */
@keyframes fly-right-one {
  0% {
    transform: translateY(7vh);
  }

  30% {
    transform: translateY(5vh);
  }

  60% {
    transform: translateY(7vh);
  }

  100% {
    transform: translateY(5vh);
  }
}

@keyframes fly-right-two {
  0% {
   transform: scale(0.8) translateX(50vw) translateY(-10vh); 
  }

 10% {
     transform: scale(0.8) translateX(55vw) translateY(-8vh); 
  }

  20% {
      transform: scale(0.8) translateX(50vw) translateY(-10vh); 
  }

  30% {
     transform: scale(0.8) translateX(55vw) translateY(-8vh); 
  }

  40% {
    transform: scale(0.8) translateX(50vw) translateY(-10vh); 
  }

  50% {
     transform: scale(0.8) translateX(55vw) translateY(-8vh); 
  }

  60% {
     transform: scale(0.8) translateX(50vw) translateY(-10vh); 
  }
  
  70% {
     transform: scale(0.8) translateX(55vw) translateY(-8vh); 
  }
  
  80% {
     transform: scale(0.8) translateX(50vw) translateY(-10vh); 
  }
  
  90% {
      transform: scale(0.8) translateX(55vw) translateY(-8vh);  
  }

  100% {
      transform: scale(0.8) translateX(50vw) translateY(-10vh); 
  }
}

@keyframes fly-right-three {
  0% {
   transform: scale(0.3) translateY(-135vh) translateX(120vw);
  }


  20% {
    transform: scale(0.3) translateY(-135vh) translateX(110vw);
  }
  
  40% {
    transform: scale(0.3) translateY(-135vh) translateX(120vw);
  }

  60% {
    transform: scale(0.3) translateY(-135vh) translateX(110vw);
  }

  80% {
     transform: scale(0.3) translateY(-135vh) translateX(120vw);
  }

  100% {
   transform: scale(0.3) translateY(-135vh) translateX(110vw);
  }

  
}

@keyframes fly-right-ay {
  0% {
    transform: translateY(-10vh) translateX(-3vw) scale(0.5);
  }

  10% {
    transform: translateY(-10vh) translateX(-1vw) scale(0.6);
  }

  20% {
    transform: translateY(-10vh) translateX(-3vw) scale(0.5);
  }

  30% {
    transform: translateY(-10vh) translateX(-1vw) scale(0.6);
  }

  40% {
    transform: translateY(-10vh) translateX(-3vw) scale(0.5);
  }

  50% {
    transform: translateY(-10vh) translateX(-1vw) scale(0.6);
  }

  60% {
    transform: translateY(-10vh) translateX(-3vw) scale(0.5);
  }

  70% {
    transform: translateY(-10vh) translateX(-3vw) scale(0.6);
  }

  80% {
    transform: translateY(-10vh) translateX(-1vw) scale(0.5);
  }

  90% {
    transform: translateY(-10vh) translateX(-3vw) scale(0.6);
  }

  100% {
    transform: translateY(-10vh) translateX(-1vw) scale(0.5);
  }
}
   </style>
</head>
<body>
     <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
       <div class="container">
         <a class="navbar-brand" href="/index">ABOO</a>
         <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
           <span class="oi oi-menu"></span> Menu
         </button>

         <div class="collapse navbar-collapse" id="ftco-nav">
           <ul class="navbar-nav ml-auto">
             <li class="nav-item active"><a href="/index" class="nav-link">Home</a></li>
             <li class="nav-item"><a href="/about" class="nav-link">About</a></li>
             <li class="nav-item"><a href="/myapt/schedule" class="nav-link">MyApt</a></li>
             <li class="nav-item"><a class="nav-link" href="/board/info/listinfo">Board</a></li>
             <li class="nav-item"><a href="/mypage/modifyinfo" class="nav-link">MyPage</a></li>
             <c:choose>
                <c:when test="${sessionScope.generation == null and sessionScope.admin == null}">
                   <li class="nav-item cta"><a href="/login" class="nav-link"><span>Login</span></a></li>             
                </c:when>
                <c:when test="${sessionScope.generation != null}">
                   <li class="nav-item cta"><a href="/logout" class="nav-link"><span>Logout</span></a></li>             
                </c:when>
                  <c:when test="${sessionScope.admin != null}">
                    <li class="nav-item cta"><a href="/admin/logout" class="nav-link"><span>Logout</span></a></li>   
                  </c:when>                          
             </c:choose>
           </ul>
         </div>
       </div>
     </nav>
    <!-- END nav -->
    
  <section class="home-slider owl-carousel">
      <div class="slider-item" style="background-image: url(../../../resources/images/bg_1.jpg);">
        <div class="overlay"></div>
        <div class="container-fluid">
          <div class="row slider-text align-items-center" data-scrollax-parent="true">

            <div class="col-md-5 wrap col-sm-12 ftco-animate" data-scrollax=" properties: { translateY: '70%' }">
              <h1 class="mb-4 mt-5" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">Everything you get what you need to Host your website</h1>
              <p class="mb-4 mb-md-5 sub-p" data-scrollax="properties: { translateY: '30%', opacity: 1.6 }">Up to 90% Discount with Free Domain Name Registration</p>
              <p><a href="#" class="btn btn-primary p-3 px-xl-5 py-xl-3">Get started</a> <a href="#" class="btn btn-primary btn-primary-2 p-3 px-xl-5 py-xl-3">Read more</a></p>
            </div>
            <!-- 자동차 구현 부분.  -->
            <div class="col-md-7 ftco-animate">
              
              <div class="cloudcontainer"> 

                <div class="bird-container cloud-container--two">
                  <div class="bird car--two"></div>
                </div>
                
                
                
                <div class="bird-container cloud-container--ay">
                  <div class="bird cloud--ay"></div>
                </div>
                
                <div class="bird-container rcloud-container--two">
                  <div class="bird rcloud--two"></div>
                </div>
                
              </div>
            </div>

          </div>
        </div>
      </div>

    </section>
    
    <section class="ftco-section services-section bg-light">
      <div class="container">
         <div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2 class="mb-4">The WebHost Guarantee</h2>
            <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
          </div>
        </div>
        <div class="row">
          <div class="col-md-4 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block text-center">
              <div class="d-flex justify-content-center">
                 <div class="icon d-flex align-items-center justify-content-center">
                    <span class="flaticon-guarantee"></span>
                 </div>
              </div>
              <div class="media-body p-2 mt-3">
                <h3 class="heading">100% Uptime Guarantee</h3>
                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
              </div>
            </div>      
          </div>
          <div class="col-md-4 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block text-center">
              <div class="d-flex justify-content-center">
                 <div class="icon d-flex align-items-center justify-content-center">
                    <span class="flaticon-shield"></span>
                 </div>
              </div>
              <div class="media-body p-2 mt-3">
                <h3 class="heading">Safe and Secured</h3>
                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
              </div>
            </div>    
          </div>
          <div class="col-md-4 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block text-center">
              <div class="d-flex justify-content-center">
                 <div class="icon d-flex align-items-center justify-content-center">
                    <span class="flaticon-support"></span>
                 </div>
              </div>
              <div class="media-body p-2 mt-3">
                <h3 class="heading">Our Dedicated Support</h3>
                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
              </div>
            </div>      
          </div>
               <div class="col-md-4 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block text-center">
              <div class="d-flex justify-content-center">
                 <div class="icon d-flex align-items-center justify-content-center">
                    <span class="flaticon-cloud-computing"></span>
                 </div>
              </div>
              <div class="media-body p-2 mt-3">
                <h3 class="heading">Domain Transfer</h3>
                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
              </div>
            </div>      
          </div>
          <div class="col-md-4 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block text-center">
              <div class="d-flex justify-content-center">
                 <div class="icon d-flex align-items-center justify-content-center">
                    <span class="flaticon-settings"></span>
                 </div>
              </div>
              <div class="media-body p-2 mt-3">
                <h3 class="heading">DNS Control</h3>
                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
              </div>
            </div>    
          </div>
          <div class="col-md-4 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block text-center">
              <div class="d-flex justify-content-center">
                 <div class="icon d-flex align-items-center justify-content-center">
                    <span class="flaticon-loading"></span>
                 </div>
              </div>
              <div class="media-body p-2 mt-3">
                <h3 class="heading">Fast Loaded</h3>
                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
              </div>
            </div>      
          </div>
        </div>
      </div>
    </section>

    <section class="ftco-section ftco-partner">
       <div class="container">
          <div class="row justify-content-center mb-5 pb-3">
          <div class="col-md-7 text-center heading-section heading-section-white ftco-animate">
            <h2 class="mb-4">Our Clients</h2>
            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in</p>
          </div>
        </div>
          <div class="row">
             <div class="col-sm ftco-animate">
                <a href="#" class="partner"><img src="../../../resources/images/partner-1.png" class="img-fluid" alt="Colorlib Template"></a>
             </div>
             <div class="col-sm ftco-animate">
                <a href="#" class="partner"><img src="../../../resources/images/partner-2.png" class="img-fluid" alt="Colorlib Template"></a>
             </div>
             <div class="col-sm ftco-animate">
                <a href="#" class="partner"><img src="../../../resources/images/partner-3.png" class="img-fluid" alt="Colorlib Template"></a>
             </div>
             <div class="col-sm ftco-animate">
                <a href="#" class="partner"><img src="../../../resources/images/partner-4.png" class="img-fluid" alt="Colorlib Template"></a>
             </div>
             <div class="col-sm ftco-animate">
                <a href="#" class="partner"><img src="../../../resources/images/partner-5.png" class="img-fluid" alt="Colorlib Template"></a>
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
                 <p>아파트를 부탁해!<br>
                 아파트 주변 공공기관부터 투표, 관리비납부, 차량등록, 층간소음 문의 등 관리하기 편한 기능을 제공합니다.
                 </p>
                 <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                   <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                   <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                   <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
                 </ul>
               </div>
             </div>
             <div class="col-md">
               <div class="ftco-footer-widget mb-4 ml-md-5">
                 <h2 class="ftco-heading-2">Unseful Links</h2>
                 <ul class="list-unstyled">
                   <li><a href="/myapt/parking" class="py-2 d-block">Parking</a></li>
                   <li><a href="/board/info/listinfo" class="py-2 d-block">Info Board</a></li>
                   <li><a href="/board/interior/intlist" class="py-2 d-block">Interior Board</a></li>
                   <li><a href="/board/used/usedlist" class="py-2 d-block">Used Board</a></li>
                   <li><a href="/myapt/schedule" class="py-2 d-block">Apt Schedule</a></li>
                   <li><a href="/myapt/institutions" class="py-2 d-block">Institutions</a></li>
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
                   <li><a href="/baord/info/listinfo" class="py-2 d-block">Board</a></li>
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

    
</body>
</html>