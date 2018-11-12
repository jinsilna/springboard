
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<head>
<%@include file="./common/basiclb.jsp"%>
<style type="text/css">
#context {
	margin-left: 200px;
	border: 1px solid #e3e5e8;
}

#comment {
	width: 500px;
	height: 34px;
	margin-left: 30px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
}

#commen{
	margin-left: 200px;
}

#commens{
	margin-left: 20px;
}

.form{
	float: left;
	margin-right: 10px;
}
</style>

</head>

<body>
	<%@ include file="./common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<%-- left --%>
			<%@ include file="./common/left.jsp"%>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<form class="form-horizontal" role="form" action="/commentServlet" method="post">
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">제목</label>
						<label for="userNm" id="title" class="col-sm-2 control-label">${postVo.post_title}</label>
						<div class="col-sm-10">
						</div>
					</div>
					<hr>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">내용</label> <br>
						<br>
						<div class="col-sm-10">
							<label class="control-label" id="context">${postVo.post_context}</label>
						</div>
					</div>
					<hr>
					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">첨부파일</label>
						<c:forEach items="${attachList}" var="attach">
							<%-- <label class="control-label">${attach.attach_name}</label> --%> 
							<a href="${attach.attach_name}">${attach.attach_name}</a>
							<br>
						</c:forEach>
						<div class="col-sm-10">
						</div>
					</div>
					<hr>
					<div class="form-group">.
						<label for="pass" class="col-sm-2 control-label">댓글</label> 
						
						<input type="text" id="comment" name="comment" value="">
						<input type="hidden" name="postNo" value="${postVo.post_no}">
		
						<button type="submit" id="commentary"class="btn btn-default">등록</button>
						<div class="col-sm-10">
							<label class="control-label"></label>
						</div>
					</div>
					<%-- 
						 상세 구현 계획 : 댓글이 달린만큼 라벨로 댓글 띄워주기
					--%>
					<c:forEach items="${commentList}" var="comm">
					
					<div class="form-group">
						<!-- <label for="pass" class="col-sm-2 control-label">댓글</label> -->
						<div class="col-sm-10">
							<label class="control-label" id="commen">${comm.comm_user} : </label>
							<label class="control-label" id="commens">${comm.comm_context}</label>
						</div>
					</div>
					</c:forEach> 
					<br>

				</form>
				
					<div class="form-group">
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								 
								<c:if test="${user.userId==postVo.post_user}">
								<form class="form" action="/post/postUpdateView" method="get">
									<input type="hidden" name="postNo" value="${postVo.post_no}">
									<input type="hidden" name="postBoard" value="${postVo.post_board}">
									<button type="submit" class="btn btn-default">수정</button>
								</form>
								<form class="form" action="/post/postDelete" method="get">
									<input type="hidden" name="postNum" value="${postVo.post_no}">
									<input type="hidden" name="postBoard" value="${postVo.post_board}">
									<button type="submit" class="btn btn-default">삭제</button>
								</form>
								</c:if>

								<form  class="form" action="/postCommentServlet" method="get">
									<input type="hidden" name="postNumber" value="${postVo.post_no}">
									<input type="hidden" name="postBoard" value="${postVo.post_board}">
									<button type="submit" class="btn btn-default">답글</button>
								</form>
							</div>
						</div>
						<div class="col-sm-10"></div>
					</div>
			</div>
		</div>
	</div>

</body>
</html>
