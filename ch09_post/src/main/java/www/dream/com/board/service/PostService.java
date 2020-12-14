package www.dream.com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.dream.com.board.model.PostVO;
import www.dream.com.board.model.ReplyVO;
import www.dream.com.board.model.mapper.ReplyMapper;
import www.dream.com.framework.model.Criteria;

@Service
public class PostService {
	@Autowired
	private ReplyMapper replyMapper;
	
	public long countTotalPostWithPaging(long boardID) {
		return replyMapper.countTotalPostWithPaging(boardID);
	}

	public List<ReplyVO> findPostWithPaging(long boardID, Criteria criteria) {
		return replyMapper.findPostWithPaging(boardID, criteria);
	}

	public void registerPost(PostVO post) {
		replyMapper.registerPost(post);	//side effect가 들어가서 내용이 바뀜
		
	}

	public ReplyVO findPostById(long id) {
		return replyMapper.findReplyById(id);
	}

	public boolean updatePost(PostVO post) {
		
		return replyMapper.updatePost(post);
	}

	public boolean removePost(PostVO post) {
		return replyMapper.removePost(post);
	}
}
