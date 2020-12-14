package www.dream.com.board.model;

import java.util.List;

import www.dream.com.framework.model.CommonMngInfoVO;
import www.dream.com.party.model.PartyVO;

public class ReplyVO extends CommonMngInfoVO {
	private long id;
	private String content;
	
	/* ------------   연관 정보 정의 영역    ------------------ */
	private PartyVO writer;
	private List<ReplyVO> listReplies;
	
	public ReplyVO(Long id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "ReplyVO [id=" + id + ", content=" + content + ", writer=" + writer + ", listReplies=" + listReplies
				+ ", regDate=" + regDate + ", updateDate=" + updateDate + "]";
	}
	protected String toString4ChildPrev() {
		return "id=" + id;
	}
	protected String toString4ChildPost() {
		return ", content=" + content + ", writer=" + writer + ", listReplies=" + listReplies
				+ ", regDate=" + regDate + ", updateDate=" + updateDate;
	}
}
