<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
<title>Jsp</title>
<%-- basiclb --%>
<%@include file="./common/basiclb.jsp"%>
</head>
<body>
<%-- header --%>
	<%@include file="./common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">

			<%-- left --%>
			<%@include file="./common/left.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


				<div class="blog-header">
					<h1 class="blog-title">${userId}님 안녕하세요~~ </h1>
					<p class="lead blog-description"> 게시판 만들기 프로젝트 </p>
				</div>

				<div class="row">

					<div class="col-sm-8 blog-main">

						<div class="blog-post">
							<h2 class="blog-post-title">BOARD</h2>
							<p class="blog-post-meta">2018.10.18, room 203 NATRUE</p>

							<p> 자유롭게 꾸미기 </p>
							</ul>
						</div>
					</div>
					<!-- /.blog-main -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>
