package com.sist.dao;

import java.sql.*;
import com.sist.vo.SuitVO;

public class SuitDAO {
   private Connection conn;
   private PreparedStatement ps;
   private static SuitDAO dao;
   private final String URL = "jdbc:oracle:thin:@211.238.142.124:1521:XE";
   
   // 드라이버 등록
   public SuitDAO() {
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   // 연결
   public void getConnection() {
      try {
         conn = DriverManager.getConnection(URL, "hr1", "happy");
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   // 해제
   public void disConnection() {
      try {
         if (ps != null) ps.close();
         if (conn != null) conn.close();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   // 싱글턴
   public static SuitDAO newInstance() {
      if (dao == null)
         dao = new SuitDAO();
      return dao;
   }

   // 기능
   /*
    이름                                      널?      유형
    ----------------------------------------- -------- ----------------------------
    su_NO                                      NOT NULL NUMBER
    su_IMAGE                                   NOT NULL VARCHAR2(4000)
    su_SUBJECT                                 NOT NULL VARCHAR2(4000)
    su_PRICE                                   NOT NULL VARCHAR2(4000)
    su_CONTENT                                          CLOB
    su_DELIVERY                                         VARCHAR2(4000)
    su_RETURN_EXCHANGE                         NOT NULL VARCHAR2(4000)
    su_DETAIL_IMAGE                                     CLOB
    */
   public void suitInsert(SuitVO vo) {
	   try {
	      getConnection();
	      String sql = "INSERT INTO suit(su_no, su_image, su_subject, su_content, su_delivery, su_return_exchange, su_detail_image, su_price) "
	                 + "VALUES(suit_su_no_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
	      ps = conn.prepareStatement(sql);

	      // 각 자리 표시자에 값 설정
	      ps.setString(1, vo.getSu_image());          // su_image
	      ps.setString(2, vo.getSu_subject());        // su_subject
	      ps.setString(3, vo.getSu_content());        // su_content
	      ps.setString(4, vo.getSu_delivery());       // su_delivery
	      ps.setString(5, vo.getSu_return_exchange()); // su_return_exchange
	      ps.setString(6, vo.getSu_detail_image());   // su_detail_image
	      ps.setString(7, vo.getSu_price());          // su_price

	      // 쿼리 실행
	      ps.executeUpdate();
	   } catch (Exception ex) {
	      ex.printStackTrace();
	   } finally {
	      disConnection();
	   }
	}
}
