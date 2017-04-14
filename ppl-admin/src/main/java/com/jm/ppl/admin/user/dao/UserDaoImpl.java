package com.jm.ppl.admin.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.jm.ppl.admin.authorization.vo.AuthorizationVO;
import com.jm.ppl.admin.user.vo.SignVO;
import com.jm.ppl.admin.user.vo.UserSearchVO;
import com.jm.ppl.admin.user.vo.UserVO;

public class UserDaoImpl implements UserDao {

	@Override
	public int insertNewUser(UserVO userVO) {
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
			query.append(" INSERT  INTO    USR     (    ");
			query.append("                             USR_ID ");
			query.append("                             , USR_NM ");
			query.append("                             , USR_PW ");
			query.append("                             , ATHRZTN_ID ");
			query.append("                             , RGST_DT ");
			
			query.append("                         ) ");
			query.append("                 VALUES  ( ");
			query.append("                             ? ");
			query.append("                             , ? ");
			query.append("                             , ? ");
			query.append("                             , ? ");
			query.append("                             , ? ");
			query.append("                         ) ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userVO.getUserId());
			stmt.setString(2, userVO.getUserName());
			stmt.setString(3, userVO.getUserPassword());
			stmt.setString(4, userVO.getAuthorizationId());
			stmt.setString(5, userVO.getAuthorizationId());

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
	public List<UserVO> selectAllUser(UserSearchVO userSearchVO) {

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

			query.append("                           SELECT		U.USR_ID      ");
			query.append("                           				, U.USR_NM        ");
			query.append("                           				, U.USR_PW       ");
			query.append("                            			    , U.RGST_DT ");
			query.append("                           				, U.ATHRZTN_ID  U_ATHRZTN_ID  ");
			query.append("                           				, A.ATHRZTN_ID   ");
			query.append("                           				, A.ATHRZTN_NM   ");
			query.append("                           				, A.PRNT_ATHRZTN_ID   ");
			query.append("                           FROM			USR  U          ");
			query.append("                           		    	, ATHRZTN  A   ");
			query.append("                           WHERE		U.ATHRZTN_ID = A.ATHRZTN_ID(+)  ");
			query.append("                           ORDER BY    U.ATHRZTN_ID");

			query.append("                           ) RST");
			query.append("               WHERE       ROWNUM  <= ?     	");

			query.append("               )	");
			query.append("  WHERE       RNUM  >= ?     	");

			stmt = conn.prepareStatement(query.toString());

			stmt.setInt(1, userSearchVO.getPager().getEndArticleNumber());
			stmt.setInt(2, userSearchVO.getPager().getStartArticleNumber());

			rs = stmt.executeQuery();

			UserVO userVO = null;
			AuthorizationVO authorizationVO = null;
			List<UserVO> userList = new ArrayList<UserVO>();

			while (rs.next()) {
				userVO = new UserVO();
				//rnum 가져오는법
				userVO.setIndex(rs.getInt("RNUM"));
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setUserPassword(rs.getString("USR_PW"));
				userVO.setRegistDate(rs.getString("RGST_DT"));
				userVO.setAuthorizationId(rs.getString("U_ATHRZTN_ID"));

				authorizationVO = userVO.getAuthorizationVO();
				authorizationVO.setAuthorizationId(rs.getString("ATHRZTN_ID"));
				authorizationVO.setAuthorizationName(rs.getString("ATHRZTN_NM"));
				authorizationVO.setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));

				userList.add(userVO);
			}

			return userList;
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
	public UserVO selectOneUser(String userId) {
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

			query.append(" SELECT		U.USR_ID      ");
			query.append(" 				, U.USR_NM        ");
			query.append(" 				, U.USR_PW       ");
			query.append(" 				, U.RGST_DT ");
			query.append(" 				, U.ATHRZTN_ID  U_ATHRZTN_ID  ");
			query.append(" 				, A.ATHRZTN_ID   ");
			query.append(" 				, A.ATHRZTN_NM   ");
			query.append(" 				, A.PRNT_ATHRZTN_ID   ");
			query.append(" FROM			USR  U          ");
			query.append(" 		    	, ATHRZTN  A   ");
			query.append(" WHERE		U.ATHRZTN_ID = A.ATHRZTN_ID(+)  ");
			query.append(" AND			U.USR_ID = ? ");
			query.append(" ORDER BY    U.ATHRZTN_ID");

			stmt = conn.prepareStatement(query.toString());

			stmt.setString(1, userId);

			rs = stmt.executeQuery();

			UserVO userVO = null;
			AuthorizationVO authorizationVO = null;

			if (rs.next()) {
				userVO = new UserVO();
				userVO.setUserId(rs.getString("USR_ID"));
				userVO.setUserName(rs.getString("USR_NM"));
				userVO.setUserPassword(rs.getString("USR_PW"));
				userVO.setRegistDate(rs.getString("RGST_DT"));
				userVO.setAuthorizationId(rs.getString("U_ATHRZTN_ID"));

				authorizationVO = userVO.getAuthorizationVO();
				authorizationVO.setAuthorizationId(rs.getString("ATHRZTN_ID"));
				authorizationVO.setAuthorizationName(rs.getString("ATHRZTN_NM"));
				authorizationVO.setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));

			}

			return userVO;
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
	public UserVO selectOneUser(UserVO userVO) {
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

			query.append(" SELECT		U.USR_ID      ");
			query.append(" 				, U.USR_NM        ");
			query.append(" 				, U.USR_PW       ");
			query.append(" 				, U.RGST_DT ");
			query.append(" 				, U.ATHRZTN_ID  U_ATHRZTN_ID  ");
			query.append(" 				, A.ATHRZTN_ID   ");
			query.append(" 				, A.ATHRZTN_NM   ");
			query.append(" 				, A.PRNT_ATHRZTN_ID   ");
			query.append(" FROM			USR  U          ");
			query.append(" 		    	, ATHRZTN  A   ");
			query.append(" WHERE		U.ATHRZTN_ID = A.ATHRZTN_ID(+)  ");
			query.append(" AND			U.ATHRZTN_ID = 'AT-2017032815-000005' ");//권한 로직 관리자만 접속하게
			query.append(" AND		U.USR_ID = ?  ");
			query.append(" AND		U.USR_PW = ? ");

			stmt = conn.prepareStatement(query.toString());
			stmt.setString(1, userVO.getUserId());
			stmt.setString(2, userVO.getUserPassword());

			rs = stmt.executeQuery();
			UserVO loginUserVO = null;
			AuthorizationVO authorizationVO = null;
			if (rs.next()) {
				loginUserVO = new UserVO();
				loginUserVO.setUserId(rs.getString("USR_ID"));
				loginUserVO.setUserName(rs.getString("USR_NM"));
				loginUserVO.setUserPassword(rs.getString("USR_PW"));
				loginUserVO.setRegistDate(rs.getString("RGST_DT"));
				loginUserVO.setAuthorizationId(rs.getString("U_ATHRZTN_ID"));

				authorizationVO = loginUserVO.getAuthorizationVO();
				authorizationVO.setAuthorizationId(rs.getString("ATHRZTN_ID"));
				authorizationVO.setAuthorizationName(rs.getString("ATHRZTN_NM"));
				authorizationVO.setParentAuthorizationId(rs.getString("PRNT_ATHRZTN_ID"));
			}

			return loginUserVO;
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
	public int updateUserInfo(UserVO userVO) {

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
			query.append(" UPDATE     USR ");
			query.append(" SET		  ");
			query.append("          USR_NM = ? ");
			query.append("          , USR_PW = ? ");
			//query.append(" 			, RGST_DT = TO_CHAR ( ? , 'YYYY-MM-DD' ) ");
			query.append("          , ATHRZTN_ID = ? ");
			query.append(" WHERE USR_ID = ? ");
			
			

			stmt = conn.prepareStatement(query.toString());
		
			stmt.setString(1, userVO.getUserName());
			stmt.setString(2, userVO.getUserPassword());
			//stmt.setString(3, userVO.getRegistDate());
			stmt.setString(3, userVO.getAuthorizationId());
			stmt.setString(4, userVO.getUserId());

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
	public int deleteOneUser(String userId) {
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
			query.append(" FROM      USR ");
			query.append(" WHERE     USR_ID = ? ");
			

			stmt = conn.prepareStatement(query.toString());
		
			stmt.setString(1, userId);
			
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
	public int selectCountByUserId(UserSearchVO searchVO) {
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
			query.append(" FROM		USR             ");
			

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
	public int updateAthorizationId(String[] authorizationId) {
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
			query.append(" UPDATE     USR ");
			query.append(" SET		  ");
			query.append("           ATHRZTN_ID = ? ");
			if(authorizationId[0] == null || authorizationId[0].length() == 0){
				query.append(" WHERE 	 ATHRZTN_ID  IS NULL");
			}
			else{
				query.append(" WHERE 	 ATHRZTN_ID = ? ");
			}
			

			stmt = conn.prepareStatement(query.toString());
		
			
		
			
			if(authorizationId[1] == null || authorizationId[1].length() == 0){
				stmt.setNull(1, Types.VARCHAR);
				
			}
			else{
				stmt.setString(1, authorizationId[1]);
			}
			
			if(authorizationId[0] != null && authorizationId[0].length() > 0){
				stmt.setString(2, authorizationId[0]);
			}
			
			
	
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
	public SignVO selectSignDate(int dateCont) {
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

			query.append(" SELECT		     ");
			query.append(" 				COUNT(1) CNT ");
			query.append(" 				, U.RGST_DT ");
			query.append(" FROM			USR  U          ");
			query.append(" WHERE		U.RGST_DT = TO_CHAR(SYSDATE + ? ,  'YYYY-MM-DD' )  ");
			query.append(" GROUP BY    U.RGST_DT ");

			
			
			
			
			stmt = conn.prepareStatement(query.toString());

			stmt.setInt(1, dateCont);

			rs = stmt.executeQuery();
			SignVO signVO = null;
			
			
			
			

			if (rs.next()) {
				signVO = new SignVO();
				signVO.setCount(rs.getInt("CNT"));
				signVO.setRegistDate(rs.getString("RGST_DT"));


			}
			
			else {
				signVO = new SignVO();
				signVO.setCount(0);
				

				
				SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
				
				Date currentTime = new Date ( );
				Date wantTime = new Date(currentTime.getTime() + dateCont * ((long) 1000 * 60 * 60 * 24));
				String dTime = formatter.format ( wantTime );
				
				//System.out.println ( dTime ); 
				signVO.setRegistDate(dTime);
    

			}
			

			return signVO;
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
