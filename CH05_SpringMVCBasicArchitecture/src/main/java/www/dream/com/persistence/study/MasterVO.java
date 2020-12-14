package www.dream.com.persistence.study;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MasterVO {
	private long id;
	private String	name;
	private boolean	sex;
	private float stature;
	private	Date regDate;
	private	Date updateDate;

	private List<DetailVO> listDetail = new ArrayList<>();
}
