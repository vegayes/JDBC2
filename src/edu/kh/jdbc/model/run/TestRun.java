package edu.kh.jdbc.model.run;

import edu.kh.jdbc.model.service.TestService2;
import edu.kh.jdbc.model.vo.TestVO;

public class TestRun {
	
	public static void main(String[] args) {
		
		TestService2 service = new TestService2();
	
		TestVO vo1 = new TestVO(1,"제목1","내용1");
		
		try {

			int result = service.insert(vo1);
			
			if(result > 0 ) {
				System.out.println(result);
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}
	
	
	

}
