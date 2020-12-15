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
			<form id="frm_modify_post" action="/post/modifyPost" method="post">
				<div class="form-group">
					<label>ID : </label><input class="form-control" name="id"
						value="${post.id}" readonly="readonly">
				</div>
				<div class="form-group">
					<label>제목 : </label><input id="title" class="form-control" name="title"
						value="${post.title}">
				</div>
				<div class="form-group">
					<label>내용</label>
					<textarea id="txacontent" class="form-control" rows=3 name="content">${post.content}</textarea>
				</div>
				<div class="form-group">
					<label>작성자 : </label><input class="form-control" name="writer.name"
						value="${post.writer.name}" readonly="readonly">
				</div>
				<div class="form-group">
					<label>해쉬 태그</label><input id="hashTag" class="form-control" name="hashTag" value="${post.hashTagAsString}">
				</div>
				
				<!-- data : 요소에 추가적으로 변수와 정보를 마음대로 넣을 수 있는 장치. *** 주의사항:웹은 대소문자 구분 없어 -->
				<button id="btnExtractHashTag" type="button" class="btn btn-default">태그 추출</button>
				<button type="submit" data-oper="modify" class="btn btn-default btnControl">수정</button>
				<button type="submit" data-oper="remove" class="btn btn-danger btnControl">삭제</button>
				<button type="submit" data-oper="list" class="btn btn-info btnControl">목록</button>

			</form>
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
		var frmModifyPost = $("#frm_modify_post");
		var frmPaging = $("#frmPaging");
		
		$(".btnControl").on("click", function(e) {
			e.preventDefault();

			var oper = $(this).data("oper");
			if (oper === "remove") {
				frmModifyPost.attr("action", "/post/removePost");
			} else if (oper === "list") {
				frmPaging.submit();
				return;
			}
			frmModifyPost.append(frmPaging.children());
			frmModifyPost.submit();
		});

		$("#btnExtractHashTag").on("click", function(e) {
			e.preventDefault();
			var postBody = {title:$("#title").val(), content:$('#txacontent').val()};
			
			$.ajax({
	            type : 'post',
	            url : '/hashtag/extractHashTag',
	            data : postBody,
	            dataType : 'json',	//결과에 대한 데이터 타입
	            success : function(listHashTag){
		            var candidateHashTag = "";
	    			for (var i = 0; i < listHashTag.length; i++) {
	    				candidateHashTag += listHashTag[i] + " ";
	    			}
	    			$("#hashTag").val(candidateHashTag);
	            },
	            error: function(xhr, status, error){
	                alert(error);
	            }
	        });
		});
	})
</script>
