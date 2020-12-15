package www.dream.com.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import www.dream.com.board.model.PostVO;
import www.dream.com.board.model.ReplyVO;
import www.dream.com.framework.model.Criteria;

public interface ReplyMapper {
	/* MyBatis에 파라메터 개수는 기본적으로 한개일 때는 잘 작동합니다.
	 * 그런데 두개 이상이 되면은 @Param을 사용해서 이름을 달아주어야지 연동됩니다.
	 */
	public long countTotalPostWithPaging(@Param("boardId") long boardId, @Param("criteria") Criteria criteria); 
	public List<ReplyVO> findPostWithPaging(@Param("boardId") long boardId, @Param("criteria") Criteria criteria); //인자 2개 넣어줌
	public List<ReplyVO> findAllPostWithReply(long boardID);
	
	public List<ReplyVO> findAllReply(long originalId);
	public List<ReplyVO> findReplyWithReply(long id);
	
	public ReplyVO findReplyById(long id);
	
	public void registerPost(PostVO post);
	public boolean updatePost(PostVO post);
	public boolean removePost(PostVO post);
}