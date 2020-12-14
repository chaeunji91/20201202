package www.dream.com.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;

import www.dream.com.persistence.study.MasterVO;

public class JDBCTest {
	SqlSessionFactoryBean ddddd;
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
			//stmt.executeUpdate(sql)	CUD
			//stmt.execute(sql) 여러건 조회
			//stmt.executeQuery(sql)   단건 조회
			//stmt.addBatch(sql);	고 성능
			//stmt.executeBatch()
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInsert() {
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", 
				"system",
				"admin");
			Statement stmt = con.createStatement();	
				) {
			
			String sql = "insert into T_Master(id, name, sex, stature) values(20000, '홍길동', 1, 160.02)";
			stmt.executeUpdate(sql);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void testInsertBatch() {
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", 
				"system",
				"admin")) {
			//stmt.addBatch(sql);	고 성능
			//stmt.executeBatch()
			Statement stmt = con.createStatement();
			for (int i = 3; i < 1000; i++) {
				String sql = "insert into T_Master(id, name, sex, stature) values(" + i + ", '홍길" + i + "', 1, 160.02)";
				stmt.addBatch(sql);
			}
			stmt.executeBatch();
			stmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void testInsertBatchOnPrepared() {
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", 
				"system",
				"admin")) {
			//stmt.addBatch(sql);	고 성능
			//stmt.executeBatch()
			String sql = "insert into T_Master(id, name, sex, stature) values(?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			for (long i = 10000; i < 10010; i++) {
				stmt.setLong(1, i);
				stmt.setString(2, "꾹" + i);
				stmt.setBoolean(3, true);
				stmt.setFloat(4, 444.33f);
				stmt.addBatch();
			}
			stmt.executeBatch();
			stmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void testSingleSelect() {
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", 
				"system",
				"admin")) {
			String sql = "select * from T_Master where id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, 10000);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			MasterVO masterVO = new MasterVO();
			masterVO.setId(rs.getLong("id"));
			masterVO.setName(rs.getString("name"));
			stmt.close();
			System.out.println(masterVO);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void testMultiSelect() {
		try(Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl", 
				"system",
				"admin")) {
			String sql = "select * from T_Master where id > ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setLong(1, 10000);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {	//Cursor
				MasterVO masterVO = new MasterVO();
				masterVO.setId(rs.getLong("id"));
				masterVO.setName(rs.getString("name"));
				System.out.println(masterVO);
			}
			stmt.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
