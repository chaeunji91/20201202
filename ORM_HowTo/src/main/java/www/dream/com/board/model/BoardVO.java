package www.dream.com.board.model;

import www.dream.com.framework.model.CommonMngInfoVO;

public class BoardVO extends CommonMngInfoVO {
	private long id;
	private String name;

	public BoardVO(Long id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "BoardVO [id=" + id + ", name=" + name + ", regDate=" + regDate + ", updateDate=" + updateDate + "]";
	}
	
	
}
