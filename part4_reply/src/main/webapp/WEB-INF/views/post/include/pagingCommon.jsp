<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

				<!-- form 안에 form 못들어가기 때문에 paging 쪼개서 넣음 -->
				<form id='frmPaging' action='/post/listPost' method="get">
					<button id="btnGotoList" class="btn btn-info">목록</button>
					<input type="hidden" name="boardId" value="${boardId}">
					<input type="hidden" name="pageNum" value="${criteria.pageNum}">
				</form>
				
			
	
<script type="text/javascript">
	$(document).ready(function() {
		var frmPaging = $("#frmPaging");
		$("#btnGotoList").on("click", function(e) {
				e.preventDefault();
				frmPaging.submit();
		});
	});	
</script>
