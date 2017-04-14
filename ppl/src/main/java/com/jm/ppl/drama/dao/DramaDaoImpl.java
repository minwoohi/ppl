package com.jm.ppl.drama.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jm.ppl.common.dao.JdbcDaoSupport;
import com.jm.ppl.drama.vo.DramaSearchVO;
import com.jm.ppl.drama.vo.DramaVO;

public class DramaDaoImpl implements DramaDao {

	@Override
	public int getAllArticlesCount(DramaSearchVO dramaSearch) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.201.23:1521:XE", "PPL", "ppl");
			
			StringBuffer query = new StringBuffer();
			
			query.append(" SELECT	COUNT(1) CNT ");
			query.append(" FROM		DRM ");
			
			stmt = conn.prepareStatement(query.toString());
			
			rs = stmt.executeQuery();
			
			if(rs.next()){
				return rs.getInt("CNT");
			} else {
				return 0;
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {	}
			}
			if(stmt != null){
				try {
					stmt.close();
				} catch (SQLException e) {	}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {	}
			}
		}
		
		/*JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(DRM_ID) CNT ");
				query.append(" FROM		DRM ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				
			}
			
			@Override
			public Object bindData(ResultSet rs) throws SQLException {
				return rs.getInt("CNT");
			}
		};
		
		return (int) dao.selectOne();*/
	}
	
	@Override
	public List<DramaVO> selectAllDramas(DramaSearchVO dramaSearch) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.201.23:1521:XE", "PPL", "ppl");
			StringBuffer query = new StringBuffer();
			
			query.append(" SELECT	*                                          ");
			query.append(" FROM	(                                              ");
			query.append(" 			SELECT	ROWNUM RNUM                        ");
			query.append(" 					, RST.*                            ");
			query.append(" 					FROM	(                          ");
			query.append(" 								SELECT	DRM_ID         ");
			query.append(" 										, DRM_TTL      ");
			query.append(" 										, DRM_PRD      ");
			query.append(" 										, DRM_WRT      ");
			query.append(" 										, DRM_SCTM     ");
			query.append(" 										, DRM_GNR      ");
			query.append(" 										, DRM_ACTR     ");
			query.append(" 										, DRM_PST      ");
			query.append(" 										, DRM_LK_CNT   ");
			query.append(" 								FROM	DRM            ");
			query.append(" 								ORDER	BY DRM_ID DESC ");
			query.append(" 							) RST                      ");
			query.append(" 			WHERE	ROWNUM <= ?                        ");
			query.append(" 		)                                              ");
			query.append(" WHERE RNUM >= ?                                     ");
			
			stmt = conn.prepareStatement(query.toString());
			
			stmt.setInt(1, dramaSearch.getPager().getEndArticleNumber());
			stmt.setInt(2, dramaSearch.getPager().getStartArticleNumber());
			
			rs = stmt.executeQuery();
			
			List<DramaVO> dramaList = new ArrayList<DramaVO>();
			DramaVO drama = null;
			
			while(rs.next()){
				drama = new DramaVO();
				
				drama.setDramaId(rs.getString("DRM_ID"));
				drama.setDramaTitle(rs.getString("DRM_TTL"));
				drama.setDramaProducer(rs.getString("DRM_PRD"));
				drama.setDramaWriter(rs.getString("DRM_WRT"));
				drama.setDramaScreeningTime(rs.getString("DRM_SCTM"));
				drama.setDramaGenre(rs.getString("DRM_GNR"));
				drama.setDramaActor(rs.getString("DRM_ACTR"));
				drama.setDramaPost(rs.getString("DRM_PST"));
				drama.setDramaLikeCount(rs.getInt("DRM_LK_CNT"));
				
				dramaList.add(drama);
			}
			
			return dramaList;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage() ,e);
		} finally {
			if(rs != null){
				try {
					rs.close();
				} catch (SQLException e) {}
			}
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
	}

	@Override
	public DramaVO selectOneDrama(String dramaId) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				
				query.append(" SELECT	DRM_ID         ");
				query.append(" 			, DRM_TTL      ");
				query.append(" 			, DRM_PRD      ");
				query.append(" 			, DRM_WRT      ");
				query.append(" 			, DRM_SCTM     ");
				query.append(" 			, DRM_GNR      ");
				query.append(" 			, DRM_ACTR     ");
				query.append(" 			, DRM_PST      ");
				query.append(" 			, DRM_LK_CNT      ");
				query.append(" FROM		DRM            ");
				query.append(" WHERE	DRM_ID = ? ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, dramaId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				DramaVO drama = new DramaVO();
				
				drama.setDramaId(rs.getString("DRM_ID"));
				drama.setDramaTitle(rs.getString("DRM_TTL"));
				drama.setDramaProducer(rs.getString("DRM_PRD"));
				drama.setDramaWriter(rs.getString("DRM_WRT"));
				drama.setDramaScreeningTime(rs.getString("DRM_SCTM"));
				drama.setDramaGenre(rs.getString("DRM_GNR"));
				drama.setDramaActor(rs.getString("DRM_ACTR"));
				drama.setDramaPost(rs.getString("DRM_PST"));
				drama.setDramaLikeCount(rs.getInt("DRM_LK_CNT"));
				return drama;
			}
		};
		return (DramaVO) dao.selectOne();
	}

	@Override
	public int insertOneDrama(DramaVO drama) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				
				query.append(" INSERT	INTO	DRM (                                                                                        ");
				query.append(" 						DRM_ID                                                                                   ");
				query.append(" 						, DRM_TTL                                                                                ");
				query.append(" 						, DRM_PRD                                                                                ");
				query.append(" 						, DRM_WRT                                                                                ");
				query.append(" 						, DRM_SCTM                                                                               ");
				query.append(" 						, DRM_GNR                                                                                ");
				query.append(" 						, DRM_ACTR                                                                               ");
				query.append(" 						, DRM_PST                                                                                ");
				query.append(" 						, DRM_LK_CNT                                                                                 ");
				query.append(" 						)                                                                                            ");
				query.append(" 				VALUES (                                                                                         ");
				query.append(" 							'DR-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(DRM_ID_SEQ.NEXTVAL, 6, '0') ");
				query.append(" 							, ?                                                                                  ");
				query.append(" 							, ?                                                                                  ");
				query.append(" 							, ?                                                                                  ");
				query.append(" 							, ?                                                                                  ");
				query.append(" 							, ?                                                                                  ");
				query.append(" 							, ?                                                                                  ");
				query.append(" 							, ?                                                                                  ");
				query.append(" 							, 0		                                                                             ");
				query.append(" 						)                                                                                        ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, drama.getDramaTitle());
				stmt.setString(2, drama.getDramaProducer());
				stmt.setString(3, drama.getDramaWriter());
				stmt.setString(4, drama.getDramaScreeningTime());
				stmt.setString(5, drama.getDramaGenre());
				stmt.setString(6, drama.getDramaActor());
				stmt.setString(7, drama.getDramaPost());
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int updateOneDrama(DramaVO drama) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				
				query.append(" UPDATE	DRM              ");
				query.append(" SET		DRM_TTL = ?      ");
				query.append(" 			, DRM_PRD   = ?  ");
				query.append(" 			, DRM_WRT   = ?  ");
				query.append(" 			, DRM_SCTM  = ?  ");
				query.append(" 			, DRM_GNR   = ?  ");
				query.append(" 			, DRM_ACTR  = ?  ");
				query.append(" 			, DRM_PST   = ?  ");
				query.append("			, DRM_LK_CNT = ? ");
				query.append(" WHERE	DRM_ID = ?       ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, drama.getDramaTitle());
				stmt.setString(2, drama.getDramaProducer());
				stmt.setString(3, drama.getDramaWriter());
				stmt.setString(4, drama.getDramaScreeningTime());
				stmt.setString(5, drama.getDramaGenre());
				stmt.setString(6, drama.getDramaActor());
				stmt.setString(7, drama.getDramaPost());
				stmt.setInt(8, drama.getDramaLikeCount());
				stmt.setString(9, drama.getDramaId());
				
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int deleteOneDrama(String dramaId) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				
				query.append(" DELETE              ");
				query.append(" FROM	DRM            ");
				query.append(" WHERE	DRM_ID = ? ");
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, dramaId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int setLike(int likeCount, String dramaId) {
			JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	DRM ");
				query.append(" SET		DRM_LK_CNT = ? ");
				query.append(" WHERE	DRM_ID = ? ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, likeCount);
				stmt.setString(2, dramaId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();		
	}

}
