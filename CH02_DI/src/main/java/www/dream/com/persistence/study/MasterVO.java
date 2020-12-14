package www.dream.com.persistence.study;

import java.util.Date;

import lombok.Setter;
import lombok.ToString;

@ToString
public class MasterVO {
	@Setter
	private long id;
	@Setter
	private String name;
	@Setter
	private boolean sex;
	@Setter
	private float stature;
	@Setter
	private Date regDate;
	@Setter
	private Date updateDate;
}
