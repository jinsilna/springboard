<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!--  tiles-config.xml 에서 tiles-definitions 을 정해줬으니깐 여기서 간추릴?수 있다. -->
	<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
	
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>spring</title>
<%@include file="../common/basiclb.jsp"%>
</head>
<body>
<!-- tiles-config.xml에서 만들어줬던 header : name값으로 넣어준다. -->
	<tiles:insertAttribute name= "header" />
	<div class="container-fluid">
		<div class="row">
		<!-- tiles-config.xml에서 만들어줬던 left : name값으로 넣어준다. -->
			<tiles:insertAttribute name= "left" />
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<!-- tiles-config.xml에서 만들어줬던 content : name값으로 넣어준다. -->
				<tiles:insertAttribute name= "content" />	
			</div>
		</div>
	</div>
</body>
</html>
