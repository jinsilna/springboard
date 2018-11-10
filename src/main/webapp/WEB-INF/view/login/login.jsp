<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login.jsp</title>
<%@include file="../common/basiclb.jsp"%>
<script type="text/javascript">
</script>
<%-- signin.css가져오기 // 위에 처럼 include로 가져올 수 없다. --%>
<link 

href="/css/signin.css" rel="stylesheet">
</head>
<body>
       <form class="form-signin" action="/user/loginProcess" method="post">
	        <h2 class="form-signin-heading">Please sign in</h2>
		        <label for="inputEmail" class="sr-only">userId</label>
		       	 	<input type="text" value = "brown" name="userId" id = "userId"class="form-control" placeholder="userId" required autofocus>
		        <label for="inputPassword" class="sr-only">Password</label>
		        	<input type="password"  name="pass"  value = "brownpass" id = "password" class="form-control" placeholder="Password" required>	   
	        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
</body>
</html>














