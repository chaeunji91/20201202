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
	<!-- 댓글 목록 표시 구간 p414-->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="fa fa-comments fa-fw">Reply</i>
				</div>
				<div class="panel-body">
					<ul id="listReply">
						<!-- 프로그램에서 처리"", 스타일처리 '' 아래쪽 function 안에 넣어둠  -->
						
					</ul>
				</div>
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

		var originalId="${post.id}";
		var listReplyUL = $("#listReply");
		showReplyList(1);

		function showReplyList(pageNum) {
			replyService.listReply( 
				{originalId:originalId, page:(pageNum || 1)},
				function(listReply){
					var strReplyLi = "";
					for (var i = 0, len = listReply.length || 0; i < len; i++) {
						strReplyLi += "<li class='left clearfix' data-id='" + listReply[i].id + "'>";
						strReplyLi += "<div><div class='header'><strong class='primary-font'>" + listReply[i].writer.name + "</strong>";
						strReplyLi += "<small class='pull-right text-muted'>" + listReply[i].updateDate + "</small></div>";
						strReplyLi +=  "<p>" + listReply[i].content + "</p></div></li>";
					}
					listReplyUL.html(strReplyLi);
				},
				function(erMsg){
					alert(erMsg);
				}
			);
		}
	});
</script>
<!--  화면이 뜨자마자 호출되는 페이지 -->
<script type="text/javascript" src="\resources\js\reply\replyService.js"></script>
<script type="text/javascript">
	var originalId="${post.id}";
	
	replyService.showReply( 
			3,
			function(reply){
				alert(reply);
			},
			function(erMsg){
				alert(erMsg);
			}
		);
	replyService.updateReply( 
			{id:3, content:"프로그램으로 자동 변경하기"},
			function(result){
				alert(result);
			},
			function(erMsg){
				alert(erMsg);
			}
		);
</script>
