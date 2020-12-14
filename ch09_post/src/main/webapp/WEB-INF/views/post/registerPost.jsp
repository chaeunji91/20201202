<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false" %>
<%@ page import="www.dream.com.framework.display.*" %> <!-- 프레임워크 import -->
<%@ page import="www.dream.com.board.model.*" %>
<%@ include file="../includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<body>
	<!-- Begin Page Content -->
	<div class="container-fluid">

		<!-- Page Heading -->
		<h1 class="h3 mb-2 text-gray-800">게시글 등록하기</h1>

		<!-- DataTales Example -->
		<div class="card shadow mb-4">
			<div class="card-body">
				<form role="form" action="/post/registerPost" method="post">
					<div class="form-group">
						<label>제목</label> <input class="form-control" name="title">
					</div>
					<div class="form-group">
						<label>내용</label> 
						<textarea class="form-control" rows=3 name="content"></textarea>
					</div>
					<button type="submit" class="btn btn-default">등록</button>
					<button type="reset" class="btn btn-default">취소</button>
					
					<input type="hidden" name="boardId" value="${boardId}">
				</form>
			</div>
		</div>
	</div>
	<!-- /.container-fluid -->

</body>

</html>
<%@ include file="../includes/footer.jsp"%>