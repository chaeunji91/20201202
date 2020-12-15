package www.dream.com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import www.dream.com.board.model.PostVO;
import www.dream.com.board.service.PostService;
import www.dream.com.framework.model.Criteria;
import www.dream.com.party.model.PersonVO;

@Controller
@RequestMapping("/post/*")
public class PostController {
	//함수 배치 순서는 목록, 상세, 생성, 수정, 삭제
	@Autowired
	private PostService postService;
		
	@GetMapping("listPost") //등록 jsp 띄워주세요.
	public void listPost(@RequestParam("boardId") long boardId, Criteria criteria, Model model) {
		long countTotal = postService.countTotalPostWithPaging(boardId, criteria);
		criteria.setTotalDataCount(countTotal);
		
		model.addAttribute("listPost", postService.findPostWithPaging(boardId, criteria));
		model.addAttribute("criteria", criteria); //listPost에서 criteria를 쓸 수 있음
		model.addAttribute("boardId", boardId);
	}
	
	/**
	 * "상세 조회 화면 열자", "수정 화면 열자"가 어떻게 불려졌나에 따라 자동으로 해당 JSP를 찾아가겠거니
	 */
	@GetMapping({"postDetail", "modifyPost"}) //post안에서 registerPost 띄움. p216, p143, p169c참고
	public void showDetailPost(@RequestParam("boardId") long boardId, @RequestParam("id") long id, Criteria criteria, Model model) {
		PostVO post = (PostVO) postService.findPostById(id);

		model.addAttribute("post", post);
		model.addAttribute("criteria", criteria);
		model.addAttribute("boardId", post.getBoardId());
	}
	
	/**
	 * 등록 화면 열자
	 */
	@GetMapping("registerPost") //post안에서 registerPost 띄움. p216, p143, p169c참고
	public void registerPost(@RequestParam("boardId") long boardId, Criteria criteria, Model model) {
		model.addAttribute("criteria", criteria);
		model.addAttribute("boardId", boardId);
	}
	@PostMapping("registerPost") //메소드 오버로딩
	public String registerPost(@RequestParam("boardId") long boardId, PostVO post, RedirectAttributes rttr) {
		//로그인 처리가 된 다음에 그 정보를 활용하는 스타일로 바꿀...디폴트 사용자로 홍길동을 임시 사용할거야
		post.setWriter(new PersonVO(21L)); //홍길동 id
		post.setBoardId(boardId); //자유게시판 사용
		postService.registerPost(post); //post 등록
		rttr.addFlashAttribute("result", post.getId());
		
		rttr.addAttribute("boardId", boardId);
		return "redirect:/post/listPost"; //목록으로 돌아오네
	}
	/**
	 * 수정 처리 하자
	 */
	@PostMapping("modifyPost") //폼에서 액션할때 jsp불러줌(수정처리, 삭제랑 비슷)
	public String modifyPost(@RequestParam("boardId") long boardId, @ModelAttribute("criteria") Criteria criteria, PostVO post, RedirectAttributes rttr) {
		if (postService.updatePost(post)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("result", "success");
		rttr.addAttribute("boardId", boardId);
		rttr.addAttribute("pageNum", criteria.getPageNum()); //listPost에서 criteria를 쓸 수 있음
		return "redirect:/post/listPost"; 
	}
	
	/**
	 * 삭제 화면 열자
	 */
	@PostMapping("removePost")
	public String removePost(@RequestParam("boardId") long boardId, Criteria criteria, PostVO post, RedirectAttributes rttr) {
		if (postService.removePost(post)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("boardId", boardId);
		rttr.addAttribute("pageNum", criteria.getPageNum()); //listPost에서 criteria를 쓸 수 있음
		return "redirect:/post/listPost"; 
	}
}
