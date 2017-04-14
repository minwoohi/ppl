package com.jm.ppl.movie.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jm.ppl.common.dao.JdbcDaoSupport;
import com.jm.ppl.movie.vo.MovieSearchVO;
import com.jm.ppl.movie.vo.MovieVO;

public class MovieDaoImpl implements MovieDao {

	@Override
	public int insertNewMovie(MovieVO movieVO) {

		JdbcDaoSupport insertDao = new JdbcDaoSupport() {

			@Override
			public String query() {

				StringBuffer query = new StringBuffer();
				query.append("	INSERT	INTO	MV ");
				query.append("							( ");
				query.append("									MV_ID  ");
				query.append("									, MV_TTL ");
				query.append("									, MV_SBTTL ");
				query.append("									, MV_DRTR ");
				query.append("									, MV_ACTR ");
				query.append("									, MV_PST ");
				query.append("									, MV_INTR ");
				query.append("									, MV_GNR ");
				query.append("									, MV_LK_CNT ");
				query.append("							) ");
				query.append("	VALUES ");
				query.append("							( ");
				query.append("									'MV-' || TO_CHAR(SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD( MV_ID_SEQ.NEXTVAL, 6, '0') ");
				query.append("									, ? ");
				query.append("									, ? ");
				query.append("									, ? ");
				query.append("									, ? ");
				query.append("									, ? ");
				query.append("									, ? ");
				query.append("									, ? ");
				query.append("									, 0 ");
				query.append("							) ");

				return query.toString();

			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {

				stmt.setString(1, movieVO.getMovieTitle());
				stmt.setString(2, movieVO.getMovieSubtitle());
				stmt.setString(3, movieVO.getMovieDirector());
				stmt.setString(4, movieVO.getMovieActor());
				stmt.setString(5, movieVO.getMoviePost());
				stmt.setString(6, movieVO.getMovieIntro());
				stmt.setString(7, movieVO.getMovieGenre());

			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};

		return insertDao.update();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MovieVO> selectAllMovies(MovieSearchVO movieSearchVO) {

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
			query.append(" SELECT	* ");
			query.append(" FROM	( ");
			query.append(" 			SELECT	ROWNUM AS RNUM ");
			query.append(" 					, A.* ");
			query.append(" 			FROM	( ");
			query.append(" 						SELECT		MV_ID ");
			query.append(" 									, MV_TTL ");
			query.append(" 									, MV_SBTTL ");
			query.append(" 									, MV_DRTR ");
			query.append(" 									, MV_ACTR ");
			query.append(" 									, MV_PST ");
			query.append(" 									, MV_INTR ");
			query.append(" 									, MV_LK_CNT ");
			query.append(" 									, MV_GNR ");
			query.append(" 						FROM		MV ");
			query.append(" 						ORDER		BY MV_ID DESC ");
			query.append(" 					) A ");
			query.append(" 			WHERE	ROWNUM <= ? ");
			query.append(" 		) ");
			query.append(" WHERE	RNUM >= ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setInt(1, movieSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, movieSearchVO.getPager().getStartArticleNumber());

			rs = stmt.executeQuery();

			List<MovieVO> movieList = new ArrayList<MovieVO>();
			MovieVO movieVO = null;

			while (rs.next()) {

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

				movieList.add(movieVO);
			}

			return movieList;

		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
				}
			}
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}
	}

	@Override
	public int updateOneMovie(MovieVO movieVO) {

		JdbcDaoSupport updateDao = new JdbcDaoSupport() {

			@Override
			public String query() {

				StringBuffer query = new StringBuffer();

				query.append(" UPDATE	MV ");
				query.append(" SET		MV_TTL = ?  ");
				query.append(" 			, MV_SBTTL = ? ");
				query.append(" 			, MV_DRTR = ? ");
				query.append(" 			, MV_ACTR = ? ");
				query.append(" 			, MV_PST = ? ");
				query.append(" 			, MV_INTR = ? ");
				query.append(" 			, MV_GNR = ? ");
				query.append(" 			, MV_LK_CNT = ? ");
				query.append(" WHERE	MV_ID = ? ");

				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {

				stmt.setString(1, movieVO.getMovieTitle());
				stmt.setString(2, movieVO.getMovieSubtitle());
				stmt.setString(3, movieVO.getMovieDirector());
				stmt.setString(4, movieVO.getMovieActor());
				stmt.setString(5, movieVO.getMoviePost());
				stmt.setString(6, movieVO.getMovieIntro());
				stmt.setString(7, movieVO.getMovieGenre());
				stmt.setInt(8, movieVO.getMovieLikeCount());
				stmt.setString(9, movieVO.getMovieId());

			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};

		return updateDao.update();
	}

	@Override
	public int deleteOneMovie(String movieId) {

		JdbcDaoSupport deleteDao = new JdbcDaoSupport() {

			@Override
			public String query() {

				StringBuffer query = new StringBuffer();
				query.append(" DELETE 	FROM MV ");
				query.append(" WHERE 	MV_ID = ? ");

				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {

				stmt.setString(1, movieId);

			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};

		return deleteDao.update();
	}

	@Override
	public int selectAllMoviesCount(MovieSearchVO movieSearchVO) {

		JdbcDaoSupport pagerCountDao = new JdbcDaoSupport() {

			@Override
			public String query() {

				StringBuffer query = new StringBuffer();
				query.append(" SELECT	COUNT(1) CNT ");
				query.append(" FROM		MV ");

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

		return (int) pagerCountDao.selectOne();
	}

	@Override
	public int addLikeCount(String movieId) {

		JdbcDaoSupport countDao = new JdbcDaoSupport() {

			@Override
			public String query() {

				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	MV ");
				query.append(" SET		MV_LK_CNT = MV_LK_CNT + 1 ");
				query.append(" WHERE	MV_ID =? ");
				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, movieId);

			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};

		return countDao.update();
	}

	@Override
	public MovieVO selectOneMovie(String movieId) {

		JdbcDaoSupport detailDao = new JdbcDaoSupport() {

			@Override
			public String query() {

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
				query.append(" 	WHERE		MV_ID =? ");

				return query.toString();
			}

			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {

				stmt.setString(1, movieId);

			}

			@Override
			public Object getData(ResultSet rs) throws SQLException {

				MovieVO movieVO = new MovieVO();

				movieVO.setMovieId(rs.getString("MV_ID"));
				movieVO.setMovieTitle(rs.getString("MV_TTL"));
				movieVO.setMovieSubtitle(rs.getString("MV_SBTTL"));
				movieVO.setMovieDirector(rs.getString("MV_DRTR"));
				movieVO.setMovieActor(rs.getString("MV_ACTR"));
				movieVO.setMoviePost(rs.getString("MV_PST"));
				movieVO.setMovieIntro(rs.getString("MV_INTR"));
				movieVO.setMovieLikeCount(rs.getInt("MV_LK_CNT"));
				movieVO.setMovieGenre(rs.getString("MV_GNR"));

				return movieVO;
			}
		};
		return (MovieVO) detailDao.selectOne();
	}

	@Override
	public int setLike(int likeCount, String movieId) {
			JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append(" UPDATE	MV ");
				query.append(" SET		MV_LK_CNT = ? ");
				query.append(" WHERE	MV_ID = ? ");
				
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, likeCount);
				stmt.setString(2, movieId);
			}
			
			@Override
			public Object getData(ResultSet rs) throws SQLException {
				return null;
			}
		};
		return dao.update();
	}

}
