package com.jm.ppl.actor.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jm.ppl.actor.vo.ActorSearchVO;
import com.jm.ppl.actor.vo.ActorVO;
import com.jm.ppl.common.dao.JdbcDaoSupport;

public class ActorDaoImpl implements ActorDao {

	@Override
	public List<ActorVO> selectAllActors(ActorSearchVO actorSearchVO) {
		
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	* ");
				query.append(" FROM		( ");
				query.append(" 				SELECT	ROWNUM AS RNUM ");
				query.append("						, A.* ");
				query.append(" 				FROM	( ");
				query.append("							SELECT	ACT_ID ");
				query.append("									, ACT_NM ");
				query.append("									, ACT_SX ");
				query.append("									, TO_CHAR(ACT_BRH, 'YYYY.MM.DD') BRH_DT ");
				query.append("									, ACT_HGHT ");
				query.append("									, ACT_WGHT ");
				query.append("									, ACT_ENT ");
				query.append("									, ACT_LK_CNT ");
				query.append("									, ACT_PST ");
				query.append("							FROM	ACT ");
				query.append("							ORDER	BY ACT_ID DESC ");
				query.append("						) A ");
				query.append("				WHERE	ROWNUM <= ? ");
				query.append("			 ) ");
				query.append(" WHERE	RNUM >= ? ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {

				stmt.setInt(1, actorSearchVO.getPager().getEndArticleNumber());
				stmt.setInt(2, actorSearchVO.getPager().getStartArticleNumber());
				
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				
				ActorVO actorVO = new ActorVO();
				actorVO.setActorId(rs.getString("ACT_ID"));
				actorVO.setActorName(rs.getString("ACT_NM"));
				actorVO.setActorSex(rs.getString("ACT_SX"));
				actorVO.setActorBirth(rs.getString("BRH_DT"));
				actorVO.setActorHeight(rs.getString("ACT_HGHT"));
				actorVO.setActorWeight(rs.getString("ACT_WGHT"));
				actorVO.setActorEntertainment(rs.getString("ACT_ENT"));
				actorVO.setActorLikeCount(rs.getInt("ACT_LK_CNT"));
				actorVO.setActorPost(rs.getString("ACT_PST"));
				
				return actorVO;
				
			}
		};
		return (List<ActorVO>) dao.selectList();
	}

	@Override
	public ActorVO selectOneActor(String actorId) {
		
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	ACT_ID ");
				query.append(" 			, ACT_NM ");
				query.append(" 			, ACT_SX ");
				query.append(" 			, TO_CHAR(ACT_BRH, 'YYYY.MM.DD') BRH_DT ");
				query.append("			, ACT_HGHT ");
				query.append("			, ACT_WGHT ");
				query.append("			, ACT_ENT ");
				query.append("			, ACT_LK_CNT ");
				query.append("			, ACT_PST ");
				query.append(" FROM		ACT ");
				query.append(" WHERE	ACT_ID = ? ");

				return query.toString();
				
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				
				stmt.setString(1, actorId);
				
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {

				ActorVO actorVO = new ActorVO();
				actorVO.setActorId(rs.getString("ACT_ID"));
				actorVO.setActorName(rs.getString("ACT_NM"));
				actorVO.setActorSex(rs.getString("ACT_SX"));
				actorVO.setActorBirth(rs.getString("BRH_DT"));
				actorVO.setActorHeight(rs.getString("ACT_HGHT"));
				actorVO.setActorWeight(rs.getString("ACT_WGHT"));
				actorVO.setActorEntertainment(rs.getString("ACT_ENT"));
				actorVO.setActorLikeCount(rs.getInt("ACT_LK_CNT"));
				actorVO.setActorPost(rs.getString("ACT_PST"));
				
				return actorVO;				
				
			}
		};
		
		return (ActorVO) dao.selectOne();
		
	}

	@Override
	public int insertActor(ActorVO actorVO) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				
				StringBuffer query = new StringBuffer();
				query.append(" INSERT	INTO ACT ( ");
				query.append(" 						ACT_ID ");
				query.append("				        , ACT_NM ");
				query.append("				        , ACT_SX ");
				query.append("				        , ACT_BRH ");
				query.append("				        , ACT_HGHT ");
				query.append("				        , ACT_WGHT ");
				query.append("				        , ACT_ENT ");
				query.append("				        , ACT_LK_CNT ");
				query.append("				        , ACT_PST ");
				query.append("					 ) ");
				query.append(" VALUES			 ( ");
				query.append("						'AC-' || ");
				query.append("						TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || ");
				query.append("						'-' || ");
				query.append("						LPAD(ACT_ID_SEQ.NEXTVAL, 6, '0') ");
				query.append("						, ? ");
				query.append("						, ? ");
				query.append("						, ? ");
				query.append("						, ? ");
				query.append("						, ? ");
				query.append("						, ? ");
				query.append("						, 0 ");
				query.append("						, ? ");
				query.append("					 ) ");
				
				return query.toString();
				
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				
				stmt.setString(1, actorVO.getActorName());
				stmt.setString(2, actorVO.getActorSex());
				stmt.setString(3, actorVO.getActorBirth());
				stmt.setString(4, actorVO.getActorHeight());
				stmt.setString(5, actorVO.getActorWeight());
				stmt.setString(6, actorVO.getActorEntertainment());
				stmt.setString(7, actorVO.getActorPost());
				
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		
		return dao.update();
	}

	@Override
	public int deleteActor(String actorId) {
		
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				
				query.append(" DELETE           ");
				query.append(" FROM		ACT        ");
				query.append(" WHERE	ACT_ID = ? ");
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, actorId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int updateActor(ActorVO actorVO) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	ACT        ");
				query.append(" SET		ACT_NM = ? ");
				query.append(" 			, ACT_SX = ? ");
				query.append(" 			, ACT_BRH = ? ");
				query.append(" 			, ACT_HGHT = ? ");
				query.append(" 			, ACT_WGHT = ? ");
				query.append(" 			, ACT_ENT = ? ");
				query.append(" 			, ACT_LK_CNT = ? ");
				query.append(" 			, ACT_PST = ? ");
				query.append(" WHERE	ACT_ID = ? ");
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, actorVO.getActorName());
				stmt.setString(2, actorVO.getActorSex());
				stmt.setString(3, actorVO.getActorBirth());
				stmt.setString(4, actorVO.getActorHeight());
				stmt.setString(5, actorVO.getActorWeight());
				stmt.setString(6, actorVO.getActorEntertainment());
				stmt.setInt(7, actorVO.getActorLikeCount());
				stmt.setString(8, actorVO.getActorPost());
				stmt.setString(9, actorVO.getActorId());
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int selectAllActorsCount(ActorSearchVO actorSearchVO) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		ACT          ");
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return rs.getInt("CNT");
			}
		};
		return (int) dao.selectOne();
	}

	@Override
	public int plusUpdateLikeCount(String actorId) {
			JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	ACT ");
				query.append(" SET		ACT_LK_CNT = ACT_LK_CNT + 1 ");
				query.append(" WHERE	ACT_ID = ? ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, actorId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int minusUpdateLikeCount(String actorId) {
			JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	ACT ");
				query.append(" SET		ACT_LK_CNT = ACT_LK_CNT - 1 ");
				query.append(" WHERE	ACT_ID = ? ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, actorId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int setLike(int likeCount, String actorId) {
			JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	ACT ");
				query.append(" SET		ACT_LK_CNT = ? ");
				query.append(" WHERE	ACT_ID = ? ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, likeCount);
				stmt.setString(2, actorId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();		
	}

}