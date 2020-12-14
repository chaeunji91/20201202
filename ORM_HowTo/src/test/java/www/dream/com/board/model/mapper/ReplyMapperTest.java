package www.dream.com.board.model.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import www.dream.com.board.model.ReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml")
public class ReplyMapperTest {
	@Autowired
	private ReplyMapper replyMapper;
	
	//@Test
	public void testFindAllPost() {
		try { 
			for (ReplyVO reply : replyMapper.findAllPost(1L))
				System.out.println(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//@Test
	public void testFindAllPostWithReply() {
			try { 
				for (ReplyVO reply : replyMapper.findAllPostWithReply(1L))
					System.out.println(reply);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	//@Test
	public void testFindAllReply() {
		try { 
			for (ReplyVO reply : replyMapper.findAllReply(2L))
				System.out.println(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testFindReplyWithReply() {
		try { 
			for (ReplyVO reply : replyMapper.findReplyWithReply(3L))
				System.out.println(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
