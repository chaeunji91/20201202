package www.dream.com.board.model;

import lombok.Getter;
import lombok.Setter;
import www.dream.com.framework.display.Caption;
import www.dream.com.framework.display.Caption.WhenUse;

public class PostVO extends ReplyVO {
	@Getter @Setter
	@Caption(whenUse=WhenUse.all, caption="게시글 제목")
	private String title;
	@Getter @Setter
	private long boardId;
	
	public PostVO() {
		
	}
	
	public PostVO(Long id) { // default 생성자가 없는 상태
		super(id);
	}

	@Override
	public String toString() {
		return "PostVO [" + toString4ChildPrev() +", title=" + title + toString4ChildPost() + "]";
	}
	
	//public static void main(String[] args) {
	//	System.out.println(TableDisplayer.displayHeader(PostVO.class));
	//}
}
