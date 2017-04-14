package com.jm.ppl.admin.authorization.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jm.ppl.admin.authorization.vo.AuthorizationSearchVO;
import com.jm.ppl.admin.authorization.vo.AuthorizationVO;
import com.jm.ppl.admin.common.web.JdbcDaoSupport;


public class AuthorDaoImpl implements AuthorDao{

	@Override
	public int insertNewAuthorization(AuthorizationVO authorizationVO) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		String url = "jdbc:oracle:thin:@192.168.201.23:1521:XE";

		try {
			conn = DriverManager.getConnection(url, "PPL", "ppl");

			StringBuffer query = new StringBuffer();
			query.append(" INSERT  INTO    ATHRZTN     (    ");
			query.append("                             ATHRZTN_ID ");
			query.append("                             , ATHRZTN_NM ");
			query.append("                             , PRNT_ATHRZTN_ID ");
			query.append("                         ) ");
			query.append("                 VALUES  ( ");			
			query.append("                              ? ");
			query.append("                             , ? ");
			query.append("                             , ? ");
			query.append("                         ) ");

			stmt = conn.prepareStatement(query.toString()); //지금 처럼 seq을 만들때는 아이디가필요할때 는 지금처럼
			
			stmt.setString(1, authorizationVO.getAuthorizationId());
			stmt.setString(2, authorizationVO.getAuthorizationName());
			stmt.setString(3, authorizationVO.getParentAuthorizationId());

			return stmt.executeUpdate();
		} catch (SQLException e) {

			throw new RuntimeException(e.getMessage(), e);
		} finally {
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
	public int selectAllAuthorizationCount(AuthorizationSearchVO authorizationSearchVO) {
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
			query.append(" SELECT	COUNT(1) CNT      ");
			query.append(" FROM		ATHRZTN            ");
			

			stmt = conn.prepareStatement(query.toString());

			rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getInt("CNT");
			}

			return 0;
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
	public List<AuthorizationVO> selectAllauthorization(AuthorizationSearchVO authorizationSearchVO) {

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

			query.append("  SELECT		*      ");
			query.append("  FROM        (     ");

			query.append("              SELECT		ROWNUM AS RNUM      ");
			query.append("                           , RST.*");
			query.append("              FROM			(");

			query.append("                           SELECT		      ");
			query.append("                           				ATHRZTN_ID   ");
			query.append("                           				, ATHRZTN_NM   ");
			query.append("                           				, PRNT_ATHRZTN_ID   ");
			query.append("                           FROM			ATHRZTN          ");
			query.append("                           ORDER BY    ATHRZTN_ID");

			query.append("                           ) RST");
			query.append("               WHERE       ROWNUM  <= ?     	");

			query.append("               )	");
			query.append("  WHERE       RNUM  >= ?     	");

			stmt = conn.prepareStatement(query.toString());

			stmt.setInt(1, authorizationSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, authorizationSearchVO.getPager().getStartArticleNumber());

			rs = stmt.executeQuery();

			
			AuthorizationVO authorizationVO = null;
			List<AuthorizationVO> authorList = new ArrayList<AuthorizationVO>();

			while (rs.next()) {
			

				authorizationVO = new AuthorizationVO();
				authorizationVO.setAuthorizationId(rs.getString("ATHRZTN_ID"));
				authorizationVO.setAuthorizationName(rs.getString("ATHRZTN_NM"));
				authorizationVO.setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));

				authorList.add(authorizationVO);
			}

			return authorList;
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
	public int updateAuthorization(AuthorizationVO authorizationVO) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		String url = "jdbc:oracle:thin:@192.168.201.23:1521:XE";

		try {
			conn = DriverManager.getConnection(url, "PPL", "ppl");

			StringBuffer query = new StringBuffer();
			query.append(" UPDATE    ATHRZTN  ");
			query.append(" SET		 ");
			query.append("           ATHRZTN_NM = ? ");
			query.append("          , PRNT_ATHRZTN_ID = ? ");
			query.append(" WHERE     ATHRZTN_ID = ? "); 
													

			stmt = conn.prepareStatement(query.toString());
		
		
			stmt.setString(1, authorizationVO.getAuthorizationName());
			stmt.setString(2, authorizationVO.getParentAuthorizationId());
			stmt.setString(3, authorizationVO.getAuthorizationId());

			return stmt.executeUpdate();
			
		} catch (SQLException e) {

			throw new RuntimeException(e.getMessage(), e);
		} finally {
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
	public int deleteOneAuthorization(String authorizationId) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		String url = "jdbc:oracle:thin:@192.168.201.23:1521:XE";

		try {
			conn = DriverManager.getConnection(url, "PPL", "ppl");

			StringBuffer query = new StringBuffer();
			query.append(" DELETE      ");
			query.append(" FROM     ATHRZTN ");
			query.append(" WHERE     ATHRZTN_ID = ? ");
			

			stmt = conn.prepareStatement(query.toString());
		
			stmt.setString(1, authorizationId);
			
			return stmt.executeUpdate();
			
		} catch (SQLException e) {

			throw new RuntimeException(e.getMessage(), e);
		} finally {
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
	public String generateNewAuthorizationId() {
	
		
		JdbcDaoSupport dao = new JdbcDaoSupport() {
			
			@Override
			public String query() {
				StringBuffer query = new StringBuffer();
				query.append("  SELECT  'AT-' || TO_CHAR( SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(ATHRZTN_ID_SEQ.NEXTVAL, 6, '0') SEQ");
				query.append("  FROM    DUAL ");
				return query.toString();
			}
			
			@Override
			public void mappingParams(PreparedStatement stmt) throws SQLException {
				
				
			}
			
			@Override
			public Object bindData(ResultSet rs) throws SQLException {
				
				return rs.getString("SEQ");
			}
		};
		//추상클래스 객체화 시키는법 추상적인거 {} 구체화시키기
		
		
		return (String) dao.selectOne();
		
		
	/*	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String url = "jdbc:oracle:thin:@localhost:1521:XE";

		
			try {
				conn = DriverManager.getConnection(url, "MELON", "melon");
				
				StringBuffer query = new StringBuffer();
				query.append("  SELECT  'AT-' || TO_CHAR( SYSDATE, 'YYYYMMDDHH24') || '-' || LPAD(ATHRZTN_ID_SEQ.NEXTVAL, 6, '0') SEQ");
				query.append("  FROM    DUAL ");
				
				stmt = conn.prepareStatement(query.toString());
				
				rs = stmt.executeQuery();
				
				if(rs.next()){
					
					return rs.getString("SEQ");//return rs.getString(1);
				}
					return null;
				
			} catch (SQLException e) {
				throw new RuntimeException(e.getMessage(), e);
			}  finally {
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
		*/
		
	
		
		
	
	}

}
