package com.jm.ppl.items.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.jm.ppl.actor.vo.ActorVO;
import com.jm.ppl.common.dao.JdbcDaoSupport;
import com.jm.ppl.drama.vo.DramaVO;
import com.jm.ppl.items.vo.ItemsSearchVO;
import com.jm.ppl.items.vo.ItemsVO;
import com.jm.ppl.movie.vo.MovieVO;

public class ItemsDaoImpl implements ItemsDao{

	@Override
	public List<ItemsVO> selectAllItems(ItemsSearchVO itemSearchVO) {
		JdbcDaoSupport dao = new JdbcDaoSupport(){

			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	* ");
				query.append(" FROM		( ");
				query.append(" 				SELECT	ROWNUM AS RNUM ");
				query.append("						, A.* ");
				query.append(" 				FROM	( ");
				query.append("							SELECT	I.ITM_ID ");
				query.append("									, I.ITM_NM ");
				query.append("									, I.ITM_BRND ");
				query.append("									, I.ITM_PDCD ");
				query.append("									, I.ITM_PRC ");
				query.append("									, I.ITM_LK_CNT ");
				query.append("									, I.ITM_PST ");
				query.append("									, M.MV_ID ");
				query.append("									, D.DRM_ID ");
				query.append("									, A.ACT_ID ");
				query.append("							FROM	ITM I ");
				query.append("									, MV M ");
				query.append("									, DRM D ");
				query.append("									, ACT A ");
				query.append("							WHERE	I.MV_ID = M.MV_ID ");
				query.append("							AND		I.DRM_ID = D.DRM_ID ");
				query.append("							AND		I.ACT_ID = A.ACT_ID ");
				
				query.append("							ORDER	BY ITM_ID ");
				query.append("						) A ");
				query.append("				WHERE	ROWNUM <= ? ");
				query.append("			 ) ");
				query.append(" WHERE	RNUM >= ? ");
				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, itemSearchVO.getPager().getEndArticleNumber());
				stmt.setInt(2, itemSearchVO.getPager().getStartArticleNumber());
				
			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				ItemsVO itemVO = new ItemsVO();
				DramaVO dramaVO = new DramaVO();
				MovieVO movieVO = new MovieVO();
				ActorVO actorVO = new ActorVO();
				
				itemVO.setItemId(rs.getString("ITM_ID"));
				itemVO.setItemName(rs.getString("ITM_NM"));
				itemVO.setItemBrand(rs.getString("ITM_BRND"));
				itemVO.setItemProductCode(rs.getString("ITM_PDCD"));
				itemVO.setItemPrice(rs.getInt("ITM_PRC"));
				itemVO.setItemLikeCount(rs.getInt("ITM_LK_CNT"));
				itemVO.setItemPost(rs.getString("ITM_PST"));
				movieVO.setMovieId(rs.getString("M_MV_ID"));
				dramaVO.setDramaId(rs.getString("D_DRM_ID"));
				actorVO.setActorId(rs.getString("A_ACT_ID"));
				
				itemVO.setMovieVO(movieVO);
				itemVO.setDramaVO(dramaVO);
				itemVO.setActorVO(actorVO);
												
				return itemVO;
			}
			
		};
		return (List<ItemsVO>) dao.selectList();
	}

	@Override
	public ItemsVO selectOneItem(String itemId) {
		JdbcDaoSupport dao = new JdbcDaoSupport(){

			@Override
			public String query() {
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
				query.append(" WHERE	ITM_ID = ? ");

				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, itemId);
				
			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				
				ItemsVO itemVO = new ItemsVO();
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
				
				return itemVO;
			}
			};
		return (ItemsVO) dao.selectOne();
	}

	@Override
	public int insertItem(ItemsVO itemVO) {
		JdbcDaoSupport dao = new JdbcDaoSupport(){
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" INSERT	INTO ITM ( ");
				query.append(" 						ITM_ID ");
				query.append(" 						, ITM_NM ");
				query.append(" 						, ITM_BRND ");
				query.append(" 						, ITM_PDCD ");
				query.append("						, ITM_PRC ");
				query.append("						, ITM_LK_CNT ");
				query.append("						, ITM_PST ");
				query.append("						, MV_ID ");
				query.append("						, DRM_ID ");
				query.append("						, ACT_ID ");
				query.append("					 ) ");
				query.append(" VALUES			 ( ");
				query.append("						'IT-' || ");
				query.append("						TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || ");
				query.append("						'-' || ");
				query.append("						LPAD(ITM_ID_SEQ.NEXTVAL, 6, '0') ");
				query.append("						, ? ");
				query.append("						, ? ");
				query.append("						, ? ");
				query.append("						, ? ");
				query.append("						, 0 ");
				query.append("						, ? ");
				query.append("						, ? ");
				query.append("						, ? ");
				query.append("						, ? ");
				query.append("					 ) ");
				
				return query.toString();
				
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				
				stmt.setString(1, itemVO.getItemName());
				stmt.setString(2, itemVO.getItemBrand());
				stmt.setString(3, itemVO.getItemProductCode());
				stmt.setInt(4, itemVO.getItemPrice());
				
				stmt.setString(5, itemVO.getItemPost());
				stmt.setString(6, itemVO.getMovieId());
				stmt.setString(7, itemVO.getDramaId());
				stmt.setString(8, itemVO.getActorId());
				
			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int deleteItem(String itemId) {
		JdbcDaoSupport dao = new JdbcDaoSupport(){

			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				
				query.append(" DELETE            ");
				query.append(" FROM		ITM        ");
				query.append(" WHERE	ITM_ID = ? ");
				return query.toString();
			
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, itemId);				
			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
			
		};
		return dao.update();
	}

	@Override
	public int updateItem(ItemsVO itemVO) {
		JdbcDaoSupport dao = new JdbcDaoSupport(){

			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	ITM        ");
				query.append(" SET		ITM_NM = ? ");
				query.append(" 			, ITM_BRND = ? ");
				query.append(" 			, ITM_PDCD = ? ");
				query.append(" 			, ITM_PRC = ? ");
				query.append(" 			, ITM_PST = ? ");
				query.append(" WHERE	ITM_ID = ? ");
				return query.toString();

			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, itemVO.getItemName());
				stmt.setString(2, itemVO.getItemBrand());
				stmt.setString(3, itemVO.getItemProductCode());
				stmt.setInt(4, itemVO.getItemPrice());
				stmt.setString(5, itemVO.getItemPost());
				stmt.setString(6, itemVO.getItemId());
			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
			
		};
		return dao.update();
	}

	@Override
	public int selectAllItemsCount(ItemsSearchVO itemsSearchVO) {
		JdbcDaoSupport dao = new JdbcDaoSupport(){

			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		ITM          ");
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
	public List<ItemsVO> selectItemsByActorId(ItemsSearchVO itemSearchVO) {
		JdbcDaoSupport dao = new JdbcDaoSupport(){

			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	* ");
				query.append(" FROM		( ");
				query.append(" 				SELECT	ROWNUM AS RNUM ");
				query.append("						, A.* ");
				query.append(" 				FROM	( ");
				query.append("							SELECT	I.ITM_ID ");
				query.append("									, I.ITM_NM ");
				query.append("									, I.ITM_BRND ");
				query.append("									, I.ITM_PDCD ");
				query.append("									, I.ITM_PRC ");
				query.append("									, I.ITM_LK_CNT ");
				query.append("									, I.ITM_PST ");
				query.append("									, I.MV_ID ");
				query.append("									, I.DRM_ID ");
				query.append("									, I.ACT_ID ");
				//query.append("									, M.MV_ID M_MV_ID ");
				//query.append("									, D.DRM_ID D_DRM_ID ");
				query.append("									, A.ACT_ID A_ACT_ID ");
				query.append("							FROM	ITM I ");
				//query.append("									, MV M ");
				//query.append("									, DRM D ");
				query.append("									, ACT A ");
				//query.append("							WHERE	I.MV_ID = M.MV_ID ");
				//query.append("							AND		I.DRM_ID = D.DRM_ID ");
				query.append("							WHERE	I.ACT_ID = A.ACT_ID ");
				query.append("							AND		I.ACT_ID = ? ");		
				//query.append("							ORDER	BY ITM_ID ");
				query.append("						) A ");
				query.append("				WHERE	ROWNUM <= ? ");
				query.append("			 ) ");
				query.append(" WHERE	RNUM >= ? ");
				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, itemSearchVO.getActorId());
				stmt.setInt(2, itemSearchVO.getPager().getEndArticleNumber());
				stmt.setInt(3, itemSearchVO.getPager().getStartArticleNumber());
			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				ItemsVO itemVO = new ItemsVO();
				//DramaVO dramaVO = new DramaVO();
				//MovieVO movieVO = new MovieVO();
				ActorVO actorVO = new ActorVO();
				
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
				
				//movieVO.setMovieId(rs.getString("M_MV_ID"));
				//dramaVO.setDramaId(rs.getString("D_DRM_ID"));
				actorVO.setActorId(rs.getString("A_ACT_ID"));
				
				//itemVO.setMovieVO(movieVO);
				//itemVO.setDramaVO(dramaVO);
				itemVO.setActorVO(actorVO);
												
				return itemVO;
			}
		};
		return (List<ItemsVO>) dao.selectList();
	}

	@Override
	public List<ItemsVO> selectItemsByMovieId(ItemsSearchVO itemSearchVO) {
		JdbcDaoSupport dao = new JdbcDaoSupport(){
						
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" SELECT	* ");
				query.append(" FROM		( ");
				query.append(" 				SELECT	ROWNUM AS RNUM ");
				query.append("						, A.* ");
				query.append(" 				FROM	( ");
				query.append("							SELECT	I.ITM_ID ");
				query.append("									, I.ITM_NM ");
				query.append("									, I.ITM_BRND ");
				query.append("									, I.ITM_PDCD ");
				query.append("									, I.ITM_PRC ");
				query.append("									, I.ITM_LK_CNT ");
				query.append("									, I.ITM_PST ");
				query.append("									, I.MV_ID ");
				query.append("									, I.DRM_ID ");
				query.append("									, I.ACT_ID ");
				query.append("									, M.MV_ID M_MV_ID ");
				/*query.append("									, D.DRM_ID D_DRM_ID ");
				query.append("									, A.ACT_ID A_ACT_ID ");*/
				query.append("							FROM	ITM I ");
				query.append("									, MV M ");
				/*query.append("									, DRM D ");
				query.append("									, ACT A ");*/
				query.append("							WHERE	I.MV_ID = M.MV_ID ");
				/*query.append("							AND		I.DRM_ID = D.DRM_ID ");
				query.append("							AND		I.ACT_ID = A.ACT_ID ");*/
				query.append("							AND		I.MV_ID = ? ");		
				/*query.append("							AND		I.DRM_ID = ? ");		
				query.append("							AND		I.ACT_ID = ? ");		*/
				/*query.append("							ORDER	BY ITM_ID ");*/
				query.append("						) A ");
				query.append("				WHERE	ROWNUM <= ? ");
				query.append("			 ) ");
				query.append(" WHERE	RNUM >= ? ");
				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, itemSearchVO.getMovieId());
				
				stmt.setInt(2, itemSearchVO.getPager().getEndArticleNumber());
				stmt.setInt(3, itemSearchVO.getPager().getStartArticleNumber());
			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				ItemsVO itemVO = new ItemsVO();
				//DramaVO dramaVO = new DramaVO();
				MovieVO movieVO = new MovieVO();
				//ActorVO actorVO = new ActorVO();
				
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
				
				movieVO.setMovieId(rs.getString("M_MV_ID"));
				//dramaVO.setDramaId(rs.getString("D_DRM_ID"));
				//actorVO.setActorId(rs.getString("A_ACT_ID"));
				
				itemVO.setMovieVO(movieVO);
				//itemVO.setDramaVO(dramaVO);
				//itemVO.setActorVO(actorVO);
				
				//System.out.println(itemVO.getItemName());
												
				return itemVO;
			}};
		return (List<ItemsVO>) dao.selectList();
	}

	@Override
	public List<ItemsVO> selectItemsByDramaId (ItemsSearchVO itemSearchVO) {
		JdbcDaoSupport dao = new JdbcDaoSupport(){

			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				/*query.append(" SELECT	* ");
				query.append(" FROM		( ");
				query.append(" 				SELECT	ROWNUM AS RNUM ");
				query.append("						, A.* ");
				query.append(" 				FROM	( ");*/
				query.append("							SELECT	I.ITM_ID ");
				query.append("									, I.ITM_NM ");
				query.append("									, I.ITM_BRND ");
				query.append("									, I.ITM_PDCD ");
				query.append("									, I.ITM_PRC ");
				query.append("									, I.ITM_LK_CNT ");
				query.append("									, I.ITM_PST ");
				query.append("									, I.MV_ID ");
				query.append("									, I.DRM_ID ");
				query.append("									, I.ACT_ID ");
				//query.append("									, M.MV_ID M_MV_ID ");
				query.append("									, D.DRM_ID D_DRM_ID ");
				//query.append("									, A.ACT_ID A_ACT_ID ");
				query.append("							FROM	ITM I ");
				//query.append("									, MV M ");
				query.append("									, DRM D ");
				//query.append("									, ACT A ");
				//query.append("							WHERE	I.MV_ID = M.MV_ID ");
				query.append("							WHERE		I.DRM_ID = D.DRM_ID ");
				//query.append("							AND		I.ACT_ID = A.ACT_ID ");
				query.append("							AND		D.DRM_ID = ? ");		
				//query.append("							ORDER	BY ITM_ID ");
				/*query.append("						) A ");
				query.append("				WHERE	ROWNUM <= ? ");
				query.append("			 ) ");
				query.append(" WHERE	RNUM >= ? ");*/
				return query.toString();
				
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, itemSearchVO.getDramaId());
				/*stmt.setInt(2, itemSearchVO.getPager().getEndArticleNumber());
				stmt.setInt(3, itemSearchVO.getPager().getStartArticleNumber());*/
			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				ItemsVO itemVO = new ItemsVO();
				DramaVO dramaVO = new DramaVO();
				/*MovieVO movieVO = new MovieVO();
				ActorVO actorVO = new ActorVO();*/
				
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
				
				//movieVO.setMovieId(rs.getString("M_MV_ID"));
				dramaVO.setDramaId(rs.getString("D_DRM_ID"));
				//actorVO.setActorId(rs.getString("A_ACT_ID"));
				
				//itemVO.setMovieVO(movieVO);
				itemVO.setDramaVO(dramaVO);
				//itemVO.setActorVO(actorVO);
												
				return itemVO;
				
			}};
		return (List<ItemsVO>) dao.selectList();
	}

	@Override
	public int plusUpdateLikeCount(String itemId) {
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	ITM ");
				query.append(" SET		ITM_LK_CNT = ITM_LK_CNT + 1 ");
				query.append(" WHERE	ITM_ID = ? ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, itemId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int minusUpdateLikeCount(String itemId) {
			JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	ITM ");
				query.append(" SET		ITM_LK_CNT = ITM_LK_CNT - 1 ");
				query.append(" WHERE	ITM_ID = ? ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, itemId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

	@Override
	public int setLike(int likeCount, String itemId) {
			JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	ITM ");
				query.append(" SET		ITM_LK_CNT = ? ");
				query.append(" WHERE	ITM_ID = ? ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, likeCount);
				stmt.setString(2, itemId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

}
