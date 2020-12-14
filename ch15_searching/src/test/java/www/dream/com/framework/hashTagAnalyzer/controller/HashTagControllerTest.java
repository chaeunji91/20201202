package www.dream.com.framework.hashTagAnalyzer.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import www.dream.com.board.model.PostVO;
import www.dream.com.board.model.ReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src\\main\\webapp\\WEB-INF\\spring\\root-context.xml", 
	"file:src\\main\\webapp\\WEB-INF\\spring\\appServlet\\servlet-context.xml"})
public class HashTagControllerTest {
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;	//Browser 흉내내기
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	@Test
	public void testListPost() {
		PostVO post = new PostVO();
		post.setTitle("비동기 방식이란");
		post.setContent("비동기 방식은 웹페이지를 리로드하지 않고 데이터를 불러오는 방식이며 Ajax");
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String jsonPost = mapper.writeValueAsString(post);
			ResultActions resultActions = mockMvc.perform(post("/hashTag/extractHashTag")
					.contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonPost));
			MvcResult mvcResult = resultActions.andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
