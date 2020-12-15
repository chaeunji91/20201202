package www.dream.com.framework.hashTagAnalyzer.mapper;

import java.util.ArrayList;
import java.util.List;

import www.dream.com.framework.hashTagAnalyzer.model.HashTagVO;
import www.dream.com.framework.hashTagAnalyzer.service.HashTagService;

public class ToCheckIDCollision extends Thread {
	
	private HashTagService hashTagService;
	
	public ToCheckIDCollision(HashTagService hashTagService) {
		this.hashTagService = hashTagService;
	}
	
	@Override
	public void run() {
		try {
				List<HashTagVO> listNewTag = new ArrayList<>();
				HashTagVO ddd = new HashTagVO();
				ddd.setWord("AAAA");
				listNewTag.add(ddd);
				
				ddd = new HashTagVO();
				ddd.setWord("BBBB");
				listNewTag.add(ddd);
				
				hashTagService.createHashTag(listNewTag);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
