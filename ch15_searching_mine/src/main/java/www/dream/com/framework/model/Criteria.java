package www.dream.com.framework.model;

import lombok.Getter;
import lombok.Setter;

//setter는 컨트롤러가 들어올때 만들어짐
@Getter
public class Criteria {
	private static final long PAGING_AMOUNT = 10; // 쪽 건너뛰기 버튼 전체 개수
	private static final int DEFAULT_AMOUNT = 10; // 쪽 당 몇 건 보여줄까
	@Setter
	private String search;
	private long pageNum; // DDL->id생각 전체 건수 나누기 페이지당 나타낼 정보건수
	// private int amount; //최대 허용할 양: 거대 모니터, 사용자의 나이, 장애인 여부 -> 사용성을 고려한 개수 설정이 필요

	private long startPage, endPage;
	
	private long totalDataCount;
	private boolean hasPrev, hasNext;

	public Criteria() { // default생성자 불림
		this(1, 1241); // 없으면 만들어줌
	}

	public Criteria(long pageNum, int totalDataCount) {
		this.totalDataCount = totalDataCount;
		// this.cri= cri 랑 같음
		setPageNum(pageNum);
		
	}

	public long getOffset() {
		return (pageNum - 1) * DEFAULT_AMOUNT;
	}

	public long getLimit() {
		return pageNum * DEFAULT_AMOUNT;
	}

	public void setPageNum(long pageNum) {
		this.pageNum = pageNum;		
		//10, 20, 30 이런식으로 페이징 처리(10개중 하나)
		//endPage = (int) Math.ceil(pageNum / (float) PAGING_AMOUNT) * PAGING_AMOUNT;
		
		//center : 구글스타일
		endPage = pageNum + PAGING_AMOUNT / 2;
		endPage = endPage < PAGING_AMOUNT ? PAGING_AMOUNT : endPage;
		
		startPage = endPage - PAGING_AMOUNT + 1;

		int realEnd = (int) Math.ceil((float) totalDataCount / DEFAULT_AMOUNT);
		if (realEnd < endPage)
			endPage = realEnd;
		hasPrev = startPage > 1;
		hasNext = endPage < realEnd;
	}
	public String[] getSearchArr() {
		return search != null ? search.split(" ") : null;
	}

	public void setTotalDataCount(long totalDataCount) {
		this.totalDataCount = totalDataCount;
		
		int realEnd = (int) Math.ceil((float) totalDataCount / DEFAULT_AMOUNT);
		if (realEnd < endPage)
			endPage = realEnd;
		hasNext = endPage < realEnd;
	}
}
