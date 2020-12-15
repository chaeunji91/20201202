package www.dream.com.framework.model;

import java.util.Date;

import lombok.Getter;
import www.dream.com.framework.display.Caption;
import www.dream.com.framework.display.Caption.WhenUse;

public abstract class CommonMngInfoVO {
	@Getter
	@Caption(whenUse=WhenUse.all, caption="등록일")
	protected Date regDate;
	@Getter
	@Caption(whenUse=WhenUse.all, caption="수정일")
	protected Date updateDate;
}
