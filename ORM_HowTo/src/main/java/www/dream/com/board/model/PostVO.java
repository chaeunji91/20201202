package www.dream.com.board.model;

public class PostVO extends ReplyVO {
	private String title;
	
	public PostVO(Long id) {
		super(id);
	}

	@Override
	public String toString() {
		return "PostVO [" + toString4ChildPrev() +", title=" + title + toString4ChildPost() + "]";
	}
	
}
