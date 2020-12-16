/**p401~ */
var replyService = (function(){
	function registerReplyInternal(reply, cbSuccess, cbError) {
		$.ajax({
	            type : 'post',
	            url : '/replies/new',
	            data : JSON.stringify(reply),
				contentType : "application/json;charset=utf-8",
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
	return {registerReply:registerReplyInternal};
})();
