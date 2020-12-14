<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false"%>
<%@ page import="www.dream.com.framework.display.*"%>
<!-- 프레임워크 import -->
<%@ page import="www.dream.com.board.model.*"%>
<%@ include file="../includes/header.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<body>
	<!-- Begin Page Content -->
	<div class="container-fluid">

		<!-- Page Heading -->
		<h1 class="h3 mb-2 text-gray-800">Tables</h1>

		<!-- DataTales Example -->
		<div class="card shadow mb-4">

			<div class="card-body">
				<div class="table-responsive">
					<button id="btnRegisterPost" type="button" class="btn btn-primary">Register
						New Post</button>
					<table class="table table-bordered" id="dataTable" width="100%"
						ellspacing="0">

						<thead>
							<%=TableDisplayer.displayHeader(PostVO.class)%>
							<!-- 프레임워크 caption 끌고옴 -->
						</thead>

						<tbody>
							<c:forEach var="post" items="${listPost}">
								<tr>
									<td><a class='move2postDetail' href='${post.id}'>${post.title}</td> <!-- getMapping으로 들어옴 -->
									<td>${post.writer.name}</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd"
											value="${post.regDate}" /></td>
									<td><fmt:formatDate pattern="yyyy-MM-dd"
											value="${post.updateDate}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					
					<!-- pagination -->
					<div class='pull-right'>
						<ul class="pagination">
							<c:if test="${criteria.hasPrev}">
								<li class="paginate_button previous">
									<a href="${criteria.startPage - 1}">&lt;&lt;</a>
								</li>
							</c:if>
							<c:forEach var="num" begin="${criteria.startPage}" end="${criteria.endPage}">
								<li class='paginate_button ${criteria.pageNum == num ? "active" : ""}'>
									<a href="${num}">${num}</a>
								</li>
							</c:forEach>
							<c:if test="${criteria.hasNext}">
								<li class="paginate_button next">
									<a href="${criteria.endPage + 1}"> &gt;&gt;</a>
								</li>
							</c:if>
						</ul>
					</div>
					<!--end pagination -->
					<form id='frmPaging' action='/post/listPost' method="get">
						<input type="hidden" name="boardId" value="${boardId}">
						<input type="hidden" name="pageNum" value="${criteria.pageNum}">
					</form>
					<!-- Modal 창 만들기 -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">Modal title</h4>
								</div>
								<div class="modal-body">처리가 완료 되었습니다.</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save
										changes</button>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->

				</div>
			</div>
		</div>

	</div>
	<!-- /.container-fluid -->

</body>

</html>
<%@ include file="../includes/footer.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		var result = '<c:out value="${result}"/>';
		
		checkModal(result); //modal에서 result가 있으면, replaceState해버림

		history.replaceState({}, null, null); // flashattribute 청소

		function checkModal(result) { //모달 호출하면 실행됨
			if (result === '' || history.state) { 
				return; 
			}
			$(".modal-body").html("게시글 " + result + "번이 등록되었습니다.");
			$("#myModal").modal("show");
		}

		var frmPaging = $("#frmPaging");
		
		$("#btnRegisterPost").on("click", function(e) {
			e.preventDefault();
			frmPaging.attr("action", "/post/registerPost");
			frmPaging.submit();
		});

		
		$(".paginate_button a").on("click", function(e) {
			e.preventDefault();
			$("input[name='pageNum']").val($(this).attr("href"))
			frmPaging.submit();
		});
		
		
		$(".move2postDetail").on("click", function(e) {
			e.preventDefault();
			frmPaging.append("<input type='hidden' name='id' value='" + $(this).attr("href") + "'>");
			frmPaging.attr("action", "/post/postDetail");
			frmPaging.submit();
		});
	});
</script>