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
			<div class="form-group">
				<label>ID : </label><input class="form-control" name="id" value="${post.id}" readonly="readonly">
			</div>
			<div class="form-group">
				<label>제목 : </label><input class="form-control" name="title" value="${post.title}" readonly="readonly">
			</div>
			<div class="form-group">
				<label>내용</label>
				<textarea class="form-control" rows=3 name="content" readonly="readonly">${post.content}</textarea>
			</div>
			<div class="form-group">
				<label>해쉬 태그</label><input id="hashTag" class="form-control" name="hashTag" value="${post.hashTagAsString}" readonly="readonly">
			</div>
			<div class="form-group">
				<label>작성자 : </label><input class="form-control" name="writer.name" value="${post.writer.name}" readonly="readonly">
			</div>
			<!-- data : 요소에 추가적으로 변수와 정보를 마음대로 넣을 수 있는 장치. *** 주의사항:웹은 대소문자 구분 없어 -->
			<button id="modify" data-id="${post.id}" class="btn btn-default">수정</button>
			<button id="list" class="btn btn-info">목록</button>
		</div>
	</div>

	<form id='frmPaging' action='/post/listPost' method="get">
		<input type="hidden" name="boardId" value="${boardId}">
		<input type="hidden" name="pageNum" value="${criteria.pageNum}">
	</form>
</div>
<!-- /.container-fluid -->
<%@include file="../includes/footer.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		var frmPaging = $("#frmPaging");

		$("#modify").on("click", function(e) {
			e.preventDefault();

			frmPaging.append("<input type='hidden' name='id' value='" + $(this).data("id") + "'>");
			frmPaging.attr("action", "/post/modifyPost");
			frmPaging.submit();
		});

		$("#list").on("click", function(e) {
			e.preventDefault();
			frmPaging.submit();
		});
	})
</script>
