package com.jm.ppl.comment.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jm.ppl.comment.vo.CommentSearchVO;
import com.jm.ppl.comment.vo.CommentVO;
import com.jm.ppl.common.dao.JdbcDaoSupport;
import com.jm.ppl.items.vo.ItemsVO;

public class CommentDaoImpl implements CommentDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CommentVO> selectAllComments(CommentSearchVO commentSearch) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {

			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	*                                               ");
				query.append(" FROM	(                                                   ");
				query.append(" 			SELECT	ROWNUM RNUM                             ");
				query.append(" 					, RST.*                                 ");
				query.append(" 					FROM	(                               ");
				query.append(" 								SELECT	C.CMNT_ID           ");
				query.append(" 										, C.ITM_ID C_ITM_ID ");
				query.append(" 										, C.USR_ID            ");
				query.append(" 										, C.CMNT            ");
				query.append(" 										, C.DT              ");
				query.append(" 										, I.ITM_ID I_ITM_ID ");
				query.append(" 										, I.ITM_NM          ");
				query.append(" 										, I.ITM_BRND        ");
				query.append(" 										, I.ITM_PDCD        ");
				query.append(" 										, I.ITM_PRC         ");
				query.append(" 										, I.ITM_LK_CNT      ");
				query.append(" 										, I.ITM_PST         ");
				query.append(" 										, I.MV_ID           ");
				query.append(" 										, I.DRM_ID          ");
				query.append(" 										, I.ACT_ID          ");
				query.append(" 								FROM	CMNT C              ");
				query.append(" 										, ITM I             ");
				query.append(" 								WHERE	I.ITM_ID = C.ITM_ID ");
				query.append(" 								AND		I.ITM_ID = ?        ");
				query.append(" 								ORDER	BY	DT DESC         ");
				query.append(" 							) RST                           ");
				query.append(" 			WHERE	ROWNUM <= ?                             ");
				query.append(" 		)                                                   ");
				query.append(" WHERE RNUM >= ?                                          ");
				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, commentSearch.getItemId());
				stmt.setInt(2, commentSearch.getPager().getEndArticleNumber());
				stmt.setInt(3, commentSearch.getPager().getStartArticleNumber());
				
			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				CommentVO comment = new CommentVO();
				
				comment.setCommentId(rs.getString("CMNT_ID"));
				comment.setItemId(rs.getString("C_ITM_ID"));
				comment.setUserId(rs.getString("USR_ID"));

				String content = rs.getString("CMNT");
				content = content.replaceAll("<br/>", "\n");
				System.out.println("content : " + content);
				comment.setComment(content);
				
				String date = rs.getString("DT");
				date = date.substring(0, 16);
				comment.setDate(date);
				
				ItemsVO item = comment.getItem();
				item.setItemId(rs.getString("I_ITM_ID"));
				item.setItemName(rs.getString("ITM_NM"));
				item.setItemBrand(rs.getString("ITM_BRND"));
				item.setItemProductCode(rs.getString("ITM_PDCD"));
				item.setItemPrice(rs.getInt("ITM_PRC"));
				item.setItemLikeCount(rs.getInt("ITM_LK_CNT"));
				item.setItemPost(rs.getString("ITM_PST"));
				item.setMovieId(rs.getString("MV_ID"));
				item.setActorId(rs.getString("ACT_ID"));
				item.setDramaId(rs.getString("DRM_ID"));
				
				return comment;
			}
		};
		return (List<CommentVO>) dao.selectList();
	}

	@Override
	public CommentVO selectOneComment(String commentId) {
			JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				
				query.append(" SELECT	CMNT_ID    ");
				query.append(" 			, USR_ID       ");
				query.append(" 			, ITM_ID       ");
				query.append(" 			, CMNT         ");
				query.append(" 			, DT           ");
				query.append(" FROM		CMNT           ");
				query.append(" WHERE	CMNT_ID = ? ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, commentId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				CommentVO comment = new CommentVO();
				
				comment.setCommentId(rs.getString("CMNT_ID"));
				comment.setItemId(rs.getString("ITM_ID"));
				comment.setUserId(rs.getString("USR_ID"));
				comment.setComment(rs.getString("CMNT"));
				comment.setDate(rs.getString("DT"));
				return comment;
			}
		};
		return  (CommentVO) dao.selectOne();
	}

	@Override
	public int insertOneComment(CommentVO comment) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				
				query.append(" INSERT	INTO	CMNT ( ");
				query.append(" 			CMNT_ID        ");
				query.append(" 			, USR_ID        ");
				query.append(" 			, ITM_ID       ");
				query.append(" 			, CMNT         ");
				query.append(" 			, DT           ");
				query.append(" 			)           ");
				query.append(" VALUES (                                                                                         ");
				query.append(" 'CM-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(CMNT_ID_SEQ.NEXTVAL, 6, '0') ");
				query.append(" , ?                                                                                  ");
				query.append(" , ?                                                                                  ");
				query.append(" , ?                                                                                  ");
				query.append(" , SYSDATE                                                                                  ");
				query.append(" 			) ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, comment.getUserId());
				stmt.setString(2, comment.getItemId());
				stmt.setString(3, comment.getComment());
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int updateOneComment(CommentVO comment) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				
				query.append(" UPDATE	CMNT              ");
				query.append(" SET		CMNT = ?      ");
				query.append(" WHERE	CMNT_ID = ?       ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, comment.getComment());
				stmt.setString(2, comment.getCommentId());
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int deleteOneComment(String commentId) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				
				query.append(" DELETE               ");
				query.append(" FROM		CMNT        ");
				query.append(" WHERE	CMNT_ID = ? ");
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, commentId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int getAllArticlesCount(CommentSearchVO commentSearch) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {

			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		CMNT         ");
				query.append(" WHERE	ITM_ID = ?   ");
				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, commentSearch.getItemId());
			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return rs.getInt("CNT");
			}
		};
		return (int) dao.selectOne();
	}

}
