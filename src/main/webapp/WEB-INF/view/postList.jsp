<%@page import="kr.or.ddit.post.model.PostVo"%>
<%@page import="kr.or.ddit.board.model.BoardVo"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
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
<title>postList.jsp</title>
<%-- basiclb --%>
<%@include file="./common/basiclb.jsp"%>
<!-- cursor pointer : 손가락 모양으로바뀜 -->
<style type="text/css">
	.userClick{
		cursor : pointer;
	}
	.search{
		text-align: center;
		
	}
	.delete{
		pointer-events: none;
	}
</style>
 <script type="text/javascript">
	$(document).ready(function(){

		console.log("document.ready");
		
		// tr에 대해 select (class="userClick")
		$("#postList").on("click",".userClick",function(){
			console.log("userClick");
			var post_no =$(this).children()[1].innerHTML;
			
			$("#post_no").val(post_no);
			  $("#frm").submit();
		});
		getPostList(1,${board_no});
	});
	
	// ***************** Ajax ******************************
	// List
	function getPostList(page, board_no) {
		var pageSize = 10;
		var searchText = $("#searchText").val();
		console.log("page" + page);
		
		$.ajax({
			type : "GET",
			url : "/post/postPageListAjax",
			data : "page="+page+"&pageSize="+pageSize+"&board_no="+board_no+"&searchText="+searchText,
			error : function (xhr){
				console.log(xhr);
			},
			success : function (data) {
				console.log(data);
				
				var html = "";
				$.each(data.postList, function (idx,post) {
					console.log(post);
					html += "<tr class='userClick'>";
					html += "<td>"+post.rnum+"</td>";
					html += "<td>"+post.post_no+"</td>";
						if(post.post_rmv=='Y'){
							html += "<td class ='delete'>"+"삭제된 글입니다."+"</td>"; 
 						}else{						 
							html += "<td>"+post.post+"</td>";						
						 }
					html += "<td>"+post.userId+"</td>";
					html += "<td>"+post.formattedDate+"</td>";
					html += "</tr>";
				});
				
			$("#postList").html("");
			$("#postList").html(html);
			
			// page //
			var paging ="";
			paging +="<li><a href='javascript:getPostList("+ i +","+${board_no}+");'aria-label='Previous'><span aria-hidden='true'>&laquo;</span>";
		    for(var i= 1; i<=data.pageCnt; i++) {
			paging += "<li><a href='javascript:getPostList("+ i +","+${board_no}+");'>"+ i+ "</a></li>";
			}
			paging +="<li><a href='javascript:getPostList("+ data.pageCnt +" ,"+${board_no}+");'aria-label='Next'><span aria-hidden='true'>&raquo;</span>";
			$(".pagination").html(paging);
 			
			}
		});
	} 
	
</script> 
</head>

<form  id = "frm" action="/post/postDetail" method="get">
	<input type = "hidden" id = "post_no" name = "post_no" value="${post_no}"/>
	<input type = "hidden" id = "board_name" name = "board_name" value="${board_name}"/>
</form>

<body>
	<div class="col-sm-8 blog-main">
		<h2 class="sub-header">${board_name}</h2>
		<div class="table-responsive">
			<table class="table table-striped table-hover" >
				<thead>
					<tr>
						<th>No</th>
						<th>게시글 번호</th>
						<th>제목</th>
						<th>작성자 Id</th>	
						<th>작성일시</th>
					</tr>
				</thead>
				<tbody id="postList">
			 <%-- 	<c:forEach items="${postList}" var = "vo" > 
			 	
				 <tr class ="userClick">
					<td>${vo.rnum}</td>
					<td>${vo.post_no}</td>
					<td>${vo.post_title}</td>
					<td>${vo.userId}</td>
					<td>${vo.post_date}</td>  
				</tr>
				 </c:forEach>  --%>
				</tbody>
			</table>
		</div>

	<%-- 테스트 해보는거  	
	<c:forEach items="postList1" var="p">
		</c:forEach> --%>
		<form  id = "frm" action="/post/postNew" method="get">
			<input type="hidden"  id="board_no" name="board_no" value="${board_no}">
	<input type = "hidden" id = "post_no" name = "post_no" value="${post_no}"/>
			<input type="submit" class="btn btn-default pull-right" value="새글작성">
		</form>

		<div class="text-center">
			<ul class="pagination">
			<%--  <li>
				     	 <a href="/post/postBoardList?page=1&pageSize=10" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      	</a>
		    		</li>
				      <c:forEach begin="1" end="${pageCnt}" var="p">
					      <li>
					      	<a href="/post/postBoardList?page=${p}&pageSize=10">${p}</a>
					      </li>
				      </c:forEach>
				     	  <li>
				     		 <a href="/post/postBoardList?page=${pageCnt}&pageSize=10" aria-label="Next"> 
				      			<span aria-hidden="true">&raquo;</span>
				      		</a>
  				 	</li>  --%>
			 </ul>
	    </div>
		<!--  검색 기능  -->
		 <form class = "search" action="/post/postSearch" method="POST">
			<div>
				<label> 제목 </label>　　
				<input type="text" id="searchText" name ="searchText" value="${searchText}">
				<input type = "hidden" id = "post_no" name = "post_no" value="${postVo.post_no}"/>
				<input type = "hidden" id = "board_no" name = "board_no" value="${boardVo.board_no}"/>
				<input type = "hidden" id = "board_name" name = "board_name" value="${board_name}"/>
				<button type="submit" class="btn btn-default">검색하기</button>  
			</div>
		 </form> 
	</div>
</body>
</html>
