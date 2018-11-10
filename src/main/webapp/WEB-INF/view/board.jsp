<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%-- header --%>
	<%@include file="./common/header.jsp"%>
			<%-- left --%>
			<%@include file="./common/left.jsp"%>


<script type="text/javascript">
$(document).ready(function() {  
	$(".labelhd").hide();
			$(".boardClick").on("click",function() {
				
				var board = this.parentElement.firstElementChild;
				var board_no = board.nextElementSibling.innerText;
				var board_name = board.nextElementSibling.nextElementSibling.value;
				var board_use = board.nextElementSibling.nextElementSibling.nextElementSibling.value;
				
				$("#board_no").val(board_no);
				$("#board_name").val(board_name);
				$("#board_use").val(board_use);
				$("#frm").submit();
			
			});
			
		});
</script>

	<form id="frm" action="/board/CreateUpdate" method="post">
		<input type="hidden" id="board_no" name="board_no"/> 
		 <input type="hidden" id="board_use" name="board_use"/> 
		 <input type="hidden" id="board_name" name="board_name"/> 
		 <input type="hidden" name="board_user" value="${userId}"/>
	</form>
	<div class="container-flui2d">
		<div class="row">


			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<div class="blog-post">
					<h2 class="sub-header">게시판 생성 및 수정</h2>
					<ul>
						<li class="board">
						<label>추가</label> 
						<label class = "labelhd"></label>
						<input id ="addboardName" type="text" placeholder="ex)입력하세요.." /> 
							<select class="addPan">
								<option value="Y">사용</option>
								<option value="N">미사용</option>
						</select> 
						<button type="button" class="boardClick">등록</button>
						</li>
					</ul>
					<hr>

					<!--boardList를 사용한 이유는 전체 board정보가 나와야 미사용 사용여부를 선택할 수 있으니깐  -->
					<c:forEach items="${boardList}" var="board">
						<ul>
							<li class="board">
							<label> 수정 </label> 
							<label class = "labelhd">${board.board_no}</label>
							<input type="text" value="${board.board_name}"/> 
								<select>
									<option value="Y"
										<c:if test="${board.board_use=='Y'}">selected</c:if>>사용
									</option>
									<option value="N"
										<c:if test="${board.board_use=='N'}">selected</c:if>>미사용
									</option>
								</select>
								<button type="button" class="boardClick">수정</button>
								</li>
							</ul>
					</c:forEach>

					<hr>
				</div>
			</div>
		</div>
	</div>
</body>
</html>