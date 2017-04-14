package com.jm.ppl.common.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class JdbcDaoSupport {

	public Object selectList() {		
		
			loadOracleDriver();
		
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String url = "jdbc:oracle:thin:@192.168.201.23:1521:XE";
			try {
				
				conn = DriverManager.getConnection(url, "PPL", "ppl");
				String query = query();
				stmt = conn.prepareStatement(query);
				
				mappingParams(stmt);
				rs = stmt.executeQuery();
				
				List result = new ArrayList();
				while(rs.next()) {
					result.add(getData(rs));
				}
				return result;
				
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			} finally {
				close(conn, stmt, rs);
			}
			
		
	}
	
	public Object selectOne() {
		
		loadOracleDriver();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@192.168.201.23:1521:XE";
		try {
			
			conn = DriverManager.getConnection(url, "PPL", "ppl");
			String query = query();
			stmt = conn.prepareStatement(query);
			
			mappingParams(stmt);
			rs = stmt.executeQuery();
			
			Object result = null;
			if(rs.next()) {
				result = getData(rs);
			}
			return result;
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			close(conn, stmt, rs);
		}
		
	}
	public int update() {
		
		loadOracleDriver();
		//System.out.println("dao");
		Connection conn = null;
		PreparedStatement stmt = null;
		
		String url = "jdbc:oracle:thin:@192.168.201.23:1521:XE";
		try {
			conn = DriverManager.getConnection(url, "PPL", "ppl");
			String query = query();
			stmt = conn.prepareStatement(query);
			mappingParams(stmt);
			return stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			close(conn, stmt, null);
		}

	}
	

	public void close(Connection conn, PreparedStatement stmt, ResultSet rs) {
		if ( conn != null ) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
		if ( stmt != null ) {
			try {
				stmt.close();
			} catch (SQLException e) {}
		}
		if ( rs != null ) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}
	}
	
	public void loadOracleDriver() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public abstract String query();
	
	public abstract void mappingParams(PreparedStatement stmt) throws SQLException;
	
	public abstract Object getData(ResultSet rs) throws SQLException;
	

}
