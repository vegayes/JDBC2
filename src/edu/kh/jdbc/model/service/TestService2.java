package edu.kh.jdbc.model.service;

import java.sql.Connection;

import static edu.kh.jdbc.common.JDBCTemplate.*;
import edu.kh.jdbc.model.dao.TestDAO2;
import edu.kh.jdbc.model.vo.TestVO;

public class TestService2 {

	private TestDAO2 dao = new TestDAO2();
	
	public int insert(TestVO vo1) throws Exception {
		
		Connection con = getConnection();
		
		int result = dao.insert(con, vo1);
		
		if(result > 0) commit(con);
		else  			rollback(con);
		
		close(con);

		return result;
	}

}
