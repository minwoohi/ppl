package com.jm.ppl.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jm.ppl.user.vo.UserVO;


public class UserDaoImpl implements UserDao {
	
	private final String URL = "jdbc:oracle:thin:@192.168.201.23:1521:XE";
	private final String USER = "PPL";
	private final String PASSWORD = "ppl";
	
	private void loadOracleDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	private void close(Connection conn, PreparedStatement stmt) {
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {}
		}
	}
	
	@Override
	public int insertNewUser(UserVO user) {
		loadOracleDriver();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuffer query = new StringBuffer();
			
			query.append(" INSERT	INTO	USR (         ");
			query.append(" 					USR_ID    ");
			query.append(" 					, USR_NM  ");
			query.append(" 					, USR_PW ");
			query.append("					, ATHRZTN_ID	");
			query.append("					, RGST_DT	");
			query.append(" 				)             ");
			query.append(" VALUES			(         ");
			query.append(" 					?         ");
			query.append(" 					, ?       ");
			query.append(" 					, ?       ");
			query.append("					, 'AT-2017032717-000003' ");
			query.append("					,	TO_CHAR(SYSDATE,'YYYY-MM-DD') ");
			query.append(" 				)             ");
			
			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, user.getUserId());
			stmt.setString(2, user.getUserName());
			stmt.setString(3, user.getUserPassword());
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally{
			close(conn, stmt);
		}
	}
	

	@Override
	public UserVO selectOneUser(UserVO user) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuffer query = new StringBuffer();
			
			query.append(" SELECT	U.USR_ID      ");
			query.append(" 			, U.USR_NM    ");
			query.append(" 			, U.USR_PW   ");
			query.append(" 			, U.ATHRZTN_ID U_ATHRZTN_ID   ");
			query.append(" 			, A.ATHRZTN_ID   ");
			query.append(" 			, A.ATHRZTN_NM   ");
			query.append(" 			, A.PRNT_ATHRZTN_ID   ");
			query.append(" FROM		USR U         ");
			query.append(" 			, ATHRZTN A         ");
			query.append(" WHERE	U.ATHRZTN_ID = A.ATHRZTN_ID   ");
			query.append(" AND		U.USR_ID = ?  ");
			query.append(" AND		U.USR_PW = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, user.getUserId());
			stmt.setString(2, user.getUserPassword());
			
			rs = stmt.executeQuery();
			
			UserVO loginUserVO = null;
			
			if(rs.next()){
				loginUserVO = new UserVO();
				
				loginUserVO.setUserId(rs.getString("USR_ID"));
				loginUserVO.setUserName(rs.getString("USR_NM"));
				loginUserVO.setUserPassword(rs.getString("USR_PW"));
				loginUserVO.setAuthorizationId(rs.getString("U_ATHRZTN_ID"));
				
				loginUserVO.getAuthorization().setAuthorizationId(rs.getString("ATHRZTN_ID"));
				loginUserVO.getAuthorization().setAuthorizationName(rs.getString("ATHRZTN_NM"));
				loginUserVO.getAuthorization().setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));
				
			}
			
			return loginUserVO;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally{
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {	}
			}
			close(conn, stmt);
		}
	}


	@Override
	public int selectCountByUserId(String userId) {
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			StringBuffer query = new StringBuffer();
			
			query.append(" SELECT	COUNT(1) CNT ");
			query.append(" FROM		USR            ");
			query.append(" WHERE	USR_ID = ? ");
			
			stmt = conn.prepareStatement(query.toString());
			
			stmt.setString(1, userId);
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				return rs.getInt("CNT");
			}
			return 0;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {	}
			}
			close(conn, stmt);
		}
	}

}
