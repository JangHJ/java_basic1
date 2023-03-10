package 미니프로젝트;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Locale;

import 화면DB연결.MemberVO;

public class ReviewDAO { //CRUD

	//메서드를 만드는 것 ==> 메서드 정의(define)!
	//메서드를 정의했다고 실행되는 것은 아니다.!
	//메서드를 쓰는 것 ==> 메서드 호출(call)!
	public ArrayList<ReviewVO> list(String id) {
		ResultSet rs = null; //항목명 + 결과 데이터를 담고 있는 테이블 
		
		//가방 넣어줄 큰 컨테이너 필요
		//ArrayList<MemberVO> ==> MemberVO만 들어간 ArrayList라는 의미
		ArrayList<ReviewVO> list = new ArrayList<>();
		
		
		ReviewVO bag = null;
		//기본형 정수/실수/문자/논리만 값으로 초기화
		//나머지 데이터형(참조형) 주소가! 들어가 있음.
		//참조형 변수를 초기화할 때는 null(주소가 없다라는 의미)
		try {
			// 1.오라클 11g와 연결한 부품 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
			Locale.setDefault(Locale.US); //맥 locale에러나신 분들만!!!
			
			// 2.오라클 11g에 연결해보자.(java --- oracle) 
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); //Connection
			//String data = JOptionPane.showInputDialog("이름입력"); //String, 임아무개 
			System.out.println("2. 오라클 연결 성공.");
			
			//ipaddress ==> InetAddress
			//String url = "http://wwww.naver.com";
			//URL u = new URL(url);
			//자바는 부품조립식이여서,String에 넣은 문자열을 특정한 부품으로 인식하지 못함.
			//특정한 부품으로 인식하려면 그 부품으로 만들어주어야 한다.
			//SQL부품으로 만들어주어야 함.
			//PreparedStatement가 SQL부품!!
			
			String sql = "select pm.STORE, pr.RATING, pm.NAME, pr.CONTENT from hr.P_REVIEW pr, hr.P_ORDER po, hr.P_MENU pm where pr.ORDER_NO = po.NO AND po.MENU_NO = pm.NO AND po.ID = ?";
			PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
			ps.setString(1, id);
			System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");
			
			rs = ps.executeQuery(); //select문 전송시  
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			while(rs.next()) { //검색결과가 있는지 여부는 rs.next() 
				//true이면 있다라는 의미, false이면 없다라는 의미 
				//검색결과가 있으면
				System.out.println("검색결과 있음. 성공.");
				//각 컬럼에서 값들을 꺼내오자
				String s_name = rs.getString(1); //s_name
				double rating = rs.getDouble(2); //rating
				String m_name = rs.getString(3); //m_name
				String content = rs.getString(4); //content
				
				//검색결과를 검색하면 UI부분을 줘야함.
				//가방을 만들자
				bag = new ReviewVO();
				
				bag.setSname(s_name);
				bag.setRating(rating);
				bag.setMname(m_name);
				bag.setContent(content);
				
				//list에 bag을 추가해준다
				list.add(bag);
			}
			//System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return id, pw, name, tel은 XXXXX
		//return 뒤에는 반드시 여러 데이터를 묶어서 리턴해주어야함.
		//검색결과가 있을 때는 bag에 데이터가 들어있음.
		//검색결과가 없을 때는 bag에 무엇이 들어있을까? -> null if문 실행이 안되었기 때문에
		return list;
		
	}
	public ReviewVO one(String id) {
		ResultSet rs = null; //항목명 + 결과 데이터를 담고 있는 테이블 
		ReviewVO bag = null;
		//기본형 정수/실수/문자/논리만 값으로 초기화
		//나머지 데이터형(참조형) 주소가! 들어가 있음.
		//참조형 변수를 초기화할 때는 null(주소가 없다라는 의미)
		try {
			// 1.오라클 11g와 연결한 부품 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
			Locale.setDefault(Locale.US); //맥 locale에러나신 분들만!!!
			
			// 2.오라클 11g에 연결해보자.(java --- oracle) 
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); //Connection
			//String data = JOptionPane.showInputDialog("이름입력"); //String, 임아무개 
			System.out.println("2. 오라클 연결 성공.");
			
			//ipaddress ==> InetAddress
			//String url = "http://wwww.naver.com";
			//URL u = new URL(url);
			//자바는 부품조립식이여서,String에 넣은 문자열을 특정한 부품으로 인식하지 못함.
			//특정한 부품으로 인식하려면 그 부품으로 만들어주어야 한다.
			//SQL부품으로 만들어주어야 함.
			//PreparedStatement가 SQL부품!!
			
			String sql = "select pm.STORE, pr.RATING, pm.NAME, pr.CONTENT from hr.P_REVIEW pr, hr.P_ORDER po, hr.P_MENU pm where pr.ORDER_NO = po.NO AND po.MENU_NO = pm.NO AND po.ID = ?";
//			String sql = "select pm.STORE, pr.RATING, pm.NAME, pr.CONTENT from hr.P_REVIEW pr, hr.P_MENU pm where order_no = ?";
//			String sql = "select * from hr.P_REVIEW pr, hr.P_ORDER po where id = ?";
//			String sql = "SELECT pr.RATING, po.ID, pr.CONTENT FROM P_REVIEW pr, P_ORDER po WHERE pr.ORDER_NO = po.NO AND po.ID = ? ";
//			String sql = "select ps.NAME, pr.RATING, pm.NAME, pr.CONTENT "
//					+ "from P_REVIEW pr, P_ORDER po, P_MENU pm, P_STORE ps "
//					+ "where pr.ORDER_NO = po.NO AND po.MENU_NO = pm.NO AND pm.STORE = ps.NAME AND po.id= ? ";
			
			PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
			ps.setString(1, id);
			System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");
			
			rs = ps.executeQuery(); //select문 전송시  
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			if(rs.next()) { //검색결과가 있는지 여부는 rs.next() 
				//true이면 있다라는 의미, false이면 없다라는 의미 
				System.out.println("검색결과 있음. 성공.");
				String s_name = rs.getString(1); //order_no
				double rating = rs.getDouble(2); //rating
				String m_name = rs.getString(3); //content
				String content = rs.getString(4);
				
				System.out.println(s_name + " "
								+ rating + " "
								+ m_name + " "
								+ content);
				//검색결과를 검색하면 UI부분을 줘야함.
				bag = new ReviewVO();
				
				bag.setSname(s_name);
				bag.setRating(rating);
				bag.setMname(m_name);
				bag.setContent(content);
				
				bag.toString();
			}
			else {
				System.out.println("검색결과 없음.");
			}
			//System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return id, pw, name, tel은 XXXXX
		//return 뒤에는 반드시 여러 데이터를 묶어서 리턴해주어야함.
		//검색결과가 있을 때는 bag에 데이터가 들어있음.
		//검색결과가 없을 때는 bag에 무엇이 들어있을까? -> null if문 실행이 안되었기 때문에
		return bag;
	}
	public int delete(int order_no) {
		int result = 0;
		try {
			// 1.오라클 11g와 연결한 부품 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
			Locale.setDefault(Locale.US); //맥 locale에러나신 분들만!!!
			
			// 2.오라클 11g에 연결해보자.(java --- oracle) 
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); //Connection
			//String data = JOptionPane.showInputDialog("이름입력"); //String, 임아무개 
			System.out.println("2. 오라클 연결 성공.");
			
			//ipaddress ==> InetAddress
			//String url = "http://wwww.naver.com";
			//URL u = new URL(url);
			//자바는 부품조립식이여서,String에 넣은 문자열을 특정한 부품으로 인식하지 못함.
			//특정한 부품으로 인식하려면 그 부품으로 만들어주어야 한다.
			//SQL부품으로 만들어주어야 함.
			//PreparedStatement가 SQL부품!!
			
			String sql = "delete from hr.P_REVIEW po where order_no = ?";
			PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
			ps.setInt(1, order_no);
			System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");
			
			result = ps.executeUpdate(); 
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			
			//System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	public int update(int order_no, String content) {
		int result = 0;
		try {
			// 1.오라클 11g와 연결한 부품 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
			Locale.setDefault(Locale.US); //맥 locale에러나신 분들만!!!
			
			// 2.오라클 11g에 연결해보자.(java --- oracle) 
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); //Connection
			//String data = JOptionPane.showInputDialog("이름입력"); //String, 임아무개 
			System.out.println("2. 오라클 연결 성공.");
			
			//ipaddress ==> InetAddress
			//String url = "http://wwww.naver.com";
			//URL u = new URL(url);
			//자바는 부품조립식이여서,String에 넣은 문자열을 특정한 부품으로 인식하지 못함.
			//특정한 부품으로 인식하려면 그 부품으로 만들어주어야 한다.
			//SQL부품으로 만들어주어야 함.
			//PreparedStatement가 SQL부품!!
			
			String sql = "update hr.P_REVIEW set content = ? where order_no = ? ";
			PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
			ps.setString(1, content);
			ps.setInt(2, order_no);
			System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");
			
			result = ps.executeUpdate(); 
			//insert, update, delete문만!! sql문 실행결과가 int
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			
			//System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insert(int order_no, double rating, String content) {
		int result = 0;
		
		try {
			// 1.오라클 11g와 연결한 부품 설정
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. 오라클과 자바 연결할 부품 설정 성공.");
			Locale.setDefault(Locale.US); //맥 locale에러나신 분들만!!!
			
			// 2.오라클 11g에 연결해보자.(java --- oracle) 
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String user = "system";
			String password = "oracle";
			Connection con = DriverManager.getConnection(url, user, password); //Connection
			//String data = JOptionPane.showInputDialog("이름입력"); //String, 임아무개 
			System.out.println("2. 오라클 연결 성공.");
			
			//ipaddress ==> InetAddress
			//String url = "http://wwww.naver.com";
			//URL u = new URL(url);
			//자바는 부품조립식이여서,String에 넣은 문자열을 특정한 부품으로 인식하지 못함.
			//특정한 부품으로 인식하려면 그 부품으로 만들어주어야 한다.
			//SQL부품으로 만들어주어야 함.
			//PreparedStatement가 SQL부품!!
			
			//public void insert(String id, String pw, String name, String tel) 
			String sql = "insert into hr.P_REVIEW values (?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql); //PreparedStatement
			//con부품으로 sql스트링에 있는 것 SQL부품으로 만들어주세요.
			//R빼고, 인덱스 0부터 시작!!
			//유일하게 db은 인덱스가 1부터 시작!!
			ps.setInt(1, order_no); 
			ps.setDouble(2, rating); 
			ps.setString(3, content); 
			//==> insert into hr.MEMBER values ('a','a','a','a');
			System.out.println("3. SQL문 부품(객체)으로 만들어주기 성공.");
			
			result = ps.executeUpdate(); //1
			System.out.println("4. SQL문 오라클로 보내기 성공.");
			if(result == 1) {
				System.out.println("리뷰입력 성공!");
			}
			//System.out.println(result);
		} catch (Exception e) {
			//insert가 제대로 안된 경우, 위험한 상황이라고 판단하고
			//catch가 실행
			//실행된 Row수가 없으므로 Result에 0을 넣어주자.!
			//result = 0;
			e.printStackTrace();
		}
		
		System.out.println(result);
		return result;
		
	}
}