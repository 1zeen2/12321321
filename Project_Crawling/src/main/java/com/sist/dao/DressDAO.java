package com.sist.dao;

import java.sql.*;
import com.sist.vo.DressVO;

public class DressDAO {
   private Connection conn;
   private PreparedStatement ps;
   private static DressDAO dao;
   private final String URL = "jdbc:oracle:thin:@211.238.142.124:1521:XE";
   
   // 드라이버 등록
   public DressDAO() {
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
   public static DressDAO newInstance() {
      if (dao == null)
         dao = new DressDAO();
      return dao;
   }

   // 기능
   /*
    이름                                      널?      유형
    ----------------------------------------- -------- ----------------------------
    D_NO                                      NOT NULL NUMBER
    D_IMAGE                                   NOT NULL VARCHAR2(4000)
    D_SUBJECT                                 NOT NULL VARCHAR2(4000)
    D_PRICE                                   NOT NULL NUMBER(10)
    D_CONTENT                                          CLOB
    D_DELIVERY                                         VARCHAR2(4000)
    D_RETURN_EXCHANGE                         NOT NULL VARCHAR2(4000)
    D_DETAIL_IMAGE                                     CLOB
    */
   public void dressInsert(DressVO vo) {
      try {
         getConnection();
         String sql = "INSERT INTO dress(d_no, d_image, d_subject, d_content, d_delivery, d_return_exchange, d_detail_image, d_price) "
                    + "VALUES(dress_dno_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
         ps = conn.prepareStatement(sql);

         // 각 자리 표시자에 값 설정
         ps.setString(1, vo.getD_image()); // d_image
         ps.setString(2, vo.getD_subject());          // d_subject
         ps.setString(3, vo.getD_content());          // d_content
         ps.setString(4, vo.getD_delivery());         // d_delivery
         ps.setString(5, vo.getD_return_exchange());  // d_return_exchange
         ps.setString(6, vo.getD_detail_image());     // d_detail_image
         ps.setString(7, vo.getD_price());            // d_price

         // 쿼리 실행
         ps.executeUpdate();
      } catch (Exception ex) {
         ex.printStackTrace();
      } finally {
         disConnection();
      }
   }
}
