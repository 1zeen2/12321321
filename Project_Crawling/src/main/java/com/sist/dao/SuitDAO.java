package com.sist.dao;

import java.util.*;

import com.sist.vo.*;

import java.sql.*;

public class SuitDAO {
	private Connection conn;
	private PreparedStatement ps;
	private static SuitDAO dao;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";

	// 드라이버 등록
	public SuitDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {

		}
	}

	// 연결
	public void getConnection() {
		try {
			conn = DriverManager.getConnection(URL, "hr", "happy");
		} catch (Exception ex) {

		}
	}

	// 해제
	public void disConnection() {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();

		} catch (Exception ex) {
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
	 * 이름 널? 유형 ----------------------------------------- --------
	 * ---------------------------- su_NO NOT NULL NUMBER su_IMAGE NOT NULL
	 * VARCHAR2(4000) su_SUBJECT NOT NULL VARCHAR2(4000) su_PRICE NOT NULL
	 * NUMBER(10) su_CONTENT CLOB su_DELIVERY VARCHAR2(4000) su_RETURN_EXCHANGE NOT
	 * NULL VARCHAR2(4000) su_DETAIL_IMAGE CLOB
	 */
	public void suitInsert(SuitVO vo) {
		try {
			getConnection();
			String sql = "INSERT INTO suit(su_no, su_image, su_subject, su_content, su_delivery, su_return_exchange, su_detail_image, su_price)"
					+ "VALUES(fh_fno_seq.nextval,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, vo.getSu_no());
			ps.setString(2, "https:" + vo.getSu_image());
			ps.setString(3, vo.getSu_subject_());
			ps.setString(4, vo.getSu_content());
			ps.setString(5, vo.getSu_delivery());
			ps.setString(6, vo.getSu_return_exchange());
			ps.setString(7, vo.getSu_detail_image());
			ps.setString(8, vo.getSu_price());

			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			disConnection();
		}
	}

}
