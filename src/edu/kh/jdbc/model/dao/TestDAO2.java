package edu.kh.jdbc.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.jdbc.model.vo.TestVO;
import static  edu.kh.jdbc.common.JDBCTemplate.*;

public class TestDAO2 {
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Properties prop;
	
	public TestDAO2() {
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("test-query.xml"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int insert(Connection con, TestVO vo1) throws Exception{
	
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insert");
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, vo1.getTestNo());
			pstmt.setString(2, vo1.getTestTitle());
			pstmt.setString(3, vo1.getTestContent());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
}
