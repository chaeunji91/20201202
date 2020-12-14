package www.dream.com.di_study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.Getter;

@Repository
public class Restaurant {
	
	//@Setter(onMethod_ = {@Autowired})
	@Autowired
	@Getter
	//DI: 컴포넌트 스캔해서 레파지토리나 서비스나 컨트롤러로 @애노테이팅 되어있는 녀석을 찾으면 객체를 만들어 끼워넣음
	//Composite(구성 관계: uml 마름모꼴)
	private Chef chef;
}
