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
import www.dream.com.framework.util.BeanUtil;

@Service
public class PostService {
	@Autowired
	private ReplyMapper replyMapper;

	public long countTotalPostWithPaging(long boardId, Criteria criteria) {
		//CachingExecutor sss = new CachingExecutor(null);
		return replyMapper.countTotalPostWithPaging(boardId, criteria);
	}

	public List<ReplyVO> findPostWithPaging(long boardId, Criteria criteria) {
		return replyMapper.findPostWithPaging(boardId, criteria);
	}

	public ReplyVO findPostById(long id) {
		return replyMapper.findReplyById(id);
	}

	@Transactional
	public void registerPost(PostVO post) {
		HashTagService hashTagService = BeanUtil.getBean(HashTagService.class);

		List<HashTagVO> listHashTag = identifyOldAndNew(post, hashTagService);

		replyMapper.registerPost(post);
		hashTagService.createRelWithReply(post.getId(), listHashTag);
	}

	public boolean updatePost(PostVO post) {
		HashTagService hashTagService = BeanUtil.getBean(HashTagService.class);
		hashTagService.deleteRelWithReply(post.getId());

		List<HashTagVO> listHashTag = identifyOldAndNew(post, hashTagService);

		hashTagService.createRelWithReply(post.getId(), listHashTag);

		return replyMapper.updatePost(post);
	}

	public boolean removePost(PostVO post) {
		HashTagService hashTagService = BeanUtil.getBean(HashTagService.class);
		hashTagService.deleteRelWithReply(post.getId());
		return replyMapper.removePost(post);
	}

	private List<HashTagVO> identifyOldAndNew(PostVO post, HashTagService hashTagService) {
		String[] arrHashTag = post.getHashTag().split(" ");
		//교집합 되는 것들, 새것들
		DreamPair<List<HashTagVO>, List<HashTagVO>> pair = hashTagService.split(arrHashTag);
		/* 신규 단어 등록 */
		hashTagService.createHashTag(pair.getSecond());
		//전체 단어와 게시글 사이의 연결 고리를 만들어 줍니다.
		//이 기능을 만들곳이 HashTag에 있어야하나? 아니면 각 사용 주체에게 달려있어야 할까?
		pair.getFirst().addAll(pair.getSecond());
		return pair.getFirst();
	}
}
