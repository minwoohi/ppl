package com.jm.ppl.admin.user.dao;

import java.util.List;

import com.jm.ppl.admin.user.vo.SignVO;
import com.jm.ppl.admin.user.vo.UserSearchVO;
import com.jm.ppl.admin.user.vo.UserVO;

public interface UserDao {

	
		public int insertNewUser(UserVO userVO);
		
		public List<UserVO> selectAllUser(UserSearchVO userSearchVO);
		//관리자페이지에서 한명의 회원정보를 보기위해
		public UserVO selectOneUser(String userId);
		//로그인을 위한 메소드
		public UserVO selectOneUser(UserVO userVO);
		
		public int updateUserInfo(UserVO userVO);
		
		public int deleteOneUser(String userId);
		
		public int selectCountByUserId(UserSearchVO searchVO);
		
		public int updateAthorizationId(String[] authorizationId);
		
		public SignVO selectSignDate(int dateCont); 
}
