<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<!DOCTYPE html>
<html>
<style>
    * {
        padding: 0;
        margin: 0;
        -webkit-text-size-adjust: none;
    }

    body {
        width: 100%;
        height: 100%;
        background-color: #fafafa;
    }

    ​ @media screen and (min-width: 281px)and (max-width: 540px) {
        * {
            -webkit-text-size-adjust: none !important;
        }

        ​ .HeaderMiddleText {
            font-size: 10px !important;
        }

        ​ .MiddleTitleText {
            font-size: 12px !important;
        }

        ​ .MiddleSubText {
            font-size: 10px !important;
        }

        ​ .footerRightText {
            font-size: 10px !important;
        }
    }

    ​ @media screen and (max-width: 280px) {
        * {
            -webkit-text-size-adjust: none !important;
        }

        ​ .HeaderMiddleText {
            font-size: 8px !important;
        }

        ​ .MiddleTitleText {
            font-size: 10px !important;
        }

        ​ .MiddleSubText {
            font-size: 7px !important;
            line-height: 150%;
        }

        ​ .footerRightText {
            font-size: 10px !important;
        }

    }
</style>

<head>
<meta charset="UTF-8">
<title>아이디 인증 메일</title>
</head>


<body>

    <!--머리글-->
    <div class="templateContainer" style="max-width: 600px; margin: 0 auto;">
        <div class="content" style="background-color:white ; width: 100%; height: auto;">
            <div class="contentHeader" style="padding: 9px 0px 0px; margin-left: 10px;">
            	<div style="display: flex;">
	                <div class="HeaderTop">
	                    <img  style="height: 7vh; width: 5vw;" src="https://postfiles.pstatic.net/MjAyMTA0MDJfMjAz/MDAxNjE3MzM0MTY0NDI5.HUB1sHczF56YK32-pnbMlXXRAS-tiVgUtoTu5477HSog.VJ0eYygCvEDLXPxVdHBgoGDIpDWA3rC--ipMKwXXykog.PNG.psuny1031/logo_b.png?type=w773" alt="">
	                </div>
	
                    <div class="HeaderMiddle" style="width: 100%; display: flex; flex-direction: column; justify-content: flex-end;">
                        <div class="HeaderMiddleText" style="font-size: 10px; color: #ACABAB; padding: 0px 0px 5px;">
                            ABOO를 이용해 주셔서 감사합니다. 더욱 편리한 서비스를 제공하기 위해 항상 최선을 다하겠습니다.</div>
                    </div>
				</div>
                <div class="HeaderBottom" style="padding: 2px 18px;">
                    <div style="border-top: 2px solid #eaeaea; width: 100%; min-width: 100%;"></div>
                </div>
            </div>

            ​

            <!--중간글-->

            <div class=" contentMiddle">
                <div class="MiddleTitle" style="padding: 9px 18px 9px;">
                    <h1 style="color: #202020; font-family: Helvetica;  font-size: 20px; text-align: left;     font-weight: bold;line-height: 125%;"
                        class="MiddleTitleText">
                        인증번호가 도착했습니다.</h1>
                    <br>
                    <br>

                </div>

                <div class="MiddleSub" style="padding: 9px 18px 9px;">
                    <p style='font-size: 14px; font-family: "Open Sans", "Helvetica Neue", Helvetica, Arial, sans-serif; color: #202020; line-height: 150%;'
                        class="MiddleSubText">
                        [ ${authPath} ]인증번호를 정확하게 입력해주세요.</p>
                        <br>
                        <br>

                </div>

                <div class="Button" style=" padding: 18px 18px 18px 18px;">
                    <a href="http://localhost:9393/index">
                        <button style="background-color: #2CCBC3; width:100%; min-width:100%;border: 1px none; border-radius: 4px;  height:8vh; font-size: 16px;cursor: pointer;">
                            	<p style="text-decoration: none;color: #FFFFFF; font-weight: bold; text-align: center; ">ABOO 홈페이지</p>
                        </button>
                    </a>
                </div>
            </div>

        </div>

        <!--바닥글-->
        <div class="footer" style="display: flex; padding: 9px 0; width: 100%; min-width: 100%; ">
            <div class="footerRight" style=" text-align: right; padding:9px 18px ; max-width:600px; width: 100%;">
                <p style="color: #656565; font-size: 12px;
                line-height: 150%" class="footerRightText">
                    아파트 비대면 관리 사이트
                    <br>
                    <strong>(주) ABOO</strong>
                </p>
            </div>
        </div>
    </div>
</body>



</html>