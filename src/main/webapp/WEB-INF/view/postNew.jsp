<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>writenew.jsp</title>
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
<%-- basiclb --%>
<%@include file="./common/basiclb.jsp"%>
</head>
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
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
<body>
		<div class="freewritenew_wrap">
				<h2>새글작성 </h2>
				<hr>
			<form action="/post/postNewSave" method="post" id="frm" enctype="multipart/form-data">  
				<span>제목 : 　</span> 
					<input type="text" id="post_title" name="post_title" />
					<input type="hidden" name="post_no" value="${post_no}"/>
					<input type="hidden" name="userId" value="${userVo.userId}"/>
					<input type="hidden" name="post_board" value="${post_board}"/>
				<hr>
				<div id="fileDiv">
					<input type="file" class="fileInput" id="inputId" name="profilePic" />
				</div><br> 	
				<textarea name="post_context" id="post_context" rows="10" cols="100" style="width: 766px; height: 412px;"></textarea>
				<input type="submit" id="savebutton" class="btn btn-default pull-right" value="등록" />
				</form>
		</div>
</head>
</body>
</html>
