<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login.jsp</title>
<%@include file="../common/basiclb.jsp"%>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript"></script>
<style type="text/css">
  html, div, body,h3{
    margin: 0;
    padding: 0;
  }
  h3{
    display: inline-block;
    padding: 0.6em;
  }
  </style>
<%-- signin.css가져오기 // 위에 처럼 include로 가져올 수 없다. --%>
<link href="/css/signin.css" rel="stylesheet">
</head>
<body>
       <form class="form-signin" action="/user/loginProcess" method="post">
	        <h2 class="form-signin-heading">Please sign in</h2>
		        <label for="inputEmail" class="sr-only">userId</label>
		       	 	<input type="text"  name="userId" id = "userId"class="form-control" placeholder="userId" required autofocus>
		        <label for="inputPassword" class="sr-only">Password</label>
		        	<input type="password"  name="pass"  id = "password" class="form-control" placeholder="Password" required>	   
	        <button class="btn btn-lg btn-primary btn-block" type="submit"> 로그인 </button>
      </form>
      
      <form class="form-signin" action="/user/signView" method="post">
     		<button class="btn btn-lg btn-primary btn-block" type="submit"> 회원가입  </button>
	   </form>
      
	  <!-- 네이버아이디로로그인 버튼 노출 영역 -->
	  <div id="naver_id_login" align="center"></div>
		  <!-- //네이버아이디로로그인 버튼 노출 영역 -->
</head>
<br>
<!-- 네이버 로그인 화면으로 이동 시키는 URL -->
<!-- 네이버 로그인 화면에서 ID, PW를 올바르게 입력하면 callback 메소드 실행 요청 -->
<div id="naver_id_login" style="text-align:center"><a href="${url}">
<img width="223" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/></a></div>
<br>
</body>
</body>
</html>















