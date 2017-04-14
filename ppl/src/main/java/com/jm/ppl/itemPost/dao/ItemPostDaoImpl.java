package com.jm.ppl.itemPost.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jm.ppl.common.dao.JdbcDaoSupport;
import com.jm.ppl.itemPost.vo.ItemPostVO;
import com.jm.ppl.items.vo.ItemsVO;

public class ItemPostDaoImpl implements ItemPostDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemPostVO> selectAllPosts(String itemId) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {

			@Override
			public String query() {
				StringBuffer query = new StringBuffer();

				query.append(" SELECT	P.ITM_PST_ID         ");
				query.append(" 			, P.ITM_ID P_ITM_ID  ");
				query.append(" 			, P.PST_NM           ");
				query.append(" 			, I.ITM_ID I_ITM_ID  ");
				query.append(" 			, I.ITM_NM           ");
				query.append(" 			, I.ITM_BRND         ");
				query.append(" 			, I.ITM_PDCD         ");
				query.append(" 			, I.ITM_PRC          ");
				query.append(" 			, I.ITM_LK_CNT       ");
				query.append(" 			, I.ITM_PST          ");
				query.append(" 			, I.MV_ID            ");
				query.append(" 			, I.DRM_ID           ");
				query.append(" 			, I.ACT_ID           ");
				query.append(" FROM		ITM_PST P            ");
				query.append(" 			, ITM I              ");
				query.append(" WHERE	P.ITM_ID = I.ITM_ID  ");
				query.append(" AND		P.ITM_ID = ?         ");

				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, itemId);
			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				ItemPostVO post = new ItemPostVO();

				post.setItemPostId(rs.getString("ITM_PST_ID"));
				post.setItemId(rs.getString("P_ITM_ID"));
				post.setPostName(rs.getString("PST_NM"));

				ItemsVO item = post.getItem();

				item.setItemId(rs.getString("I_ITM_ID"));
				item.setItemName(rs.getString("ITM_NM"));
				item.setItemBrand(rs.getString("ITM_BRND"));
				item.setItemProductCode(rs.getString("ITM_PDCD"));
				item.setItemPrice(rs.getInt("ITM_PRC"));
				item.setItemLikeCount(rs.getInt("ITM_LK_CNT"));
				item.setItemPost(rs.getString("ITM_PST"));
				item.setMovieId(rs.getString("MV_ID"));
				item.setDramaId(rs.getString("DRM_ID"));
				item.setActorId(rs.getString("ACT_ID"));

				return post;
			}
		};
		return (List<ItemPostVO>) dao.selectList();
	}

	@Override
	public ItemPostVO selectOnePost(String itemPostId) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {

			@Override
			public String query() {
				StringBuffer query = new StringBuffer();

				query.append(" SELECT	ITM_PST_ID     ");
				query.append(" 			, ITM_ID       ");
				query.append(" 			, PST_NM       ");
				query.append(" FROM		ITM_PST        ");
				query.append(" WHERE	ITM_PST_ID = ? ");
				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, itemPostId);
			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				ItemPostVO post = new ItemPostVO();

				post.setItemPostId(rs.getString("ITM_PST_ID"));
				post.setItemId(rs.getString("ITM_ID"));
				post.setPostName(rs.getString("PST_NM"));
				return post;
			}
		};
		return (ItemPostVO) dao.selectOne();
	}

	@Override
	public int insertOnePost(ItemPostVO item) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {

			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" INSERT	INTO	ITM_PST (                                                                                 ");
				query.append(" 							ITM_PST_ID                                                                        ");
				query.append(" 							, ITM_ID                                                                          ");
				query.append(" 							, PST_NM                                                                          ");
				query.append(" 						)                                                                                     ");
				query.append(" VALUES			(                                                                                         ");
				query.append(" 					'PS-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(ITM_PST_ID_SEQ.NEXTVAL, 6, '0')  ");
				query.append(" 					, ?                                                                                       ");
				query.append(" 					, ?                                                                                       ");
				query.append(" 				)                                                                                             ");
				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, item.getItemId());
				stmt.setString(2, item.getPostName());
			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int deleteOnePost(String itemPostId) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {

			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" DELETE	               ");
				query.append(" FROM		ITM_PST        ");
				query.append(" WHERE	ITM_PST_ID = ? ");
				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, itemPostId);
			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

}
