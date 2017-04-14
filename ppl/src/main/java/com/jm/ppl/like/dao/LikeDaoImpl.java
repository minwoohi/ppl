package com.jm.ppl.like.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jm.ppl.common.dao.JdbcDaoSupport;
import com.jm.ppl.like.vo.LikeVO;

public class LikeDaoImpl implements LikeDao {

	@Override
	public int insertLike(LikeVO likeVO) {
		
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" INSERT	INTO LK ( ");
				query.append(" 						LK_ID ");
				query.append(" 						, TRGT_ID ");
				query.append(" 						, USR_ID ");
				query.append(" 					  ) ");
				query.append(" VALUES			  ( ");
				query.append("						'LK-' || ");
				query.append("						TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || ");
				query.append("						'-' || ");
				query.append("						LPAD(ITM_ID_SEQ.NEXTVAL, 6, '0') ");
				query.append(" 						, ? ");
				query.append(" 						, ? ");
				query.append(" 					  ) ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, likeVO.getTargetId());
				stmt.setString(2, likeVO.getUserId());
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int deleteLike(String userId, String targetId) {

		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" DELETE	");
				query.append(" FROM		LK ");
				query.append(" WHERE	USR_ID = ? ");
				query.append(" AND		TRGT_ID = ? ");
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, userId);
				stmt.setString(2, targetId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		}; 
		return dao.update();
		
	}

	@Override
	public int countLikeByTargetId(String targetId) {
		
			JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		LK ");
				query.append(" WHERE	TRGT_ID = ? ");
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, targetId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return rs.getInt("CNT");
			}
		}; 
		return (int) dao.selectOne();
	}

	@Override
	public LikeVO selectOneLike(String userId, String targetId) {

			JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	LK_ID ");
				query.append(" 			, TRGT_ID ");
				query.append(" 			, USR_ID ");
				query.append(" FROM		LK ");
				query.append(" WHERE	TRGT_ID = ? ");
				query.append(" AND		USR_ID = ? ");
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, targetId);
				stmt.setString(2, userId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				LikeVO likeVO = new LikeVO();
				likeVO.setLikeId(rs.getString("LK_ID"));
				likeVO.setTgtId(rs.getString("TRGT_ID"));
				likeVO.setUserId(rs.getString("USR_ID"));
				return likeVO;
			}
		}; 	
		return (LikeVO) dao.selectOne();
	}

}
