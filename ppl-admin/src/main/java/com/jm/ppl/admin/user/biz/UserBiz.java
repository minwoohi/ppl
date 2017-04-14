package com.jm.ppl.admin.user.biz;

import java.util.List;

import com.jm.ppl.admin.user.vo.SignVO;
import com.jm.ppl.admin.user.vo.UserSearchVO;
import com.jm.ppl.admin.user.vo.UserVO;

public interface UserBiz {


		public boolean inputNewUser(UserVO userVO);
		
		public List<UserVO> selectAllUser(UserSearchVO userSearchVO);
		
		public UserVO signInUser(UserVO user);
		
		public UserVO selectOneUser(String userId);
		
		public UserVO selectOneUser(UserVO userVO);
		
		public boolean updateUserInfo(UserVO userVO);
	
		public boolean deleteOneUser(String userId);
		
		public boolean changeAuthorization(String[] authorizationId);
		
		public SignVO viewSignUpUserByDate (int dateCont);
		
}
