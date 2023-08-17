package edu.kh.jdbc.model.service;

// import static 구문
// -> static이 붙은 필드, 메서드를 호출할 때
//     클래스 명을 생략할 수 있게 해주는 구문 
// JDBCTemplate 안에 있는 모든 것. 
import static edu.kh.jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;

import edu.kh.jdbc.model.dao.TestDAO;
import edu.kh.jdbc.model.vo.TestVO;

public class TestService {
	// Service :  비즈니스 로직(데이터 가공,  트랜잭션 제어) 처리
	// -> 실제 프로그램이 제공하는 기능을 모아놓은 클래스 
	
	// 하나의 Service 메서드에서 n개의 DAO 메서드를 호출하여 
	// 이를 하나의 트랜잭션 단위로 취급하여 
	// 한번에 commit, rollback을 수행할 수 있다. 
	
	// DAO 호출을 하기 위해서 필드에 만들어줌.
	private TestDAO dao = new TestDAO();
	
	
	public int insert(TestVO vo1)throws SQLException {
	
		// 커넥션 생성 ***
		
//		Connection con = JDBCTemplate.getConnection(); // import static 구문을 사용하면 JDBCTemplate 사용하지 않아도 됨. 
		Connection con = getConnection();
		
		// DAO 메서드 호출하여 수행 후 결과 반환
		// -> Service에서 생성한 Connection 
		int result = dao.insert(con, vo1);
		
		
		
		// 트랜잭션 제어 ***
		if(result > 0) commit(con);
		else 		   rollback(con); // if) 내가 이전에 commit을 안한 상태에서 INSERT를 잘못하게 된다면, rollback 되는건가? 
		
		
		 
		// 커넥션 반환 ***
		close(con);
		
		// 호출된 곳에 결과 반환 
		return result;
		
	}
	
	
	
	
	/** 3행 삽입 서비스
	 * @param vo1
	 * @param vo2
	 * @param vo3
	 * @return result
	 * @throws SQLException
	 */
	public int insert(TestVO vo1, TestVO vo2, TestVO vo3) throws SQLException{
		
		// 1. Connection 생성 (무조건 1번)
		Connection con = getConnection();
		int result = 0; // insert 3회 모두 성공시 1, 아니면 0
		
		int res1 = dao.insert(con, vo1);
		int res2 = dao.insert(con, vo2);
		int res3 = dao.insert(con, vo3);
		
		if(res1 + res2 + res3 == 3) { // 모두 insert 성공한 경우
			commit(con);
			result = 1;
		}else {
			rollback(con);
		}
		close(con);
		
		return result;	
	}




	/** 제목, 내용 수정 Service
	 * @param vo
	 * @return
	 */
	public int update(TestVO vo) throws Exception {
		
		Connection con = getConnection();

		int result = dao.update(con,vo);
		
		if(result > 0 ) commit(con);
		else 			rollback(con);
		
		close(con);
		
		return result;
	}
	
	
	
	

}
