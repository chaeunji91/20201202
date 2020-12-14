package www.dream.com.board.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import www.dream.com.framework.display.Caption;
import www.dream.com.framework.display.Caption.WhenUse;
import www.dream.com.framework.model.CommonMngInfoVO;
import www.dream.com.party.model.PartyVO;

public class ReplyVO extends CommonMngInfoVO {
	@Getter @Setter
	private long id; //pojo 방식으로 들어감.
	@Getter @Setter
	@Caption(whenUse=WhenUse.detail, caption="게시글 내용")
	private String content;
	
	@Getter @Setter
	@Caption(whenUse=WhenUse.detail, caption="해쉬 태그")
	private String hashTag;
	
	/* ------------   연관 정보 정의 영역    ------------------ */
	@Getter @Setter
	@Caption(whenUse=WhenUse.all, caption="작성자")
	private PartyVO writer;
	private List<ReplyVO> listReplies;
	
	public ReplyVO() {
	}
	
	public ReplyVO(Long id) { //default 생성자가 없는 상태에서
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
