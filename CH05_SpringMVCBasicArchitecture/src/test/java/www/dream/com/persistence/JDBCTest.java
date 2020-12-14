package www.dream.com.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

import www.dream.com.persistence.study.MasterVO;

public class JDBCTest {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} 

	
	public void testConnection() {
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", 
				"system", 
				"admin")) {
			//Statement stmt = con.createStatement();
			// stmt.executeUpdate(sql) CUD
			//stmt.execute(sql) 여러건 조회
			//stmt.executeQuery(sql) 단건 조회
			//stmt.addBatch(sql) 고성능
			//stmt.executeBatch()
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void	testInsert() {
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", 
				"system", 
				"admin");
				Statement stmt = con.createStatement();
				) {
			
			
			// stmt.executeUpdate(sql) CUD
			//int executeUpdate(String sql) throws SQLException;
			// jdbc나 mybatis에서 sql 구문 넣을때는 "" 안에 ;없어야함 junit test에서 보면 에러나게 됨
			String sql = "insert into T_Master(id, name, sex, stature) values(20000, '홍길동', 1, 160.02)";
			stmt.executeUpdate(sql); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void	testInsertBatch() {
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", 
				"system", 
				"admin")) {
			//stmt.addBatch(sql) 고성능 batch 쓸 때 -> for 돌려서 씀
			//stmt.executeBatch() 
			Statement stmt = con.createStatement();
			for (int i = 3; i<1000; i++) {	//string은 ''안에 잘 들어가야함 -> 홍길
				String sql = "insert into T_Master(id, name, sex, stature) values(" + i + ", '홍길" + i +"', 1, 160.02)";
				stmt.addBatch(sql); //1000번의 해석을 거쳐서 들어가게 함
			}
			
			stmt.executeBatch();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void	testInsertBatchOnPrepared() { 
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", 
				"system", 
				"admin")) {
			//stmt.addBatch(sql) 고성능
			//stmt.executeBatch()
			String sql = "insert into T_Master(id, name, sex, stature) values(?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			//prepareStatement 성능 높이기 
			for (long i = 10000; i<10010; i++) {
				stmt.setLong(1, i); //java에서는 0~4 , db에서는 index 1~4로 함
				stmt.setString(2, "꾹" + i);
				stmt.setBoolean(3, true);
				stmt.setFloat(4, 444.33f);
				stmt.addBatch();
			}
			stmt.executeBatch();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//stmt.executeQuery(sql) 단건 조회
	public void	testSingleSelect() { 
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", 
				"system", 
				"admin")) {
			String sql = "select * from T_Master where id=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, 10000);
			ResultSet rs = stmt.executeQuery(); //결과 집합
			rs.next(); //ResultSet.next가 호출되지 않았음 -> next가 호출되야함
			MasterVO masterVO = new MasterVO();
			masterVO.setId(rs.getLong("id"));
			masterVO.setName(rs.getString("name"));		
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//stmt.execute(sql) 여러건 조회
	
	public void	testMultiSelect() { 
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", 
				"system", 
				"admin")) {
			String sql = "select * from T_Master where id > ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, 10000);
			ResultSet rs = stmt.executeQuery(); //결과 집합
			while(rs.next()) {	//cursor 작동
				MasterVO masterVO = new MasterVO();
				masterVO.setId(rs.getLong("id"));
				masterVO.setName(rs.getString("name"));
			}		
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
