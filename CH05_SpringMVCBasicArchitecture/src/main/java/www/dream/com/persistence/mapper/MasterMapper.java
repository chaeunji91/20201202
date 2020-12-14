package www.dream.com.persistence.mapper;

import org.apache.ibatis.annotations.Select;

public interface MasterMapper {
	@Select("select sysdate from dual")
	public String getTime();
	
	public String getTimeFromXML();
}
