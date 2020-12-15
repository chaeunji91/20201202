package www.dream.com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import www.dream.com.board.model.PostVO;
import www.dream.com.board.model.ReplyVO;
import www.dream.com.board.model.mapper.ReplyMapper;
import www.dream.com.framework.dataType.DreamPair;
import www.dream.com.framework.hashTagAnalyzer.model.HashTagVO;
import www.dream.com.framework.hashTagAnalyzer.service.HashTagService;
import www.dream.com.framework.model.Criteria;

@Service
public class PostService {
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private HashTagService hashTagService;
	
	public long countTotalPostWithPaging(long boardId, Criteria criteria) {
		//CashingExecuter sss = new cashingExecuter(null);
		return replyMapper.countTotalPostWithPaging(boardId, criteria);
	}

	public List<ReplyVO> findPostWithPaging(long boardId, Criteria criteria) {
		return replyMapper.findPostWithPaging(boardId, criteria);
	}
	@Transactional
	public void registerPost(PostVO post) {
		String[] arrHashTag = post.getHashTag().split(" ");
		//교집합 되는 것들, 새것들
		DreamPair<List<HashTagVO>, List<HashTagVO>> pair = hashTagService.split(arrHashTag);
		//신규 단어 등록
		hashTagService.createHashTag(pair.getSecond()); //HashTagVO id가 채워진 side effect
		//게시글 등록
		replyMapper.registerPost(post);	//side effect가 들어가서 내용이 바뀜
		
		//전체 단어와 게시글 사이의 연결 고리르 만들어 줍니다.
		//이 기능을 만들곳이 HashTag에 있어야 하나? 아니면 각 사용 주체에게 달려 있어야 할까?
		
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