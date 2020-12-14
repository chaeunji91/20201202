package www.dream.com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import www.dream.com.persistence.study.MasterVO;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/sample/") // handlerMapping에 달아주겠다
public class SampleController {
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "")
	public void basic() {
		System.out.println("basic() executing");
	}
	@RequestMapping(value = "basic", method= {RequestMethod.GET})
	public void basicGet(MasterVO master) {
		System.out.println(master);
	}
}
