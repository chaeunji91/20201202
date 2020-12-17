/**p418~ */
var dateGapDisplayService = (function(){
	function displayTimeInternal(dataValue) {
		var now = new Date();
		var gap =now.getTime() - dataValue;
		var dateObj = new Date(dataValue);
		
		var strReturn = "";
		
		if (gap < (1000 * 60)) {
			/* 일분 안에 등록된 경우 */
			
		} else if(gap < (1000 * 60 * 60)) {
			/* 한시간 안에 등록된 경우 */
		} else if(gap < (1000 * 60 * 60 * 24)) {
			/* 하루 안에 등록된 경우 */
		} else if(gap < (1000 * 60 * 60 * 24 * 30.5)) {
			/* 한달안에 등록된 경우 */
		} else if(gap < (1000 * 60 * 60 * 24 * 365)) {
			/* 일년안에 등록된 경우 */
		} else {
			/* 몇 년이 지난 경우 */
		}
		return strReturn;
	}
		
	return {
		displayTime:displayTimeInternal, 
	};
})();