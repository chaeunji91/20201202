/**p401~ */
var replyService = (function(){
	function listReplyInternal(param, cbSuccess, cbError) {
		var page = param.page || 1;
		$.getJSON(
			"/replies/pages/" + param.originalId + "/" + page + ".json",
			function(result) {
				 if(cbSuccess) {
					cbSuccess(result);
				}
			}
		).fail(
			function(xhr, status, erMsg){
				if(cbError) {
						cbError(erMsg);
           		}
			}
		);
	}
	
	function showReplyInternal(id, cbSuccess, cbError) {
			$.getJSON(
			"/replies/" + id + ".json",
			function(reply) {
				 if(cbSuccess) {
					cbSuccess(result);
				}
			}
		).fail(
			function(xhr, status, erMsg){
				if(cbError) {
						cbError(erMsg);
           		}
			}
		);
	}
		
	function registerReplyInternal(reply, cbSuccess, cbError) {
		$.ajax({
	            type : 'post',
	            url : '/replies/new',
	            data : JSON.stringify(reply),
				contentType : "application/json;charset=utf-8", // 보내는 데이터의 타입을 지정해 준다.
	            dataType : "text",	//결과에 대한 데이터 타입
	            success : function(result, status, xhr){
		          if(cbSuccess) {
					cbSuccess(result);
					}
	            },
	            error: function(xhr, status, erMsg){
					if(cbError) {
						cbError(erMsg);
	           		}
				}
		 });
	}
	function updateReplyInternal(reply, cbSuccess, cbError) {
		$.ajax({
	            type : 'PATCH',
	            url : '/replies/' + reply.id,
	            data : JSON.stringify(reply),
				contentType : "application/json;charset=utf-8", // 보내는 데이터의 타입을 지정해 준다.
	            
	            success : function(result, status, xhr){
		          if(cbSuccess) {
					cbSuccess(result);
					}
	            },
	            error: function(xhr, status, erMsg){
					if(cbError) {
						cbError(erMsg);
	           		}
				}
		 });
	}
	function deleteReplyInternal(id, cbSuccess, cbError) {
		$.ajax({
	            type : 'Delete',
	            url : '/replies/' + id,
	            dataType : "text",	//결과에 대한 데이터 타입
	            success : function(result, status, xhr){
		          if(cbSuccess) {
					cbSuccess(result);
					}
	            },
	            error: function(xhr, status, erMsg){
					if(cbError) {
						cbError(erMsg);
	           		}
				}
		 });
	}
	return {
		listReply:listReplyInternal, 
		showReply:showReplyInternal,
		registerReply:registerReplyInternal,
		updateReply:updateReplyInternal, 
		deleteReply: deleteReplyInternal
	};
})();