<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

				<div id="divIdentifier" class="form-group">
				<!-- int, long 같은 것은 null 이면, 문제가 발생할까? 발생한다. 숫자로 들어가는 것은 null 안들어가도록, default값 0이라도 넣어놔야한다. -->
					<label>ID : </label><input type="number" name="id" value="${post != null ? post.id : 0 }" readonly="readonly" class="form-control" >
				</div>
	
				<div class="form-group">
					<label>제목</label><input id="title" name="title" value="${post.title}" class="form-control">
				</div>
				
				<div class="form-group">
					<label>내용</label>
					<textarea id="txacontent" name="content" class="form-control" rows=3>${post.content}</textarea>
				</div>
				
				<div class="form-group">
					<label>해쉬 태그</label><input id="hashTag" name="hashTag" value="${post.hashTagAsString}" class="form-control">
				</div>
				
				<div id="divWriter" class="form-group">
					<label>작성자 : </label><input value="${post.writer.name}" readonly="readonly" class="form-control">
				</div>
				
				<button id="btnExtractHashTag" type="button" class="btn btn-default">태그 추출</button>		
	
<script type="text/javascript">
	//Create Read(제목, 내용, 해쉬태그 -> readonly) Update
	function setOprationMode(oprationmode) {
		if ("read" === oprationmode) {
			$("#title").attr("readonly", true);
			$("#txacontent").attr("readonly", true);
			$("#hashTag").attr("readonly", true);
		} else if("create" === oprationmode) {
			$("#divIdentifier").hide();
			$("#divWriter").hide();
			}
		}
	$(document).ready(function() {
		//Update
		setOprationMode("update");		
		
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
	});
	
</script>
