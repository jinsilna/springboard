
<%@page import="kr.or.ddit.board.model.BoardVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<%@include file="./common/basiclb.jsp"%>
<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico" />

<!-- jQuery -->
<!-- <script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>-->

<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>

<style type="text/css">
#savebutton {
	margin-right: 350px;
}

#post_title {
	width: 700px;
	height: 34px;
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
</style>

<script src="/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
	var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

	$(document).ready(function() {
		// Editor Setting
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors, // 전역변수 명과 동일해야 함.
			elPlaceHolder : "post_context", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
			sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
			fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
			htParams : {
				// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
				bUseVerticalResizer : true,
				// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
				bUseModeChanger : true,
			}
		});

		// 전송버튼 클릭이벤트
		$("#savebutton").click(function(){
			if(confirm("저장하시겠습니까?")) {
				// id가 smarteditor인 textarea에 에디터에서 대입
				oEditors.getById["post_context"].exec("UPDATE_CONTENTS_FIELD", []);

				// 이부분에 에디터 validation 검증
				if(validation()) {
					$("#frm").submit();
				}
			}
		})
		
		var count =0;
		$("#fileDiv").on("change",".fileInput", function(){
			count++;
			if(count > 4){
				return;
			}
			$("<input type=\"file\" class=\"fileInput\" name=\"postFile\" id=inputId"+count+" />").insertAfter("#inputId"+(count-1));
		});
		
	});

	// 필수값 Check
	function validation(){
		var contents = $.trim(oEditors[0].getContents());
		if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
			alert("내용을 입력하세요.");
			oEditors.getById['post_context'].exec('FOCUS');
			return false;
		}
		return true;
	}
	
</script>

</head>
<body>
	<%-- header --%>
	<%@include file="./common/header.jsp"%>
	<div class="container-fluid">
		<div class="row">


			<%-- left --%>
			<%@include file="./common/left.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			 <div class="freewritenew_wrap">
				<h2> 수정 </h2>
				<hr>
			<form action="/post/postDetail" method="post" id="frm"> <!-- enctype="multipart/form-data" -->
				<span>제목 : 　</span> 
					<input type="text" id="post_title" name="post_title" />
				<hr>
				<div id="fileDiv">
					<input type="file" class="fileInput" id="inputId" name="postFile" />
				</div><br> 	
				<textarea name="post_context" id="post_context" rows="10" cols="100" style="width: 766px; height: 412px;"></textarea>
				<hr>
				<input type="submit" id="savebutton" class="btn btn-default pull-right" value="수정 저장" />
				</form>
				</div>
			</div>
		</div>
	</div>
</head>
</body>
</html>
