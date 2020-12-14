package www.dream.com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import www.dream.com.board.model.PostVO;
import www.dream.com.board.model.ReplyVO;
import www.dream.com.board.model.mapper.ReplyMapper;
import www.dream.com.framework.dataType.Pair;
import www.dream.com.framework.hashTagAnalyzer.model.HashTagVO;
import www.dream.com.framework.hashTagAnalyzer.service.HashTagService;
import www.dream.com.framework.model.Criteria;

@Service
public class PostService {
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private HashTagService komoranService;
	
	public long countTotalPostWithPaging(long boardId, Criteria criteria) {
		//CashingExecuter sss = new cashingExecuter(null);
		return replyMapper.countTotalPostWithPaging(boardId, criteria);
	}

	public List<ReplyVO> findPostWithPaging(long boardId, Criteria criteria) {
		return replyMapper.findPostWithPaging(boardId, criteria);
	}

	public void registerPost(PostVO post) {
		String[] arrHashTag = post.getHashTag().split(" ");
		//교집합 되는 것들, 새것들
		Pair<List<HashTagVO>, List<HashTagVO>> pair = komoranService.split(arrHashTag);
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
