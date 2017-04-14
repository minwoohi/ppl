package com.jm.ppl.admin.likecount.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jm.ppl.admin.likecount.vo.ActorVO;
import com.jm.ppl.admin.likecount.vo.DramaVO;
import com.jm.ppl.admin.likecount.vo.ItemVO;
import com.jm.ppl.admin.likecount.vo.MovieVO;

public class LikeCountDaoimpl implements LikeCountDao {

	@Override
	public List<ActorVO> selectActorLikeCount() {

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

			StringBuffer query = new StringBuffer();

			query.append("							SELECT	ACT_ID ");
			query.append("									, ACT_NM ");
			query.append("									, ACT_SX ");
			query.append("									, ACT_BRH ");
			query.append("									, ACT_HGHT ");
			query.append("									, ACT_WGHT ");
			query.append("									, ACT_ENT ");
			query.append("									, ACT_LK_CNT ");
			query.append("									, ACT_PST ");
			query.append("							FROM	ACT ");
			query.append("							ORDER	BY ACT_LK_CNT DESC");

			stmt=conn.prepareStatement(query.toString());
			
			rs = stmt.executeQuery();
			
			
			List<ActorVO> actors = new ArrayList<ActorVO>();
			ActorVO actorVO = null;
			while (rs.next()){
				actorVO = new ActorVO();
				actorVO.setActorId(rs.getString("ACT_ID"));
				actorVO.setActorName(rs.getString("ACT_NM"));
				actorVO.setActorSex(rs.getString("ACT_SX"));
				actorVO.setActorBirth(rs.getString("ACT_BRH"));
				actorVO.setActorHeight(rs.getString("ACT_HGHT"));
				actorVO.setActorWeight(rs.getString("ACT_WGHT"));
				actorVO.setActorEntertainment(rs.getString("ACT_ENT"));
				actorVO.setActorLikeCount(rs.getInt("ACT_LK_CNT"));
				actorVO.setActorPost(rs.getString("ACT_PST"));
				actors.add(actorVO);
				
			}

			return actors;

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

	@Override
	public List<DramaVO> selectDramaLikeCount() {
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
			
			query.append("							ORDER	BY DRM_LK_CNT  DESC");

			stmt=conn.prepareStatement(query.toString());
			
			rs = stmt.executeQuery();
			
			
			List<DramaVO> dramas = new ArrayList<DramaVO>();
			DramaVO drama = null;
			while (rs.next()){
				drama = new DramaVO();
				
				drama.setDramaId(rs.getString("DRM_ID"));
				drama.setDramaTitle(rs.getString("DRM_TTL"));
				drama.setDramaProducer(rs.getString("DRM_PRD"));
				drama.setDramaScreeningTime(rs.getString("DRM_SCTM"));
				drama.setDramaGenre(rs.getString("DRM_GNR"));
				drama.setDramaActor(rs.getString("DRM_ACTR"));
				drama.setDramaPost(rs.getString("DRM_PST"));
				drama.setDramaLikeCount(rs.getInt("DRM_LK_CNT"));
				
				dramas.add(drama);
			}

			return dramas;

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

	@Override
	public List<MovieVO> selectMovieLikeCount() {


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

			StringBuffer query = new StringBuffer();

			query.append(" 	SELECT		MV_ID ");
			query.append("				, MV_TTL ");
			query.append(" 				, MV_SBTTL ");
			query.append(" 				, MV_DRTR ");
			query.append(" 				, MV_ACTR ");
			query.append(" 				, MV_PST ");
			query.append(" 				, MV_INTR ");
			query.append(" 				, MV_LK_CNT ");
			query.append(" 				, MV_GNR ");
			query.append(" 	FROM		MV ");
			query.append("	ORDER	BY MV_LK_CNT  DESC");

			stmt=conn.prepareStatement(query.toString());
			
			rs = stmt.executeQuery();
			
			
			List<MovieVO> movies = new ArrayList<MovieVO>();
			MovieVO movieVO = null;
			while (rs.next()){
				movieVO = new MovieVO();
				movieVO.setMovieId(rs.getString("MV_ID"));
				movieVO.setMovieTitle(rs.getString("MV_TTL"));
				movieVO.setMovieSubtitle(rs.getString("MV_SBTTL"));
				movieVO.setMovieDirector(rs.getString("MV_DRTR"));
				movieVO.setMovieActor(rs.getString("MV_ACTR"));
				movieVO.setMoviePost(rs.getString("MV_PST"));
				movieVO.setMovieIntro(rs.getString("MV_INTR"));
				movieVO.setMovieLikeCount(rs.getInt("MV_LK_CNT"));
				movieVO.setMovieGenre(rs.getString("MV_GNR"));
				
				movies.add(movieVO);
				
			}

			return movies;

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

	@Override
	public List<ItemVO> selectItemLikeCount() {

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

			StringBuffer query = new StringBuffer();

			query.append(" SELECT	ITM_ID ");
			query.append(" 			, ITM_NM ");
			query.append(" 			, ITM_BRND ");
			query.append(" 			, ITM_PDCD ");
			query.append("			, ITM_PRC ");
			query.append("			, ITM_LK_CNT ");
			query.append("			, ITM_PST ");
			query.append("			, MV_ID ");
			query.append("			, DRM_ID ");
			query.append("			, ACT_ID ");
			query.append(" FROM		ITM ");
			query.append("	ORDER	BY ITM_LK_CNT  DESC");

			stmt=conn.prepareStatement(query.toString());
			
			rs = stmt.executeQuery();
			
			
			List<ItemVO> items = new ArrayList<ItemVO>();
			ItemVO itemVO = null;
			while (rs.next()){
				itemVO = new ItemVO();
				itemVO.setItemId(rs.getString("ITM_ID"));
				itemVO.setItemName(rs.getString("ITM_NM"));
				itemVO.setItemBrand(rs.getString("ITM_BRND"));
				itemVO.setItemProductCode(rs.getString("ITM_PDCD"));
				itemVO.setItemPrice(rs.getInt("ITM_PRC"));
				itemVO.setItemLikeCount(rs.getInt("ITM_LK_CNT"));
				itemVO.setItemPost(rs.getString("ITM_PST"));
				itemVO.setMovieId(rs.getString("MV_ID"));
				itemVO.setDramaId(rs.getString("DRM_ID"));
				itemVO.setActorId(rs.getString("ACT_ID"));
				
				items.add(itemVO);
				
			}

			return items;

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

}
