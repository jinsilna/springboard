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
       <form class="form-signin" action="/user/signProcess" method="post">
	        <h2 class="form-signin-heading">Please sign up..</h2>
		        <label for="inputEmail" class="sr-only">userId</label>
		       	 	<input type="text"name="userId" id = "userId"class="form-control" placeholder="userId" required autofocus>
		        <label for="inputPhone" class="sr-only">PhoneNumber</label>
		        	<input type="tel"  name="tel" id = "tel" class="form-control" placeholder="tel" required>	    
		        	<!-- <button onclick="/user/phoneletter" class="btn btn-lg btn-primary btn-block">핸드폰 문자 인증</button>	 -->
		       	 <label for="inputName" class="sr-only">userId</label>
		       	 	<input type="text"name="name" id = "name"class="form-control" placeholder="name" required autofocus>
		        <label for="inputPassword" class="sr-only">Password</label>
		        	<input type="password"  name="pass"  id = "password" class="form-control" placeholder="Password" required>	   
	        <button class="btn btn-lg btn-primary btn-block" type="submit">회원가입 </button>
      </form>
      
      <form class="form-signin" action="/user/loginView" method="post">
     		<button class="btn btn-lg btn-primary btn-block" type="submit"> 로그인  </button>
	  </form>
	  
	  <!--  핸드폰 인증 부분  -->
	   <form method="post" id="smsForm">
		    <ul>
		      <li> 보낼사람 : <input type="text" name="from"/></li>
		      <li> 내용 : <textarea name="text"></textarea></li>
		      <li><input type="button" onclick="sendSMS('sendSms')" value="전송하기" /></li>
			</ul>
	  </form>
	  <script>
	    function sendSMS(pageName){
	
	    	console.log("문자를 전송합니다.");
	    	$("#smsForm").attr("action", pageName + ".do");
	    	$("#smsForm").submit();
	    }
	  </script>
</head>
<br>
</body>
</body>
</html>















