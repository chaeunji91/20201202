package www.dream.com.board.model.mapper;

import java.util.List;

import www.dream.com.board.model.ReplyVO;

public interface ReplyMapper {
	public List<ReplyVO> findAllPost(long boardID);
	public List<ReplyVO> findAllPostWithReply(long boardID);
	public List<ReplyVO> findAllReply(long originalId);
	public List<ReplyVO> findReplyWithReply(long id);
}
