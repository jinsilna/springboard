
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<head>
<%@include file="./common/basiclb.jsp"%>
<style type="text/css">
#context {
	margin-left: 200px;
	border: 1px solid #e3e5e8;
}
.commlist{
	text-align: center;
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
.commtable{
	text-align: center;
}
</style>

</head>

<body>
<div class="col-sm-8 blog-main">
<form  action="/commentary/commentaryInsert" method="post" class="form-horizontal" role="form">
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
		 <label class="control-label">${attach.attach_name}</label> 
			<a>${attachVo.attach_name}</a>
			<br>
		</c:forEach>
		<div class="col-sm-10">
		</div>
	</div>
	<hr>
	<div class="form-group">
		<label for="pass" class="col-sm-2 control-label">댓글</label>
		<hr>
		<input type="text" id="comment" name="comment" value="">
		<input type="hidden" name="post_no" value="${postVo.post_no}">
		<%-- <input type="hidden" name="comm_context" value="${comm_context}"> --%>
		<input type="hidden" name="comm_user" value="${userVo.userId}">
		<hr>
		<input type="submit" id="commentary" value ="등록" class="btn btn-default"/>
		<div class="col-sm-10">
			<label class="control-label"></label>
		</div>
	</div>
</form>

	<div class="form-group">
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				 <hr>
				 
				<c:if test="${userVo.userId==postVo.userId}">
				<form class="form" action="/post/postUpdateView" method="get">
					<input type="hidden" name="post_no" value="${postVo.post_no}">
					<input type="hidden" name="post_board" value="${postVo.post_board}">
					<button type="submit" class="btn btn-default">수정</button>
				</form>
				
				<form class="form" action="/post/postDelete" method="get">
					<input type="hidden" name="post_no" value="${postVo.post_no}">
					<input type="hidden" name="post_board" value="${postVo.post_board}">
					<button type="submit" class="btn btn-default">삭제</button>
				</form>
				</c:if>

				<form  class="form" action="/post/postCommentView" method="get">
					<input type="hidden" name="post_board" value="${postVo.post_board}">
					<input type="hidden" name="post_pid" value="${postVo.post_no}">
					<input type="hidden" name="post_no" value="${postVo.post_no}">
					<button type="submit" class="btn btn-default">답글</button>
				</form>
			</div>
	<table class = "commtable" >
		<c:forEach items="${commList}" var="comm">	
			<c:if test="${comm.comm_rmv =='N'}">
					<tr>
					<td class="control-label" id="commens"> * 댓글 내용 : ${comm.comm_context} 
							<form action="/commentary/commentaryDelete" method="post">	
							<input type="hidden" name="comm_post" value="${comm.comm_post}"> 
							<input type="hidden" name="post_no" value="${postVo.post_no}"> 
							<input type="hidden" name="comm_user" value="${comm.comm_user}">
							<input type="hidden" name="comm_no" value="${comm.comm_no}">
							<label class="control-label" id="commens"> * 작성자 : ${comm.comm_user}</label>　
							<%-- <c:if test="${userVo.userId==postVo.userId}"> --%>
								<input type="submit" id="commdelete" value ="댓글삭제" class="btn btn-default"/><br>
							<%-- </c:if> --%>
							<hr>
							</form>
						</td> 
					</tr>
			</c:if>	
			<c:if test="${comm.comm_rmv =='Y'}">	
				<tr>
					<td class="control-label" id="commens"> 삭제된 댓글입니다..</td> 
				</tr>
			</c:if>
		</c:forEach> 
	</table>	
</div>
</div>
</div>
</body>
</html>
