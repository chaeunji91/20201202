<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false"%>
<%@ page import="www.dream.com.framework.display.*"%>
<%@ page import="www.dream.com.board.model.*"%>
<%@include file="../includes/header.jsp"%>
<!-- Begin Page Content -->
<div class="container-fluid">

	<!-- Page Heading -->
	<h1 class="h3 mb-2 text-gray-800">게시글 상세 조회</h1>

	<!-- DataTales Example -->
	<div class="card shadow mb-4">
		<div class="card-body">
		
			<%@include file="./include/postCommon.jsp"%>
			
			<!-- data : 요소에 추가적으로 변수와 정보를 마음대로 넣을 수 있는 장치. *** 주의사항:웹은 대소문자 구분 없어 -->
			<button id="modify" data-id="${post.id}" class="btn btn-default">수정</button>
			<%@include file="./include/pagingCommon.jsp"%>
		</div>
	</div>

</div>
<!-- /.container-fluid -->
<%@include file="../includes/footer.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		//Read 
		setOprationMode("read");
		
		//디테일화면/수정버튼클릭/frmpaging append의 input 보임 -> modufy el 앞에 var frmpaging 변수 필요
		
		var frmPaging = $("#frmPaging");
		$("#modify").on("click", function(e) {
			e.preventDefault();

			frmPaging.append("<input type='hidden' name='id' value='" + $(this).data("id") + "'>");
			frmPaging.attr("action", "/post/modifyPost");
			frmPaging.submit();
		});
	});
</script>
