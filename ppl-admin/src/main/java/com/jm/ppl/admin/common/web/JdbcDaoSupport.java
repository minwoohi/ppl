package com.jm.ppl.admin.common.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class JdbcDaoSupport {

	public Object selectOne() { //구체화되있는부분

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String url = "jdbc:oracle:thin:@192.168.201.23:1521:XE";

		try {
			conn = DriverManager.getConnection(url, "PPL", "ppl");

			//TODO 쿼리 만들기
			/*	StringBuffer query = new StringBuffer();
				query.append(
					"  SELECT  'AT-' || TO_CHAR( SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(ATHRZTN_ID_SEQ.NEXTVAL, 6, '0') SEQ");
			query.append("  FROM    DUAL ");*/
			String query = query();

			stmt = conn.prepareStatement(query);
			// TODO 파라미터 필요
			mappingParams(stmt);

			rs = stmt.executeQuery();

			if (rs.next()) {
				//TODO VO 만들기
				/*return rs.getString("SEQ");*/// return rs.getString(1);
				return bindData(rs);
			}
			return null;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e1) {
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
			}
		}

	}

	
	public abstract String query();

	public abstract void mappingParams(PreparedStatement stmt) throws SQLException;
	
	public abstract Object bindData(ResultSet rs) throws SQLException;
	
	
	
	
	
	
}





 











